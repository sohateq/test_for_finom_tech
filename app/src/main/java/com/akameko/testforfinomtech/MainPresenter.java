package com.akameko.testforfinomtech;

import android.util.Log;

import com.akameko.testforfinomtech.repository.Latest;
import com.akameko.testforfinomtech.repository.PagerItem;
import com.akameko.testforfinomtech.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    Repository repository = new Repository();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Double USD;
    private Double USDcount = 100d;
    private Double EURcount = 100d;
    private Double GBPcount = 100d;

    private Double USDrate;
    private Double EURrate;
    private Double GBPrate;


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void init() {
        Disposable disposable = repository.loadRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(latest -> {

                    USD = latest.getRates().getUSD();
                    setRates(latest.getRates().getUSD(), 1d, latest.getRates().getGBP());
                    getViewState().createViewPagers(buildPagerItems(latest));

                    Log.d("123", latest.getRates().toString());
                    Log.d("123", "Rates loaded!!");


                }, throwable -> {
                    Log.d("123", "Rates loading failed", throwable);
                    //Toast.makeText(this,"load error", Toast.LENGTH_SHORT).show();
                });
        compositeDisposable.add(disposable);
    }


    private void setRates(Double USDrate, Double EURrate, Double GBPrate) {
        this.USDrate = USDrate;
        this.EURrate = EURrate;
        this.GBPrate = GBPrate;
    }

    public Double convert(String currencyNameToConvert, String currencyNameToGet, Double currencyToConvertCount) {
        Double currencyToGetCount = 0d;

        if (currencyNameToConvert.equals("USD")) {
            switch (currencyNameToGet) {
                case "USD":
                    currencyToGetCount = currencyToConvertCount;
                    break;
                case "EUR":
                    currencyToGetCount = currencyToConvertCount * USDrate;
                    break;
                case "GBP":
                    currencyToGetCount = currencyToConvertCount * USDrate / GBPrate;
                    break;
            }
        }
        if (currencyNameToConvert.equals("EUR")) {
            switch (currencyNameToGet) {
                case "USD":
                    currencyToGetCount = currencyToConvertCount / USDrate;
                    break;
                case "EUR":
                    currencyToGetCount = currencyToConvertCount;
                    break;
                case "GBP":
                    currencyToGetCount = currencyToConvertCount / GBPrate;
                    break;
            }
        }
        if (currencyNameToConvert.equals("GBP")) {
            switch (currencyNameToGet) {
                case "USD":
                    currencyToGetCount = currencyToConvertCount * GBPrate / USDrate;
                    break;
                case "EUR":
                    currencyToGetCount = currencyToConvertCount * GBPrate;
                    break;
                case "GBP":
                    currencyToGetCount = currencyToConvertCount;
                    break;
            }
        }

        return currencyToGetCount;
    }

    private List<PagerItem> buildPagerItems(Latest latest) {
        List<PagerItem> pagerItemList = new ArrayList<>();
        pagerItemList.add(new PagerItem("USD", latest.getRates().getUSD(), USDcount));
        pagerItemList.add(new PagerItem("EUR", 1d, EURcount));
        pagerItemList.add(new PagerItem("GBP", latest.getRates().getGBP(), GBPcount));
        return pagerItemList;
    }

}
