package com.example.androiddemo;
//******************************************************************
//  Android Alert Demo activity
//******************************************************************

import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;

public class AlertDemoActivity extends Activity {

   private AlertDemoView alertDemoView;
    
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      alertDemoView = new AlertDemoView(this);
      setContentView(alertDemoView);

      runAlert();
   }
    
   public void runAlert()
   {
      AlertDialog.Builder qBuilder = new AlertDialog.Builder(this);
      qBuilder.setTitle("This is the Alert Title");
      qBuilder.setMessage("This is the Alert Message");
      qBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int id) {
            alertDemoView.setMessage("You pressed: 'Yes'");
            runAlert();
         }
      });
      qBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int id) {
             alertDemoView.setMessage("You pressed: 'No'");
             runAlert();
         }
      });
      qBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int id) {
            alertDemoView.noAlert();
            alertDemoView.setMessage("The Alert is Finished!");
         }
      });
      AlertDialog alertDialog = qBuilder.create();
      alertDialog.setCancelable(false);
      alertDialog.setCanceledOnTouchOutside(false);
      alertDialog.show();
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
//      getMenuInflater().inflate(R.menu.happy_face, menu);
      return true;
   }
}
