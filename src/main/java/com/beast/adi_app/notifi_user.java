package com.beast.adi_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class notifi_user extends AppCompatActivity {

    EditText msg,u;
    Button notify;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifi_user);
        msg=findViewById(R.id.msg);
        u=findViewById(R.id.url);
        notify=findViewById(R.id.notify);
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cid="channel1";
                NotificationChannel nc=new NotificationChannel(cid,"c1", NotificationManager.IMPORTANCE_HIGH);
                NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                nm.createNotificationChannel(nc);
                NotificationCompat.Builder nb=new NotificationCompat.Builder(getApplicationContext(),cid);
                nb.setSmallIcon(R.drawable.ic_launcher_background);
                nb.setContentTitle("News Karkala");
                nb.setContentText(msg.getText().toString());
                Intent i=new Intent(getApplicationContext(),Home.class);
                i.putExtra("msg",u.getText().toString());
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(),0,i,PendingIntent.FLAG_UPDATE_CURRENT);
                nb.setContentIntent(pi);
                nm.notify(121,nb.build());
            }
        });
    }
}
