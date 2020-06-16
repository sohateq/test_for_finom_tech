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
                    getViewState().setRates(USD, buildPagerItems(latest));

                    Log.d("123", latest.getRates().toString());
                    Log.d("123", "Rates loaded!!");


                }, throwable -> {
                    Log.d("123", "Rates loading failed", throwable);
                    //Toast.makeText(this,"load error", Toast.LENGTH_SHORT).show();
                });
        compositeDisposable.add(disposable);
    }

    public double calculateUSD(Double EUR) {
        return (EUR * USD);

    }

    private List<PagerItem> buildPagerItems(Latest latest) {
        List<PagerItem> pagerItemList = new ArrayList<>();
        pagerItemList.add(new PagerItem("USD", latest.getRates().getUSD(), USDcount));
        pagerItemList.add(new PagerItem("EUR", 1d, EURcount));
        pagerItemList.add(new PagerItem("GBP", latest.getRates().getGBP(), GBPcount));
        return pagerItemList;
    }

}
