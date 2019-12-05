package com.bmsce.ise.madstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

// Setting up a simple alarm

/*
Add this in AndroidManifest.xml
<receiver android:name=".AlarmActivity$AlarmReceiver" />
 */

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        soundAlarm(5);
    }

    public void soundAlarm(int seconds) {
        Intent i = new Intent(AlarmActivity.this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(AlarmActivity.this, 0, i, 0);

        long time = System.currentTimeMillis() + (seconds * 1000);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        assert am!=null;
        am.set(AlarmManager.RTC_WAKEUP, time, pi);
        Toast.makeText(getApplicationContext(), "Alarm in " + seconds + " seconds", Toast.LENGTH_SHORT).show();
    }

    public static class AlarmReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context c, Intent i) {
            Uri nt = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(c.getApplicationContext(), nt);
            r.play();
            Toast.makeText(c, "Alarm....", Toast.LENGTH_LONG).show();
        }
    }
}
