package com.example.francisco.finalprojectweek1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class EMIProject extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, TextWatcher {
    private static final String TAG = "Test";
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    EditText et1,et2;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emiproject);

        //To lose focus in EditText
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //Change Title
        setTitle("EMI project");

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);

        et1.addTextChangedListener(this);
        et2.addTextChangedListener(this);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(this);

        tv5.setText("Percent: "+String.valueOf(seekBar.getProgress())+"% ");
    }

    public void CalculateEMI(){
        try {
            int i = seekBar.getProgress();
            double rate = ((double)i) / (12 * 100);
            double amount = Double.parseDouble(et1.getText().toString());
            double year = Double.parseDouble(et2.getText().toString());
            double emi = 0;
            Log.d(TAG, "CalculateEMI: "+rate+" "+amount+" "+year);
            if( !et1.getText().toString().equals("") && !et2.getText().toString().equals("") && i != 0)
                emi = (amount * rate * Math.pow(1 + rate, year)) / (Math.pow(1 + rate, year) - 1);
            //converts to string it was a double
            if((emi + "").equalsIgnoreCase("nan"))
                tv4.setText("Result of operation too high");
            else {
                String emi_s = "" + emi;
                //Remove 0's at the right or at the left
                emi_s = emi_s.indexOf(".") < 0 ? emi_s : emi_s.replaceAll("0*$", "").replaceAll("\\.$", "");
                tv4.setText(emi_s);

                emi_s = "" + (emi*year);
                emi_s = emi_s.indexOf(".") < 0 ? emi_s : emi_s.replaceAll("0*$", "").replaceAll("\\.$", "");
                tv7.setText(emi_s);
            }
        }catch(Exception ex){tv4.setText("");}
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        tv5.setText("Percent: "+String.valueOf(i)+"% ");
        CalculateEMI();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        CalculateEMI();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
