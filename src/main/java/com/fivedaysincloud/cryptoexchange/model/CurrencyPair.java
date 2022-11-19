package com.fivedaysincloud.cryptoexchange.model;

public enum CurrencyPair {
    BTCUSD("BTCUSD"),
    ETHUSD("ETHUSD");

    public final String label;

    CurrencyPair(String label) {
        this.label = label;
    }

}
