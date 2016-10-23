package com.example.setu4.home_v4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText et_name, et_user, et_pswd, et_dkey;
    String name, user, pswd, dkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_name = (EditText)findViewById(R.id.name);
        et_user = (EditText)findViewById(R.id.user);
        et_pswd = (EditText)findViewById(R.id.pswd);
        et_dkey = (EditText)findViewById(R.id.dkey);
    }

    public void userNReg(View view)
    {
        name = et_name.getText().toString();
        user = et_user.getText().toString();
        pswd = et_pswd.getText().toString();
        dkey = et_dkey.getText().toString();

        String method = "register";
        RegisterBackgroundTask registerBackgroundTask = new RegisterBackgroundTask(this);
        registerBackgroundTask.execute(method, name, user, pswd, dkey);
        finish();
    }
}
