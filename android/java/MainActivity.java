package com.example.setu4.home_v4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usr, pwd;
    //EditText et_name, et_pass;
    //String login_name, login_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //et_name = (EditText)findViewById(R.id.username);
        //et_pass = (EditText)findViewById(R.id.password);
    }
    public void userLogin(View view)
    {
        //local log in
        usr = (EditText) findViewById(R.id.username);
        pwd = (EditText) findViewById(R.id.password);
        if (usr.getText().toString().equals("admin") && pwd.getText().toString().equals("1234"))
        {
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(MainActivity.this, MenuActivity.class);
            MainActivity.this.startActivity(myIntent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT).show();
        }


        /*
        //server log in
        login_name = et_name.getText().toString();
        login_pass = et_pass.getText().toString();
        String method = "login";
        RegisterBackgroundTask registerBackgroundTask = new RegisterBackgroundTask(this);
        registerBackgroundTask.execute(method, login_name, login_pass);
        */
    }
}
