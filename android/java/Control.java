package com.example.setu4.home_v4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Control extends AppCompatActivity {

    EditText et_device, et_action;
    String action, device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        et_device = (EditText)findViewById(R.id.device);
        et_action = (EditText)findViewById(R.id.action);
    }

    public void userCControl(View view)
    {
        action = et_action.getText().toString();
        device = et_device.getText().toString();
        String method = "control";
        ControlBackgroundTask controlBackgroundTask = new ControlBackgroundTask(this);
        controlBackgroundTask.execute(method, device, action);
        finish();
    }
}
