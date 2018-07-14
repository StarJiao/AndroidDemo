package com.star.demo.animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.star.demo.R;
import com.star.util.ToastUtil;

public class FirstActivity extends Activity implements OnClickListener {

    Button button = null;

    TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        button = (Button) findViewById(R.id.go_to_next);
        textView = (TextView) findViewById(R.id.textview);
        button.setOnClickListener(this);
        textView.setText(Html.fromHtml(getString(R.string.text_demo)));
    }

    @Override
    public void onClick(View v) {
        Intent intnet = new Intent(FirstActivity.this, SecondActivity.class);
        startActivityForResult(intnet, 0);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);//切换Activity的过渡动画  
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ToastUtil.showToast(getApplicationContext(), "haha");

        super.onActivityResult(requestCode, resultCode, data);
    }
}
