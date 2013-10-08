package com.example.androidhappyface;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HappyFaceActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    setContentView(new HappyFaceView(this));
    setContentView(new SmilingFaceView(this));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.happy_face, menu);
    return true;
  }

}
