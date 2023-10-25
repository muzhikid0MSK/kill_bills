package com.example.killbillrecord;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private NotificationChannel notificationChannel;
    private NotificationManager notificationManager;

    private Notification aliveNotification;

    private static final String CHANNEL_ID = "ALIVE CHANNEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initHandler();

    }

    private void initHandler() {

    }

    private void initData() {
        createNotificationChannel();

    }

    private void initView() {
        createAliveNotification();
        int aliveNotiId = 1;
        notificationManager.notify(aliveNotiId,aliveNotification);
    }

    private void createAliveNotification() {
        aliveNotification = new Notification.Builder(MainActivity.this, CHANNEL_ID).
                setContentTitle(getString(R.string.alive_notification_title)).
                setContentText(getString(R.string.alive_notification_content)).
                setSmallIcon(R.drawable.ic_launcher_foreground).
                setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground)).setOngoing(true)
                .build();
    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.notification_channel_name);
            String description = getString(R.string.notification_channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);
            // Register the channel with the system. You can't change the importance
            // or other notification behaviors after this.
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        } else {
            ;
        }
    }

}

