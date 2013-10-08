package com.example.androidhappyface;
//******************************************************************
//  Android version of an animated Snowman view
//******************************************************************

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;

public class SnowmanAnimatedView extends View {
   private final int DELAY = 250;   // Delay between movements
   private final int XMID = 240;
   private final int YTOP = 200;
   private Paint paint = new Paint();
   private RectF oval = new RectF();
   private Timer timer;             // Timer for animation

   private int waveCnt = 0;         // Counts wave positions
   private int x1 = XMID-100, y1 = YTOP+60;
   private int x2 = XMID+115, y2 = YTOP+100;

   public SnowmanAnimatedView(Context context) {
      super(context);

      timer = new Timer();    // Instiantiate the timer
      timer.schedule(new TimerTask() {          
         @Override
         public void run() {
            TimerMethod();
         }
      }, 0, DELAY);
   }
    
   private void TimerMethod()
   {
      // We call the method that will work with the UI
      // through the post method.
      this.post(Timer_Tick);
   }

   private Runnable Timer_Tick = new Runnable() {
      @Override
      public void run() {
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
   };
   
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

      paint.setColor(Color.WHITE);
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

      paint.setStyle(Style.FILL);
      paint.setTextSize(24);
      canvas.drawText("Frosty Dancing", 20, YTOP+400, paint);   // draws "Frosty"
   }
}
