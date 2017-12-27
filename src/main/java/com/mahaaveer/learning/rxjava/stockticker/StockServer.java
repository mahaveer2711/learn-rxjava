package com.mahaaveer.learning.rxjava.stockticker;

import rx.Observable;
import rx.Subscriber;

import java.util.Collection;

public class StockServer {



    public static Observable<StockInfo> getFeed(Collection<String> symbols) {
        return Observable.create(subscriber -> process(subscriber, symbols));
    }

    private static void process(Subscriber<? super StockInfo> subscriber, Collection<String> symbols) {
        System.out.println("start processing...");
        while (!subscriber.isUnsubscribed()) {
            symbols.stream()
                    .map(StockFetcher::fetchStockInfo)
                    .forEach(subscriber::onNext);

        }

    }


}
