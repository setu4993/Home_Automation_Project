package com.example.setu4.home_v4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListViewActive extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ActiveAdapter activeAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list_view_active_layout);
        listView = (ListView)findViewById(R.id.listViewActive);
        activeAdapter = new ActiveAdapter(this, R.layout.row_layout_active);
        listView.setAdapter(activeAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("active");
            String sr, date, time, device, devid, status;
            int count = 0;


            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                sr = JO.getString("sr");
                date = JO.getString("date");
                time = JO.getString("time");
                device = JO.getString("device");
                devid = JO.getString("devid");
                status = JO.getString("status");
                Active active = new Active(sr, date, time, device, devid, status);
                activeAdapter.add(active);
                count++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
