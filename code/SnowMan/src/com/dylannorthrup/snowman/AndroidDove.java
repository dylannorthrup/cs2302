package com.dylannorthrup.snowman;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class AndroidDove extends BasicDove {
    public static final int IMAGEMAX = 8;      // The max image index per direction

    private static Bitmap [] doveLeft = new Bitmap[IMAGEMAX];
    private static Bitmap [] doveRight = new Bitmap[IMAGEMAX];

    private int imageWidth = 0, imageHeight = 0;
    private boolean noScale = false;

    // Fields for drawing bitmaps
    private Paint paint = new Paint();
    private Rect scaleRect = new Rect(0,0,0,0);
    
    public AndroidDove(Context context) {
        this(context,true,0,0,0,0);
    }

    public AndroidDove(Context context, boolean initIsLeft,
                       int initX, int initY, int initXOff, int initYOff)
    {
        this(context,initIsLeft,initX,initY,initXOff,initYOff,1.0);
    }
    
    public AndroidDove(Context context, boolean initIsLeft,
            int initX, int initY, int initXV, int initYV, double scale)
    {
        super(0,0,IMAGEMAX,initIsLeft,initX,initY,initXV,initYV);
      
        Resources res = context.getResources();
        for (int i = 0; i < IMAGEMAX; ++i)
        {
            if (doveLeft[i] != null)
                doveLeft[i].recycle();
            if (doveRight[i] != null)
                doveRight[i].recycle();
        }
        doveLeft[0] = BitmapFactory.decodeResource(res, R.drawable.ldove1_trans);
        doveLeft[1] = BitmapFactory.decodeResource(res, R.drawable.ldove2_trans);
        doveLeft[2] = BitmapFactory.decodeResource(res, R.drawable.ldove3_trans);
        doveLeft[3] = BitmapFactory.decodeResource(res, R.drawable.ldove4_trans);
        doveLeft[4] = BitmapFactory.decodeResource(res, R.drawable.ldove5_trans);
        doveLeft[5] = BitmapFactory.decodeResource(res, R.drawable.ldove6_trans);
        doveLeft[6] = BitmapFactory.decodeResource(res, R.drawable.ldove7_trans);
        doveLeft[7] = BitmapFactory.decodeResource(res, R.drawable.ldove8_trans);

        doveRight[0] = BitmapFactory.decodeResource(res, R.drawable.rdove1_trans);
        doveRight[1] = BitmapFactory.decodeResource(res, R.drawable.rdove2_trans);
        doveRight[2] = BitmapFactory.decodeResource(res, R.drawable.rdove3_trans);
        doveRight[3] = BitmapFactory.decodeResource(res, R.drawable.rdove4_trans);
        doveRight[4] = BitmapFactory.decodeResource(res, R.drawable.rdove5_trans);
        doveRight[5] = BitmapFactory.decodeResource(res, R.drawable.rdove6_trans);
        doveRight[6] = BitmapFactory.decodeResource(res, R.drawable.rdove7_trans);
        doveRight[7] = BitmapFactory.decodeResource(res, R.drawable.rdove8_trans);
        
        if (scale > 0.9 && scale < 1.1) {
            this.imageWidth = doveLeft[0].getWidth();
            this.imageHeight = doveLeft[0].getHeight();
            this.noScale = true;
        } else {
            this.imageWidth = (int) (doveLeft[0].getWidth() * scale);
            this.imageHeight = (int) (doveLeft[0].getHeight() * scale);
        }
    }

     /**
      * Gets the width
      * 
      * @return Returns the width
      */
    public int imageWidth()
    {
       return this.imageWidth;
    }

     /**
      * Gets the height
      * 
      * @return Returns the height
      */
    public int imageHeight()
    {
       return this.imageHeight;
    }

     /**
      * Gets the current image
      * 
      * @return Returns the current image
      */
    public Bitmap getCurrentImage()
    {
       return getIsLeft() ? doveLeft[getImageIndex()] :
                            doveRight[getImageIndex()];
    }

     /**
      * Draws the dove on the given page
      * 
      * @param page is the graphics page
      */
    public void draw(Canvas canvas)
    {
       if (this.noScale)
          canvas.drawBitmap(getCurrentImage(),(int)getX(),(int)getY(),paint);
       else {
          scaleRect.set((int)getX(), (int)getY(),
                        (int)getX()+imageWidth,(int)getY()+imageHeight);
          canvas.drawBitmap(getCurrentImage(),null,scaleRect,paint);
       }
    }

     /**
      * setdoves does nothing because we have a dove array
      */
    protected void setdoves() {}
}