package com.akameko.testforfinomtech.eventbus;

public class EventEnter {

    public final String currencyName;
    public final String calculateCount;


    public EventEnter(String currencyName, String calculateCount) {
        this.currencyName = currencyName;
        this.calculateCount = calculateCount;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCalculateCount() {
        return calculateCount;
    }
}
