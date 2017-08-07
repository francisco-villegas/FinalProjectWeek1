package com.example.francisco.finalprojectweek1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by FRANCISCO on 05/08/2017.
 */

public class W1D4_2 extends Fragment{

    private static final String MY_PREF_FILE = "mypref_file";
    private static final String TAG = "Main";
    EditText et1,et2;
    Button btn1, btn2, btnInt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_w1d4_2, container, false);

        et1 = (EditText) view.findViewById(R.id.et1);
        et2 = (EditText) view.findViewById(R.id.et2);

        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btnInt = (Button) view.findViewById(R.id.btnInt);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData(view);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(view);
            }
        });

        btnInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Second(view);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Week 1 Day 2");
    }

    public void sendData(View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MY_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("value1", et1.getText().toString());
        editor.putString("value2", et2.getText().toString());
        editor.commit();

        Toast.makeText(getActivity(), et1.getText().toString() + " " +et2.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void getData(View view) {
        SharedPreferences sharePreferences = getActivity().getSharedPreferences(MY_PREF_FILE, Context.MODE_PRIVATE);
        Log.d(TAG, "getData: " + sharePreferences.getString("value1","default"));
        Toast.makeText(getActivity(), "The data was retrieved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(),SecondActivityW1D4_2.class);
        startActivity(intent);

        Log.d(TAG, "getData: ");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(getActivity(), "Landscape", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onConfigurationChanged: Landscape");
        }
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(getActivity(), "Portrait", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onConfigurationChanged: Portrait");
        }
    }

    public void Second(View view) {
        Intent intent = new Intent(getActivity(),SecondActivityW1D4_2.class);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
}
