package com.dylannorthrup.snowman;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
  //  private SnowmanAnimatedView snowman;     // drawing View
  //  private SnowmanOneGestureView gestureSnowman;  // Gesture view
  //  private SnowmanFourGesturesView fourGestureSnowman; // 4 gestures view
  //  private SnowmanGestureWithToastView toastSnowman;   // Gestures view with toast
  //  private AndroidDoveFlockView doveView;
  private SnowmanAndMoreView snowmanView; // Homework view

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_main);
    //    snowman = new SnowmanAminatedView(this);
    //    setContentView(snowman);
    //    gestureSnowman = new SnowmanOneGestureView(this);
    //    setContentView(gestureSnowman);
    //    fourGestureSnowman = new SnowmanFourGesturesView(this);
    //    setContentView(fourGestureSnowman);
    //    toastSnowman = new SnowmanGestureWithToastView(this);
    //    setContentView(toastSnowman);
    //    doveView = new AndroidDoveFlockView(this);
    //    setContentView(doveView);
    //    doveView.start();
    snowmanView = new SnowmanAndMoreView(this);
    setContentView(snowmanView);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

}
