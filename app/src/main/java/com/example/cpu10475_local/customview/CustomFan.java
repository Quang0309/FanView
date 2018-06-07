package com.example.cpu10475_local.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;


public class CustomFan extends View {
    private int speed=5;

    private float angle = 0;
    private int mWidth;
    private int mHeight;

    private Matrix matrix;
    int fanSize;
    int incr=0;
    Bitmap fan;
    Runnable runnable;
    Handler handler;
    public CustomFan(Context context) {
        super(context);
        init();
    }

    public CustomFan(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public CustomFan(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init()
    {

        //screenSize =
        fan = BitmapFactory.decodeResource(getResources(),R.drawable.fan);
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        handler = new Handler();
        matrix = new Matrix();

    }
    public void redraw()
    {

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        //mRadius =(float) (Math.min(mWidth,mHeight)/6*0.8);
        fanSize = Math.min(mWidth,mHeight)/2;
        fan = Bitmap.createScaledBitmap(fan,fanSize,fanSize,false);
       // Log.e("test",w + " " + h);
    }

    @Override
    protected void onDraw(Canvas canvas) {



        matrix.setRotate(angle,fan.getWidth()/2,fan.getHeight()/2);
        matrix.postTranslate(mWidth/2-fanSize/2,mHeight/2-fanSize/2+80);
        angle+=incr;
        canvas.drawBitmap(fan,matrix,null);
        //canvas.drawCircle(,10,circlePaint);
        //canvas.drawCircle(fanSize/2,fanSize/2+70,10,circlePaint);
        handler.postDelayed(runnable,speed);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        invalidate();
    }
    public void setAngle(int Angle)
    {
        this.incr = Angle;
        invalidate();
    }
}
