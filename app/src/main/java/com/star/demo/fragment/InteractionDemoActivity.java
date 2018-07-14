package com.star.demo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.star.demo.BaseActivity;
import com.star.demo.R;
import com.star.demo.fragment.fragment.TestFragment1;
import com.star.demo.fragment.fragment.TestFragment2;

/**
 * Created by Star on 2016/6/3.
 */
public class InteractionDemoActivity extends BaseActivity implements InteractionListener {
    private String TAG = InteractionDemoActivity.class.getSimpleName();
    private Fragment fragment1;
    private Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏应用程序的标题栏，即当前activity的标题栏
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_fragment_demo);
        fragment1 = new TestFragment1();
        fragment2 = new TestFragment2();

        openFragment(R.id.content, fragment1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, "onPostResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.i(TAG, "onAttachFragment:" + fragment.toString());
    }

    @Override
    public void callActivity(int tag) {
        switch (tag) {
            case 0:
                removeFragment(fragment1);
                openFragment(R.id.content, fragment2);
                break;
            case 1:
                break;
            default:
                break;
        }
    }
}
