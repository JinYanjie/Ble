package com.jin.myapplication.ui;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.jin.myapplication.BaseActivity;
import com.jin.myapplication.R;
import com.jin.myapplication.model.BleMethod;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jyj on 2017/3/9.
 */
public class ShowActivity extends BaseActivity {
    private static final String TAG = "ShowActivity";
    @BindView(R.id.text)
    TextView text;
    private BleMethod bleMethod;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity);
        ButterKnife.bind(this);
        BluetoothDevice bleDevice = getIntent().getParcelableExtra("BLE_DEVICE");
        bleMethod = new BleMethod(this);
        bleMethod.connectBle(this, bleDevice);
    }


    @Override
    public void showMsg(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(msg);
            }
        });
    }

    @OnClick(R.id.text)
    public void onClick() {
        startActivity(new Intent(ShowActivity.this,TestActivity.class));
    }
}
