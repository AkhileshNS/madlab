package com.bmsce.ise.madstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;

//  Using notifications from a receiver

/*
Add this in AndroidManifest.xml
<receiver android:name=".NotificationReceiverActivity$MyNotificationPublisher" />
 */

public class NotificationReceiverActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_receiver);

        scheduleNotification();
    }

    private void scheduleNotification () {
        Intent i = new Intent( this, MyNotificationPublisher.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0 , i , 0);

        long futureInMillis = SystemClock.elapsedRealtime();
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert am!=null;
        am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP , futureInMillis , pi) ;
    }

    public static class MyNotificationPublisher extends BroadcastReceiver {
        public void onReceive (Context c , Intent i) {
            NotificationManager nm = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);

            Notification nt = new NotificationCompat.Builder( c, "1")
                    .setContentTitle("Title")
                    .setContentText("This is the description")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setAutoCancel(true)
                    .setChannelId("1")
                    .build();

            if (android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.O) {
                NotificationChannel nc = new NotificationChannel(
                        "1", "default", NotificationManager.IMPORTANCE_HIGH
                );
                assert nm != null;
                nm.createNotificationChannel(nc) ;
            }
            assert nm!=null;
            nm.notify(1 , nt) ;
        }
    }
}
