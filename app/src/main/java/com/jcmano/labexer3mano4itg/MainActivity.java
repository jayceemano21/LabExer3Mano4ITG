package com.jcmano.labexer3mano4itg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etData,etFilename;
    SharedPreferences preferences;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etData = (EditText) findViewById(R.id.etData);
        etFilename = (EditText) findViewById(R.id.etFilename);
        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);
    }

    public void saveSharedPreferences(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Data",etData.getText().toString());
        editor.putString("Text",etFilename.getText().toString());
        editor.commit();
        Toast.makeText(this, "Saved in Shared Preferences!", Toast.LENGTH_SHORT).show();
    }

    public void saveInternalStorage(View view){
        String path = etFilename.getText().toString();
        String data = "Data is" + etData.getText().toString() + " | ";
        String filename = "Filename is  " + etFilename.getText().toString() + " ";

        try {
            fos = openFileOutput(path, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.write(filename.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Saved in Internal Storage!", Toast.LENGTH_SHORT).show();
    }

    public void saveInternalCache(View view){
        String path = etFilename.getText().toString();
        File folder = getCacheDir();
        File file = new File(folder,path);
        String message = etData.getText().toString();
        writeData(file,message);
        Toast.makeText(this,"Successfully written to Internal Cache",Toast.LENGTH_SHORT).show();
    }

    public void saveExternalCache(View view){
        String path = etFilename.getText().toString();
        File folder = getExternalCacheDir();
        File file = new File(folder,path);
        String message = etData.getText().toString();
        writeData(file,message);
        Toast.makeText(this,"Successfully written to External Cache",Toast.LENGTH_SHORT).show();
    }

    public void saveExternalStorage(View view){
        String path = etFilename.getText().toString();
        File folder = getExternalFilesDir("temp");
        File file = new File(folder,path);
        String message = etData.getText().toString();
        writeData(file,message);
        Toast.makeText(this,"Successfully written to External Storage",Toast.LENGTH_SHORT).show();
    }

    public void saveExternalPublicStorage(View view){
        String path = etFilename.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder,path);
        String message = etData.getText().toString();
        writeData(file,message);
        Toast.makeText(this,"Successfully written to External Public Storage",Toast.LENGTH_SHORT).show();
    }

    public void nextPage(View view){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    public void writeData(File file, String message){

        try{
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
