package views;
//********************************************************
//  Demonstrates integrating multiple Swing components
//********************************************************

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// The whole class is a JFrame, so we don't create an explicit JFrame in 
// the constructor
public class EditFullName extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  JLabel firstNameLbl = new JLabel("First Name:");
   JLabel lastNameLbl = new JLabel("Last Name:");
   JLabel fullNameLbl = new JLabel("Full Name: <editing the name>");
   JLabel fillerLbl1 = new JLabel("                           ");
   JLabel fillerLbl2 = new JLabel("                           ");
   JTextField firstNameTxt = new JTextField("", 20);
   JTextField lastNameTxt = new JTextField("", 20);
   JButton editBtn = new JButton("Edit");
   JButton lockBtn = new JButton("Lock");

   //----------------------------------------------------------------
   //  Adds the components to a panel and adds panel to the frame
   //----------------------------------------------------------------
   public EditFullName() {
      JPanel panel = new JPanel();

      panel.add(firstNameLbl);
      panel.add(firstNameTxt);
      panel.add(lastNameLbl);
      panel.add(lastNameTxt);
      panel.add(fillerLbl1);
      panel.add(editBtn);
      panel.add(lockBtn);
      panel.add(fillerLbl2);
      panel.add(fullNameLbl);

      // Connects the buttons to the actionPerformed method
      editBtn.addActionListener(this);
      lockBtn.addActionListener(this);
    
      // Changing color and font of FullName label
      fullNameLbl.setForeground(Color.blue);
      fullNameLbl.setFont(new Font("Arial",Font.ITALIC,16));
      // Alter Font properties
      firstNameLbl.setFont(new Font("Courier",Font.BOLD,20));

      // Changing background color and panel size
      panel.setBackground(Color.cyan);
      panel.setPreferredSize(new Dimension (320, 120));
    
      // Adds panel to frame
      add(panel);
   }

   //----------------------------------------------------------------
   //  Responds to the Button clicks
   //----------------------------------------------------------------
   public void actionPerformed (ActionEvent e)
   {
      if (e.getSource() == editBtn) {  // sets text boxes for editing
         firstNameTxt.setEditable(true);
         lastNameTxt.setEditable(true);
         // Shows in edit state
         fullNameLbl.setText("Full Name: <editing the name>");
      } else {   // locks text boxes
         firstNameTxt.setEditable(false);
         lastNameTxt.setEditable(false);
         // Shows name
         fullNameLbl.setText("Full Name: " +
              firstNameTxt.getText() + " " + lastNameTxt.getText());
      }
      repaint();
   }

   //----------------------------------------------------------------
   // Main method - creates the JFrame
   //----------------------------------------------------------------
   public static void main(String[] args) {
      EditFullName frame = new EditFullName();
      frame.setTitle("Name Editor");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}
