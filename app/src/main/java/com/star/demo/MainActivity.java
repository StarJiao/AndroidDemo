package com.star.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.star.demo.animation.FirstActivity;
import com.star.demo.application.AppUtil;
import com.star.demo.camera.CameraDemoActivity;
import com.star.demo.compass.CompassActivity;
import com.star.demo.customview.CustomViewActivity;
import com.star.demo.fragment.InteractionDemoActivity;
import com.star.demo.home.HomeWatcherReceiver;
import com.star.demo.mvp.MvpActivity;
import com.star.demo.numberpicker.SampleActivity;
import com.star.demo.rxjava.RxJavaDemoActivity;
import com.star.demo.sharedpreference.SPDemoActivity;
import com.star.demo.systemui.SystemUIDemoActivity;
import com.star.demo.tab.CopyTabActivity;
import com.star.demo.viewpager.ViewPagerDemoActivity;
import com.star.demo.viewswitcher.ViewSwitcherDemoActivity;
import com.star.demo.zxing.ScanTriggerActivity;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView mainList;

    private String[] featureNames;

    private HomeWatcherReceiver receiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(AppUtil.getCurrentTheme());
        setContentView(R.layout.activity_main);
        mainList = findViewById(R.id.main_list);
        featureNames = getResources().getStringArray(R.array.feature_list);
        mainList.setAdapter(new ArrayAdapter<>(this, R.layout.list_item, featureNames));
        mainList.setOnItemClickListener(this);

        receiver = new HomeWatcherReceiver();
        filter = new IntentFilter();
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

        registerReceiver(receiver, filter);

        int orientation = getResources().getConfiguration().orientation;
        Log.i("main", "Config:" + orientation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("TAG", "on item click");
        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(MainActivity.this, ViewPagerDemoActivity.class);
                break;
            case 1:
                intent.setClass(MainActivity.this, SPDemoActivity.class);
                break;
            case 2:
                intent.setClass(MainActivity.this, FirstActivity.class);
                break;
            case 3:
                intent.setClass(MainActivity.this, RxJavaDemoActivity.class);
                break;
            case 4:
                intent.setClass(MainActivity.this, ViewSwitcherDemoActivity.class);
                break;
            case 5:
                intent.setClass(MainActivity.this, CameraDemoActivity.class);
                break;
            case 6:
                intent.setClass(MainActivity.this, CopyTabActivity.class);
                break;
            case 7:
                intent.setClass(MainActivity.this, SampleActivity.class);
                break;
            case 8:
                intent.setClass(MainActivity.this, CompassActivity.class);
                break;
            case 9:
                intent.setClass(MainActivity.this, InteractionDemoActivity.class);
                break;
            case 10:
                intent.setClass(MainActivity.this, ScanTriggerActivity.class);
                break;
            case 11:
                intent.setClass(MainActivity.this, SystemUIDemoActivity.class);
                break;
            case 12:
                intent.setClass(MainActivity.this, MvpActivity.class);
                break;
            case 13:
                intent.setClass(MainActivity.this, CustomViewActivity.class);
            default:
                break;
        }
        if (null != intent.getComponent()) {
            startActivity(intent);
        }
    }
}
