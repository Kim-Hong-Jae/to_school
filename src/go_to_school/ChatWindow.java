package go_to_school;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class ChatWindow {
    private String name;
    private Frame frame;
    private Panel pannel;
    private Button buttonSend;
    private Button buttonToTimeTable;
    private Button buttonToCompare;
    private TextField textField;
    private TextArea textArea;
    private Socket socket;

    public ChatWindow(String name, Socket socket) {
    	System.out.println("Enter chatWindow");
        this.name = name;
        frame = new Frame(name);
        pannel = new Panel();
        buttonSend = new Button("전송");
        buttonToTimeTable = new Button("메인화면");
        buttonToCompare = new Button("공유");
        textField = new TextField();
        textArea = new TextArea(30, 80);
        this.socket = socket;
        frame.setVisible(true);
        
        new ChatClientReceiveThread(socket).start();
    }

    public void show() {
        // 전송 버튼
        buttonSend.setBackground(Color.GRAY);
        buttonSend.setForeground(Color.WHITE);
        buttonSend.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                sendMessage();
            }
        });
        

	    // 돌아가기 버튼
	    buttonToTimeTable.setBackground(Color.GRAY);
	    buttonToTimeTable.setForeground(Color.WHITE);
	    buttonToTimeTable.addActionListener( new ActionListener() {
	        @Override
	        public void actionPerformed( ActionEvent actionEvent ) {
	            new TimeTable();
	            frame.dispose();
	        }
	    });
	    
	    // 공유 요청 버튼
	    buttonToCompare.setBackground(Color.GRAY);
	    buttonToCompare.setForeground(Color.WHITE);
	    buttonToCompare.addActionListener( new ActionListener() {
	        @Override
	        public void actionPerformed( ActionEvent actionEvent ) {
	        	sendShare();
	        }
	    });

        // 채팅 text
        textField.setColumns(80);
        textField.addKeyListener( new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                char keyCode = e.getKeyChar();
                if (keyCode == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });

        // panel들
        pannel.setBackground(Color.LIGHT_GRAY);
        pannel.add(textField);
        pannel.add(buttonSend);
        pannel.add(buttonToTimeTable);
        pannel.add(buttonToCompare);
        frame.add(BorderLayout.SOUTH, pannel);

        // TextArea 설정
        textArea.setEditable(false);
        frame.add(BorderLayout.CENTER, textArea);

        // Frame 종료 설정
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                PrintWriter pw;
                try {
                    pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
                    String request = "quit@@\r\n";
                    pw.println(request);
                    System.exit(0);
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        frame.setVisible(true);
        frame.pack();
        

    }
    


    // 쓰레드를 만들어서 대화를 보내기
    private void sendMessage() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            String message = textField.getText();
            String request = "message@@" + message + "\r\n";
            pw.println(request);

            textField.setText("");
            textField.requestFocus();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    //준비물 보내주기
    private void sendReq(String requirements) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            // 준비물 넣는 곳
            String request = "requirements@@" + requirements + "\r\n";
            pw.println(request);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //공유 요청 보내기
    private void sendShare() {
        PrintWriter pw;
        try {
        	Client.isSendReq = true;
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            // 준비물 넣는 곳
            String request = "shareRequest@@" + "\r\n";
            pw.println(request);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ChatClientReceiveThread extends Thread{
        Socket socket = null;

        ChatClientReceiveThread(Socket socket){
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                while(true) {
                	String request = br.readLine();
                	String[] tokens = request.split("@@");
                	if("joinComplete".equals(tokens[0])) {
                		textArea.append(tokens[1]);
                        textArea.append("\n");
                	}
                	else if("message".equals(tokens[0])) {
                		textArea.append(tokens[1]);
                        textArea.append("\n");
                    }
                	//TODO	asad
                	else if("reqRequest".equals(tokens[0])&&Client.isSendReq){
                		 int totalReq = Client.requirementsArray.size();
                		 String preSetted = Client.requirementsArray.get(0);
                		 for(int i=1; i<totalReq; i++) {
                         	preSetted = preSetted + "," + Client.requirementsArray.get(i);
                         }
                		sendReq(preSetted);
                    }
                	else if("shareResult".equals(tokens[0])){
                		Client.isSendReq = false;
                		String[] nameReq = tokens[1].split(":");
                		textArea.append(nameReq[0]);
                        textArea.append("\n");
                        textArea.append(nameReq[1]);
                        textArea.append("\n");
                    }
                    
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
