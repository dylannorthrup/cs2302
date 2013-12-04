package com.example.androidbettercalculator;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BetterCalculatorActivity extends Activity {

  private boolean DEBUG = true;
  private TextView inputView = null;
  private TextView outputView = null;
  private Button button1 = null;
  private Button button2 = null;
  private Button button3 = null;
  private Button button4 = null;
  private Button button5 = null;
  private Button button6 = null;
  private Button button7 = null;
  private Button button8 = null;
  private Button button9 = null;
  private Button button0 = null;
  private Button buttonPlus = null;
  private Button buttonMinus = null;
  private Button buttonMultiply = null;
  private Button buttonDivide = null;
  private Button buttonRoot = null;
  private Button buttonPower = null;
  private Button buttonBackUp = null;
  private Button buttonOpenPar = null;
  private Button buttonClosePar = null;
  private Button buttonClear = null;
  private Button buttonEquals = null;
  private Button buttonDot = null;

  private String str1 = "";
  private String str2 = "";
  private String str3 = "";
  private String str4 = "";
  private String str5 = "";
  private String str6 = "";
  private String str7 = "";
  private String str8 = "";
  private String str9 = "";
  private String str0 = "";
  private String strPlus = "";
  private String strMinus = "";
  private String strMultiply = "";
  private String strDivide = "";
  private String strRoot = "";
  private String strPower = "";
//  private String strBackUp = "";
  private String strOpenPar = "";
  private String strClosePar = "";
//  private String strClear = "";
//  private String strEquals = "";
  private String strDot = "";

  // The onCreate method
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_better_calculator);

    str1 = getString(R.string.str1);
    str2 = getString(R.string.str2);
    str3 = getString(R.string.str3);
    str4 = getString(R.string.str4);
    str5 = getString(R.string.str5);
    str6 = getString(R.string.str6);
    str7 = getString(R.string.str7);
    str8 = getString(R.string.str8);
    str9 = getString(R.string.str9);
    str0 = getString(R.string.str0);
    strPlus = getString(R.string.strPlus);
    strMinus = getString(R.string.strMinus);
    strMultiply = getString(R.string.strMultiply);
    strDivide = getString(R.string.strDivide);
    strRoot = getString(R.string.strRoot);;
    strPower = getString(R.string.strPower);
//    strBackUp = getString(R.string.strBackUp);
    strOpenPar = getString(R.string.strOpenPar);
    strClosePar = getString(R.string.strClosePar);
//    strClear = getString(R.string.strClear);
//    strEquals = getString(R.string.strEquals);
    strDot = getString(R.string.strDot);

    inputView = (TextView) findViewById(R.id.inputView);
    outputView = (TextView) findViewById(R.id.outputView);

    button1 = (Button) findViewById(R.id.button01);
    button1.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        pdebug("button01 pushed");
        addToInput(str1);
      }
    });
    button2 = (Button) findViewById(R.id.button02);
    button2.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(str2);
      }
    });
    button3 = (Button) findViewById(R.id.button03);
    button3.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(str3);
      }
    });
    button4 = (Button) findViewById(R.id.button04);
    button4.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(str4);
      }
    });
    button5 = (Button) findViewById(R.id.button05);
    button5.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(str5);
      }
    });
    button6 = (Button) findViewById(R.id.button06);
    button6.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(str6);
      }
    });
    button7 = (Button) findViewById(R.id.button07);
    button7.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(str7);
      }
    });
    button8 = (Button) findViewById(R.id.button08);
    button8.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(str8);
      }
    });
    button9 = (Button) findViewById(R.id.button09);
    button9.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(str9);
      }
    });
    button0 = (Button) findViewById(R.id.button00);
    button0.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(str0);
      }
    });
    buttonPlus = (Button) findViewById(R.id.buttonPlus);
    buttonPlus.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(strPlus);
      }
    });
    buttonMinus = (Button) findViewById(R.id.buttonMinus);
    buttonMinus.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(strMinus);
      }
    });
    buttonDivide = (Button) findViewById(R.id.buttonDivide);
    buttonDivide.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(strDivide);
      }
    });
    buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
    buttonMultiply.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(strMultiply);
      }
    });
    buttonRoot = (Button) findViewById(R.id.buttonRoot);
    buttonRoot.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(strRoot);
      }
    });
    buttonPower = (Button) findViewById(R.id.buttonPower);
    buttonPower.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(strPower);
      }
    });
    buttonBackUp = (Button) findViewById(R.id.buttonBackUp);
    buttonBackUp.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        backUpInput();
      }
    });
    buttonOpenPar = (Button) findViewById(R.id.buttonOpenPar);
    buttonOpenPar.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(strOpenPar);
      }
    });
    buttonClosePar = (Button) findViewById(R.id.buttonClosePar);
    buttonClosePar.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(strClosePar);
      }
    });
    buttonDot = (Button) findViewById(R.id.buttonDot);
    buttonDot.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        addToInput(strDot);
      }
    });
    buttonClear = (Button) findViewById(R.id.buttonClear);
    buttonClear.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        inputView.setText("");
      }
    });
    buttonEquals = (Button) findViewById(R.id.buttonEquals);
    buttonEquals.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        String txt = inputView.getText().toString();
        //                AndroidEvalExpression ev = new AndroidEvalExpression(getApplicationContext(),txt);
        EvaluateExpression ev = new EvaluateExpression(getApplicationContext(),txt);
        outputView.setText(ev.getAnswer());
      }
    });
  }

//  private void addToOutput(String appendStr) {
//    outputTxt.setText(outputTxt.getText() + appendStr);
//  }

  // Adds text to the input TextView adding spacing where appropriate
  public void addToInput(String str) {
    String txt = inputView.getText().toString();
    pdebug("inputView text: '" + txt + "'");
//    int len = txt.length();
//    if (len > 0 && txt.charAt(len-1) != ' ' && txt.charAt(len-1) != '^' && txt.charAt(len-1) != '(' && txt.charAt(len-1) != 'Ã')
//      if (str.length() > 0 && str.charAt(0) != ')' && (!digitOp1(str.charAt(0)) || !digitOp1(txt.charAt(len-1))))
//        if (txt.charAt(len-1) != '-' || (len > 2 && txt.charAt(len-2) == ' ' && digitOp2(txt.charAt(len-3))))
//          txt += " ";
    pdebug("new inputView text: '" + txt + str + "'");
    inputView.setText(txt + str);
  }

  // Removes the last thing added to the input TextView
  public void backUpInput() {
    String txt = inputView.getText().toString().trim();
    if (txt.length() > 0)
      inputView.setText(txt.substring(0,txt.length()-1).trim());
  }

  // Test for characters that are digits or the power operator
  public boolean digitOp1(char ch) {
    return (Character.isDigit(ch) || ch == '.' || ch == '^');
  }

  // Test for characters that are digits or the close parenthesis
  public boolean digitOp2(char ch) {
    return (Character.isDigit(ch) || ch == '.' || ch == ')');
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.better_calculator, menu);
    return true;
  }

  public void pdebug(String msg) {
    if(DEBUG) {
      System.out.println("DEBUG: " + msg);
      Log.d("debug", "DEBUG: " + msg);
    }
  }

}