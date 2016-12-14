package com.aron.xiao.app.verifydemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.aron.xiao.app.verifydemo.R;
import com.aron.xiao.app.verifydemo.util.LogUtil;
import com.aron.xiao.app.verifydemo.view.custom.MyView;
import com.aron.xiao.app.verifydemo.view.custom.MyViewGroup;

/**
 * Created by XiaoJingzhu on 2016/12/14 0014.
 */

public class TouchDispatchActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = TouchDispatchActivity.class.getSimpleName();

    MyViewGroup myViewGroup = null;
    MyView myView = null;
    @Override
    public void onUserInteraction() {
        LogUtil.i(TAG,"onUserInteraction");
        super.onUserInteraction();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG,"onCreate");
        setContentView(R.layout.touch_dispatch_activity);
        findViewById(R.id.btn_disallowIntercept).setOnClickListener(this);
        findViewById(R.id.btn_intercept).setOnClickListener(this);
        myViewGroup  = (MyViewGroup) findViewById(R.id.myViewgroup);
        myView = (MyView) findViewById(R.id.myView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.i(TAG,"onTouchEvent :　"+event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_disallowIntercept:
            break;
            case R.id.btn_intercept:
                myViewGroup.setIntercept(true);
                break;
            case R.id.btn_consume:
                myView.setConsumeEvent(true);
            break;
            case R.id.btn_reback:
                myViewGroup.setIntercept(false);
                myView.setConsumeEvent(false);
            break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.i(TAG,"dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

}
