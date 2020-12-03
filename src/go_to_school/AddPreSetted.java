package go_to_school;

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.*;
import javax.swing.event.*;



public class AddPreSetted extends JFrame{
    String[] weekDays={"월요일", "화요일", "수요일", "목요일", "금요일"};
    String[] timeLine={"9", "10", "11", "12", "13", "14", "15", "16","17", "18", "19", "20", "21", "22"};
    AddPreSetted(){
        this.setTitle("콤보박스 만들기 예제");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = this.getContentPane();
        
        JComboBox weekDaysCombo= new JComboBox(weekDays);
        JComboBox timeLineCombo = new JComboBox(timeLine);
        JTextField subjectText = new JTextField(12);
        JTextArea requirementsText = new JTextArea(7, 20); 
        JButton complete = new JButton("완료");
        
        JPanel combos = new JPanel();
        combos.add(new JLabel("요일"));
        combos.add(weekDaysCombo);
        combos.add(new JLabel("시간"));
        combos.add(timeLineCombo);
        
        JPanel texts = new JPanel();
        texts.add(new JLabel("과목"));
        texts.add(subjectText);
        texts.add(new JLabel("준비물"));
        texts.add(requirementsText);
        
        contentPane.add(combos,"North");
        contentPane.add(texts,"Center");
        contentPane.add(complete,"South");
        
        
        complete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//시간표에 추가
            	int r=0,c=0;
            	if (weekDaysCombo.getSelectedItem().toString().equals("월요일")) r=1;
            	else if (weekDaysCombo.getSelectedItem().toString().equals("화요일")) r=2;
            	else if (weekDaysCombo.getSelectedItem().toString().equals("수요일")) r=3;
            	else if (weekDaysCombo.getSelectedItem().toString().equals("목요일")) r=4;
            	else if (weekDaysCombo.getSelectedItem().toString().equals("금요일")) r=5;
            	
            	if (timeLineCombo.getSelectedItem().toString().equals("09")) c=0;
            	else if (timeLineCombo.getSelectedItem().toString().equals("10")) c=1;
            	else if (timeLineCombo.getSelectedItem().toString().equals("11")) c=2;
            	else if (timeLineCombo.getSelectedItem().toString().equals("12")) c=3;
            	else if (timeLineCombo.getSelectedItem().toString().equals("13")) c=4;
            	else if (timeLineCombo.getSelectedItem().toString().equals("14")) c=5;
            	else if (timeLineCombo.getSelectedItem().toString().equals("15")) c=6;
            	else if (timeLineCombo.getSelectedItem().toString().equals("16")) c=7;
            	else if (timeLineCombo.getSelectedItem().toString().equals("17")) c=8;
            	else if (timeLineCombo.getSelectedItem().toString().equals("18")) c=9;
            	else if (timeLineCombo.getSelectedItem().toString().equals("19")) c=10;
            	else if (timeLineCombo.getSelectedItem().toString().equals("20")) c=11;
            	else if (timeLineCombo.getSelectedItem().toString().equals("21")) c=12;
            	else if (timeLineCombo.getSelectedItem().toString().equals("22")) c=13;
            	
            	MyModel.TABLE_DATA[c][r] = subjectText.getText();
            	
            	
            	
            	//타임테이블로
         	   	if (subjectText.getText().isEmpty() == true ) {
         	   		JOptionPane.showMessageDialog(null, "\"과목명은 한글자 이상 입력해야 합니다.");
         	   	}
         	   	else if (requirementsText.getText().isEmpty() == true ) {
         	   		JOptionPane.showMessageDialog(null, "\"준비물은 한글자 이상 입력해야 합니다.");
         	   	}
         	   	else {
         	   		//MyModel에서 시간표 변경을 위한 ArrayList들
         	   		Client.weekDaysArray.add(weekDaysCombo.getSelectedItem().toString());
         	   		Client.timeLineArray.add(timeLineCombo.getSelectedItem().toString());
         	   		Client.subjectArray.add(subjectText.getText().toString());
         	   		int reqArraySize = requirementsText.getText().toString().split("\n").length;
         	   		for(int i=0; i<reqArraySize; i++) {
         	   			Client.requirementsArray.add(requirementsText.getText().toString().split("\n")[i]);
         	   		}
         	   		
         	   		new TimeTable();
         	   		dispose();
         	   	}
            }
        });
        
        this.setLocationRelativeTo(null);
        this.setSize(300,300);
        this.setVisible(true);
    }
}

