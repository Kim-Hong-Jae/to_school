package go_to_school;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ChatClient {
    
    private static final String SERVER_IP = "192.168.219.107";
    private static final int SERVER_PORT = 1111;
    public static int brk = 0;
    public ChatClient() {
        

        String name = Login.name;
        Socket socket = new Socket();
        try {
            socket.connect( new InetSocketAddress(SERVER_IP, SERVER_PORT) );
            consoleLog("Entered the group chat.");
            System.out.println(name);
            new ChatWindow(name, socket).show();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            String request = "join@@" + name + "\r\n";
            pw.println(request);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void consoleLog(String log) {
        System.out.println(log);
    }
    
    private static String inputWindow() {
    	String name = null;
    	
    	JFrame newWindow = new JFrame("Name");
    	newWindow.setSize(400, 200);
     	newWindow.getContentPane().setLayout(new FlowLayout());
     	
     	JLabel inputLabel = new JLabel("대화명을 입력하세요.");
     	
     	JTextField nameText = new JTextField();
     	name=nameText.getText();
     	System.out.println(name);
 		inputLabel.add(nameText);
 		
 		newWindow.getContentPane().add(inputLabel);
 		newWindow.setVisible(true);
    	
 		System.out.println(name);
    	return name;
    }
}