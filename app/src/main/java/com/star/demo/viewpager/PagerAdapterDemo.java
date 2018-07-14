package com.star.demo.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * PagerAdapterDemo
 * Created by star on 16-4-6.
 */
public class PagerAdapterDemo extends PagerAdapter {
    private Context context;
    private List<String> data;

    public PagerAdapterDemo(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView textView = new TextView(context);
        textView.setText(data.get(position));
        container.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_dark));
        container.addView(textView);
        return textView;
    }
}
