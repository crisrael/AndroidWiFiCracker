package com.example.iain.androidwificracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void start_connection(View v){
        int port;
        EditText ip_input = (EditText)findViewById(R.id.ip_input);
        EditText port_input = (EditText) findViewById(R.id.port_input);
        String ip = ip_input.getText().toString();
        if(port_input.getText().toString().equals("")) port = 0;
        else port = Integer.parseInt(port_input.getText().toString());

        connect(ip, port);
/*        AlertDialog.Builder alert_builder = new AlertDialog.Builder(context).setMessage("ERROR");
        alert_builder.setTitle("IP is not a suitable IP").setNeutralButton("Close",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        try{

        } catch(NumberFormatException e){
            AlertDialog alert = alert_builder.create();
            alert.show();
        }*/
    }

    public void connect(String ip, int port){

    }
}
