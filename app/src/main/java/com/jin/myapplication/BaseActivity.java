package com.jin.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jin.myapplication.model.BleMethod;
import com.jin.myapplication.view.BaseView;

/**
 * Created by jyj on 2017/3/9.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private static final String TAG = "BaseActivity";
    public BleMethod bleMethod;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bleMethod=new BleMethod(this);
    }



    @Override
    public void connect() {
        Log.e(TAG, "connect: "+"蓝牙连接" );
    }

    @Override
    public void disConnect() {
        Log.e(TAG, "connect: "+"蓝牙断开连接" );

    }

    @Override
    public void showMsg(final String msg) {

    }
}
