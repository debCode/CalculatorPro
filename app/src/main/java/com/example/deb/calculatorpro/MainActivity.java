package com.example.deb.calculatorpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity{

    private TextView calculatorDisplay;
    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private DebCalculatorActivity calculationActivity;
    private static final String DIGITS = "0123456789.";

    DecimalFormat df = new DecimalFormat("@###########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculationActivity = new DebCalculatorActivity();
        calculatorDisplay = (TextView) findViewById(R.id.textView1);

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(9);

        findViewById(R.id.button0).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button1).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button2).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button3).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button4).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button5).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button6).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button7).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button8).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.button9).setOnClickListener((View.OnClickListener) this);

        findViewById(R.id.buttonAdd).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.buttonSubtract).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.buttonMultiply).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.buttonDivide).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.buttonToggleSign).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.buttonDecimalPoint).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.buttonEquals).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.buttonClear).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.buttonClearMemory).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.buttonAddToMemory).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.buttonSubtractFromMemory).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.buttonRecallMemory).setOnClickListener((View.OnClickListener) this);

        if (findViewById(R.id.buttonSquareRoot) != null) {
            findViewById(R.id.buttonSquareRoot).setOnClickListener((View.OnClickListener) this);
        }
        if (findViewById(R.id.buttonSquared) != null) {
            findViewById(R.id.buttonSquared).setOnClickListener((View.OnClickListener) this);
        }
        if (findViewById(R.id.buttonInvert) != null) {
            findViewById(R.id.buttonInvert).setOnClickListener((View.OnClickListener) this);
        }
        if (findViewById(R.id.buttonSine) != null) {
            findViewById(R.id.buttonSine).setOnClickListener((View.OnClickListener) this);
        }
        if (findViewById(R.id.buttonCosine) != null) {
            findViewById(R.id.buttonCosine).setOnClickListener((View.OnClickListener) this);
        }
        if (findViewById(R.id.buttonTangent) != null) {
            findViewById(R.id.buttonTangent).setOnClickListener((View.OnClickListener) this);
        }
    }

    public void onClick(View view){
        String buttonPressed = ((Button) view).getText().toString();

        if (DIGITS.contains(buttonPressed)){
            if (userIsInTheMiddleOfTypingANumber){
                if (buttonPressed.equals(".") && calculatorDisplay.getText().toString().contains(".")){
                    //ERROR
                }
                else {
                    calculatorDisplay.append(buttonPressed);
                }
            }
            else {
                if (buttonPressed.equals(".")){
                    //ERROR
                    calculatorDisplay.setText(0 + buttonPressed);
                }
                else {
                    calculatorDisplay.setText(buttonPressed);
                }
            }
        }
        else {
            if (userIsInTheMiddleOfTypingANumber){
                calculationActivity.setCalculatorOperand(Double.parseDouble(calculatorDisplay.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }
            calculationActivity.performOperation(buttonPressed);
            calculatorDisplay.setText(df.format(calculationActivity.getCalculatorOperand()));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save variables on screen orientation change
        outState.putDouble("OPERAND", calculationActivity.getCalculatorOperand());
        outState.putDouble("MEMORY", calculationActivity.getCalculatorMemory());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // restore variables on screen orientation change
        calculationActivity.setCalculatorOperand(savedInstanceState.getDouble("OPERAND"));
        calculationActivity.setCalculatorMemory(savedInstanceState.getDouble("MEMORY"));
        calculatorDisplay.setText(df.format(calculationActivity.getCalculatorOperand()));
    }
}

