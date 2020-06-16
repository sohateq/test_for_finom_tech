package com.akameko.testforfinomtech.repository;

public class PagerItem {
    private String currencyName;
    private Double currencyRate;
    private Double currencyCount;

    public PagerItem(String currencyName, Double currencyRate, Double currencyCount) {
        this.currencyName = currencyName;
        this.currencyRate = currencyRate;
        this.currencyCount = currencyCount;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public Double getCurrencyRate() {
        return currencyRate;
    }

    public Double getCurrencyCount() {
        return currencyCount;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setCurrencyRate(Double currencyRate) {
        this.currencyRate = currencyRate;
    }

    public void setCurrencyCount(Double currencyCount) {
        this.currencyCount = currencyCount;
    }
}
