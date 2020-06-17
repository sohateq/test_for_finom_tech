package com.akameko.testforfinomtech;

import android.os.Bundle;
import android.widget.EditText;

import androidx.fragment.app.FragmentManager;
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
        viewPagerMain.setAdapter(viewPagerFragmentAdapterMain);

        ViewPagerFragmentAdapter viewPagerFragmentAdapterSecond = new ViewPagerFragmentAdapter(fm, getLifecycle());
        PagerSlotFragment pagerSlotFragmentSecond0 = new PagerSlotFragment(pagerItems.get(0));
        PagerSlotFragment pagerSlotFragmentSecond1 = new PagerSlotFragment(pagerItems.get(1));
        PagerSlotFragment pagerSlotFragmentSecond2 = new PagerSlotFragment(pagerItems.get(2));
        viewPagerFragmentAdapterSecond.addFragment(pagerSlotFragmentSecond0);
        viewPagerFragmentAdapterSecond.addFragment(pagerSlotFragmentSecond1);
        viewPagerFragmentAdapterSecond.addFragment(pagerSlotFragmentSecond2);
        viewPagerSecond.setAdapter(viewPagerFragmentAdapterSecond);


    }


}
