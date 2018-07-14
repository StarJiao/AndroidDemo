package com.star.demo.systemui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.star.demo.R;

/**
 * Created by Star on 2016/8/8.
 */
public class SystemUIDemoActivity extends Activity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        button = (Button) findViewById(R.id.button);
        button.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);//设置虚拟键隐藏
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "button on click");
                switch (button.getSystemUiVisibility()) {
                    case View.SYSTEM_UI_FLAG_HIDE_NAVIGATION:
                        button.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                        break;
                    case View.SYSTEM_UI_FLAG_VISIBLE:
                        button.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                        break;
                    case View.SYSTEM_UI_FLAG_LOW_PROFILE:
                        button.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                        break;
                }
            }
        });//点击事件
    }
}
