package com.star.demo.compass;

import android.app.Activity;
import android.os.Bundle;

import com.star.demo.R;

public class CompassActivity extends Activity implements CompassFragment.CompassListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
    }

    @Override
    public void onHorizontalState(boolean isHorizontal) {

    }
}
