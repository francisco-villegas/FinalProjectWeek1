package com.example.francisco.finalprojectweek1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SecondActivityW1D3 extends AppCompatActivity {

    private static final String TAG = "Second";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_w1d3);

        Intent intent = getIntent();

        switch (intent.getAction()){
            case "FirstIntent":
                Log.d(TAG, "onCreate: " + intent.getStringExtra(getString(R.string.KEY_VALUE1)));
                break;
            case "sendingPerson":
                Person person = (Person) intent.getSerializableExtra(getString(R.string.KEY2));
                Log.d(TAG, "onCreate: " + person.getName() + " " + person.getGender());
                break;
        }


    }
}
