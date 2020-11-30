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
		 JButton buttonChat = new JButton("채팅방");
		 JButton buttonRequirements = new JButton("준비물");
		 JButton buttonAdd = new JButton("시간표 추가");
		 
		 buttons.add(buttonChat);
		 buttons.add(buttonRequirements);
		 buttons.add(buttonAdd);
		 add(buttons,"South");
		 
		 //채팅방으로 가는 버튼
		 buttonChat.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 //TODO 대기모드 풀기
            	new ChatClient();
      		   	dispose();
             }
         });
		 
		//체크리스트로 가는 버튼
		 buttonRequirements.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
             	//체크리스트로
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
		 
		//시간표 추가 창으로 가는 버튼
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
