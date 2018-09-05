package com.star.demo.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.star.demo.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Star on 2016/6/2.
 */
public class RxJavaDemoActivity extends Activity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        textView = findViewById(R.id.textview);
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
                textView.append(s);
                Log.i("tag", s + "Subscriber");
            }
        };
        observable.subscribe(observer);
        /**************************/
        Consumer<String> action = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                textView.append(s);
            }

        };
        observable.subscribe(action).dispose();
    }

}
