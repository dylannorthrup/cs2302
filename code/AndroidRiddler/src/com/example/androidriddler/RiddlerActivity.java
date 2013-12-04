package com.example.androidriddler;

/*
 * Course: CS 2302
 * Section: 03
 * Name: Dylan Northrup
 * Professor: Prof. Shaw
 * Assignment #: Lab 18
 */

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RiddlerActivity extends Activity {

  String[] riddle = new String[3];
  String[] answer = new String[3];
  TextView riddleTxt;
  String notRight;
  EditText answerTxt;

  RelativeLayout mainView;
  int colorNum = 0;
  boolean correct = false;
  int riddleNum = 0;

  Button nextButton;
  Button answerButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_riddler);

    mainView = (RelativeLayout) findViewById(R.id.mainView);
    mainView.setBackgroundColor(Color.CYAN);

    riddle[0] = getString(R.string.strRiddle1);
    riddle[1] = getString(R.string.strRiddle2);
    riddle[2] = getString(R.string.strRiddle3);

    answer[0] = getString(R.string.strAnswer1);
    answer[1] = getString(R.string.strAnswer2);
    answer[2] = getString(R.string.strAnswer3);

    riddleTxt = (TextView) findViewById(R.id.riddleTxt);
    riddleTxt.setText(riddle[0]);

    answerTxt = (EditText) findViewById(R.id.answerTxt);

    notRight = getString(R.string.strNotRight);

    answerButton = (Button) findViewById(R.id.answerBtn);
    answerButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        String response = answerTxt.getText().toString();
        if(response.toLowerCase().indexOf(answer[riddleNum].toLowerCase()) > -1) {
          answerTxt.setBackgroundColor(Color.GREEN);
          correct = true;
        } else {
          Toast.makeText(getApplicationContext(), notRight, Toast.LENGTH_LONG).show();
        }
      }
    });

    nextButton = (Button) findViewById(R.id.nextBtn);
    nextButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (correct) {
          if(colorNum == 0) {
            mainView.setBackgroundColor(Color.GREEN);
            colorNum = 1;
          } else {
            mainView.setBackgroundColor(Color.CYAN);
            colorNum = 0;
          }
          correct = false;
          if(++riddleNum >= 3) {
            riddleNum = 0;
          }
          riddleTxt.setText(riddle[riddleNum]);
          answerTxt.setText("");
          answerTxt.setBackgroundColor(0xFFCCCCCC); // Need to set Transparency bit explicitly
        } else {
          Toast.makeText(getApplicationContext(), notRight, Toast.LENGTH_LONG).show();
        } 
      }
    });

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.riddler, menu);
    return true;
  }

}
