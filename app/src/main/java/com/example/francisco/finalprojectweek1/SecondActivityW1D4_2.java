package com.example.francisco.finalprojectweek1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class SecondActivityW1D4_2 extends AppCompatActivity {

    TextView et1, et2;
    private static final String MY_PREF_FILE = "mypref_file";
    private static final String TAG = "Second";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_w1d4_2);

        et1 = (TextView) findViewById(R.id.et1);
        et2 = (TextView) findViewById(R.id.et2);

        SharedPreferences sharePreferences = getSharedPreferences(MY_PREF_FILE, Context.MODE_PRIVATE);

        et1.setText(sharePreferences.getString("value1","default"));
        et2.setText(sharePreferences.getString("value2","default"));

        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
}
