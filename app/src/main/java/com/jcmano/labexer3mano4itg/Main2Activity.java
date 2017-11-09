package com.jcmano.labexer3mano4itg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    TextView tvDisplay;
    EditText etFilename2;
    SharedPreferences preferences;
    FileInputStream fis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvDisplay = (TextView) findViewById(R.id.tvDisplayData);
        etFilename2 = (EditText) findViewById(R.id.etFilename2);
        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);
    }

    public void loadSharedPreferences(View view){
        String data = preferences.getString("Data","");
        String filename = preferences.getString("Text","");
        tvDisplay.setText("Data is " + data + " and the filename is " + filename);

    }

    public void loadInternalStorage(View view){
        String path = etFilename2.getText().toString();
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput(path);
            while ((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void loadInternalCache(View views){
        String path = etFilename2.getText().toString();
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(new File(getCacheDir(),path));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvDisplay.setText(buffer.toString());
    }

    public void loadExternalCache(View view){
        String path = etFilename2.getText().toString();
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try{
            fis = new FileInputStream(new File(getExternalCacheDir(),path));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvDisplay.setText(buffer.toString());
    }

    public void loadExternalStorage(View view){
        String path = etFilename2.getText().toString();
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try{
            fis = new FileInputStream(new File(getExternalFilesDir("temp"),path));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvDisplay.setText(buffer.toString());
    }

    public void loadExternalPublicStorage(View view){
        String path = etFilename2.getText().toString();
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try{
            fis = new FileInputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),path));
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tvDisplay.setText(buffer.toString());
    }

    public void backPage(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
