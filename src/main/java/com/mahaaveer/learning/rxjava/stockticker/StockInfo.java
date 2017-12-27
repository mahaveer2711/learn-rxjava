package com.mahaaveer.learning.rxjava.stockticker;

import java.math.BigDecimal;

public class StockInfo {

    private final String ticker;
    private final BigDecimal price;

    public StockInfo(String ticker, BigDecimal price) {
        this.ticker = ticker;
        this.price = price;
    }

    @Override
    public String toString() {
        return ticker + ":" + price;
    }
}
