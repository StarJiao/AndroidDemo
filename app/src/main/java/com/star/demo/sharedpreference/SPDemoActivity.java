package com.star.demo.sharedpreference;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.star.demo.R;
import com.star.demo.SPTester;

public class SPDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spdemo);

        final EditText editText = (EditText) findViewById(R.id.edit);
        final TextView content = (TextView) findViewById(R.id.current_content);

        Button add = (Button) findViewById(R.id.add);
        Button remove = (Button) findViewById(R.id.remove);
        Button display = (Button) findViewById(R.id.display);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPTester spTester = new SPTester(SPDemoActivity.this);
                if (!TextUtils.isEmpty(editText.getText().toString())) {
                    spTester.addCustomResponse(editText.getText().toString());
                }
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPTester spTester = new SPTester(SPDemoActivity.this);
                if (!TextUtils.isEmpty(editText.getText().toString())) {
                    spTester.removeCustomResponse(editText.getText().toString());
                }
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPTester spTester = new SPTester(SPDemoActivity.this);
                content.setText(spTester.getStringSet().toString());
            }
        });
    }
}
