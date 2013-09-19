package graphics;
//******************************************************************
// The SudokuDriver Class with tests to make sure there are no
// illegal moves in the board.
// 
// Add a button to reset the game.
//******************************************************************


import sdk.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SudokuDriver extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  static final int WIDTH = 656;
  static final int HEIGHT = 714;
  JPanel bottomPanel = new JPanel();
  JButton testButton = new JButton ("Test Sudoku");
  JButton resetButton = new JButton("Reset");
  Sudoku gamePanel;
  String answerString = "";

  // Set up Sudoku board
  public SudokuDriver() {
    setSize (WIDTH, HEIGHT);
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    setTitle ("Sudoku Driver");
    Container contentPane = getContentPane ();
    contentPane.setLayout (new BorderLayout ());

    bottomPanel.setBackground (Color.GREEN);

    //test sudoku button creator
    bottomPanel.add (testButton);
    testButton.setActionCommand("test");
    testButton.addActionListener (this);

    //reset button button creator
    bottomPanel.add (resetButton);
    resetButton.setActionCommand("Reset");
    resetButton.addActionListener(this);

    gamePanel = new Sudoku();
    contentPane.add (gamePanel, BorderLayout.NORTH);
    contentPane.add (bottomPanel, BorderLayout.SOUTH);
    setVisible(true);

    gamePanel.requestFocus();  // set focus to game panel
  }

  // Handler for "Test Sudoku" button clicks
  public void actionPerformed (ActionEvent evt) {
    int [][] grid = gamePanel.getGrid();  // get game grid

    if (isLegal(grid))
      JOptionPane.showMessageDialog(null, "No illgeal moves yet!");
    else
      JOptionPane.showMessageDialog(null, "There are illegal moves at position: " + answerString);

    gamePanel.requestFocus();  // return focus to game panel
  }

  //checks if entries are valid
  public boolean isLegal(int [][] grid) {
    answerString = "";
    for(int row = 0; row < grid.length; ++row)
      for(int col = 0; col < grid[row].length; ++col)
        if(!gamePanel.isLegalSquare(row, col))
          answerString += "(" + row + ", " + col + ") ";
    if (answerString.length() == 0)
      return true;
    return false;
  }

  //main method
  public static void main(String[] args) {
    new SudokuDriver();
  }
}