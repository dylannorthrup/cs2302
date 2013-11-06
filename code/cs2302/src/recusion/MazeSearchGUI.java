package recusion;
//********************************************************************
//  Represents a maze with brick walls. The goal is to get from the
//  top left corner to the bottom right. The path of 1s in the grid
//  represent empty spaces, so vertical or horizontal consecutive 1s
//  represent a traversable path.
//
//  Need to have available the brick, flag and mole images
//********************************************************************

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeSearchGUI extends JPanel implements ActionListener, Runnable {
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

  // Data structure of Grid
  private static int[][] grid = { {1,1,1,0,1,1,0,0,0,1,1,1,1},
    {1,0,1,1,1,0,1,1,1,1,0,0,1},
    {0,0,0,0,1,0,1,0,1,0,1,0,0},
    {1,1,1,0,1,1,1,0,1,0,1,1,1},
    {1,0,1,0,0,0,0,1,1,1,0,0,1},
    {1,0,1,1,1,1,1,1,0,1,1,1,1},
    {1,0,0,0,0,0,0,0,0,0,0,0,0},
    {1,1,1,1,1,1,1,1,1,1,1,1,1} };

  // No Arg Constructor - the thread process runs this
  public MazeSearchGUI() {
  }

  // One Arg Constructor - main panel process runs this   
  public MazeSearchGUI(String label) {
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
    (new Thread(new MazeSearchGUI())).start();
  }

  // starts the traversal thread
  public void run() {
    if (traverse(0,0)) {
      success = true;
      headLabel = "I Found A Way Out!";
      mainPanel.repaint();
      //JOptionPane.showMessageDialog(null,"A successful path was found!");
    } else {
      headLabel = "There's No Way Out!";
      mainPanel.repaint();
      //JOptionPane.showMessageDialog(null,"There is no way out of this maze!");
    }
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
          if (grid[row][col] == PATH) {
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

  //  Attempts to recursively traverse the maze. Inserts special
  //  characters indicating locations that have been tried and that
  //  eventually become part of the solution.
  public boolean traverse(int row, int col)
  {
    boolean done = false;

    if (valid(row, col)) {
      pause(stepDelay);  // half second waits
      xPos = col;
      yPos = row;
      grid[row][col] = TRIED;  // this cell has been tried
      mainPanel.repaint();

      if (row == grid.length-1 && col == grid[0].length-1)
        done = true;  // the maze is solved
      else {
        done = traverse (row+1, col);     // down
        if (!done)
          done = traverse (row, col+1);  // right
        if (!done)
          done = traverse (row-1, col);  // up
        if (!done)
          done = traverse (row, col-1);  // left
      }

      if (done)  // this location is part of the final path
        grid[row][col] = PATH;
    }
    return done;
  }

  //  Determines if a specific location is valid.
  private boolean valid (int row, int col)
  {
    // check if cell is in the bounds of the matrix
    if (row >= 0 && row < grid.length &&
        col >= 0 && col < grid[row].length)
      //  check if cell is not blocked and not previously tried
      if (grid[row][col] == EMPTY)
        return true;

    return false;
  }

  public void pause(long millisecs) {
    try {
      Thread.sleep(millisecs);
    } catch (InterruptedException e) {
    }
  }

  public static void main(String[] args)
  {
    JFrame frame = new JFrame("Maze Search GUI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainPanel = new MazeSearchGUI("Trying To Traverse This Maze...");
    frame.getContentPane().add(mainPanel);
    frame.pack();
    frame.setVisible(true);
  }
}
