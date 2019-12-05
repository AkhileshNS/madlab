package com.bmsce.ise.madstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

// Consuming HTTP Services

/*
* Mam said its alright to use Volley
*
* so at build.gradle (Module:app) add
* dependencies {
*   ...
*   implementation "com.android.volley:volley:1.1.0"
* }
*
* at AndroidManifest.xml add
* <uses-permission android:name="android.permission.INTERNET" />
* */

public class HttpServiceActivity extends AppCompatActivity {
    String TAG = "ConsumeHTTPService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            RequestQueue queue = Volley.newRequestQueue(HttpServiceActivity.this);

            StringRequest getReq = new StringRequest(
                    Request.Method.GET,
                    "https://jsonplaceholder.typicode.com/todos/1",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, error.toString());
                        }
                    }
            );

            StringRequest postReq = new StringRequest(
                    Request.Method.POST,
                    "https://jsonplaceholder.typicode.com/todos",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, error.toString());
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("title", "foo");
                    params.put("body", "bar");
                    params.put("userId", "1");
                    return params;
                }
            };

            queue.add(getReq);
            queue.add(postReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*

Response Logs
2019-12-05 15:59:10.972 10029-10029/com.bmsce.ise.madstudy D/ConsumeHTTPService: {
      "userId": 1,
      "id": 1,
      "title": "delectus aut autem",
      "completed": false
    }
2019-12-05 15:59:12.770 10029-10029/com.bmsce.ise.madstudy D/ConsumeHTTPService: {
      "title": "foo",
      "body": "bar",
      "userId": "1",
      "id": 201
    }

 */
