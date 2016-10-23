package com.example.setu4.home_v4;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MenuActivity extends AppCompatActivity {


    String JSON_STRINGActive, JSON_STRINGDoor, JSON_STRINGGas, JSON_STRINGLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    //Active
    public void getActive(View view)
    {

        new BackgroundTaskActive().execute();
        //String JSON_STRING = new BackgroundTask().execute().get();
        if (JSON_STRINGActive == null)
        {
            Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this,DisplayListViewActive.class);
            intent.putExtra("json_data", JSON_STRINGActive);
            startActivity(intent);
        }

    }

    class BackgroundTaskActive extends AsyncTask<Void, Void, String>
    {

        String json_url;
        @Override

        protected void onPreExecute()
        {
            json_url = "json_active.php";
        }

        @Override
        protected String doInBackground(Void... params)
        {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRINGActive = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSON_STRINGActive+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result)
        {
            //TextView textView = (TextView)findViewById(R.id.textview);
            //textView.setText(result);
            JSON_STRINGActive = result;

        }
    }

    //Door
    public void getDoor(View view)
    {

        new BackgroundTaskDoor().execute();
        //String JSON_STRING = new BackgroundTask().execute().get();
        if (JSON_STRINGDoor == null)
        {
            Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this,DisplayListViewDoor.class);
            intent.putExtra("json_data", JSON_STRINGDoor);
            startActivity(intent);
        }

    }

    class BackgroundTaskDoor extends AsyncTask<Void, Void, String>
    {

        String json_url;
        @Override

        protected void onPreExecute()
        {
            json_url = "json_door.php";
        }

        @Override
        protected String doInBackground(Void... params)
        {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRINGDoor = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSON_STRINGDoor+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result)
        {
            //TextView textView = (TextView)findViewById(R.id.textview);
            //textView.setText(result);
            JSON_STRINGDoor = result;

        }
    }

    //Gas
    public void getGas(View view)
    {

        new BackgroundTaskGas().execute();
        //String JSON_STRING = new BackgroundTask().execute().get();
        if (JSON_STRINGGas == null)
        {
            Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this,DisplayListViewGas.class);
            intent.putExtra("json_data", JSON_STRINGGas);
            startActivity(intent);
        }

    }

    class BackgroundTaskGas extends AsyncTask<Void, Void, String>
    {

        String json_url;
        @Override

        protected void onPreExecute()
        {
            json_url = "json_gas.php";
        }

        @Override
        protected String doInBackground(Void... params)
        {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRINGGas = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSON_STRINGGas+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result)
        {
            //TextView textView = (TextView)findViewById(R.id.textview);
            //textView.setText(result);
            JSON_STRINGGas = result;

        }
    }

    //Light
    public void getLight(View view)
    {

        new BackgroundTaskLight().execute();
        //String JSON_STRING = new BackgroundTask().execute().get();
        if (JSON_STRINGLight == null)
        {
            Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this,DisplayListViewLight.class);
            intent.putExtra("json_data", JSON_STRINGLight);
            startActivity(intent);
        }

    }
    class BackgroundTaskLight extends AsyncTask<Void, Void, String>
    {

        String json_url;
        @Override

        protected void onPreExecute()
        {
            json_url = "json_light.php";
        }

        @Override
        protected String doInBackground(Void... params)
        {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRINGLight = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSON_STRINGLight+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result)
        {
            //TextView textView = (TextView)findViewById(R.id.textview);
            //textView.setText(result);
            JSON_STRINGLight = result;

        }
    }

    //New User Registration
    public void userReg (View view)
    {
        startActivity(new Intent(this, Register.class));
    }
    //User Control
    public void userControl (View view)
    {
        startActivity(new Intent(this, Control.class));
    }
}
