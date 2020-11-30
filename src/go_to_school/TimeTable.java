package go_to_school;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class TimeTable extends JFrame{
	 public TimeTable() {
		 Container contentPane = this.getContentPane();
		 
		 MyModel mm = new MyModel();
		 
		 JTable table = new JTable(mm);
		 table.getTableHeader().setReorderingAllowed(false);
		 table.getTableHeader().setResizingAllowed(false);
		 table.setFocusable(false);
		 table.setRowSelectionAllowed(false);
		 add(new JScrollPane(table));
		 
		 JPanel buttons = new JPanel();
		 JButton buttonChat = new JButton("ä�ù�");
		 JButton buttonRequirements = new JButton("�غ�");
		 JButton buttonAdd = new JButton("�ð�ǥ �߰�");
		 
		 buttons.add(buttonChat);
		 buttons.add(buttonRequirements);
		 buttons.add(buttonAdd);
		 add(buttons,"South");
		 
		 //ä�ù����� ���� ��ư
		 buttonChat.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 //TODO ����� Ǯ��
            	new ChatClient();
      		   	dispose();
             }
         });
		 
		//üũ����Ʈ�� ���� ��ư
		 buttonRequirements.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
             	//üũ����Ʈ��
            	 try {
					new Weather();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	 System.out.println(Weather.getRequirements().toString());

            	 new CheckList();
            	 dispose();
             }
         });
		 
		//�ð�ǥ �߰� â���� ���� ��ư
		 buttonAdd.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 
            	 new AddPreSetted();
            	 dispose();
             }
         });
		 
		 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 pack();
		 setSize(1000,400);
		 setVisible(true);
	  
	 }



	}
