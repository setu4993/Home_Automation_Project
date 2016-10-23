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
public class DoorAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public DoorAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Door object) {
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

        DoorHolder doorHolder;

        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_door,parent,false);
            doorHolder = new DoorHolder();
            doorHolder.tx_sr = (TextView)row.findViewById(R.id.tx_sr);
            doorHolder.tx_date = (TextView)row.findViewById(R.id.tx_date);
            doorHolder.tx_time = (TextView)row.findViewById(R.id.tx_time);
            doorHolder.tx_userid = (TextView)row.findViewById(R.id.tx_userid);
            doorHolder.tx_code = (TextView)row.findViewById(R.id.tx_code);
            doorHolder.tx_action = (TextView)row.findViewById(R.id.tx_action);
            row.setTag(doorHolder);
        }
        else
        {
            doorHolder = (DoorHolder) row.getTag();
        }

        Door door = (Door)this.getItem(position);
        doorHolder.tx_sr.setText(door.getSr());
        doorHolder.tx_date.setText(door.getDate());
        doorHolder.tx_time.setText(door.getTime());
        doorHolder.tx_userid.setText(door.getUserid());
        doorHolder.tx_code.setText(door.getCode());
        doorHolder.tx_action.setText(door.getAction());
        return row;
    }
    static class DoorHolder
    {
        TextView tx_sr, tx_date, tx_time, tx_userid, tx_code, tx_action;
    }
}
