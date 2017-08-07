package com.example.francisco.finalprojectweek1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class W1D4_1 extends Fragment {

    EditText editText, etPersonName, etPersonGender;
    TextView textView;
    Button btnChangeText, btnGoToSecond, btnShareData;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_w1d4_1, container, false);

        editText = (EditText) view.findViewById(R.id.etname);
        textView = (TextView) view.findViewById(R.id.tvName);

        etPersonName = (EditText) view.findViewById(R.id.PersonName);
        etPersonGender = (EditText) view.findViewById(R.id.PersonGender);

        btnChangeText = (Button) view.findViewById(R.id.btnChangeText);
        btnGoToSecond = (Button) view.findViewById(R.id.btnGoToSecond);
        btnShareData = (Button) view.findViewById(R.id.btnShareData);

        btnChangeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomething(view);
            }
        });

        btnGoToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomething(view);
            }
        });

        btnShareData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomething(view);
            }
        });
        return view;
    }

    public void doSomething(View view) {
        switch (view.getId()){
            case R.id.btnChangeText:
                String data = editText.getText().toString();
                textView.setText(data);
                break;
            case R.id.btnGoToSecond:

                String personName = etPersonName.getText().toString();
                String personGender = etPersonGender.getText().toString();

                PersonW1D4_1 personW1D41 = new PersonW1D4_1(personName,personGender);

                Intent intent = new Intent(getActivity(),SecondActivityW1D4_1.class);
                intent.setAction("sendingPerson");
                intent.putExtra(getString(R.string.KEY1), personW1D41);
                startActivity(intent);
                break;
            case R.id.btnShareData:

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,"This is a message2");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
        }
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        String data = textView.getText().toString();
//        outState.putString("data", data);
//    }

//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        textView.setText(savedInstanceState.getString("data"));
//    }

}
