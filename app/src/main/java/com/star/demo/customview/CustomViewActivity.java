package com.star.demo.customview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.star.demo.R;

public class CustomViewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }
}
