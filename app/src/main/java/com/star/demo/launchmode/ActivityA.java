package com.star.demo.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.star.demo.R;

public class ActivityA extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_launch_mode);
        Log.i("launch", "task" + getTaskId());
        TextView info = findViewById(R.id.info);
        info.setText("name:" + this.getClass().getSimpleName() +
                "\ninstance:" + toString() +
                "\ntaskid:" + getTaskId());
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityA.this, ActivityB.class));
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, this.getClass().getSimpleName() + ":onNewIntent", Toast.LENGTH_SHORT).show();
    }
}
