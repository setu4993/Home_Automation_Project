package com.example.setu4.home_v4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListViewGas extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    GasAdapter gasAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list_view_gas_layout);
        listView = (ListView)findViewById(R.id.listViewGas);
        gasAdapter = new GasAdapter(this, R.layout.row_layout_gas);
        listView.setAdapter(gasAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        try
        {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("gas");
            String sr, date, time, value, wrlvl;
            int count = 0;

            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                sr = JO.getString("sr");
                date = JO.getString("date");
                time = JO.getString("time");
                value = JO.getString("value");
                wrlvl = JO.getString("wrlvl");
                Gas gas = new Gas(sr, date, time, value, wrlvl);
                gasAdapter.add(gas);
                count++;
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
