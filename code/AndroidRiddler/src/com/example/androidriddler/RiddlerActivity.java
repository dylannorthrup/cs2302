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
import android.view.Menu;
import android.widget.TextView;

public class RiddlerActivity extends Activity {
	
	String[] riddle = new String[3];
	String[] answer = new String[3];
	TextView riddleTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_riddler);
		
		riddle[0] = getString(R.string.strRiddle1);
		riddle[1] = getString(R.string.strRiddle2);
		riddle[2] = getString(R.string.strRiddle3);
		
		answer[0] = getString(R.string.strAnswer1);
		answer[1] = getString(R.string.strAnswer2);
		answer[2] = getString(R.string.strAnswer3);
		
		riddleTxt = (TextView) findViewById(R.id.riddleTxt);
		riddleTxt.setText(riddle[0]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.riddler, menu);
		return true;
	}

}
