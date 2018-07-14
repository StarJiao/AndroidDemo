package com.star.demo.zxing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.CaptureActivity;
import com.google.zxing.Intents;
import com.star.demo.R;
import com.star.util.Util;

public class ScanTriggerActivity extends Activity implements OnClickListener {
    private static final int REQUEST_CODE = 200;

    private Button start_scan;
    private Button start_create;
    private TextView scan_result;
    private EditText content;
    private ImageView create_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_trigger);
        start_scan = (Button) findViewById(R.id.start_scan);
        start_create = (Button) findViewById(R.id.start_create);
        scan_result = (TextView) findViewById(R.id.scan_result);
        content = (EditText) findViewById(R.id.content);

        create_result = (ImageView) findViewById(R.id.create_result);
        start_scan.setOnClickListener(this);
        start_create.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != data && requestCode == REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    scan_result.setText(data.getStringExtra(Intents.Scan.RESULT));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_scan:
                Intent intent = new Intent();
                intent.setAction(Intents.Scan.ACTION);
                // intent.putExtra(Intents.Scan.MODE, Intents.Scan.QR_CODE_MODE);
                intent.putExtra(Intents.Scan.CHARACTER_SET, "UTF-8");
                intent.putExtra(Intents.Scan.WIDTH, 800);
                intent.putExtra(Intents.Scan.HEIGHT, 600);
                // intent.putExtra(Intents.Scan.PROMPT_MESSAGE, "type your prompt message");
                intent.setClass(this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.start_create:
                String contentString;
                if (!TextUtils.isEmpty(content.getText())) {
                    contentString = content.getText().toString().trim();
                } else if (!TextUtils.isEmpty(scan_result.getText())) {
                    contentString = scan_result.getText().toString().trim();
                } else {
                    contentString = "Hello";
                }
                create_result.setImageBitmap(Util.createQRImage(contentString, 1000, 1000));
                break;
        }
    }
}
