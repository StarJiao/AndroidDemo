package com.star.demo.mvp;

import com.star.demo.mvp.presenter.MainPresenter;

public interface Contract {
    interface View extends BaseView<MainPresenter> {
        void showToast(String toast);
    }

    interface Presenter extends BasePresenter {
        void showPresentTemperature();
    }
}
