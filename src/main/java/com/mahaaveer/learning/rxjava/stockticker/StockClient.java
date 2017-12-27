package com.mahaaveer.learning.rxjava.stockticker;

import rx.Observable;

import java.util.Arrays;

public class StockClient {

    public static void main(String[] args) {

        // observable is like a message bus. onNext => data channel, onError => error channel (data channel gets closed)
        final Observable<StockInfo> feedObservable = StockServer.getFeed(Arrays.asList("AAPL", "GOOG", "TSLA", "AMZN"));


        feedObservable.subscribe(StockClient::handleData,
                                 StockClient::handleError,
                                 StockClient::handleCompletion);

    }

    public static void handleData(StockInfo stockInfo){
        System.out.println(stockInfo);
    }

    public static void handleError(Throwable throwable) {
        System.out.println("UhOh!! " +throwable.getMessage());
    }

    public static void handleCompletion() {
        System.out.println("All done!");
    }

}
