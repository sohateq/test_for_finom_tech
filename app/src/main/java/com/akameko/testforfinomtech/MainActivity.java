package com.akameko.testforfinomtech;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.akameko.testforfinomtech.repository.PagerItem;

import java.util.ArrayList;
import java.util.List;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;


    ViewPager2 viewPagerMain;
    ViewPager2 viewPagerSecond;

    ArrayList<PagerSlotFragment> fragmentsMainList = new ArrayList<>();
    ArrayList<PagerSlotFragment> fragmentsSecondList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        presenter.init();

    }

    private void initViews() {

        viewPagerMain = findViewById(R.id.view_pager_main);
        viewPagerSecond = findViewById(R.id.view_pager_second);


    }

    @Override
    public void createViewPagers(List<PagerItem> pagerItems) {

        FragmentManager fm = getSupportFragmentManager();
        ViewPagerFragmentAdapter viewPagerFragmentAdapterMain = new ViewPagerFragmentAdapter(fm, getLifecycle());

        PagerSlotFragment pagerSlotFragment0 = new PagerSlotFragment(pagerItems.get(0));
        PagerSlotFragment pagerSlotFragment1 = new PagerSlotFragment(pagerItems.get(1));
        PagerSlotFragment pagerSlotFragment2 = new PagerSlotFragment(pagerItems.get(2));

        viewPagerFragmentAdapterMain.addFragment(pagerSlotFragment0);
        viewPagerFragmentAdapterMain.addFragment(pagerSlotFragment1);
        viewPagerFragmentAdapterMain.addFragment(pagerSlotFragment2);

        fragmentsMainList.add(pagerSlotFragment0);
        fragmentsMainList.add(pagerSlotFragment1);
        fragmentsMainList.add(pagerSlotFragment2);

        viewPagerMain.setAdapter(viewPagerFragmentAdapterMain);

        ViewPagerFragmentAdapter viewPagerFragmentAdapterSecond = new ViewPagerFragmentAdapter(fm, getLifecycle());

        PagerSlotFragment pagerSlotFragmentSecond0 = new PagerSlotFragment(pagerItems.get(0));
        PagerSlotFragment pagerSlotFragmentSecond1 = new PagerSlotFragment(pagerItems.get(1));
        PagerSlotFragment pagerSlotFragmentSecond2 = new PagerSlotFragment(pagerItems.get(2));

        viewPagerFragmentAdapterSecond.addFragment(pagerSlotFragmentSecond0);
        viewPagerFragmentAdapterSecond.addFragment(pagerSlotFragmentSecond1);
        viewPagerFragmentAdapterSecond.addFragment(pagerSlotFragmentSecond2);

        fragmentsSecondList.add(pagerSlotFragmentSecond0);
        fragmentsSecondList.add(pagerSlotFragmentSecond1);
        fragmentsSecondList.add(pagerSlotFragmentSecond2);

        viewPagerSecond.setAdapter(viewPagerFragmentAdapterSecond);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_exchange:

                String currencyNameToSpend = fragmentsMainList.get(viewPagerMain.getCurrentItem()).getCurrencyName();
                Double currencyToSpendCount = fragmentsMainList.get(viewPagerMain.getCurrentItem()).getCurrencyCount();
                String currencyNameToGet = fragmentsSecondList.get(viewPagerSecond.getCurrentItem()).getCurrencyName();
                Double currencyToGetCount = fragmentsSecondList.get(viewPagerSecond.getCurrentItem()).getCurrencyCount();
//                Log.d("123", "currencyNameToSpend: " + currencyNameToSpend);
//                Log.d("123", "currencyToSpendCount" + currencyToSpendCount.toString());
//                Log.d("123", "currencyNameToGet: " + currencyNameToGet);
//                Log.d("123", "currencyToGetCount: " + currencyToGetCount);
                Boolean test = presenter.exchange(currencyNameToSpend, currencyToSpendCount, currencyNameToGet, currencyToGetCount );
                Log.d("123", "exchange: " + test.toString());


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }
}
