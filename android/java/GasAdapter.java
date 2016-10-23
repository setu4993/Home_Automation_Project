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
public class GasAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public GasAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Gas object) {
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

        GasHolder gasHolder;

        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_gas,parent,false);
            gasHolder = new GasHolder();
            gasHolder.tx_sr = (TextView)row.findViewById(R.id.tx_sr);
            gasHolder.tx_date = (TextView)row.findViewById(R.id.tx_date);
            gasHolder.tx_time = (TextView)row.findViewById(R.id.tx_time);
            gasHolder.tx_value = (TextView)row.findViewById(R.id.tx_value);
            gasHolder.tx_wrlvl = (TextView)row.findViewById(R.id.tx_wrlvl);
            row.setTag(gasHolder);
        }
        else
        {
            gasHolder = (GasHolder) row.getTag();
        }

        Gas gas = (Gas)this.getItem(position);
        gasHolder.tx_sr.setText(gas.getSr());
        gasHolder.tx_date.setText(gas.getDate());
        gasHolder.tx_time.setText(gas.getTime());
        gasHolder.tx_value.setText(gas.getValue());
        gasHolder.tx_wrlvl.setText(gas.getWrlvl());
        return row;
    }
    static class GasHolder
    {
        TextView tx_sr, tx_date, tx_time, tx_value, tx_wrlvl;
    }
}
