package com.example.cpu10475_local.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class CustomNode extends View {
    private static int section = 4;
    private float mWidth;
    private float mHeight;
    private Paint mTextPaint;
    private Paint mDialPaint;
    private float Radius;
    private int mActiveSelection;
    private float[] memHolder = new float[2];
    private StringBuffer mTempLabel = new StringBuffer(8);
    public CustomNode(Context context) {
        super(context);
        init();
    }

    public CustomNode(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomNode(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init()
    {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(40.0f);
        mDialPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDialPaint.setColor(Color.GRAY);
        mActiveSelection = 0;
        /*setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mActiveSelection = (++mActiveSelection)%section;
                invalidate();
            }
        });*/
    }
    public void redraw()
    {
        invalidate();
    }
    public void incActiveSelection()
    {
        mActiveSelection = (++mActiveSelection)%section;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        Radius =(float) (Math.min(mWidth,mHeight)/4*0.8);
    }
    private float[] computeXY(int position,float radius)
    {
        float[] result = memHolder;
        Double startAngle = Math.PI*(9/8d);
        Double angle = startAngle + (position*(Math.PI/4));
        result[0] = (float) (radius * Math.cos(angle)) + (mWidth / 2);
        result[1] = (float) (radius * Math.sin(angle)) + (mHeight / 2 );
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth/2,mHeight/2,Radius,mDialPaint);
        final float labelRadius = Radius + 20;
        StringBuffer label = mTempLabel;
        for (int i = 0; i < section; i++) {
            float[] xyData = computeXY(i, labelRadius);
            float x = xyData[0];
            float y = xyData[1];
            label.setLength(0);
            label.append(i);
            canvas.drawText(label, 0, label.length(), x, y, mTextPaint);
        }
        float markerRadius = Radius -35;
        float[] XY = computeXY(mActiveSelection,markerRadius);
        canvas.drawCircle(XY[0],XY[1],20,mTextPaint);
    }


    public int getmActiveSelection() {
        return mActiveSelection;
    }
}
