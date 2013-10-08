package com.dylannorthrup.androidflockingdoves;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

public class AndroidDoveFlockViewExtended extends View {
   private final int DOVEAMT = 15;                // Number of doves
   private final int DELAY = 40;                  // Delay between dove moves
   private final int STARTRADIUS = 200;           // Radius of doves from center
   private final double SIZERATIO = 0.5;          // Size ratio of doves
   private final boolean STARTSAMEDIR = false;    // Flag for birds facing same dir

   private boolean showRadius = false;            // Flag for showing flock radius
   private boolean started = false;               // Flag for starting simulation
   private int scrWidth, scrHeight;
   private ArrayList <AndroidDoveFlockModel> doves;   // Dove list
   
   private static Timer myTimer;                  // Timer for simulation
   
   // Constructor that instantiates the doves and the timer
   public AndroidDoveFlockViewExtended(Context context) {
      super(context);

      doves = new ArrayList<AndroidDoveFlockModel>();
      for (int i = 0; i < DOVEAMT; ++i)
         addDove(context);
      
      myTimer = new Timer();
      myTimer.schedule(new TimerTask() {          
         @Override
         public void run() {
            TimerMethod();
         }
      }, 0, DELAY);
   }
   
   private void TimerMethod() {
      // We call the method that will work with the UI thread
      // through the post method.
      this.post(Timer_Tick);
   }

   // Timer thread
   private Runnable Timer_Tick = new Runnable() {
      @Override
      public void run() {
         if (started)  {
            for (AndroidDoveFlockModel dove : doves)
               dove.move(doves,scrWidth,scrHeight);
            invalidate();
         }
      }
   };

   // Handles drawing the GUI screen
   @Override
   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      
      canvas.drawColor(Color.YELLOW);
      for (AndroidDoveFlockModel dove : doves)
         dove.draw(canvas,showRadius);
   }

   // Handles touch events
   @Override
   public boolean onTouchEvent(MotionEvent event) {
      return super.onTouchEvent(event);
   }

   // Handles screen size change events
   @Override
   protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
      super.onSizeChanged(xNew, yNew, xOld, yOld);
      
      scrWidth = xNew;
      scrHeight = yNew;
      int doveamt = doves.size();
      doves.clear();
      for (int i = 0; i < doveamt; ++i)
         addDove(this.getContext());
   }

   // Handles adding a new dove to the simulation
   private void addDove(Context context) {
      AndroidDoveFlockModel dove =
              new AndroidDoveFlockModel(context,true,0,0,0,0,SIZERATIO);

      int centerX = scrWidth / 2;
      int centerY = scrHeight / 2;
      
      double radius = Math.random() * STARTRADIUS;
      double angle = Math.random() * 2 * Math.PI;
        
      int x = (int)(radius * Math.cos(angle)) + centerX;
      if (x > scrWidth-dove.imageWidth())
         x = scrWidth-dove.imageWidth();
      if (x < 0)
         x = 0;
      int y = (int)(radius * Math.sin(angle)) + centerY;
      if (y > scrHeight-dove.imageWidth())
         y = scrHeight-dove.imageHeight();
      if (y < 0)
         y = 0;
      dove.setX(x);
      dove.setY(y);
    
      int speedX = 5 + (int) (Math.random() * 6);
      int speedY = 5 + (int) (Math.random() * 6);
      if (!STARTSAMEDIR)  {
         if (Math.random() < 0.5)
            speedX *= -1;
         if (Math.random() < 0.5)
            speedY *= -1;
      }
      dove.setXVelocity(speedX);
      dove.setYVelocity(speedY);
      
      dove.setIsLeft(speedX<0);
      dove.setImageIndex((int)(Math.random() * 8));
      
      doves.add(dove);
   }

   // Handles removing a dove from the simulation
   public void removeDove() {
      if (doves.size() > 0)
         doves.remove(0);
   }

   // Handles starting the simulation
   public void start() {
      started = true;
      invalidate();
   }
}