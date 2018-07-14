package com.star.demo.animation;

import android.app.Activity;
import android.os.Bundle;

import com.star.demo.R;
import com.star.util.ToastUtil;

public class SecondActivity extends Activity
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ToastUtil.showToast(SecondActivity.this, "hehe");
    }
}
