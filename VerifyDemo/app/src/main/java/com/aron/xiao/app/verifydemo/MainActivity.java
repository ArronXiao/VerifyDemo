package com.aron.xiao.app.verifydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aron.xiao.app.verifydemo.view.TouchDispatchActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_2_touch_dispatch).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_2_touch_dispatch:
                Intent intent = new Intent(MainActivity.this, TouchDispatchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
