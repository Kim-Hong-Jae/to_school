package go_to_school;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ChatServerProcessThread extends Thread{
    private String nickname = null;
    private Socket socket = null;
    List<PrintWriter> listWriters = null;

    public ChatServerProcessThread(Socket socket, List<PrintWriter> listWriters) {
        this.socket = socket;
        this.listWriters = listWriters;
    }

    @Override
    public void run() {
        try {
            BufferedReader buffereedReader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            while(true) {
                String request = buffereedReader.readLine();

                if( request == null) {
                    consoleLog("Ŭ���̾�Ʈ�κ��� ���� ����");
                    doQuit(printWriter);
                    break;
                }

                String[] tokens = request.split("$");
                if("join".equals(tokens[0])) {
                    doJoin(tokens[1], printWriter);
                }
                else if("message".equals(tokens[0])) {
                    doMessage(tokens[1]);
                }
                else if("compRequest".equals(tokens[0])) {
                	doRequest();
                }
                else if("requirements".equals(tokens[0])) {
                	//���� tokens[1]�� ���ϱ�
                	String compareResult = "";
                	doCompare(compareResult);
                }
                else if("quit".equals(tokens[0])) {
                    doQuit(printWriter);
                }
            }
        }
        catch(IOException e) {
            consoleLog(this.nickname + "���� ä�ù��� �������ϴ�.");
        }
    }

    private void doQuit(PrintWriter writer) {
        removeWriter(writer);

        String data = this.nickname + "���� �����߽��ϴ�.";
        broadcast(data);
    }

    private void removeWriter(PrintWriter writer) {
        synchronized (listWriters) {
            listWriters.remove(writer);
        }
    }

    private void doMessage(String data) {
        broadcast("massage$" + this.nickname + ":" + data);
    }
    
    private void doRequest() {
    	broadcast( "reqRequest$" );
    }
    
    private void doCompare(String compareResult) {
    	broadcast( "compareResult$" + this.nickname + ":" + compareResult);
    }

    private void doJoin(String nickname, PrintWriter writer) {
        this.nickname = nickname;

        String data = nickname + "���� �����Ͽ����ϴ�.";
        broadcast(data);

        // writer pool�� ����
        addWriter(writer);
    }

    private void addWriter(PrintWriter writer) {
        synchronized (listWriters) {
            listWriters.add(writer);
        }
    }

    private void broadcast(String data) {
        synchronized (listWriters) {
            for(PrintWriter writer : listWriters) {
                writer.println(data);
                writer.flush();
            }
        }
    }

    private void consoleLog(String log) {
        System.out.println(log);
    }
}