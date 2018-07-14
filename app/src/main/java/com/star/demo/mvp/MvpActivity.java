package com.star.demo.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.star.demo.R;
import com.star.demo.mvp.presenter.MainPresenter;

public class MvpActivity extends Activity implements Contract.View, View.OnClickListener {
    private TextView textView;
    private Contract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        findViewById(R.id.mvp_text);
        presenter = new MainPresenter(this);

        textView = findViewById(R.id.mvp_text);
        textView.setOnClickListener(this);
    }

    @Override
    public void showToast(String toast) {

    }

    @Override
    public void setPresenter(MainPresenter presenter) {

    }

    @Override
    public void onClick(View v) {
        if (v == textView) presenter.showPresentTemperature();
    }
}
