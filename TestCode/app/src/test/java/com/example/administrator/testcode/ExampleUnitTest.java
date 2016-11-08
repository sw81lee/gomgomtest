package com.example.administrator.testcode;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void defer_비동기_테스트_2() {
        System.out.println(Thread.currentThread().getName() + ", create observable");
        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            @Override public Observable<String> call() {
                System.out.println(Thread.currentThread().getName() + ", defer function call");
                return Observable.just("HelloWorld");
            }
        });
        System.out.println(Thread.currentThread().getName() + ", do subscribe");

        Observable<String> observable2 = observable
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread())
                .map(new Func1<String, String>() {
                    @Override public String call(String text) {
                        System.out.println(Thread.currentThread().getName() + ", map");
                        return "(" + text + ")";
                    }
                });

        observable2
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {
                    @Override public void onNext(String text) {
                        System.out.println(Thread.currentThread().getName() + ", onNext : " + text);
                    }

                    @Override public void onCompleted() {
                        System.out.println(Thread.currentThread().getName() + ", onCompleted");
                    }

                    @Override public void onError(Throwable e) {
                        System.out.println(Thread.currentThread().getName() + ", onError : " + e.getMessage());
                    }
                });

        System.out.println(Thread.currentThread().getName() + ", after subscribe");
    }





}