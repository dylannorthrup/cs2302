package com.example.androidhappyface;
//******************************************************************
//  Android version of the SmilingFace view
//******************************************************************

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;

public class SmilingFaceView extends View {
   private final int BASEX = 100;
   private final int BASEY = 150;
   private Paint paint = new Paint();
   private RectF oval = new RectF();
    
   public SmilingFaceView(Context context) {
      super(context);
   }
        
   @Override
   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      
      paint.setColor(Color.GREEN);
      oval.set(BASEX-5*3 - 10, BASEY+20*3, BASEX+90*3 - 10, BASEY+40*3);
      canvas.drawOval(oval, paint);    // Ears
      paint.setColor(Color.CYAN);
      paint.setStyle(Style.FILL);
      oval.set(BASEX, BASEY, BASEX+80*3, BASEY+80*3);
      canvas.drawOval(oval, paint);    // Face

      paint.setColor(Color.BLACK);
      paint.setStyle(Style.STROKE);
      oval.set(BASEX+20*3, BASEY+30*3, BASEX+35*3, BASEY+37*3);
      canvas.drawOval(oval, paint);    // Eye 1
      oval.set(BASEX+45*3, BASEY+30*3, BASEX+60*3, BASEY+37*3);
      canvas.drawOval(oval, paint);    // Eye 2

      paint.setStyle(Style.FILL);
      oval.set(BASEX+25*3, BASEY+31*3, BASEX+30*3, BASEY+36*3);
      canvas.drawOval(oval, paint);    // Pupil 1
      oval.set(BASEX+50*3, BASEY+31*3, BASEX+55*3, BASEY+36*3);
      canvas.drawOval(oval, paint);    // Pupil 2

      paint.setStyle(Style.STROKE);
      oval.set(BASEX+20*3, BASEY+25*3, BASEX+35*3, BASEY+32*3);
      canvas.drawArc(oval, 0, -180, false, paint);    // Eyebrow 1
      oval.set(BASEX+45*3, BASEY+25*3, BASEX+60*3, BASEY+32*3);
      canvas.drawArc(oval, 0, -180, false, paint);    // Eyebrow 2

      paint.setStyle(Style.STROKE);
      oval.set(BASEX+35*3, BASEY+40*3, BASEX+50*3, BASEY+50*3);
      canvas.drawArc(oval, 180, -180, false, paint);    // Nose
      oval.set(BASEX+20*3, BASEY+50*3, BASEX+60*3, BASEY+65*3);
      canvas.drawArc(oval, 180, -180, false, paint);    // Mouth

      paint.setTextSize(48);
      canvas.drawText("Xander!", BASEX+30, BASEY-40, paint); 
      canvas.drawText("STOP", BASEX+50, BASEY+280, paint);  
      canvas.drawText("BEING", BASEX+50, BASEY+340, paint);  
      canvas.drawText("CUTE!", BASEX+50, BASEY+400, paint);  
   }
}
