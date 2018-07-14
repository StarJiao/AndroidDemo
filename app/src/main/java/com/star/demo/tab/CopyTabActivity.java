package com.star.demo.tab;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.star.demo.R;

public class CopyTabActivity extends Activity implements OnClickListener
{
    private TextView tab01, tab02, tab03;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_tab);
        
        tab01 = (TextView) findViewById(R.id.tab01);
        tab02 = (TextView) findViewById(R.id.tab02);
        tab03 = (TextView) findViewById(R.id.tab03);
        tab01.setOnClickListener(this);
        tab02.setOnClickListener(this);
        tab03.setOnClickListener(this);
        setBottomDrawable(tab01);
    }
    
    private void setBottomDrawable(TextView view)
    {
        tab01.setCompoundDrawablesWithIntrinsicBounds(0,
                0,
                0,
                view.getId() == tab01.getId() ? R.drawable.table_bottom_shape
                        : R.drawable.table_bottom_shape_normal);
        tab02.setCompoundDrawablesWithIntrinsicBounds(0,
                0,
                0,
                view.getId() == tab02.getId() ? R.drawable.table_bottom_shape
                        : R.drawable.table_bottom_shape_normal);
        tab03.setCompoundDrawablesWithIntrinsicBounds(0,
                0,
                0,
                view.getId() == tab03.getId() ? R.drawable.table_bottom_shape
                        : R.drawable.table_bottom_shape_normal);
    }
    
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.tab01:
                setBottomDrawable(tab01);
                break;
            case R.id.tab02:
                setBottomDrawable(tab02);
                break;
            case R.id.tab03:
                setBottomDrawable(tab03);
                break;
            default:
                break;
        }
    }
}
