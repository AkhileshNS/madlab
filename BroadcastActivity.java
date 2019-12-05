package com.bmsce.ise.madstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

//  Sending a broadcast

/*
In AndroidManifest.xml add after activity tags:
<receiver android:name=".BroadcastActivity$CustomReceiver">
    <intent-filter>
        <action android:name="com.bmsce.ise.madstudy.intent.textbc" />
    </intent-filter>
</receiver>
*/

public class BroadcastActivity extends AppCompatActivity {
    static String TAG = "BroadcastActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        Intent i = new Intent("com.bmsce.ise.madstudy.intent.textbc");
        i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        i.putExtra("message", "Text Broadcast");

        BroadcastReceiver receiver = new CustomReceiver();
        receiver.onReceive(BroadcastActivity.this, i);

        BroadcastActivity.this.sendBroadcast(i);
    }

    public class CustomReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                Log.d(TAG, intent + "");
                Log.d(TAG, intent.getStringExtra("message"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

/*
response logs:
2019-12-05 16:11:27.692 10378-10378/com.bmsce.ise.madstudy D/BroadcastActivity: Intent { act=com.bmsce.ise.madstudy.intent.textbc flg=0x20 (has extras) }
2019-12-05 16:11:27.692 10378-10378/com.bmsce.ise.madstudy D/BroadcastActivity: Text Broadcast
*/
