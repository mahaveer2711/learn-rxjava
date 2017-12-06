package com.mahaaveer.learning.rxjava;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class IteratorPattern {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("one", "two", "three", "four");
        Observable<String> listObservable = Observable.from(list);
        listObservable.subscribe(s -> System.out.println(s));
    }



}
