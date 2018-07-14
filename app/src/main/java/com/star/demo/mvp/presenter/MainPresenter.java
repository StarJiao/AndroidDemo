package com.star.demo.mvp.presenter;

import android.os.Handler;

import com.star.demo.mvp.Contract;
import com.star.demo.mvp.bean.Temperature;
import com.star.demo.mvp.model.MainModel;
import com.star.demo.MainActivity;


/**
 * Listens to user actions from the UI ({@link MainActivity}), receives the data and updates the UI
 * (监听用户在UI上的动作，接收温度变化，并更新UI)
 */

public class MainPresenter implements Contract.Presenter {

    private final Contract.View mMainView;

    private final MainModel mMainModel;

    public MainPresenter(Contract.View mainView) {
        mMainView = mainView;
        mMainModel = new MainModel();

        mainView.setPresenter(this);
    }

    @Override
    public void showPresentTemperature() {

        // mainHandler is created in UI thread(mainHandler在UI线程中创建)
        final Handler mainHandler = new Handler();

        new Thread() {
            @Override
            public void run() {
                // query temperature in non-UI thread(在非UI线程中，查询温度)
                final Temperature temperature = mMainModel.queryTemperature();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // update View in UI thread(在UI线程中更新View)
                        mMainView.showToast(String.valueOf(temperature.getTemper()));
                    }
                });
            }
        }.start();
    }
}
