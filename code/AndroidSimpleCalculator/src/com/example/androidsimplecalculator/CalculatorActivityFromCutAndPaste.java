//package com.example.androidsimplecalculator;
////******************************************************************
////  The AndroidCalculator App Activity.
////******************************************************************
//
//import android.os.Bundle;
//import android.app.Activity;
//import android.view.Menu;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//public class CalculatorActivityFromCutAndPaste extends Activity {
//
//    private static TextView outputTxt = null;
//    private static TextView headerTxt = null;
//    private static String hdrMessage = null;
//    private static Button button1 = null;
//    private static Button button2 = null;
//    private static Button button3 = null;
//    private static Button button4 = null;
//    private static Button button5 = null;
//    private static Button button6 = null;
//    private static Button button7 = null;
//    private static Button button8 = null;
//    private static Button button9 = null;
//    private static Button button0 = null;
//    private static Button buttonDecimal = null;
//    private static Button buttonPlus = null;
//    private static Button buttonMinus = null;
//    private static Button buttonDivide = null;
//    private static Button buttonMultiply = null;
//    private static Button buttonEquals = null;
//    private static Button buttonOpenPar = null;
//    private static Button buttonClosePar = null;
//    private static Button buttonBack = null;
//    private static Button buttonClear = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calculator);
//
//        // Load the Output and Header TextViews
//        outputTxt = (TextView) findViewById(R.id.txtOutput);
//        headerTxt = (TextView) findViewById(R.id.txtHeader);
//        
//        // Load the default header message
//        hdrMessage = getString(R.string.hdrStrMessage);
//        
//        /************* Loading Buttons  **************/
//        button1 = (Button) findViewById(R.id.button1);
//        button1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStr1));
//            }
//        });
//        
//        button2 = (Button) findViewById(R.id.button2);
//        button2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStr2));
//            }
//        });
//        
//        button3 = (Button) findViewById(R.id.button3);
//        button3.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStr3));
//            }
//        });
//        
//        button4 = (Button) findViewById(R.id.button4);
//        button4.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStr4));
//            }
//        });
//        
//        button5 = (Button) findViewById(R.id.button5);
//        button5.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStr5));
//            }
//        });
//        
//        button6 = (Button) findViewById(R.id.button6);
//        button6.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStr6));
//            }
//        });
//        
//        button7 = (Button) findViewById(R.id.button7);
//        button7.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStr7));
//            }
//        });
//        
//        button8 = (Button) findViewById(R.id.button8);
//        button8.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStr8));
//            }
//        });
//        
//        button9 = (Button) findViewById(R.id.button9);
//        button9.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStr9));
//            }
//        });
//        
//        button0 = (Button) findViewById(R.id.button0);
//        button0.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStr0));
//            }
//        });
//        
//        buttonDecimal = (Button) findViewById(R.id.buttonDecimal);
//        buttonDecimal.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStrDecimal));
//            }
//        });
//        
//        buttonOpenPar = (Button) findViewById(R.id.buttonOpenPar);
//        buttonOpenPar.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStrOpenPar));
//            }
//        });
//
//        buttonClosePar = (Button) findViewById(R.id.buttonClosePar);
//        buttonClosePar.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStrClosePar));
//            }
//        });
//
//        buttonClear = (Button) findViewById(R.id.buttonClear);
//        buttonClear.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                outputTxt.setText("");
//            }
//        });
//
//        buttonBack = (Button) findViewById(R.id.buttonBack);
//        buttonBack.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                ;
//            }
//        });
//
//        buttonPlus = (Button) findViewById(R.id.buttonPlus);
//        buttonPlus.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStrPlus));
//            }
//        });
//
//        buttonMinus = (Button) findViewById(R.id.buttonMinus);
//        buttonMinus.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStrMinus));
//            }
//        });
//
//        buttonDivide = (Button) findViewById(R.id.buttonDivide);
//        buttonDivide.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStrDivide));
//            }
//        });
//
//        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
//        buttonMultiply.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                addToOutput(getString(R.string.btnStrMultiply));
//            }
//        });
//
//        buttonEquals = (Button) findViewById(R.id.buttonEquals);
//        buttonEquals.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                ;
//            }
//        });
//    }
//    
//    // Adds the given text to the text in the output label box
//    private void addToOutput(String appendStr)
//    {
//        outputTxt.setText(outputTxt.getText() + appendStr);
//    }
//
//    // Handles the menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.calculator, menu);
//        return true;
//    }
//
//}
