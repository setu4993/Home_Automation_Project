package com.example.setu4.home_v4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by setu4 on 30-May-16.
 */
public class ActiveAdapter extends ArrayAdapter
{
    List list = new ArrayList();
    public ActiveAdapter(Context context, int resource)
    {
        super(context, resource);
    }

    public void add(Active object)
    {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row;
        row = convertView;

        ActiveHolder activeHolder;

        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_active,parent,false);
            activeHolder = new ActiveHolder();
            activeHolder.tx_sr = (TextView)row.findViewById(R.id.tx_sr);
            activeHolder.tx_date = (TextView)row.findViewById(R.id.tx_date);
            activeHolder.tx_time = (TextView)row.findViewById(R.id.tx_time);
            activeHolder.tx_device = (TextView)row.findViewById(R.id.tx_device);
            activeHolder.tx_devid = (TextView)row.findViewById(R.id.tx_devid);
            activeHolder.tx_status = (TextView)row.findViewById(R.id.tx_status);
            row.setTag(activeHolder);
        }
        else
        {
            activeHolder = (ActiveHolder)row.getTag();
        }

        Active active = (Active)this.getItem(position);
        activeHolder.tx_sr.setText(active.getSr());
        activeHolder.tx_date.setText(active.getDate());
        activeHolder.tx_time.setText(active.getTime());
        activeHolder.tx_device.setText(active.getDevice());
        activeHolder.tx_devid.setText(active.getDevid());
        activeHolder.tx_status.setText(active.getStatus());
        return row;

    }
    static class ActiveHolder
    {
        TextView tx_sr, tx_date, tx_time, tx_device, tx_devid, tx_status;
    }
}