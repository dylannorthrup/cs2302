package com.example.androidtictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class GameView extends View {

  // Constants for the array dimensions and height of header area
  private final int COLS = 3, ROWS = 3;
  private final int HDHEIGHT = 60;

  // The two-dimensional board array
  private int [][] board = { {0, 0, 0}, {0, 0, 0}, {0, 0, 0} };

  private int playerTurn = 1;           // 1 is for X, 2 is for O
  private boolean gameOver = false;     // flags when game ends
  private boolean gameStarted = false;  // flags when game begins
  private int winState = 0;             // holds game state:
  //   0 => no winner yet
  //   1 => X is the winner
  //   2 => O is the winner
  //   3 => its a draw

  private int computerTurn = 2;

  // Paint objects for drawing different parts of the GUI
  private Paint borderPaint;
  private Paint headingPaint;
  private Paint textPaint;
  private Paint emptyPaint;

  // Bitmaps of the X and O images
  private Bitmap xIMap = BitmapFactory.decodeResource(getResources(), R.drawable.xmark);
  private Bitmap oIMap = BitmapFactory.decodeResource(getResources(), R.drawable.omark);

  private Rect imgRect = new Rect();    // for drawing images

  // Loading the strings
  private String computerThinkingStr = getContext().getString(R.string.computerThinking);
  private String yourTurnStr = getContext().getString(R.string.yourTurn);
  private String welcomeStr = getContext().getString(R.string.welcometext);
  private String xWinsStr = getContext().getString(R.string.xwinstext);
  private String oWinsStr = getContext().getString(R.string.owinstext);
  private String aDrawStr = getContext().getString(R.string.adrawtext);

  // Handler for displaying Toast when game is over
  Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
      case 1:
      case 2:
      case 3:
        Toast.makeText(getContext(), hdMessage(), Toast.LENGTH_SHORT).show();
        break;
      case 4:
        computerPlay(playerTurn);     // computer plays as playerTurn
        playerTurn = 3 - playerTurn;  // switch players
        invalidate();                 // update board
        checkGameState();             // determine if game is over
        break;
      default:
        break;
      }
      super.handleMessage(msg);
    }
  };

  // The GameView constructor
  public GameView(Context context) {
    super(context);

    // Paint for the border lines
    borderPaint = new Paint();
    borderPaint.setARGB(255, 0, 0, 0);       // black color
    borderPaint.setStyle(Style.STROKE);
    borderPaint.setStrokeWidth(5);

    // Paint for background color of header
    headingPaint = new Paint();
    headingPaint.setARGB(255, 255, 255, 0);  // yellow color
    headingPaint.setStyle(Style.FILL);

    // Paint for header text
    textPaint = new Paint();
    textPaint.setARGB(255, 65, 105, 225);    // bluish color
    textPaint.setTextSize(30);

    // Paint to clear board squares
    emptyPaint = new Paint();
    emptyPaint.setARGB(255, 255, 255, 255);  // white color
    emptyPaint.setStyle(Style.FILL);
  }

  // Draws the GameView GUI
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    int vOffset = (this.getHeight() - HDHEIGHT) / ROWS;
    int hOffset = this.getWidth() / COLS;
    for (int i = 0; i < ROWS; i++)
      for (int j = 0; j < COLS; j++) {
        if (board[i][j] != 0) {   // draws X or O image
          imgRect.set(j*hOffset, HDHEIGHT+i*vOffset,
              (j*hOffset)+hOffset,HDHEIGHT+(i*vOffset)+vOffset);
          canvas.drawBitmap((board[i][j] == 1) ? xIMap : oIMap,
              null, imgRect, emptyPaint);
        } else   // clears empty square
          canvas.drawRect(j*hOffset, HDHEIGHT+i*vOffset, (j*hOffset)+hOffset,
              HDHEIGHT+(i*vOffset)+vOffset, emptyPaint);
      }

    // draws the background color for the header
    canvas.drawRect(0, 0, this.getWidth(), HDHEIGHT, headingPaint);

    // draws the header message
    String message = hdMessage();
    int offset = (int)(this.getWidth() - textPaint.measureText(message) - 10) / 2;
    canvas.drawText(message, offset, HDHEIGHT-15, textPaint);

    // draws the horizontal lines on the board
    for (int i = 0; i <= ROWS; i++)
      canvas.drawLine(0, HDHEIGHT + vOffset * i, this.getWidth(),
          HDHEIGHT + vOffset * i, borderPaint);

    // draws the vertical lines on the board
    for (int i = 0; i <= COLS; i++)
      canvas.drawLine(hOffset * i, HDHEIGHT, hOffset * i, 
          this.getHeight(), borderPaint);
  }

  // Handles next play when the user touches the bord 
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (gameOver) {   // if game is over, reset the board
      for (int i = 0; i < ROWS; i++)
        for (int j = 0; j < COLS; j++)
          board[i][j] = 0;
      gameOver = false;   // start a new game
      playerTurn = 1;     // set playerTurn to X
      if (computerTurn == playerTurn) {
        handler.sendMessageDelayed(Message.obtain(handler,4),2000);
//        handler.sendMessageDelayed(Message.obtain(handler,4),0);
      }
      invalidate();       // redraw the screen
    } else if (gameStarted && playerTurn != computerTurn) {
      int rOff = (int) (event.getY() / (this.getHeight() / ROWS));
      int cOff = (int) (event.getX() / (this.getWidth() / COLS));
      if (board[rOff][cOff] == 0) {  // if touched board position empty
        board[rOff][cOff] = playerTurn;  // put player in that position
        playerTurn = 3 - playerTurn;     // switch players
        invalidate();                    // update board
        checkGameState();                // determine if game is over
        if (!gameOver) {
          handler.sendMessageDelayed(Message.obtain(handler,4),2000);
//          handler.sendMessageDelayed(Message.obtain(handler,4),0);
          invalidate();                 // update board
          checkGameState();             // determine if game is over
        }

      }
    }
    return super.onTouchEvent(event);
  }

  // Determine if game has a winner
  private void checkGameState() {
    int plyNum = 1;

    // Loop through the board to see if 1 or 2 is in a
    //   a set of rows, columns or the diagonal
    for (; plyNum <= 2; ++plyNum) {  
      int i = 0;
      for (; i <= 2; ++i) {
        if ((board[i][0] == plyNum && board[i][1] == plyNum && board[i][2] == plyNum) ||
            (board[0][i] == plyNum && board[1][i] == plyNum && board[2][i] == plyNum))
          break;
      }
      if (i < 3 || (board[0][0] == plyNum && board[1][1] == plyNum && board[2][2] == plyNum) ||
          (board[0][2] == plyNum && board[1][1] == plyNum && board[2][0] == plyNum))
        break;
    }
    if (plyNum < 3 || isDraw()) {  // if there's a winner or draw
      gameOver = true;           // update gameOver flag to true
      winState = plyNum;         // set winState to the winner or to draw

      // Send message to handler so it can show Toast message
      handler.sendMessage(Message.obtain(handler, winState));
    }
  }

  // Determines if there is a draw because there are no
  //   empty positions
  public boolean isDraw() {
    for (int i = 0; i < ROWS; ++i)
      for (int j = 0; j < COLS; ++j)
        if (board[i][j] == 0)
          return false;
    return true;
  }

  // Starts the game
  public void startGame() {
    gameStarted = true;
    invalidate();
  }

  // Returns the header message that is appropriate for
  //   the current game state
  public String hdMessage() {
    if (!gameStarted)
      return welcomeStr;       // Welcome
    if (gameOver)
      if (winState == 3)       // A draw
        return aDrawStr;
      else if (winState == 1)  // The X won
        return xWinsStr;
      else if (winState == 2)  // The Y won
        return oWinsStr;
      else
        return welcomeStr;   // Welcome
    if (playerTurn == computerTurn)
      return computerThinkingStr;
    return yourTurnStr;
  }

  // Set the computer to play first
  public void computerIsFirst() {
    computerTurn = 1;
    handler.sendMessageDelayed(Message.obtain(handler,4),2000);
    invalidate();     // redraw the screen
  }

  
  // Determine what is the optimal play for the computer
  private void computerPlay(int player) {
    // Pick a random move (for testing other functionality)
//    do {
//      int row = (int) (Math.random() * 3);
//      int col = (int) (Math.random() * 3);
//      if (board[row][col] == 0) {     // find empty spot
//        board[row][col] = player;   // play empty spot
//        return;                     // return
//      }
//    } while (true);
    
    // Recursive method for making a move
//    set maxGoodness to 100.0
    double maxGoodness = 100.0;
//    set currentGoodness to -maxGoodness
    double currentGoodness = -maxGoodness;
//    set saveRow to -1
    int saveRow = -1;
//    set saveCol to -1
    int saveCol = -1;
    
//    for row from 1 to 3
    for (int r=0; r < 3; r++){
//      for col from 1 to 3
      for (int c=0; c < 3; c++) {
//        if board is empty at (row,col)
        if(board[r][c] == 0) {
          Log.d("DEBUG", "Looking at [" + r + "][" + c + "]");

//                set goodness to rateMove(player,row,col,maxGoodness)
          double goodness = rateMove(player, r, c, maxGoodness);
//                if goodness is greater than currentGoodness
          if(goodness > currentGoodness) {
//                set currentGoodness to goodness
            currentGoodness = goodness;
//                set saveRow to row
            saveRow = r;
//                set saveCol to col
            saveCol = c;
          }
        }
      }
    }

//    if saveRow is greater than -1
    if(saveRow > -1) {
//        set board at (saveRow,saveCol) to player
      board[saveRow][saveCol] = player;
    }
  }

  private double rateMove(int player, int r, int c, double maxGoodness) {
// set opponent to value of other player
    int opponent = 3 - player;
    
//
// // base case
// set board at (currentRow,currentCol) to player
    board[r][c] = player;
// if playerWins(player) is true
    if(playerWins(player)) {
//     set board at (currentRow,currentCol) to zero
      board[r][c] = 0;
//     return maxGoodness                 // this is a winning position to play
      return maxGoodness;
    }
//
// set currentGoodness to maxGoodness / 2       // goodness lowered for next play
    double currentGoodness = maxGoodness / 2;
// set negMax to -currentGoodness               // get negative max for next play
    double negMax = -currentGoodness;
//
// for row from 1 to 3
    for(int row = 0; row < 3; row++) {
//     for col from 1 to 3
      for(int col = 0; col < 3; col++) {
//         if board is empty at (row,col)
        if(board[row][col] == 0) {
//             set goodness to rateMove(opponent,row,col,negMax)    // recursion
          double goodness = rateMove(opponent, row, col, negMax);
//             if negMax greater than zero AND goodness greater than currentGoodness
          if(negMax > 0 && goodness > currentGoodness) {
//                 set currentGoodness to goodness                  // maximize
            currentGoodness = goodness;
//             else if negMax less than zero AND goodness less than currentGoodness
          } else if (negMax < 0 && goodness < currentGoodness) {
//                 set currentGoodness to goodness                  // minimize
            currentGoodness = goodness;
          }
        }
      }
    }
//
// set board at (currentRow,currentCol) to zero        // undo the play
    board[r][c] = 0;
// return currentGoodness
    return currentGoodness;
  }

  // Determine if the player would win with a given play
  private boolean playerWins(int participant) {
    // Loop through the board to see if 1 or 2 is in a
    //   a set of rows, columns or the diagonal
//      int i = 0;
//      for (; i <= 2; ++i) {
//        if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
//            (board[0][i] == player && board[1][i] == player && board[2][i] == player))
//          return true;
//      }
//      if (i < 3 || (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
//          (board[0][2] == player && board[1][1] == player && board[2][0] == player))
//        return true;
//      return false;

    if(board[0][0] == participant && board[0][1] == participant && board[0][2] == participant) {
      return true;
    }
    if(board[1][0] == participant && board[1][1] == participant && board[1][2] == participant) {
      return true;
    }
    if(board[2][0] == participant && board[2][1] == participant && board[2][2] == participant) {
      return true;
    }
    if(board[0][0] == participant && board[1][0] == participant && board[2][0] == participant) {
      return true;
    }
    if(board[0][1] == participant && board[1][1] == participant && board[2][1] == participant) {
      return true;
    }
    if(board[0][2] == participant && board[1][2] == participant && board[2][2] == participant) {
      return true;
    }
    if(board[0][0] == participant && board[1][1] == participant && board[2][2] == participant) {
      return true;
    }
    if(board[0][2] == participant && board[1][1] == participant && board[2][0] == participant) {
      return true;
    }
    return false;
  }
}