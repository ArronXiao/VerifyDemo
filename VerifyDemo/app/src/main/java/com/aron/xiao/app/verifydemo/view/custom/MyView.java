package com.aron.xiao.app.verifydemo.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.aron.xiao.app.verifydemo.util.LogUtil;

/**
 * Created by XiaoJingzhu on 2016/12/14 0014.
 */

public class MyView extends View{

    private static final String TAG = MyView.class.getSimpleName();

    private boolean consumeEvent = false;

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogUtil.i(TAG,"MyView");
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogUtil.i(TAG,"onMeasure");
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        LogUtil.i(TAG,"onLayout");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LogUtil.i(TAG,"onDraw");
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.i(TAG,"onTouchEvent");
        return consumeEvent;
//        return super.onTouchEvent(event);
    }

    public void setConsumeEvent(boolean consumeEvent){
        this.consumeEvent = consumeEvent;
    }

}
