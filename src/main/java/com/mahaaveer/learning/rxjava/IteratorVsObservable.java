package com.mahaaveer.learning.rxjava;

import rx.Observable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorVsObservable {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("one", "two", "three", "four");

        // Using an iterator
        Iterator<String> itr = list.iterator();
        try {
            while (itr.hasNext()) {
                System.out.println(itr.next());
            }
            System.out.println("completed processing");
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }


        // Using Observable
        Observable<String> listObservable = Observable.from(list);
        listObservable.subscribe(
                s -> System.out.println(s),
                t -> System.out.println(t.getMessage()),
                () -> System.out.println("completed processing"));
    }

}
