package com.example.androidtictactoe;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;

public class AndroidTicTacToeActivity extends Activity {

    private GameView gameView;  // The gameView object
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);  // instantiate the gameView object
        setContentView(gameView);       // set current view to gameView

        // This dialog to determine who goes first: the user or the computer
        AlertDialog.Builder qBuilder = new AlertDialog.Builder(this);
        qBuilder.setTitle(R.string.whoisfirsttitle);   // the title
        qBuilder.setMessage(R.string.willyougofirst);  // the message

        // The "Yes" button handler
        qBuilder.setPositiveButton(R.string.yes, 
                      new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // Selecting "Yes" does nothing because the
                       //   user goes first is the default

                       ;   // do nothing
                   }
               });

        // The "No" button handler
        qBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // Selecting "No" means computer goes first
                       //   and you need to write the computerIsFirst()
                       //   in the gameView class and call it by uncommeting
                       //   the line below:

                        gameView.computerIsFirst();
                   }
               });

        // The "Cancel" button handler
        qBuilder.setNeutralButton(R.string.canceltext,
               new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // Selecting "Cancel" ends the game

                       finish();
                   }
               });

        AlertDialog alertDialog = qBuilder.create();

        // Uncomment the line below to show the dialog
        //   which determines who goes first:

         alertDialog.show();
        
        gameView.startGame();   // starts the game
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tic_tac_toe, menu);
        return true;
    }

}