package com.example.francisco.finalprojectweek1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by FRANCISCO on 05/08/2017.
 */

public class W1D3 extends Fragment {

    public static final String TAG = "MainActivity";
    EditText etName;
    EditText etName2;
    Button btnName, btnSecond, btnPass;
    TextView tvName;

    //Binding person views
    EditText etPersonName, etPersonGender;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_w1d3, container, false);

        etName = (EditText) view.findViewById(R.id.etName);
        etName2 = (EditText) view.findViewById(R.id.etName2);
        btnName = (Button) view.findViewById(R.id.btnName);
        btnSecond = (Button) view.findViewById(R.id.btnSecond);
        btnPass = (Button) view.findViewById(R.id.btnPass);
        tvName = (TextView) view.findViewById(R.id.tvName);

        etPersonName = (EditText) view.findViewById(R.id.etPersonName);
        etPersonGender = (EditText) view.findViewById(R.id.etPersonGender);

        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int etValue = Integer.parseInt(etName.getText().toString());
                int etValue2 = Integer.parseInt(etName2.getText().toString());
                tvName.setText("Result "+(etValue+etValue2));
            }
        });

        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSecond(view);
            }
        });

        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passPersonToSecond(view);
            }
        });

        //tvName.setText("Some tv Text different of xml");
        Log.d(TAG, "onCreate: ");

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart: ");

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }


    public void goToSecond(View view) {

        String value = etName.getText().toString();

        Intent intent = new Intent(getActivity(),SecondActivityW1D3.class);
        intent.setAction("FirstIntent");
        intent.putExtra(getString(R.string.KEY_VALUE1),value);
        startActivity(intent);
    }

    public void passPersonToSecond(View view) {
        String personName = etPersonName.getText().toString();
        String personGender = etPersonGender.getText().toString();

        Person person = new Person(personName,personGender);

        Intent intent = new Intent(getActivity(),SecondActivityW1D3.class);
        intent.setAction("sendingPerson");
        intent.putExtra(getString(R.string.KEY2), person);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Week 1 Day 3");
    }
}
