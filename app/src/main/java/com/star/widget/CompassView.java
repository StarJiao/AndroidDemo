package com.star.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CompassView extends View {

    private Paint normalScaleLinePaint;//普通刻度线画笔
    private Paint deepScaleLinePaint;//深色刻度线画笔
    private Paint innerCirclePaint;//内部圆画笔
    private Paint bubblePaint;//气泡画笔

    private float innerRadius;//内圆半径
    private float outerRadius;//外圆半径
    private float bubbleRadius;//小球半径

    private float angleX;//视图X方向的俯仰角
    private float angleY;//视图Y方向的俯仰角
    private float scaleLineLength;//刻度线长

    private int scaleLineCount = 60;//刻度线数量
    private float rate = 10;//小球敏感度系数.约大动的越快

    public CompassView(Context context) {
        this(context, null);
    }

    public CompassView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CompassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        normalScaleLinePaint = new Paint();
        normalScaleLinePaint.setColor(Color.DKGRAY);
        normalScaleLinePaint.setStrokeWidth(6);

        deepScaleLinePaint = new Paint();
        deepScaleLinePaint.setColor(Color.RED);
        deepScaleLinePaint.setStrokeWidth(6);

        innerCirclePaint = new Paint();
        innerCirclePaint.setStrokeWidth(3);
        innerCirclePaint.setAntiAlias(true);
        innerCirclePaint.setColor(Color.BLACK);
        innerCirclePaint.setStyle(Paint.Style.STROKE);

        bubblePaint = new Paint();
        bubblePaint.setColor(Color.BLUE);
        bubblePaint.setAntiAlias(true);
        bubblePaint.setStrokeWidth(3);
        bubblePaint.setStyle(Paint.Style.FILL);
        bubblePaint.setAlpha(100);
    }

    //传递当前的俯仰角
    public void setAngle(float angleX, float angleY) {
        this.angleX = angleX;
        this.angleY = angleY;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int diameter = Math.min(widthSize, heightSize);
        outerRadius = (diameter - Math.max((getPaddingLeft() + getPaddingRight()), (getPaddingTop() + getPaddingBottom()))) / 2;//视图的一半为外层半径
        innerRadius = outerRadius / 2;//外层半径的一半为内圆半径
        bubbleRadius = outerRadius / 5;//小球半径
        scaleLineLength = outerRadius / 4;//刻度线长
        setMeasuredDimension(diameter, diameter);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerY = getPaddingLeft() + outerRadius;
        float centerX = getPaddingTop() + outerRadius;
        //刻度线
        for (int i = 0; i < scaleLineCount; i++) {
            int quarter = scaleLineCount / 4;
            if (i % quarter == 0) {
                canvas.drawLine(centerX, getPaddingTop(), centerX, getPaddingTop() + scaleLineLength, deepScaleLinePaint);
            } else {
                canvas.drawLine(centerX, getPaddingTop(), centerX, getPaddingTop() + scaleLineLength, normalScaleLinePaint);
            }
            canvas.rotate(360 / scaleLineCount, centerX, centerY);
        }
        //内圆
        canvas.drawCircle(centerX, centerY, innerRadius, innerCirclePaint);

        float preX = angleX * rate;
        float preY = angleY * rate;
        float offset = (float) Math.sqrt(Math.pow(preX, 2) + Math.pow(preY, 2));
        float bubbleActiveRadius = outerRadius - bubbleRadius;//小球的活动半径

        boolean isOut = offset > bubbleActiveRadius;
        float offsetX = isOut ? bubbleActiveRadius * preX / offset : preX;
        float offsetY = isOut ? bubbleActiveRadius * preY / offset : preY;

        canvas.drawCircle(centerX - offsetX, centerY - offsetY, bubbleRadius, bubblePaint);
    }
}
