package com.jin.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jin.myapplication.BaseActivity;
import com.jin.myapplication.model.BleMethod;

/**
 * Created by jyj on 2017/3/9.
 */
public class TestActivity extends BaseActivity {
    private static final String TAG = "TestActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BleMethod.gatt==null){
        Log.e(TAG, "onCreate: "+"gatt为空");
        }else {
            Log.e(TAG, "onCreate: "+"gatt=======不为空");
        }

    }
}
