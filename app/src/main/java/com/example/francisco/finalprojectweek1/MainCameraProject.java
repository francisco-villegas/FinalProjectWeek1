package com.example.francisco.finalprojectweek1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainCameraProject extends Fragment implements View.OnClickListener {
    Button btn1, btn2;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_camera_project_main, container, false);

        //Change Title
        getActivity().setTitle("Camera Project Main window");

        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn1:
                Intent intent = new Intent(getActivity(),CameraProject.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                Intent intent2 = new Intent(getActivity(),EMIProject.class);
                startActivity(intent2);
                break;
        }
    }
}
