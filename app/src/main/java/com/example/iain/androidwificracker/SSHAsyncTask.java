package com.example.iain.androidwificracker;

/**
 * Created by Iain on 11/22/2015.
 */

import android.os.AsyncTask;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHAsyncTask extends AsyncTask<String, Void, Boolean> {
    String ip;
    int port;

    @Override
    protected Boolean doInBackground(String... urls){
        try{
            JSch jsch = new JSch();
            Session session = jsch.getSession("Iain", ip, port);
            session.connect();

        } catch(Exception e){}
        return false;
    }

    public SSHAsyncTask(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

}
