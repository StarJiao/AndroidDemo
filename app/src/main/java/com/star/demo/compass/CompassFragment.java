package com.star.demo.compass;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.star.demo.App;
import com.star.demo.R;
import com.star.widget.CompassView;

public class CompassFragment extends Fragment {
    private final static String TAG = "CompassFragment";
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private CompassListener listener;
    private CompassView compassView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CompassFragment.CompassListener) {
            listener = (CompassFragment.CompassListener) context;
        } else {
            Log.w(TAG, context.toString() + " must implement InteractionListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof CompassFragment.CompassListener) {
            listener = (CompassFragment.CompassListener) activity;
        } else {
            Log.w(TAG, activity.toString() + " must implement InteractionListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) App.getInstance().getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager != null) {
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mSensorManager != null) {
            mSensorManager.registerListener(mSenorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.e(TAG, "mSensorManager==null");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(mSenorEventListener, mAccelerometer);
        } else {
            Log.e(TAG, "mSensorManager==null");
        }
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_compass, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        compassView = view.findViewById(R.id.compass_view);
    }

    private SensorEventListener mSenorEventListener = new SensorEventListener() {
        float yAngle;
        float zAngle;

        @Override
        public void onSensorChanged(SensorEvent event) {
            switch (event.sensor.getType()) {
                case Sensor.TYPE_ORIENTATION:
                    yAngle = event.values[1];
                    zAngle = event.values[2];
                    break;
                case Sensor.TYPE_ACCELEROMETER:
                    yAngle = event.values[1] * (5);
                    zAngle = event.values[0] * (-5);
            }

            /**
             * 计算水平仪气泡位置(获取偏移值)
             *
             * @param yAngle 影响界面Y方向的值
             * @param zAngle 影响界面X方向的值
             */
            compassView.setAngle(zAngle, yAngle);
            compassView.postInvalidate();

            if (yAngle < 5 && zAngle < 5) {
                listener.onHorizontalState(true);
            } else {
                listener.onHorizontalState(false);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public interface CompassListener {
        void onHorizontalState(boolean isHorizontal);
    }
}
