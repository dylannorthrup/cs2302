package com.example.androiddemo;
//******************************************************************
//  Android Alert Demo view
//******************************************************************

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.view.View;

public class AlertDemoView extends View {
   private Paint paint = new Paint();
   private String message = null;
   private boolean hasAlert = true;

   public AlertDemoView(Context context) {
      super(context);
   }
        
   @Override
   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      
      canvas.drawColor(Color.YELLOW);   // background color

      int baseX = 110;
      int baseY = 60;

      paint.setStyle(Style.FILL);
      paint.setColor(Color.BLUE);
      paint.setTextSize(36);
      paint.setTypeface(Typeface.create(Typeface.SERIF,Typeface.BOLD_ITALIC));
      canvas.drawText("Alert DEMO", baseX, baseY, paint);
      canvas.drawLine(baseX-5, baseY+2, baseX+225, baseY+2, paint);
      canvas.drawLine(baseX-5, baseY+3, baseX+225, baseY+3, paint);

      paint.setTextSize(24);
      baseY += 50;
      if (message != null) {
         paint.setColor(Color.rgb(0,128,0));
         paint.setTypeface(Typeface.create(Typeface.SANS_SERIF,Typeface.ITALIC));
         canvas.drawText(message, 10, baseY, paint);
         baseY += 30;
      }
      if (hasAlert) {
         paint.setColor(Color.RED);
         paint.setTypeface(Typeface.SANS_SERIF);
         canvas.drawText("Only Cancel ends this Alert",10,baseY,paint);
      }
   }

   public void setMessage(String newMessage)
   {
      message = newMessage;
      invalidate();
   }

   public void noAlert()
   {
      hasAlert = false;
      invalidate();
   }
}
