package com.aron.xiao.app.verifydemo.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.aron.xiao.app.verifydemo.util.LogUtil;

/**
 * Created by XiaoJingzhu on 2016/12/14 0014.
 */

public class MyViewGroup extends LinearLayout{


    private static final String TAG = MyViewGroup.class.getSimpleName();

    private boolean intercepted = false;
    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtil.i(TAG,"");
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogUtil.i(TAG,"");
    }

    public MyViewGroup(Context context ){
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.i(TAG,"onTouchEvent");
//        return true;
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogUtil.i(TAG,"onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        LogUtil.i(TAG,"onLayout");
        super.onLayout(b,i,i1,i2,i3);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        LogUtil.i(TAG,"onDraw");
        super.onDraw(canvas);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        LogUtil.i(TAG,"dispatchDraw");
        super.dispatchDraw(canvas);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.i(TAG,"onInterceptTouchEvent");
//        if(intercepted){
//            return true;
//        }else{
//            return super.onInterceptTouchEvent(ev);
//        }

        return intercepted;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.i(TAG,"dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        LogUtil.i(TAG,"onTrackballEvent");
        return super.onTrackballEvent(event);
    }


    public void setIntercept(boolean intercepted){
        this.intercepted = intercepted;
    }
}
