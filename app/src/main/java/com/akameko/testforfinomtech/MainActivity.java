package com.akameko.testforfinomtech;

import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;

import com.akameko.testforfinomtech.repository.Rates;
import com.akameko.testforfinomtech.repository.Repository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    Repository repository = new Repository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Disposable disposable = repository.loadRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(latest -> {


                        Log.d("123", latest.getRates().toString());


                    Log.d("123", "Rates loaded!!");


                }, throwable -> {
                    Log.d("123", "Rates loading failed", throwable);
                    //Toast.makeText(this,"load error", Toast.LENGTH_SHORT).show();
                });
    }
}
