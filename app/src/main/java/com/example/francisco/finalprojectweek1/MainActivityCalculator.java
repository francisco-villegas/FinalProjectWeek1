package com.example.francisco.finalprojectweek1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivityCalculator extends Fragment implements View.OnClickListener {

    private static final String TAG = "MainActivityCalculator";
    ImageButton btnDel;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnClear, btnBracketEnd, btnBracketStart, btnMod, btnDivide, btnMultiply, btnSubtract, btnAdd, btnEqual, btnDot, btnSQRT, btnSIN, btnCOS, btnPOW;
    EditText Result;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_calculator, container, false);

        //To lose focus in EditText
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btn3 = (Button) view.findViewById(R.id.btn3);
        btn4 = (Button) view.findViewById(R.id.btn4);
        btn5 = (Button) view.findViewById(R.id.btn5);
        btn6 = (Button) view.findViewById(R.id.btn6);
        btn7 = (Button) view.findViewById(R.id.btn7);
        btn8 = (Button) view.findViewById(R.id.btn8);
        btn9 = (Button) view.findViewById(R.id.btn9);
        btn0 = (Button) view.findViewById(R.id.btn0);

        Result = (EditText) view.findViewById(R.id.Result);

        btnClear = (Button) view.findViewById(R.id.btnClear);
        btnBracketEnd = (Button) view.findViewById(R.id.btnBracketEnd);
        btnBracketStart = (Button) view.findViewById(R.id.btnBracketStart);
        btnMod = (Button) view.findViewById(R.id.btnMod);
        btnDivide = (Button) view.findViewById(R.id.btnDivide);
        btnMultiply = (Button) view.findViewById(R.id.btnMultiply);
        btnSubtract = (Button) view.findViewById(R.id.btnSubtract);
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnEqual = (Button) view.findViewById(R.id.btnEqual);
        btnDel = (ImageButton) view.findViewById(R.id.btnDel);
        btnDot = (Button) view.findViewById(R.id.btnDot);
        btnSQRT = (Button) view.findViewById(R.id.btnSQRT);
        btnSIN = (Button) view.findViewById(R.id.btnSIN);
        btnCOS = (Button) view.findViewById(R.id.btnCOS);
        btnPOW = (Button) view.findViewById(R.id.btnPOW);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);

        btnClear.setOnClickListener(this);
        btnBracketEnd.setOnClickListener(this);
        btnBracketStart.setOnClickListener(this);
        btnMod.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnSQRT.setOnClickListener(this);
        btnSIN.setOnClickListener(this);
        btnCOS.setOnClickListener(this);
        btnPOW.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        String buttonText = "";
        try{
            Button b = (Button) view;
            buttonText = b.getText().toString();

        }catch(Exception exc){
            ImageButton c = (ImageButton) view;
        }


        String previous_result = Result.getText().toString();
        switch (view.getId()) {
            case R.id.btnEqual:
                try {
                    String eval_result = ""+eval(previous_result);
                    eval_result = eval_result.indexOf(".") < 0 ? eval_result : eval_result.replaceAll("0*$", "").replaceAll("\\.$", "");
                    Result.setText(eval_result);
                }catch(Exception ex){
                    Toast.makeText(getActivity().getApplicationContext(),"Invalid Operation", Toast.LENGTH_LONG).show();}
                break;
            case R.id.btnDel:
                if(previous_result.length()>0)
                    Result.setText(previous_result.substring(0,previous_result.length()-1));
                break;
            case R.id.btnClear:
                Result.setText("");
                break;
            default:
                if(previous_result.equalsIgnoreCase("0"))
                    Result.setText(buttonText);
                else
                    Result.setText(previous_result+buttonText);


        }
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else if (eat('%')) x %= parseFactor(); // mod
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.' || ch == 'E') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.' || ch == 'E') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equalsIgnoreCase("sqrt")) x = Math.sqrt(x);
                    else if (func.equalsIgnoreCase("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equalsIgnoreCase("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equalsIgnoreCase("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
