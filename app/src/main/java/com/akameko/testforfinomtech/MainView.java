package com.akameko.testforfinomtech;

import com.akameko.testforfinomtech.repository.PagerItem;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface MainView extends MvpView {
    void setRates(Double USD, List<PagerItem> pagerItems);

}
