package com.example.androidhappyface;
//******************************************************************
//  Android version of the HappyFace view
//******************************************************************

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;

public class HappyFaceView extends View {
   private final int BASEX = 130;
   private final int BASEY = 150;
   private Paint paint = new Paint();
   private RectF oval = new RectF();
    
   public HappyFaceView(Context context) {
      super(context);
   }
        
   @Override
   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      
      paint.setColor(Color.rgb(173,255,47));   // Custom Face color
      paint.setStyle(Style.FILL);
      oval.set(BASEX, BASEY, BASEX+200, BASEY+200);
      canvas.drawOval(oval, paint);    // Face

      paint.setColor(Color.BLUE);
      paint.setStyle(Style.STROKE);
      canvas.drawOval(oval, paint);    // Face outline

      paint.setStyle(Style.FILL);
      oval.set(BASEX+55, BASEY+50, BASEX+65, BASEY+70);
      canvas.drawOval(oval, paint);    // Eye 1
      oval.set(BASEX+130, BASEY+50, BASEX+140, BASEY+70);
      canvas.drawOval(oval, paint);    // Eye 2

      paint.setStyle(Style.STROKE);     // Smile
      oval.set(BASEX+50, BASEY+125, BASEX+150, BASEY+175);
      canvas.drawArc(oval, 0, 180, false, paint); 
      
      canvas.drawText("Rock on!", BASEX+200, BASEY+150, paint);
   }
}
