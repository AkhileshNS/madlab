package com.bmsce.ise.madstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

/*
Storing data using SQLite: Creating database, defining database through DDLs,
Inserting, updating, deleting, reading rows, Applying transactions
 */

public class SqliteActivity extends AppCompatActivity {
    String TAG = "SqliteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        MyDb db = new MyDb(this, 1);
        Log.d(TAG, db.read());
        db.insert("Hello World", "First program of every language");
        Log.d(TAG, db.read());
        db.update("Hello World", "First program of every programming language", "Hello World");
        Log.d(TAG, db.read());
        db.delete("Hello World");
        Log.d(TAG, db.read());
    }

    public class MyDb extends SQLiteOpenHelper {
        MyDb(Context c, int v) {
            super(c, "State.db", null, v);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE state(title TEXT, body TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS state;");
            onCreate(db);
        }

        public void insert(String t, String b) {
            this.getWritableDatabase().execSQL(String.format(
                    "INSERT INTO state VALUES ('%s','%s')", t, b
            ));
        }

        public void update(String t, String b, String n) {
            this.getWritableDatabase().execSQL(String.format(
                    "UPDATE state SET title='%s',body='%s' WHERE title='%s'", t, b, n
            ));
        }

        public void delete(String n) {
            this.getWritableDatabase().execSQL(String.format(
                    "DELETE FROM state WHERE title='%s'", n
            ));
        }

        public String read() {
            Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM state", null);
            StringBuilder sb = new StringBuilder();
            while (c.moveToNext()) {
                String line = c.getString(0) + "," + c.getString(1);
                sb.append(line);
            }
            c.close();
            return sb.toString();
        }
    }
}

/*
response logs
2019-12-05 22:17:12.044 15758-15758/com.bmsce.ise.madstudy D/SqliteActivity: Hello World,First program of every language
2019-12-05 22:17:12.049 15758-15758/com.bmsce.ise.madstudy D/SqliteActivity: Hello World,First program of every programming language
 */
