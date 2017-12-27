package com.mahaaveer.learning.rxjava.reactivesum;

import rx.Observable;
import rx.Subscriber;
import rx.observables.ConnectableObservable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class ReactiveSumMain {

    public static void main(String[] args) {
        // An observable that starts emitting to subscribers only upon `connect`
        ConnectableObservable<String> input = from(System.in);

        Observable<Double> a = varStream("a", input);
        Observable<Double> b = varStream("b", input);

        ReactiveSum reactiveSum = new ReactiveSum(a, b);

        input.connect();
    }

    static ConnectableObservable<String> from(InputStream inputStream) {
        return from(new BufferedReader(new InputStreamReader(inputStream)));
    }

    static ConnectableObservable<String> from (BufferedReader bufferedReader) {
        return Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }

                try {
                    String line;
                    while (!subscriber.isUnsubscribed()) {
                        line = bufferedReader.readLine();

                        if (line == null || "exit".equalsIgnoreCase(line)) {
                            break;
                        }
                        subscriber.onNext(line);
                    }

                } catch (Exception ex) {
                    subscriber.onError(ex);
                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }

        }).publish();

    }

    private static Observable<Double> varStream(String varName, ConnectableObservable<String> input) {
        final Pattern pattern = Pattern.compile("\\s*" + varName + "\\s*[:|=]\\s*(-?\\d+\\.?\\d*)$");

        return input
                .map(str -> pattern.matcher(str))
                .filter(matcher -> matcher.matches())
                .map(matcher -> Double.parseDouble(matcher.group(1)));
    }



}
