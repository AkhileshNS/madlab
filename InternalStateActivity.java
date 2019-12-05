package com.bmsce.ise.madstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/*
Saving state using internal files

Add to build.gradle (Module:app)
dependencies {
    ...
    implementation 'com.google.code.gson:gson:2.8.6'
}
*/

public class InternalStateActivity extends AppCompatActivity {
    String FILENAME = "state.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_state);

        State state = new State();
        state.title = "Sup Dawg";
        state.body = "Sup people and dawgs";

        writeToState(FILENAME, new Gson().toJson(state));

        State retrievedState = new Gson().fromJson(readFromState(FILENAME), State.class);
        Log.d("InternalStateActivity", retrievedState.title + "," + retrievedState.body);
    }

    public class State {
        String title = "";
        String body = "";
    }

    public void writeToState(String filename,String outputString) {
        try {
            FileOutputStream outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(outputString.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readFromState(String filename) {
        try {
            FileInputStream inputStream = openFileInput(filename);
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            r.close();
            inputStream.close();

            return total.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}

/*
response logs
2019-12-05 21:48:26.914 14837-14837/com.bmsce.ise.madstudy D/InternalStateActivity: Sup Dawg,Sup people and dawgs
 */
