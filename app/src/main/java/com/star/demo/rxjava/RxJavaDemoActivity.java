package com.star.demo.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.star.demo.R;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

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
        Observable<String> observable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("hello rxjava");
                        subscriber.onCompleted();
                    }
                }
        );
        /**************************/
//        Observable<String> observable = Observable.just("hello");
        /**************************/

        /**************************/
        //观察者
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                textView.append(s);
                Log.i("tag", s + "Subscriber");
            }
        };
        observable.subscribe(subscriber);
        /**************************/
        Action1<String> action = new Action1<String>() {
            @Override
            public void call(String s) {
                textView.append(s);
            }
        };
        observable.subscribe(action);
    }

}
