package com.example.francisco.finalprojectweek1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class SecondActivityW1D4_1 extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_w1d4_1);

        Intent intent = getIntent();

        switch (intent.getAction()) {
            case "sendingPerson":
                PersonW1D4_1 personW1D41 = (PersonW1D4_1) intent.getParcelableExtra(getString(R.string.KEY1));
                Log.d(TAG, "onCreate: " + personW1D41.getName() + " " + personW1D41.getGender());
                Toast.makeText(this, personW1D41.getName() + " " + personW1D41.getGender(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Second toast", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
