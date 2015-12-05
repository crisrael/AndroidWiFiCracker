package com.example.iain.androidwificracker;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Iain on 12/5/2015.
 */
public class FileUploader implements Runnable {
    URL url;
    String response_string;
    byte[] data_to_server;
    FileInputStream input_stream = null;

    FileUploader(String url){
        try{
            this.url = new URL(url);
        } catch(IOException e){}
    }

    public void send(FileInputStream stream){
        input_stream = stream;
        sending();
    }

    private void sending(){
        String file_name = "test.cap";
        String line_end = "\r\n";
        String two_hyphens = "--";
        String boundary = "*****";
        String tag = "fSnd";

        try{
            Log.e(tag, "Starting upload");

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            DataOutputStream output_stream = new DataOutputStream(connection.getOutputStream());
            output_stream.writeBytes("--" + boundary + line_end);
            output_stream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + file_name + "\" + line_end");
            output_stream.writeBytes(line_end);

            int bytes_available = input_stream.available();
            int max_buffer_size = 1024;
            int buffer_size = Math.min(bytes_available, max_buffer_size);
            byte[] buffer = new byte[buffer_size];

            int bytes_read = input_stream.read(buffer, 0, buffer_size);

            while(bytes_read > 0){
                output_stream.write(buffer, 0, buffer_size);
                bytes_available = input_stream.available();
                buffer_size = Math.min(bytes_available, max_buffer_size);
                bytes_read = input_stream.read(buffer, 0, buffer_size);
            }

            output_stream.writeBytes(line_end);
            output_stream.writeBytes("--" + boundary + "--" + line_end);

            input_stream.close();
            output_stream.flush();

            Log.e(tag, "File sent, Response: " + String.valueOf(connection.getResponseCode()));

            //retrieve response from server
            InputStream stream = connection.getInputStream();
            int character;
            StringBuffer b = new StringBuffer();
            while((character = stream.read()) != -1){
                b.append((char)character);
            }
            String s = b.toString();
            Log.i("response", s);

            output_stream.close();

        } catch (IOException e){
            Log.e(tag, "IO error: " + e.getMessage(), e);
        }
    }

    @Override
    public void run(){}

}
