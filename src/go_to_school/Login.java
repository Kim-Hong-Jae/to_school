package go_to_school;


import javax.swing.*;

import java.awt.event.*;

public class Login extends JFrame {

   public static String name;
   public Login() {

       JPanel panel = new JPanel();

       JLabel label = new JLabel("ID : ");


       JTextField txtID= new JTextField(10);

       JButton logBtn = new JButton("Log in");


       panel.add(label);
       panel.add(txtID);
       panel.add(logBtn);
       
       logBtn.addActionListener( new ActionListener() {
                       public void actionPerformed(ActionEvent e) {
                    	   if (txtID.getText().isEmpty() == true ) {
                    		   JOptionPane.showMessageDialog(null, "\"대화명은 한글자 이상 입력해야 합니다.");
                           }
                    	   else {
                    		   new TimeTable();
                    		   name = txtID.getText();
                    		   dispose();
                    	   }
                       }
               } );

       add(panel);
       setVisible(true);
       setSize( 600 , 400);
       setLocationRelativeTo(null);
       setResizable(false);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }
   public String getName() {
	   return name;
   }
}