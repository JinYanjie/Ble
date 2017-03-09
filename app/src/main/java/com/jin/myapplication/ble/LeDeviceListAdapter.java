package com.jin.myapplication.ble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jin.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyj on 2017/3/9.
 */
public class LeDeviceListAdapter extends BaseAdapter {
    private List<MyBleDevice> list=new ArrayList<>();
    private Context context;

    public LeDeviceListAdapter(Context context) {
        this.context = context;
    }

    public void addDevice(MyBleDevice device){
        list.add(device);
    }
    public void cleraDevice(){
        list.clear();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    ViewHolder holder=new ViewHolder();
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.device_item,viewGroup,false);
            holder.deviceName= (TextView) view.findViewById(R.id.device_name);
            holder.deviceRssi= (TextView) view.findViewById(R.id.device_ssi);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.deviceName.setText(list.get(i).getDevice().getName());
        holder.deviceRssi.setText(list.get(i).getRssi()+"");
        return view;
    }


    class ViewHolder{
        private TextView deviceName;
        private TextView deviceRssi;
    }
}
