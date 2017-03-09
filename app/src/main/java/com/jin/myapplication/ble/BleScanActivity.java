package com.jin.myapplication.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.jin.myapplication.R;
import com.jin.myapplication.ui.ShowActivity;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jyj on 2017/3/9.
 */
public class BleScanActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "BleScanActivity";
    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;
    private boolean mScanning;
    private Handler mHandler=new Handler();
    private LeDeviceListAdapter adapter;

    @BindView(R.id.startScan)
    Button startScan;
    @BindView(R.id.scan_list)
    ListView scanList;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ble_scan_activity);
        ButterKnife.bind(this);
        adapter=new LeDeviceListAdapter(this);
        scanList.setAdapter(adapter);
        scanList.setOnItemClickListener(this);
    }

    @OnClick(R.id.startScan)
    public void onClick() {
        BleScan.enableBle(this);
        devices.clear();
        adapter.cleraDevice();
        adapter.notifyDataSetChanged();
        ScanDevice(true);
    }

    private void ScanDevice(final boolean enable){
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    BleScan.mBluetoothAdapter.stopLeScan(leScanCallback);
                }
            }, SCAN_PERIOD);

            mScanning = true;
            BleScan.mBluetoothAdapter.startLeScan(leScanCallback);
        } else {
            mScanning = false;
            BleScan.mBluetoothAdapter.stopLeScan(leScanCallback);
        }

    }
    private Set<BluetoothDevice> devices=new HashSet<>();
    private BluetoothAdapter.LeScanCallback leScanCallback=new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {
            String bond= String.valueOf(bluetoothDevice.getBondState());
            String address=bluetoothDevice.getAddress();
            String name=bluetoothDevice.getName();
            Log.e(TAG, "onLeScan: bond "+bond );
            Log.e(TAG, "onLeScan: address "+address );

            if (name.substring(0,4).equals("IMCO")){
                if (!devices.contains(bluetoothDevice)){
                    adapter.addDevice(new MyBleDevice(bluetoothDevice,i));
                    adapter.notifyDataSetChanged();
                    devices.add(bluetoothDevice);
                }
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==BleScan.REQUEST_ENABLE_BT){

            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MyBleDevice device= (MyBleDevice) adapter.getItem(i);
        Log.e(TAG, "onItemClick: "+device.getDevice().getName() );
        Log.e(TAG, "onItemClick: "+device.getRssi()+"" );

        BluetoothDevice bleDevice=device.getDevice();
        Intent intent=new Intent(BleScanActivity.this, ShowActivity.class);
        intent.putExtra("BLE_DEVICE",bleDevice);
        startActivity(intent);
    }


}
