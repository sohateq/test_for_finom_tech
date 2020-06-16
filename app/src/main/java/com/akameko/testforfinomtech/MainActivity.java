package com.akameko.testforfinomtech;

import android.os.Bundle;
import android.widget.EditText;

import androidx.viewpager2.widget.ViewPager2;

import com.akameko.testforfinomtech.repository.PagerItem;

import java.util.List;

import io.reactivex.Observable;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;


    ViewPager2 viewPagerMain;
    ViewPager2 viewPagerSecond;
    //Double USD = 0d;
   // Repository repository = new Repository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        presenter.init();

//        Disposable disposable = repository.loadRates()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(latest -> {
//
//
//                        Log.d("123", latest.getRates().toString());
//
//
//                    Log.d("123", "Rates loaded!!");
//
//
//                }, throwable -> {
//                    Log.d("123", "Rates loading failed", throwable);
//                    //Toast.makeText(this,"load error", Toast.LENGTH_SHORT).show();
//                });
    }

    private void initViews(){

        viewPagerMain = findViewById(R.id.view_pager_main);
        viewPagerSecond = findViewById(R.id.view_pager_second);
//        editTextEURRate.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!s.toString().equals("")) {
//                    Double a = presenter.calculateUSD(Double.parseDouble(s.toString()));
//                    editTextUSDRate.setText(a.toString());
//                    //Log.d("123", ":)");
////                    Double a = Double.parseDouble(s.toString()) * USD;
////                    editTextUSDRate.setText(a.toString());
//
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        //viewPager.set

    }

    @Override
    public void setRates(Double USD, List<PagerItem> pagerItems) {
        //this.USD = USD;
        //editTextUSDRate.setText("USD: " + USD.toString());

        PagerAdapter pagerAdapter = new PagerAdapter(pagerItems);
        viewPagerMain.setAdapter(pagerAdapter);
        viewPagerSecond.setAdapter(pagerAdapter);

        viewPagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        });

        //Observable.f
    }


}
