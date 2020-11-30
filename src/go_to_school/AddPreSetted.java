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
    String[] weekDays={"������", "ȭ����", "������", "�����", "�ݿ���"};
    String[] timeLine={"9", "10", "11", "12", "13", "14", "15", "16","17", "18", "19", "20", "21", "22"};
    AddPreSetted(){
        this.setTitle("�޺��ڽ� ����� ����");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = this.getContentPane();
        
        JComboBox weekDaysCombo= new JComboBox(weekDays);
        JComboBox timeLineCombo = new JComboBox(timeLine);
        JTextField subjectText = new JTextField(12);
        JTextArea requirementsText = new JTextArea(7, 20); 
        JButton complete = new JButton("�Ϸ�");
        
        JPanel combos = new JPanel();
        combos.add(new JLabel("����"));
        combos.add(weekDaysCombo);
        combos.add(new JLabel("�ð�"));
        combos.add(timeLineCombo);
        
        JPanel texts = new JPanel();
        texts.add(new JLabel("����"));
        texts.add(subjectText);
        texts.add(new JLabel("�غ�"));
        texts.add(requirementsText);
        
        contentPane.add(combos,"North");
        contentPane.add(texts,"Center");
        contentPane.add(complete,"South");
        
        
        complete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//MyModel���� �ð�ǥ ������ ���� ArrayList��
            	Client.weekDaysArray.add(weekDaysCombo.getSelectedItem().toString());
            	Client.timeLineArray.add(timeLineCombo.getSelectedItem().toString());
            	Client.subjectArray.add(subjectText.getText().toString());
            	Client.requirementsArray.add(requirementsText.getText().toString());
            	System.out.println(Client.requirementsArray.toString());
            	//Ÿ�����̺��
           	 new TimeTable();
           	 dispose();
            }
        });
        
        this.setLocationRelativeTo(null);
        this.setSize(300,300);
        this.setVisible(true);
    }
}

