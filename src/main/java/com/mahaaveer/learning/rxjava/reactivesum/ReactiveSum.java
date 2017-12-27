package com.mahaaveer.learning.rxjava.reactivesum;


import rx.Observable;
import rx.Observer;

public class ReactiveSum implements Observer<Double> {
//    private double sum = 0;

    public ReactiveSum(Observable<Double> observableVar1, Observable<Double> observableVar2) {
        Observable.combineLatest(observableVar1, observableVar2, (a, b) -> a + b)
                  .subscribe(this);
    }

    @Override
    public void onCompleted() {
        System.out.println("Exiting!");
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onNext(Double sum) {
        System.out.println("sum: " + sum);
    }
}
