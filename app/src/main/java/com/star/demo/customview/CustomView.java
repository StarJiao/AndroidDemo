package com.star.demo.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {
    public final String TAG = CustomView.this.getClass().getSimpleName();
    private Paint paint = new Paint();

    public CustomView(Context context) {
        super(context);
        Log.i(TAG, "constructor1");
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "constructor2");
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "constructor3");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw");
        super.onDraw(canvas);
        Log.i(TAG, "onDraw after super");
        paint.setColor(Color.BLUE);
        canvas.drawLine(400, 400, 400, 800, paint);
    }

    @Override
    public void draw(Canvas canvas) {
        Log.i(TAG, "draw");
        super.draw(canvas);
        Log.i(TAG, "draw after super");
        paint.setColor(Color.RED);
        canvas.drawLine(0, 0, 400, 400, paint);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i(TAG, "dispatchKeyEvent");
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public void invalidate() {
        Log.i(TAG, "invalidate");
        super.invalidate();
    }

    @Override
    public void postInvalidate() {
        Log.i(TAG, "invalidate");
        super.postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth;
        int measuredHeight;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                measuredWidth = 80;
                break;
            case MeasureSpec.EXACTLY:
                measuredWidth = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                measuredWidth = widthSize;
                break;
        }
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                measuredHeight = 120;
                break;
            case MeasureSpec.EXACTLY:
                measuredHeight = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                measuredHeight = heightSize;
                break;
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        Log.i(TAG, "dispatchDraw");
    }



    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.i(TAG, "dispatchDraw");
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i(TAG, "onLayout");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onFinishInflate() {
        Log.i(TAG, "onFinishInflate");
        super.onFinishInflate();
    }
}
