package com.example.mydemo;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxDemo {
    public void fun() {
        //被观察者
        /**************************/
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("hello");
            }
        });
        /**************************/
//        Observable<String> observable = Observable.just("hello");
        /**************************/
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        /**************************/
        //观察者
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {
                Log.i("tag", s + "Subscriber");
            }
        };
        observable.subscribe(observer);
        /**************************/
        Consumer<String> action = (String s) -> System.out.println(s);
        observable.subscribe(action);
    }
}
