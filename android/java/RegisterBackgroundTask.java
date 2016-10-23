package com.example.setu4.home_v4;

import android.content.Context;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by setu4 on 30-May-16.
 */
public class RegisterBackgroundTask extends AsyncTask<String, Void, String>
{
    //AlertDialog alertDialog;
    Context ctx;
    RegisterBackgroundTask(Context ctx)
    {
        this.ctx = ctx;
    }
    @Override
    protected void onPreExecute() {
        //alertDialog = new AlertDialog().Builder(ctx).create();
        //alertDialog.setTitle("Login Information");
    }

    @Override
    protected String doInBackground(String... params)
    {
        String reg_url = "reguser.php";
        String method = params[0];
        if(method.equals("register"))
        {
            String name = params[1];
            String user = params[2];
            String pswd = params[3];
            String dkey = params[4];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("name", "UTF-8") +"="+ URLEncoder.encode(name, "UTF-8") +"&"+
                        URLEncoder.encode("user", "UTF-8") +"="+ URLEncoder.encode(user, "UTF-8") +"&"+
                        URLEncoder.encode("pswd", "UTF-8") +"="+ URLEncoder.encode(pswd, "UTF-8") +"&"+
                        URLEncoder.encode("dkey", "UTF-8") +"="+ URLEncoder.encode(dkey, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "New user registered!";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        if(result.equals("New user registered!"))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        /*
        else
        {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
        */
    }
}
