package com.dylannorthrup.snowman;

/*
 * Course: CS 2302
 * Section: 2
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Homework #4
 */

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SnowmanAndMoreView extends View {
  private final int DELAY = 40;      // Delay in car movements
  private final int XMID = 240;
  private int YTOP = 500;
  private final int STARTRADIUS = 200;          // Radius of doves from center
  private final double SIZERATIO = 0.5;         // Size ratio of doves
  private final int DOVEAMT = 5;                // Number of doves

  private Paint paint = new Paint();
  private RectF oval = new RectF();
  private Timer lrCarTimer;           // Timer for left to right car animation
  private Timer rlCarTimer;           // Timer for right to left car animation
  private Timer doveTimer;            // Timer for doves

  private int x1 = XMID-100, y1 = YTOP+60;
  private int x2 = XMID+115, y2 = YTOP+100;
  private int snowManColor = Color.WHITE;

  // Two array lists to keep track of the cars we add to the screen. Each element of the 
  // ArrayList will hold the X and Y values of the individual car
  private ArrayList<int[]> rightCars = new ArrayList<int[]>();
  private ArrayList<int[]> leftCars = new ArrayList<int[]>();
  private int initLCarX = 0, initLCarY = 400; // Right Car X and Y coordinates
  private int initRCarX = 0, initRCarY = 450;  // Car X and Y coordinates
  private int carXOff = 10;          // Car horizontal offset
  private ArrayList <AndroidDoveFlockModel> doves;   // Dove list

  private int carWidth;              // Width of car
  private int scrWidth;   // Width of screen
  private int scrHeight;  // Height of screen
  private int doveHeight = scrHeight + 50;

  private Bitmap carRight, carLeft;  // Right and left car images

  private GestureDetector gestureDetector;  // detects gestures

  //  // Extract out logic to set screen size variables
  private void setScreenSize(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    //    Point outSize = (0,0);
    //    display.getSize(outSize);
    //    scrWidth = outSize.x;
    //    scrHeight = outSize.y;
    ////  deprecated method (pre API level 13)
    scrWidth = display.getWidth();
    scrHeight = display.getHeight();
  }

  public SnowmanAndMoreView(Context context) {
    super(context);

    // Set screen size variables here so we can use them later
    setScreenSize(context);
    
    YTOP = scrHeight - 400;

    // creating new gesture detector
    gestureDetector = new GestureDetector(context, new GestureListener());

    Resources res = context.getResources();
    carRight = BitmapFactory.decodeResource(res, R.drawable.carright);
    carLeft = BitmapFactory.decodeResource(res, R.drawable.carleft);
    carWidth = carLeft.getWidth();     // Get the car width
    initRCarX = initRCarX - carWidth;   // And use that width to set where the right going cars should start off
    initLCarY = scrHeight - 100;        // Set this based on screen height
    initRCarY = initLCarY + 50;         // Just offset this from the previous calculation
    doves = new ArrayList<AndroidDoveFlockModel>(); // Init doves arrayList
    for (int i = 0; i < DOVEAMT; ++i)
      addDove(context);


    lrCarTimer = new Timer();    // Instantiate the Left to Right car timer
    lrCarTimer.schedule(new TimerTask() {          
      @Override
      public void run() {
        lrCarTimerMethod();
      }
    }, 0, DELAY);

    rlCarTimer = new Timer();    // Instantiate the Right to Left car timer
    rlCarTimer.schedule(new TimerTask() {          
      @Override
      public void run() {
        rlCarTimerMethod();
      }
    }, 0, DELAY);

    doveTimer = new Timer();    // Instantiate the dove timer
    doveTimer.schedule(new TimerTask() {          
      @Override
      public void run() {
        doveTimerMethod();
      }
    }, 0, DELAY);
  }

  // We call the method that will work with the UI through the post method
  private void lrCarTimerMethod() { this.post(lrCarTimer_Tick); }

  private Runnable lrCarTimer_Tick = new Runnable() {
    @Override
    public void run() {
      if (! leftCars.isEmpty()) {  // only wave when waveOn flag is true
        ArrayList<int[]> newCars = new ArrayList<int[]>();
        for (int[] coordinates : leftCars) {
          coordinates[0] -= carXOff;
          if (coordinates[0] < -carWidth) {  // check for boundary and delete car if it's over boundary
            coordinates[0] = -100000;
          }
          newCars.add(coordinates);
        }
        leftCars.clear();
        for (int[] coords : newCars) {
          if (coords[0] > -100000) {
            leftCars.add(coords);
          }
        }
        invalidate();
      }
    }
  };

  // We call the method that will work with the UI through the post method
  private void rlCarTimerMethod() { this.post(rlCarTimer_Tick); }

  // Do 
  private Runnable rlCarTimer_Tick = new Runnable() {
    @Override
    public void run() {
      if (! rightCars.isEmpty()) {  // only move when carOn flag is true
        ArrayList<int[]> newCars = new ArrayList<int[]>();
        for (int[] coordinates : rightCars) {
          coordinates[0] += carXOff;
          if (coordinates[0] > scrWidth) {  // check for boundary and delete car if it's over boundary
            coordinates[0] = -100000;
          }
          newCars.add(coordinates);
        }
        // Now, use the recently created arraylist to re-initialize the old arraylist
        rightCars.clear();
        for (int[] coords : newCars) {
          if (coords[0] > -100000) {
            rightCars.add(coords);
          }
        }
        invalidate();
      }
    }
  };

  // We call the method that will work with the UI through the post method
  private void doveTimerMethod() { this.post(doveTimer_Tick); }

  // Timer tick for what we do with doves
  private Runnable doveTimer_Tick = new Runnable() {
    @Override
    public void run() {
      if (doves.size() > 0) {  // only moves when there are actually doves to move
        for (AndroidDoveFlockModel dove : doves)
          dove.move(doves,scrWidth,doveHeight);
        invalidate();
      }
    }
  };

  // Method to add a dove
  private void addDove(Context context) {
    AndroidDoveFlockModel dove =
        new AndroidDoveFlockModel(context,true,0,0,0,0,SIZERATIO);

    int centerX = scrWidth / 2;
    int centerY = doveHeight / 2;

    double radius = Math.random() * STARTRADIUS;
    double angle = Math.random() * 2 * Math.PI;

    int x = (int)(radius * Math.cos(angle)) + centerX;
    if (x > scrWidth-dove.imageWidth())
      x = scrWidth-dove.imageWidth();
    if (x < 0)
      x = 0;
    int y = (int)(radius * Math.sin(angle)) + centerY;
    if (y > doveHeight-dove.imageWidth())
      y = doveHeight-dove.imageHeight();
    if (y < 0)
      y = 0;
    dove.setX(x);
    dove.setY(y);

    int speedX = 5 + (int) (Math.random() * 6);
    int speedY = 5 + (int) (Math.random() * 6);
    if (Math.random() < 0.5)
      speedX *= -1;
    if (Math.random() < 0.5)
      speedY *= -1;
    dove.setXVelocity(speedX);
    dove.setYVelocity(speedY);

    dove.setIsLeft(speedX<0);
    dove.setImageIndex((int)(Math.random() * 8));

    doves.add(dove);
  }


  // Get's the screen size
  @Override
  protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
    super.onSizeChanged(xNew, yNew, xOld, yOld);
    scrWidth = xNew;
    initLCarX = scrWidth; // Reset where the Left heading car starts from
    scrHeight = yNew;
    doveHeight = scrHeight + 50;
  }

  // Drawing method
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.drawColor(Color.CYAN);   // background color
    paint.setStrokeWidth(0);
    paint.setStyle(Style.FILL);

    paint.setColor(Color.BLUE);
    canvas.drawRect(0, doveHeight, scrWidth, scrHeight, paint);   // ground

    paint.setColor(Color.YELLOW);
    oval.set(-80, -80, 80, 80);
    canvas.drawOval(oval, paint);   // sun

    // DRAW Left facing car
    if (!leftCars.isEmpty()) {
      for(int[] coords : leftCars) {
        canvas.drawBitmap(carLeft, coords[0], coords[1], paint);
      }
    }

    // DRAW doves
    if (!doves.isEmpty()) {
      for(AndroidDoveFlockModel dove : doves) {
        dove.draw(canvas);
      }
    }

    paint.setColor(snowManColor);
    oval.set(XMID-40, YTOP, XMID-40+80, YTOP+80);
    canvas.drawOval(oval, paint);   // head

    oval.set(XMID-70, YTOP+70, XMID-70+140, YTOP+160);
    canvas.drawOval(oval, paint);   // upper torso

    oval.set(XMID-90, YTOP+150, XMID-90+180, YTOP+280);
    canvas.drawOval(oval, paint);   // lower torso

    paint.setColor(Color.BLACK);
    oval.set(XMID-20, YTOP+20, XMID-20+10, YTOP+30);
    canvas.drawOval(oval, paint);   // left eye

    oval.set(XMID+10, YTOP+20, XMID+10+10, YTOP+30);
    canvas.drawOval(oval, paint);   // right eye

    canvas.drawRect(XMID-25, YTOP-30, XMID+25, YTOP+10, paint);   // top of hat

    paint.setStrokeWidth(2);
    canvas.drawLine(XMID-45, YTOP+10, XMID+45, YTOP+10, paint);   // brim of hat

    canvas.drawLine(XMID-45, YTOP+100, x1, y1, paint);   // left arm
    canvas.drawLine(XMID+45, YTOP+100, x2, y2, paint);   // right arm

    paint.setStyle(Style.STROKE);
    oval.set(XMID-25, YTOP+45, XMID+25, YTOP+55);
    canvas.drawArc(oval, 200, -220, false, paint);   // smile - clockwise arc

    // DRAW Right facing car
    if (!rightCars.isEmpty()) {
      for(int[] coords : rightCars) {
        canvas.drawBitmap(carRight, coords[0], coords[1], paint);
      }
    }

    //    paint.setStyle(Style.FILL);
    //    paint.setTextSize(24);
    //    canvas.drawText("CS2302 Homework 4 for Dylan Northrup", 20, YTOP+400, paint);
    //    canvas.drawText(leftCars.size() + " cars driving Left and " + rightCars.size() + " cars driving Right", 20, YTOP+435, paint);
    //    if(doves.isEmpty()) {
    //      canvas.drawText("doves is empty", 20, YTOP+470, paint);
    //    } else {
    //      canvas.drawText("There are " + doves.size() + " doves in the sky", 20, YTOP+470, paint);
    //    }
  }

  // The onTouchEvent handler
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    return gestureDetector.onTouchEvent(event);
  }

  // The GestureListener handlers
  private class GestureListener extends SimpleOnGestureListener {
    @Override
    public boolean onDown(MotionEvent e) {
      return true;
    }

    // event when single tap occurs
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
      int[] newCar = new int[] {initRCarX, initRCarY};
      rightCars.add(newCar);
      return true;
    }

    // event when double tap occurs
    @Override
    public boolean onDoubleTap(MotionEvent e) {
      int[] newCar = new int[] {initLCarX, initLCarY};
      leftCars.add(newCar);
      return true;
    }

    // event when long press occurs
    // Add flock of doves
    @Override
    public void onLongPress(MotionEvent e) {
      // Only add doves if we don't have any yet
      if(doves.isEmpty()) {
        for (int i = 0; i < DOVEAMT; ++i)
          addDove(getContext());
        invalidate();
      }
    }

    // For flings, remove all of the doves
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
      for(int i = doves.size(); i > 0; --i) {
        doves.remove(0);
      }
      invalidate();
      return true;
    }  
  }
}
