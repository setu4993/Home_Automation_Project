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
 * Created by setu4 on 02-Jun-16.
 */
public class LightAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public LightAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Light object) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;

        LightHolder lightHolder;

        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_light,parent,false);
            lightHolder = new LightHolder();
            lightHolder.tx_sr = (TextView)row.findViewById(R.id.tx_sr);
            lightHolder.tx_date = (TextView)row.findViewById(R.id.tx_date);
            lightHolder.tx_time = (TextView)row.findViewById(R.id.tx_time);
            lightHolder.tx_id = (TextView)row.findViewById(R.id.tx_id);
            lightHolder.tx_vl = (TextView)row.findViewById(R.id.tx_vl);
            row.setTag(lightHolder);
        }
        else
        {
            lightHolder = (LightHolder) row.getTag();
        }

        Light light = (Light)this.getItem(position);
        lightHolder.tx_sr.setText(light.getSr());
        lightHolder.tx_date.setText(light.getDate());
        lightHolder.tx_time.setText(light.getTime());
        lightHolder.tx_id.setText(light.getId());
        lightHolder.tx_vl.setText(light.getVl());
        return row;
    }
    static class LightHolder
    {
        TextView tx_sr, tx_date, tx_time, tx_id, tx_vl;
    }
}
