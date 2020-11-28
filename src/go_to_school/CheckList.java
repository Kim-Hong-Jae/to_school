package go_to_school;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class CheckList {
	public JButton complete = new JButton("�Ϸ�");
    public CheckList()  {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }

                JFrame frame = new JFrame("�غ� üũ����Ʈ");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                //�Ϸ� ��ư�� ���� ���� �� �ٽ� ���ư���
                complete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	//���ư���
						try {
							new TimeTable();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
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
            
            //�ؽ�Ʈ �ʵ� �߰�
            JTextField addText = new JTextField();
            add(addText, BorderLayout.NORTH);
            
            //TODO �������� �����ͼ� �̸� ������ �غ� ����
            String preSetted = "�̸� ������ �غ�";
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
            
            JPanel buttons = new JPanel();
            
            JButton add = new JButton("�߰�");
            
            //�߰� ��ư Ŭ���� ���� ���� �غ� �߰�
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	String addRequirements = addText.getText();
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
            });
           
            
            //��ġ��ġ
            buttons.add(add);
            buttons.add(complete);
            add(buttons, BorderLayout.SOUTH);

        }

        //��ü ������ ũ�� ����
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 600);
        }
    }
}
