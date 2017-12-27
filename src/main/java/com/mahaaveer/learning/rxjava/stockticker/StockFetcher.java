package com.mahaaveer.learning.rxjava.stockticker;

import rx.Observable;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class StockFetcher {

    public static StockInfo fetchStockInfo(String symbol){
        final Stock stock;
        try {
            stock = YahooFinance.get(symbol);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new StockInfo(stock.getSymbol(), stock.getQuote().getPrice());
    }

    public static void main(String[] args) throws IOException {
        System.out.println(fetchStockInfo("AAPL"));
    }


}
