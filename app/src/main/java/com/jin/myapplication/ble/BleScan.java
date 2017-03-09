package com.jin.myapplication.ble;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

/**
 * Created by jyj on 2017/3/9.
 */
public class BleScan {
    public static final int REQUEST_ENABLE_BT=1;

    public static BluetoothAdapter mBluetoothAdapter;
    public static boolean supportBle(Context context){
        if (!context.getPackageManager().
                hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            return false;
        }
        return true;
    }

    public static BluetoothAdapter getBleAdapter(Context context){
        final BluetoothManager bluetoothManager= (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter=bluetoothManager.getAdapter();
        if (bluetoothManager!=null&&mBluetoothAdapter!=null){
            return mBluetoothAdapter;
        }else {

        return null;
        }
    }

    public static void enableBle(Activity activity){
        if (supportBle(activity)){
            if (getBleAdapter(activity)==null||!mBluetoothAdapter.isEnabled()){
                Intent enableIntent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                activity.startActivityForResult(enableIntent,REQUEST_ENABLE_BT);
            }
        }
    }

}
