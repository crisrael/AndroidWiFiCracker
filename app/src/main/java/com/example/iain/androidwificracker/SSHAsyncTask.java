package com.example.iain.androidwificracker;

/**
 * Created by Iain on 11/22/2015.
 */

import android.os.AsyncTask;
import android.util.Log;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHAsyncTask extends AsyncTask<String, Void, Boolean> {
    String ip;
    int port;

    @Override
    protected Boolean doInBackground(String... urls){
        try{
            Log.d("asynctask", "works");
            JSch jsch = new JSch();
            Session session = jsch.getSession("Iain", ip, port);
            session.connect();
            Log.d("asynctask", "works");

        } catch(JSchException e){
            Log.e("asynctask", e.getMessage());
            Log.e("asynctask", "" + e.getCause());
            Log.e("asynctask", "async connection error");
        }
        return false;
    }

    public SSHAsyncTask(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

}
