package hw7;
//********************************************************************
//  Represents a maze of paths and walls. The goal is to get from the
//  top left corner to the bottom right corner. The 1's in the grid
//  array represent empty spaces, so a group of vertical or horizontal
//  consecutive 1's represent a traversable path. The O's represent
//  walls. The brick image is used for the walls. The flag image is
//  placed at the bottom right corner. The mole image is placed
//  at the current position being traversed.
//
//  Need to have available the brick.png, flag.png, mole.png images.
//********************************************************************

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeShortestPathGUI extends JPanel implements ActionListener, Runnable {
  private static final long serialVersionUID = 1L;
  private final int WALL = 0;                  // A wall position value
  private final int EMPTY = 1;                 // An empty position value
  private final int TRIED = 3;                 // Previously tried path value
  private final int PATH = 7;                  // Successful path value
  private final int HEADERSZ = 60;             // Height in header area

  private int width, height;                   // Width and Height

  private Image brick;                         // Brick images for walls
  private Image flag;                          // Flag finish line images
  private Image mole;                          // Flag finish line images

  private int brickWidth, brickHeight;         // Brick dimensions
  private int flagWidth, flagHeight;           // Flag image dimensions
  private int moleWidth, moleHeight;           // Flag image dimensions

  private Color backCol = Color.white;         // Header background color
  private Color mazeCol = Color.yellow;        // Maze background color
  private Color triedCol = Color.lightGray;    // Tried path color
  private Color successCol = Color.green;      // Successful path color

  private Timer timer;                         // Timer to start game
  private int startDelay = 3000;               // Delay to start traversal
  private int stepDelay = 200;                 // Delay between each step

  private static JPanel mainPanel;             // Main game panel
  private static String headLabel;             // String at top
  private static boolean success = false;      // Flags successful path
  private static int xPos, yPos;               // Current position

  //   Data structure of Grid
  private static int[][] grid = { 
    {1,1,1,0,1,1,1,1,1,1,1,1,1},
    {1,0,1,1,1,0,0,0,0,0,0,0,1},
    {1,0,0,0,1,0,1,1,1,0,1,0,1},
    {1,0,1,0,1,0,1,0,1,0,1,0,1},
    {1,0,1,0,1,1,1,0,1,0,1,0,1},
    {1,0,1,0,0,0,0,0,1,1,1,0,1},
    {1,0,1,1,1,0,1,1,1,0,1,0,1},
    {1,1,1,0,1,1,1,0,1,0,1,1,1} };
  // Data structure of Grid

  // Simple grid to test with
  //  private static int[][] grid = { 
  //    {1,0,1,1,1,0,1,1,1,1,1,1,1,1},
  //    {1,0,1,0,1,0,1,0,1,0,1,0,0,1},
  //    {1,0,1,0,1,0,1,0,1,0,1,0,1,1},
  //    {1,0,1,0,1,0,1,0,1,0,1,0,1,0},
  //    {1,1,1,1,1,1,1,0,1,1,1,0,1,1} };

  // DEBUG flag
  private boolean DEBUG = false;

  // Keep track of these so I can print it out at the end of everything
  int[][] shortestPath = new int[grid.length][grid[0].length];
  int shortestLength = -1;

  // No arg constructor

  // No Arg Constructor - the thread process runs this
  public MazeShortestPathGUI() {
  }
  // Argument constructor

  // One Arg Constructor - main panel process runs this   
  public MazeShortestPathGUI(String label) {
    headLabel = label;
    backCol = new Color(255,182,193);
    xPos = 0;
    yPos = 0;

    brick = new ImageIcon("brick.png").getImage();
    flag = new ImageIcon("flag.png").getImage();
    mole = new ImageIcon("mole.png").getImage();

    MediaTracker track = new MediaTracker(this);
    track.addImage(brick,0);
    track.addImage(flag,0);
    track.addImage(mole,0);
    try {
      track.waitForAll();
    } catch ( InterruptedException e ) { }

    brickWidth = brick.getWidth(null);
    brickHeight = brick.getHeight(null);
    flagWidth = flag.getWidth(null);
    flagHeight = flag.getHeight(null);
    moleWidth = mole.getWidth(null);
    moleHeight = mole.getHeight(null);

    width = brickWidth * grid[0].length;
    height = brickHeight * grid.length + HEADERSZ;

    setBackground(backCol);
    setPreferredSize(new Dimension(width, height));
    setFocusable(true);

    timer = new Timer(startDelay,this);
    timer.start();
  }

  // runs once just to start the traversal thread
  // Handle events
  public void actionPerformed (ActionEvent event) {
    timer.stop();
    (new Thread(new MazeShortestPathGUI())).start();
  }
  // Actually run the logic of the program

  // starts the traversal thread
  public void run() {
    int steps = traverse(0,0);
    if(steps > 0) { pdebug("Steps: " + steps); }  // To shut up warning
    if (shortestLength > 1) {
      success = true;
      // Copy the recorded shortestPath array into grid
      arrayCopy(shortestPath, grid);
      // Cheat and set the mole back to the end of the maze
      xPos = grid[0].length - 1;
      yPos = grid.length - 1;
      // Update the number of steps
      headLabel = "I Found The Shortest Way Out!";
    } else {
      headLabel = "There's No Way Out!";
    }
    mainPanel.repaint();
    pdebug("Shortest path length is " + shortestLength + "\nShortest Array: \n" + aryToString(shortestPath));
    pdebug("grid length: " + grid.length + " => grid[0].length: " + grid[0].length);
  }

  // Updates the window
  public void paintComponent(Graphics page) {
    super.paintComponent(page);

    page.setColor(backCol);
    page.fillRect(0,0,width,HEADERSZ);

    page.setColor(mazeCol);
    page.fillRect(0,HEADERSZ,width,height-HEADERSZ);

    page.setColor(Color.black);
    page.setFont(new Font("Arial",Font.BOLD,24));
    FontMetrics metrics = page.getFontMetrics();
    int labelWidth = metrics.stringWidth(headLabel);
    page.drawString(headLabel,(width-labelWidth)/2,HEADERSZ-20);

    page.drawImage(flag,grid[0].length*brickWidth-(brickWidth+flagWidth)/2,
        height-(brickHeight+flagHeight)/2,null);

    for (int row = 0; row < grid.length; ++row)   {
      for (int col = 0; col < grid[row].length; ++col)   {
        if (grid[row][col] == WALL)
          page.drawImage(brick,col*brickWidth,
              row*brickHeight+HEADERSZ,null);
        else if (success) {
          if (grid[row][col] == PATH || grid[row][col] == TRIED) {
            page.setColor(successCol);
            page.fillRect(col*brickWidth,row*brickHeight+HEADERSZ,
                brickWidth,brickHeight);
          }
        } else if (grid[row][col] == TRIED && (row != yPos || col != xPos)) {
          page.setColor(triedCol);
          page.fillRect(col*brickWidth,row*brickHeight+HEADERSZ,
              brickWidth,brickHeight);
        }
      }
    }

    page.drawImage(mole,(xPos+1)*brickWidth-(brickWidth+moleWidth)/2,
        HEADERSZ+(yPos+1)*brickHeight-(brickHeight+moleHeight)/2,null);
  }
  // Utility print debug method
  private void pdebug(String msg) {
    if(DEBUG) {
      System.out.println("DEBUG: " + msg);
    }
  }
  // Utility method to stringify multi-dimensional array
  private String aryToString(int[][] ary) {
    String retStr = "";
    for(int i=0; i<ary.length; i++) {
      for(int j=0; j<ary[i].length; j++) {
        retStr += ary[i][j] + " ";
      }
      retStr += "\n";
    }
    return retStr;
  }
  // Utility method to count up the squares in a successful path
  private int stepsInPath(int[][] ary) {
    int steps = 0;
    for(int i=0; i<ary.length; i++) {
      for(int j=0; j<ary[i].length; j++) {
        if(ary[i][j] == TRIED || ary[i][j] == PATH) {
          steps++;
        }
      }
    }
    return steps;
  }
  //  Attempts to recursively traverse the maze. Inserts special
  //  characters indicating locations that have been tried and that
  //  eventually become part of the solution.
  public int traverse(int row, int col) {
    //  1.  If the current position [row,col] is valid, then do the following:
    if (valid(row, col)) {
      //    1.1.  Update the animation by: first calling pause() to add a pause between
      //    steps, and then updating xPos and yPos to the current position, and then
      //    setting the grid value at the current position to TRIED, and then
      //    repainting the view.  [Note: This is really 5 steps and the 5 steps are all
      //    already being done in the unedited version of this program].
      pause(stepDelay);        // delay between steps
      xPos = col;              // setting the current position
      yPos = row;
      grid[row][col] = TRIED;  // marking this cell as tried
      mainPanel.repaint();
      //    1.2.  If the current position is the final position, then do the following:
      if (row == grid.length-1 && col == grid[0].length-1) {
        //      1.2.1  Set the grid value at the current position to PATH (which means you
        //      have found your way out).
        grid[row][col] = PATH;
        // DEBUG:
        pdebug("Array at end of traversal...\n" + aryToString(grid));
        // Count up steps in path.
        int pathSteps = stepsInPath(grid);
        // If we haven't initialized the shortestLength variable yet or
        // if the pathSteps is less than the shortest Length...
        if(shortestLength == -1 || pathSteps < shortestLength) {
          pdebug("Updating shortestLength from " + shortestLength + " to " + pathSteps);
          // Update shortestLength
          shortestLength = pathSteps;
          // And copy the grid to the shortestArray so we can display it later
          arrayCopy(grid, shortestPath);
        }
        //      1.2.2  Return the value of 1.
        return 1;
      }
      //    1.3.  If the current position is not the final position, then do the
      //    following:
      else {
        //      1.3.1.  Create a new array called beforeState that is two-dimensional and
        //      that has the same dimensions as the grid array.
        int[][] beforeState = new int[grid.length][grid[0].length];
        //      1.3.2.  Create a new array called afterState that is two-dimensional and
        //      that has the same dimensions as the grid array.
        int[][] afterState = new int[grid.length][grid[0].length];
        //      (Remember: The beforeState array will be used to copy the grid before any
        //      of the four recursive traverse() calls, and the afterState array will be
        //      used to copy the grid after any of the four traverse() calls that was
        //      successful and that took fewer steps than the last successful call.)

        //      1.3.3.  Copy the grid into the beforeState array.  (The copy must be a full
        //      copy, not just a clone because that is only a shallow copy).  This
        //      preserves the original state of the grid before the recursive calls.
        arrayCopy(grid, beforeState);
        //      1.3.4.  Set an int value called min to the value of -1.
        int min = -1;
        //      1.3.5.  Looping for some int value i = 1, until i <= 4, adding 1 to i each
        //      time, do the following:
        for(int i = 1; i <=4; i++) {
          //      1.3.5.1.  Create an int value called steps.
          int steps = -1; // Initializing to shut up Eclipse warnings
          //      1.3.5.2.  If i = 1 then set steps to the value returned by traverse() going
          //      down one position.
          if(i == 1) { steps = traverse(row, col-1); }
          //      1.3.5.3.  If i = 2 then set steps to the value returned by traverse() going
          //      right one position.
          if(i == 2) { steps = traverse(row, col+1); }
          //      1.3.5.4.  If i = 3 then set steps to the value returned by traverse() going
          //      up one position.
          if(i == 3) { steps = traverse(row-1, col); }
          //      1.3.5.5.  If i = 4 then set steps to the value returned by traverse() going
          //      left one position.
          if(i == 4) { steps = traverse(row+1, col); }
          //      1.3.5.6.  If steps is less than min or min is equal to -1 then do the
          //      following:
          if(steps < min || min == -1) {
            //      1.3.5.6.1  If steps is greater than -1 then do the following:
            if(steps > -1) {
              //      1.3.5.6.1.1  Set min to the value of steps.
              min = steps;
              //      1.3.5.6.1.2  Copy the grid into the afterState array.  (The copy must be a
              //      full copy, not just a clone because that is only a shallow copy).  This
              //      preserves the state of the last successful recursive call that is the
              //      lowest number of steps.
              arrayCopy(grid, afterState);
            }
          }
          //      1.3.5.7.  Copy the beforeState array into the grid array.  (The copy must
          //      be a full copy, not just a clone because that is only a shallow copy).
          //      This restores the grid to its original state.
          arrayCopy(beforeState, grid);
        }
        //      1.3.6.  If min is greater than -1 do the following:
        if(min > -1) {
          //      1.3.6.1.  Copy the afterState array into the grid array.  (The copy must be
          //      a full copy, not just a clone because that is only a shallow copy).  This
          //      restores the grid to the state of the last successful recursive call that
          //      was the lowest number of steps.
          arrayCopy(afterState, grid);
          //      1.3.6.2.  Set the grid value at the current position to PATH.
          grid[row][col] = PATH;
          //      1.3.6.3.  Return the value of min + 1.
          return min + 1;
        } else {
          //      1.3.7.  If min is not greater than -1 than return the value of -1.
          return -1;
        }
      }
    }
    //  2.  If the current position [row,col] is not valid, then return the value
    //  of -1.
    return -1;
  }
  //  Determines if a specific location is valid.
  private boolean valid (int row, int col) {
    // check if cell is in the bounds of the matrix
    if (row >= 0 && row < grid.length &&
        col >= 0 && col < grid[row].length)
      //  check if cell is not blocked and not previously tried
      if (grid[row][col] == EMPTY)
        return true;

    return false;
  }
  // Utility method to make a full copy of the multi-dimensional array

  // Convenience method to do a copy of the multi-dimensional array
  private void arrayCopy(int[][] src, int[][] dest) {
    for(int i=0; i<src.length; i++) {
      for(int j=0; j<src[i].length; j++) {
        dest[i][j]=src[i][j];
      }
    }
  }
  // Method to sleep between actions

  // Causes a pause for a given amount of milliseconds
  public void pause(long millisecs) {
    try { Thread.sleep(millisecs); } catch (InterruptedException e) {}
  }
  // Main method

  // the main method
  public static void main(String[] args) {
    JFrame frame = new JFrame("Maze Search GUI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainPanel = new MazeShortestPathGUI("Trying To Traverse This Maze...");
    frame.getContentPane().add(mainPanel);
    frame.pack();
    frame.setVisible(true);
  }
}