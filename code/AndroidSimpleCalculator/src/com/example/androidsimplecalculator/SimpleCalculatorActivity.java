package com.example.androidsimplecalculator;

/*
 * Course: CS 2302
 * Section: 03
 * Name: Dylan Northrup
 * Professor: Prof. Shaw
 * Assignment #: Lab 17
 */

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimpleCalculatorActivity extends Activity {
  public final int DONE = 0;        // The value of no operator
  public final int PLUS = 1;        // The value of the plus operator
  public final int MINUS = 2;       // The value of the minus operator
  public final int MULTIPLY = 3;    // The value of the multiply operator
  public final int DIVIDE = 4;      // The value of the divide operator

  private static TextView outputTxt = null;
  private static Button button1 = null;
  private static Button button2 = null;
  private static Button button3 = null;
  private static Button button4 = null;
  private static Button button5 = null;
  private static Button button6 = null;
  private static Button button7 = null;
  private static Button button8 = null;
  private static Button button9 = null;
  private static Button button0 = null;
  private static Button buttonPlus = null;
  private static Button buttonMinus = null;
  private static Button buttonDivide = null;
  private static Button buttonMultiply = null;
  private static Button buttonEquals = null;
  private static Button buttonClear = null;

  private static View mainView;

  private double input1, input2, input3;
  private int operator = 0;
  
  private boolean DEBUG = true;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_calculator);

    mainView = findViewById(R.id.mainView);
    outputTxt = (TextView) findViewById(R.id.txtOutput);

    //buttonName.setBackgroundResource(R.drawable.imageName);

    button1 = (Button) findViewById(R.id.button1);
    button1.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToOutput(getString(R.string.btnStr1));
      }
    });

    button2 = (Button) findViewById(R.id.button2);
    button2.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToOutput(getString(R.string.btnStr2));
      }
    });

    button3 = (Button) findViewById(R.id.button3);
    button3.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToOutput(getString(R.string.btnStr3));
      }
    });

    button4 = (Button) findViewById(R.id.button4);
    button4.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToOutput(getString(R.string.btnStr4));
      }
    });

    button5 = (Button) findViewById(R.id.button5);
    button5.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToOutput(getString(R.string.btnStr5));
      }
    });

    button6 = (Button) findViewById(R.id.button6);
    button6.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToOutput(getString(R.string.btnStr6));
      }
    });

    button7 = (Button) findViewById(R.id.button7);
    button7.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToOutput(getString(R.string.btnStr7));
      }
    });

    button8 = (Button) findViewById(R.id.button8);
    button8.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToOutput(getString(R.string.btnStr8));
      }
    });

    button9 = (Button) findViewById(R.id.button9);
    button9.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToOutput(getString(R.string.btnStr9));
      }
    });

    button0 = (Button) findViewById(R.id.button0);
    button0.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToOutput(getString(R.string.btnStr0));
      }
    });

    buttonClear = (Button) findViewById(R.id.buttonClear);
    buttonClear.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        outputTxt.setText("");
        operator = DONE;
        input1 = 0;
      }
    });

    buttonPlus = (Button) findViewById(R.id.buttonPlus);
    buttonPlus.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        getInput1();
        outputTxt.setText("");
        operator = PLUS;
      }
    });

    buttonMinus = (Button) findViewById(R.id.buttonMinus);
    buttonMinus.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        String outputstr = outputTxt.getText().toString();
        if (outputstr.equals(""))
          outputTxt.setText(getString(R.string.btnStrMinus));
        else if (operator >= DONE &&
            outputstr.equals(getString(R.string.btnStrMinus)))
          outputTxt.setText("");
        else {
          getInput1();
          outputTxt.setText("");
          operator = MINUS;
        }
      }
    });

    buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
    buttonMultiply.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        getInput1();
        outputTxt.setText("");
        operator = MULTIPLY;
      }
    });

    buttonDivide = (Button) findViewById(R.id.buttonDivide);
    buttonDivide.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        getInput1();
        outputTxt.setText("");
        operator = DIVIDE;
      }
    });

    buttonEquals = (Button) findViewById(R.id.buttonEquals);
    buttonEquals.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if(operator > 0) {
          input3 = getOutputDbl();
        }
        String outputstr = "" + getResult();
        if (outputstr.endsWith(".0"))
          outputstr = outputstr.substring(0,outputstr.length()-2);
        outputTxt.setText(outputstr);
        operator = -Math.abs(operator);

        /*
                if (operator <= DONE)
                    result = getOutputDbl();
                else
                    result = getResult();
                String outputstr = "" + result;
                if (outputstr.endsWith(".0"))
                    outputstr = outputstr.substring(0,outputstr.length()-2);
                outputTxt.setText(outputstr);
                operator = -1;
         */
      }
    });
  }

  private void addToOutput(String appendStr)
  {
    checkOp();
    outputTxt.setText(outputTxt.getText() + appendStr);
  }

  private double getOutputDbl() {
    String outputstr = outputTxt.getText().toString();
    return (outputstr.equals("")) ? 0 : Double.parseDouble(outputstr);
  }

  private void checkOp() {
    if (operator < 0) {
      outputTxt.setText("");
      operator = 0;
    }
  }

  private void pdebug(String msg) {
    if(DEBUG) {
      System.out.println("DEBUG: " + msg);
    }
  }
  
  private double getResult() {
    double result;
    double outDbl = getOutputDbl();
    if(operator < 0) {
      input1 = outDbl;
      input2 = input3;
    } else {
      input2 = outDbl;
    }

    pdebug("input1 is " + input1 + "; input2 is " + input2 + "; and operator is " + Math.abs(operator));
    switch(Math.abs(operator)) {
    case PLUS: result = input1 + input2; break;
    case MINUS: result = input1 - input2; break;
    case MULTIPLY: result = input1 * input2; break;
    case DIVIDE: result = input1 / input2; break;
    default: result = input2;
    }
    return result;
  }

  private void getInput1() {
    if (operator > DONE)
      input1 = getResult();
    else
      input1 = getOutputDbl();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.calculator, menu);
    return true;
  }

}