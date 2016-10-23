package com.example.setu4.home_v4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListViewDoor extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    DoorAdapter doorAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list_view_door_layout);
        listView = (ListView)findViewById(R.id.listViewDoor);
        doorAdapter = new DoorAdapter(this, R.layout.row_layout_door);
        listView.setAdapter(doorAdapter);
        json_string = getIntent().getExtras().getString("json_data");
        try
        {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("door");
            String sr, date, time, userid, code, action;
            int count = 0;

            while(count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                sr = JO.getString("sr");
                date = JO.getString("date");
                time = JO.getString("time");
                userid = JO.getString("userid");
                code = JO.getString("code");
                action = JO.getString("action");
                Door door = new Door(sr, date, time, userid, code, action);
                doorAdapter.add(door);
                count++;
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}