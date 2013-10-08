package com.dylannorthrup.snowman;
//******************************************************************
//  Android animated Snowman and car controlled by four gesture
//******************************************************************

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SnowmanFourGesturesView extends View {
  private final int DELAY1 = 250;    // Delay between waves
  private final int DELAY2 = 40;     // Delay in car movements
  private final int XMID = 240;
  private final int YTOP = 200;
  private Paint paint = new Paint();
  private RectF oval = new RectF();
  private Timer timer1;              // Timer for wave animation
  private Timer timer2;              // Timer for car animation

  private int waveCnt = 0;           // Counts wave positions
  private boolean waveOn = false;    // Flag turns waving on or off 
  private int x1 = XMID-100, y1 = YTOP+60;
  private int x2 = XMID+115, y2 = YTOP+100;
  private int snowManColor = Color.WHITE;

  private int carX = 0, carY = 450;  // Car X and Y coordinates
  private int carXOff = 10;          // Car horizontal offset
  private int carYOff = 50;          // Car vertical offset
  private boolean carGoRight = true; // Car direction flag
  private boolean carOn = false;     // Flag turns Car on or off 

  private int carWidth;              // Width of car
  @SuppressWarnings("unused")
  private int scrWidth, scrHeight;   // Width and Height of screen

  private Bitmap carRight, carLeft;  // Right and left car images

  private GestureDetector gestureDetector;  // detects gestures

  public SnowmanFourGesturesView(Context context) {
    super(context);

    // creating new gesture detector
    gestureDetector = new GestureDetector(context, new GestureListener());

    Resources res = context.getResources();
    carRight = BitmapFactory.decodeResource(res, R.drawable.carright);
    carLeft = BitmapFactory.decodeResource(res, R.drawable.carleft);
    carWidth = carLeft.getWidth();     // Get the car width

    timer1 = new Timer();    // Instiantiate the wave timer
    timer1.schedule(new TimerTask() {          
      @Override
      public void run() {
        TimerMethod1();
      }
    }, 0, DELAY1);

    timer2 = new Timer();    // Instiantiate the car timer
    timer2.schedule(new TimerTask() {          
      @Override
      public void run() {
        TimerMethod2();
      }
    }, 0, DELAY2);
  }

  private void TimerMethod1()
  {
    // We call the method that will work with the UI
    // through the post method.
    this.post(Timer1_Tick);
  }

  private Runnable Timer1_Tick = new Runnable() {
    @Override
    public void run() {
      if (waveOn) {  // only wave when waveOn flag is true
        if (++waveCnt > 3)  // After 4 start back at 0
          waveCnt = 0;
        switch(waveCnt) {   // Set new arm pos for each count
        case 0:
          x1 = XMID-100; y1 = YTOP+60;
          x2 = XMID+115; y2 = YTOP+100;
          break;
        case 1:
          x1 = XMID-107; y1 = YTOP+80;
          x2 = XMID+107; y2 = YTOP+80;
          break;
        case 2:
          x1 = XMID-115; y1 = YTOP+100;
          x2 = XMID+100; y2 = YTOP+60;
          break;
        case 3:
          x1 = XMID-107; y1 = YTOP+80;
          x2 = XMID+107; y2 = YTOP+80;
          break;
        }
        invalidate();
      }
    }
  };

  private void TimerMethod2()
  {
    // We call the method that will work with the UI
    // through the post method.
    this.post(Timer2_Tick);
  }

  private Runnable Timer2_Tick = new Runnable() {
    @Override
    public void run() {
      if (carOn) {  // only move when carOn flag is true
        if (carGoRight) {  // if go right, add to car pos
          carX += carXOff;
          // check for boundary
          if (carX > scrWidth && scrWidth > carWidth) {
            carY -= carYOff;      // lift car up slightly 
            carGoRight = false;   // turn car around
          } 
        } else {  // if not go right, subtract from car pos
          carX -= carXOff;
          if (carX < -carWidth) {  // check for boundary
            carY += carYOff;      // drop car down slightly
            carGoRight = true;    // turn car around
          }
        }
        invalidate();
      }
    }
  };

  // Get's the screen size
  @Override
  protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {
    super.onSizeChanged(xNew, yNew, xOld, yOld);
    scrWidth = xNew;
    scrHeight = yNew;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.drawColor(Color.CYAN);   // background color
    paint.setStrokeWidth(0);
    paint.setStyle(Style.FILL);

    paint.setColor(Color.BLUE);
    canvas.drawRect(0, 460, 480, 550, paint);   // ground

    paint.setColor(Color.YELLOW);
    oval.set(-80, -80, 80, 80);
    canvas.drawOval(oval, paint);   // sun

    if (!carGoRight)
      canvas.drawBitmap(carLeft, carX, carY, paint);

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

    if (carGoRight)
      canvas.drawBitmap(carRight, carX, carY, paint);

    paint.setStyle(Style.FILL);
    paint.setTextSize(24);
    canvas.drawText("Frosty Waving at the Car", 20, YTOP+400, paint);   // draws "Frosty"
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
      waveOn = !waveOn;
      return true;
    }

    // event when double tap occurs
    @Override
    public boolean onDoubleTap(MotionEvent e) {
      carOn = !carOn ;
      return true;
    }

    // event when long press occurs
    @Override
    public void onLongPress(MotionEvent e) {
      // random color values from 25 to 225
      int red = (int) (Math.random() * 200 + 26);
      int green = (int) (Math.random() * 200 + 26);
      int blue = (int) (Math.random() * 200 + 26);
      snowManColor = Color.rgb(red,green,blue);
      invalidate();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
      snowManColor = Color.WHITE;
      invalidate();
      return true;
    }  
  }
}
