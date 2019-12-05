package com.bmsce.ise.madstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

// Essentials of a simple AsyncTas

public class AsyncTaskActivity extends AppCompatActivity {

    static String TAG = "AsyncTaskActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        bgTask task = new bgTask();
        task.execute("Anirudh", "Akhilesh", "Anirban"); // Sends params to doInBackground
    }

    public static class bgTask extends AsyncTask<String /*params*/, String /*progress*/, String /*response*/> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute");
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d(TAG, "doInBackground");
            Log.d(TAG, TextUtils.join(",", params));
            publishProgress("Raksha", "Disha", "Pachauri"); // sends params to onProgressUpdate
            return "Hello World"; // Sends to onPostExecute
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            Log.d(TAG, "onPostExecute");
            Log.d(TAG, response);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, "onProgressUpdate");
            Log.d(TAG, TextUtils.join(",", values));
        }
    }
}

/*
Response Logs

2019-12-05 15:59:54.038 10128-10128/com.bmsce.ise.madstudy D/AsyncTaskActivity: onPreExecute
2019-12-05 15:59:54.041 10128-10167/com.bmsce.ise.madstudy D/AsyncTaskActivity: doInBackground
2019-12-05 15:59:54.041 10128-10167/com.bmsce.ise.madstudy D/AsyncTaskActivity: Anirudh,Akhilesh,Anirban
2019-12-05 15:59:54.086 10128-10128/com.bmsce.ise.madstudy D/AsyncTaskActivity: onProgressUpdate
2019-12-05 15:59:54.086 10128-10128/com.bmsce.ise.madstudy D/AsyncTaskActivity: Raksha,Disha,Pachauri
2019-12-05 15:59:54.087 10128-10128/com.bmsce.ise.madstudy D/AsyncTaskActivity: onPostExecute
2019-12-05 15:59:54.087 10128-10128/com.bmsce.ise.madstudy D/AsyncTaskActivity: Hello World
* */
