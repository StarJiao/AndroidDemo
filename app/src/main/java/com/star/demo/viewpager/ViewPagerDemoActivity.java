package com.star.demo.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.star.demo.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerDemoActivity extends Activity {
    private ViewPager viewPager;
    private CirclePageIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_demo);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        List<String> data = new ArrayList<>();
        data.add("one");
        data.add("two");
        data.add("three");
        viewPager.setAdapter(new PagerAdapterDemo(this, data));
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
    }
}
