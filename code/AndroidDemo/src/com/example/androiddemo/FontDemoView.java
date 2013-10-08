package com.example.androiddemo;
//******************************************************************
//  Android Font Demo view
//******************************************************************

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.view.View;

public class FontDemoView extends View {
   private int baseX = 20;
   private int baseY = 150;
   private Paint paint = new Paint();
    
   public FontDemoView(Context context) {
      super(context);
   }
        
   @Override
   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      
      paint.setStyle(Style.FILL);
      paint.setColor(Color.BLUE);
      paint.setTextSize(24);
      
      paint.setTypeface(Typeface.create(Typeface.SERIF,Typeface.NORMAL));
      canvas.drawText("SERIF:  Normal font style", baseX, baseY, paint);
      baseY += 30;
      paint.setTypeface(Typeface.create(Typeface.SERIF,Typeface.BOLD));
      canvas.drawText("SERIF:  Bold font style", baseX, baseY, paint);
      baseY += 30;
      paint.setTypeface(Typeface.create(Typeface.SERIF,Typeface.ITALIC));
      canvas.drawText("SERIF:  Italic font style", baseX, baseY, paint);
      baseY += 30;
      paint.setTypeface(Typeface.create(Typeface.SERIF,Typeface.BOLD_ITALIC));
      canvas.drawText("SERIF:  Bold Italic font style", baseX, baseY, paint);

      paint.setColor(Color.rgb(75,0,130));   // Indigo
      baseY += 70;
      paint.setTypeface(Typeface.create(Typeface.SANS_SERIF,Typeface.NORMAL));
      canvas.drawText("SANS_SERIF:  Normal font style", baseX, baseY, paint);
      baseY += 30;
      paint.setTypeface(Typeface.create(Typeface.SANS_SERIF,Typeface.BOLD));
      canvas.drawText("SANS_SERIF:  Bold font style", baseX, baseY, paint);
      baseY += 30;
      paint.setTypeface(Typeface.create(Typeface.SANS_SERIF,Typeface.ITALIC));
      canvas.drawText("SANS_SERIF:  Italic font style", baseX, baseY, paint);
      baseY += 30;
      paint.setTypeface(Typeface.create(Typeface.SANS_SERIF,Typeface.BOLD_ITALIC));
      canvas.drawText("SANS_SERIF:  Bold Italic font style", baseX, baseY, paint);

      paint.setColor(Color.rgb(0,128,0));   // Darker Green
      baseY += 70;
      paint.setTypeface(Typeface.MONOSPACE);
      canvas.drawText("MONOSPACE: Fixed size style", baseX, baseY, paint);
      baseY += 30;
      canvas.drawText("           (no bold or italic)", baseX, baseY, paint);
      
      // Doing the heading last
      baseX = 110;
      baseY = 80;
      paint.setTextSize(36);
      paint.setColor(Color.rgb(255,127,80));   // Coral
      paint.setTypeface(Typeface.create("SERIF",Typeface.BOLD_ITALIC));
      canvas.drawText("Font DEMO", baseX, baseY, paint);
      canvas.drawLine(baseX-5, baseY+2, baseX+205, baseY+2, paint);
      canvas.drawLine(baseX-5, baseY+3, baseX+205, baseY+3, paint);
   }
}
