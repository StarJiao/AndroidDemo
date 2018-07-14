package com.star.demo.numberpicker;

import android.app.Activity;
import android.os.Bundle;

import com.star.demo.R;
import com.star.util.ToastUtil;
import com.view.numberpicker.NumberPicker;
import com.view.numberpicker.NumberPicker.OnValueChangeListener;

/**
 * @author Simon Vig Therkildsen <simonvt@gmail.com>
 */
public class LightThemeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_light);

        NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);
        final String[] city = {"北京", "上海", "广州", "深圳", "成都", "天津"};
        np.setOnValueChangedListener(new OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal) {
                ToastUtil.showToast(LightThemeActivity.this, city[newVal]);
            }
        });
        np.setDisplayedValues(city);
        np.setMinValue(0);
        np.setMaxValue(city.length - 1);
        np.setFocusable(true);
        np.setFocusableInTouchMode(true);
    }
}
