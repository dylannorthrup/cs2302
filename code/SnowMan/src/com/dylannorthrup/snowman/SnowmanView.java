package com.dylannorthrup.snowman;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Alan Shaw
 * Assignment #: Lab 10
 */

//******************************************************************
//  Android version of the Snowman view
//******************************************************************

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;

public class SnowmanView extends View {
  private final int XMID = 340;  // (#5)
  private final int YTOP = 200;
  private Paint paint = new Paint();
  private RectF oval = new RectF();

  // Constructor
  public SnowmanView(Context context) {
    super(context);
  }

  // Called to render View
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.drawColor(Color.CYAN);   // background color
    paint.setStrokeWidth(0);
    paint.setStyle(Style.FILL);

    paint.setColor(Color.BLUE);
    canvas.drawRect(0, 460, 480, 550, paint);   // ground

    paint.setColor(Color.YELLOW);
    oval.set(400, -80, 560, 80);    // Set sun on right side of screen (#3)
    canvas.drawOval(oval, paint);   // sun

    paint.setColor(Color.WHITE);
    oval.set(XMID-40, YTOP, XMID-40+80, YTOP+80);
    canvas.drawOval(oval, paint);   // head

    oval.set(XMID-70, YTOP+70, XMID-70+140, YTOP+160);
    canvas.drawOval(oval, paint);   // upper torso
    paint.setColor(Color.RED);
    oval.set(XMID+5, YTOP+110, XMID-5, YTOP+120); // Bottom button (#1a)
    canvas.drawOval(oval, paint);
    oval.set(XMID+5, YTOP+90, XMID-5, YTOP+100);  // Top button (#1b)
    canvas.drawOval(oval, paint);

    paint.setColor(Color.WHITE);
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

    canvas.drawLine(XMID-45, YTOP+100, XMID-100, YTOP+60, paint);   // left arm
    canvas.drawLine(XMID+45, YTOP+100, XMID+115, YTOP+100, paint);   // right arm

    paint.setStyle(Style.STROKE);
    oval.set(XMID-25, YTOP+55, XMID+25, YTOP+65);
    canvas.drawArc(oval, -200, 220, false, paint);    // smile - counter-clockwise arc (#2)

    paint.setStyle(Style.FILL);
    paint.setTextSize(24);
    canvas.drawText("Frosty", 20, YTOP+400, paint);   // draws "Frosty"
    canvas.drawText("Dylan Northrup", 20, 50, paint); // draws my name (#5)   
  }
}