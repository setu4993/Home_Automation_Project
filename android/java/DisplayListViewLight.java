package com.example.setu4.home_v4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListViewLight extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    LightAdapter lightAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list_view_light_layout);
        listView = (ListView)findViewById(R.id.listViewLight);
        lightAdapter = new LightAdapter(this, R.layout.row_layout_light);
        listView.setAdapter(lightAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        try
        {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("light");
            String sr, date, time, id, action;
            int count = 0;

            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                sr = JO.getString("sr");
                date = JO.getString("date");
                time = JO.getString("time");
                id = JO.getString("id");
                action = JO.getString("action");
                Light light = new Light(sr, date, time, id, action);
                lightAdapter.add(light);
                count++;
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
