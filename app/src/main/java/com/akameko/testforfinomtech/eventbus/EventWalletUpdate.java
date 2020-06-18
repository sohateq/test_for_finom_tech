package com.akameko.testforfinomtech.eventbus;

import java.util.HashMap;

public class EventWalletUpdate {
    HashMap<String, Double> wallet = new HashMap<>();

    public EventWalletUpdate(Double USDcount, Double EURcount, Double GBPcount ) {
        wallet.put("USD", USDcount);
        wallet.put("EUR", EURcount);
        wallet.put("GBP", GBPcount);
    }

    public Double getCurrencyCount(String currencyName){
        if(wallet.get(currencyName) != null) return wallet.get(currencyName);
        return 0d;
    }


}
