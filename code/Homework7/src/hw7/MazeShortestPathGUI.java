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
import java.util.ArrayList;
import java.util.Stack;

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
  
  private boolean DEBUG = true;   // DEBUG variable

  // Set up some classes here to handle book keeping
  // A Point class to encapsulate x and y
  class Point {
    public int x; public int y;
    // No Argument Constructor
    Point() {}
    // Argument Constructor
    Point(int X, int Y) { x = X; y = Y; }
    public String toString() { return "x: " + x + " -- y: " + y; }
  }
  // A Path is a collection of points along with something to get the
  // size of the Path
  class Path {
    public ArrayList<Point> points = new ArrayList<Point>();
    public int size() { return points.size(); }
  }
  // And an ArrayList of paths that are valid (i.e. have reached the end of the maze)
  private ArrayList<Path> validPaths = new ArrayList<Path>();



  // Data structure of Grid
  //  private static int[][] grid = { 
  //    {1,1,1,0,1,1,1,1,1,1,1,1,1},
  //    {1,0,1,1,1,0,0,0,0,0,0,0,1},
  //    {1,0,0,0,1,0,1,1,1,0,1,0,1},
  //    {1,0,1,0,1,0,1,0,1,0,1,0,1},
  //    {1,0,1,0,1,1,1,0,1,0,1,0,1},
  //    {1,0,1,0,0,0,0,0,1,1,1,0,1},
  //    {1,0,1,1,1,0,1,1,1,0,1,0,1},
  //    {1,1,1,0,1,1,1,0,1,0,1,1,1} };

  // Simple grid to test with
  private static int[][] grid = { 
    {1,1,1,1},
    {0,1,0,1},
    {1,1,0,1},
    {1,0,0,1},
    {1,1,1,1} };

  private void pdebug(String msg) {
    if(DEBUG) {
      System.out.println("DEBUG: " + msg);
    }
  }
  
  // No Arg Constructor - the thread process runs this
  public MazeShortestPathGUI() { }

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
  public void actionPerformed (ActionEvent event) {
    timer.stop();
    (new Thread(new MazeShortestPathGUI())).start();
  }

  // starts the traversal thread
  public void run() {
    if (traverse(0,0)) {
      success = true;
      headLabel = "I Found A Way Out!";
    } else {
      headLabel = "There's No Way Out!";
    }
    mainPanel.repaint();
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

    for (int row = 0; row < grid.length; ++row) {
      for (int col = 0; col < grid[row].length; ++col) {
        if (grid[row][col] == WALL) {
          page.drawImage(brick,col*brickWidth, row*brickHeight+HEADERSZ,null);
        } else if (success) {
          if (grid[row][col] == PATH) {
            page.setColor(successCol);
            page.fillRect(col*brickWidth,row*brickHeight+HEADERSZ, brickWidth,brickHeight);
          }
        } else if (grid[row][col] == TRIED && (row != yPos || col != xPos)) {
          page.setColor(triedCol);
          page.fillRect(col*brickWidth,row*brickHeight+HEADERSZ, brickWidth,brickHeight);
        }
      }
    }

    page.drawImage(mole,(xPos+1)*brickWidth-(brickWidth+moleWidth)/2, HEADERSZ+(yPos+1)*brickHeight-(brickHeight+moleHeight)/2,null);
  }

  // Iterate through moves and return an ArrayList of valid moves
  public Stack<Point> findValidMoves(Point point) {
    Stack<Point> validMoves = new Stack<Point>();
    // Search up, down, left and right
    Point np = new Point(point.x+1, point.y);
    if(valid(np)){ pdebug(np + " is a valid point"); validMoves.add(new Point(np.x, np.y)); }
    np = new Point(point.x-1, point.y);
    if(valid(np)){ pdebug(np + " is a valid point"); validMoves.add(new Point(np.x, np.y)); }
    np = new Point(point.x, point.y-1);
    if(valid(np)){ pdebug(np + " is a valid point"); validMoves.add(new Point(np.x, np.y)); }
    np = new Point(point.x, point.y+1);
    if(valid(np)){ pdebug(np + " is a valid point"); validMoves.add(new Point(np.x, np.y)); }
    return validMoves;
  }

  //  Attempts to recursively traverse the maze. Inserts special
  //  characters indicating locations that have been tried and that
  //  eventually become part of the solution.
  public boolean traverse(int row, int col) {
    boolean done = false;
    Stack<Point> validMoves = findValidMoves(new Point(row, col));

    while(! validMoves.isEmpty()) {
      Point move = validMoves.pop();
      pdebug("Checking if move " + move + " is a valid move");
      if (valid(move)) {
        pause(stepDelay);        // delay between steps
        xPos = move.x;              // setting the current position
        yPos = move.y;
        grid[move.x][move.y] = TRIED;  // marking this cell as tried
        mainPanel.repaint();

        if (move.x == grid.length-1 && move.y == grid[0].length-1) {
          done = true;  // at last position so the maze is solved
        } else {
          done = traverse (row+1, col);     // searching down
          if (!done) { done = traverse (move.x, move.y+1); } // searching right
          if (!done) { done = traverse (move.x-1, move.y); } // searching up
          if (!done) { done = traverse (move.x, move.y-1); } // searching left
        }

        if (done) { grid[move.x][move.y] = PATH; } // this location is part of the final path
      }
    }
    return done;
  }

  //  Determines if a specific location is valid.
  private boolean valid (int row, int col) {
    // check if cell is in the bounds of the matrix
    if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length) {
      //  check if cell is not blocked and not previously tried
      if (grid[row][col] == EMPTY) {
        return true;
      }
    }
    return false;
  }

  // Overloaded version of valid using Points
  private boolean valid (Point point) {
    return valid(point.x, point.y);
  }
  
  // Causes a pause for a given amount of milliseconds
  public void pause(long millisecs) {
    try { Thread.sleep(millisecs); } catch (InterruptedException e) {}
  }

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