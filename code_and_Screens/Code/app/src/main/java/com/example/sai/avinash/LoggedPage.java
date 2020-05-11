package com.example.sai.avinash;

import android.Manifest;
import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.Channel;
import java.util.ArrayList;


public class LoggedPage extends AppCompatActivity implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_READ_CONTACTS = 100;
    ArrayList smsList;
    TextView t1,t2,rest,txt,b1;
    int X,Y,msg;
    String Body;
    Button link;
    Spinner limit;
    String listlimit[]={"25","50","75","100","125","150","175","200","225","250","275","300","325","350","375","400","425","450","475","500"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_page);
        link=(Button)findViewById(R.id.btnlink);
        limit=(Spinner)findViewById(R.id.spinlimit);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        rest=(TextView)findViewById(R.id.textView10);
        txt=(TextView)findViewById(R.id.txt);
        b1=(TextView)findViewById(R.id.butn);
        b1.setOnClickListener(this);


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,listlimit);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        limit.setAdapter(adapter);

        link.setOnClickListener(this);


        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            showContacts();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, PERMISSION_REQUEST_READ_CONTACTS);
        }
    }
    private void showContacts() {
        Uri inboxURI = Uri.parse("content://sms/inbox");
        smsList = new ArrayList();
        ContentResolver cr = getContentResolver();


        Cursor c = cr.query(inboxURI, null, null, null, null);
//        while (c.moveToNext()) {
//            String Number = c.getString(c.getColumnIndexOrThrow("address")).toString();
//            String Body = c.getString(c.getColumnIndexOrThrow("body")).toString();
//            if (Number.equals("+918940457506"))
//            {
//                smsList.add("Number: " + Number + "\n" + "Body: " + Body);
//            }
//
//        }
        c.moveToFirst();
            String Number = c.getString(c.getColumnIndexOrThrow("address")).toString();
                Body = c.getString(c.getColumnIndexOrThrow("body")).toString();

                String A=Body;
                msg=Integer.parseInt(A);

                msg=((msg-7)/3);

                Log.d("Message decryption",""+msg);
                Log.d("sender", "" + Number);
                Log.d("msg", "" + Body);

        t1.setText("Consumed Energy");
                t2.setText(msg + " KWh");
           //res.setText(a);

        txt.setVisibility(View.INVISIBLE);
        rest.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onClick(View v) {
        if(v==b1) {
            double z;

            String x = limit.getSelectedItem().toString();
            //String y=Body;
            X = Integer.parseInt(x);
            Y = msg;


            if (Y > X) {
                txt.setVisibility(View.VISIBLE);
                rest.setVisibility(View.VISIBLE);
                rest.setText("You have exceeded the limit");

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("eSalvagE")
                        .setContentText("You have exceeded the limit")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
// notificationId is a unique int for each notification that you must define
                notificationManager.notify(1, mBuilder.build());


            } else {

                z = (double) ((Y * 100) / X);
                Log.d("divide Value", "" + z);
                txt.setVisibility(View.VISIBLE);
                rest.setVisibility(View.VISIBLE);
                Log.d("final value", "" + z);

                rest.setText(Double.toString(z).concat("%"));

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("eSalvagE")
                        .setContentText("You have consumed" + z + "KWh")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                notificationManager.notify(1, mBuilder.build());

            }
        }
        if(v==link)
        {
            String url = "https://www.tnebnet.org/awp/tariffMaster";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }


    }
}
