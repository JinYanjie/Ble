package com.jin.myapplication.ble;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jyj on 2017/3/9.
 */
public class MyBleDevice implements Parcelable {
    private BluetoothDevice device;
    private int rssi;

    public BluetoothDevice getDevice() {
        return device;
    }

    public MyBleDevice(BluetoothDevice device, int rssi) {
        this.device = device;
        this.rssi = rssi;
    }

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.device, flags);
        dest.writeInt(this.rssi);
    }

    public MyBleDevice() {
    }

    protected MyBleDevice(Parcel in) {
        this.device = in.readParcelable(BluetoothDevice.class.getClassLoader());
        this.rssi = in.readInt();
    }

    public static final Parcelable.Creator<MyBleDevice> CREATOR = new Parcelable.Creator<MyBleDevice>() {
        @Override
        public MyBleDevice createFromParcel(Parcel source) {
            return new MyBleDevice(source);
        }

        @Override
        public MyBleDevice[] newArray(int size) {
            return new MyBleDevice[size];
        }
    };
}
