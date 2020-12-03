package go_to_school;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;


public class CheckList {
	public JButton complete = new JButton("완료");
    public CheckList()  {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }

                JFrame frame = new JFrame("준비물 체크리스트");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                //완료 버튼을 통한 저장 및 다시 돌아가기
                complete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	//돌아가기
						try {
							new TimeTable();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				
                    	frame.dispose();
                    	
                    }
                });
            }
        });
    }
    
    
    public class TestPane extends JPanel {

        private JPanel mainList;
        
        public TestPane() {
            setLayout(new BorderLayout());
            
            mainList = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = 1;
            mainList.add(new JPanel(), gbc);
            
            add(new JScrollPane(mainList));
            
            //텍스트 필드 추가
            JTextField addText = new JTextField();
            add(addText, BorderLayout.NORTH);
            
            
            int totalReq = Client.requirementsArray.size();
            
            String preSetted;
            
            for(int i=0; i<totalReq; i++) {
            	preSetted = Client.requirementsArray.get(i);
            	System.out.println(preSetted);
            	JPanel panel = new JPanel();
                panel.add(new JCheckBox(preSetted));
                panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
                GridBagConstraints hbc = new GridBagConstraints();
                hbc.gridwidth = GridBagConstraints.REMAINDER;
                hbc.weightx = 1;
                hbc.fill = GridBagConstraints.HORIZONTAL;
                
                mainList.add(panel, hbc, 0);
                validate();
                repaint();
            }
            
            JPanel buttons = new JPanel();
            
            JButton add = new JButton("추가");
            
            //추가 버튼 클릭을 통한 수동 준비물 추가
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	
                	String addRequirements = addText.getText();
                	if (addRequirements.isEmpty() == true) {
                		JOptionPane.showMessageDialog(null, "\"준비물은 한글자 이상 입력해야 합니다.");
                	}
                	else {
                		Client.requirementsArray.add(addRequirements);
                		JPanel panel1 = new JPanel();
                    	panel1.add(new JCheckBox(addRequirements));
                        panel1.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
                        GridBagConstraints hbc = new GridBagConstraints();
                        hbc.gridwidth = GridBagConstraints.REMAINDER;
                        hbc.weightx = 1;
                        hbc.fill = GridBagConstraints.HORIZONTAL;
                        mainList.add(panel1, hbc, 0);
                        validate();
                        repaint();
                        addText.setText("");
                	}
                }
            });
           
            
            //위치배치
            buttons.add(add);
            buttons.add(complete);
            add(buttons, BorderLayout.SOUTH);

        }

        //전체 프레임 크기 설정
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 600);
        }
    }
}
