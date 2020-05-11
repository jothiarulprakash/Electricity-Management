package com.example.sai.avinash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Thread splash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            //showContacts();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 100);
        }
        splash=new Thread(){
            public void run()//calling the run function to start the thread activity
            {
                try
                {
                    sleep(5000);
                    //creating an intent to show next page after 3 seconds of splash screen
                    Intent mySecondPage = new Intent(getApplicationContext(), LoginPage.class);
                    startActivity(mySecondPage);
                    finish();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

        };splash.start();
    }
}
