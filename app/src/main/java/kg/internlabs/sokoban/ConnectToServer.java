package kg.internlabs.sokoban;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectToServer extends Thread {
    private int message;
    Socket echoSocket = new Socket("194.152.37.7", 4445);

    public ConnectToServer(int message) throws IOException {
        super();
        this.message = message;
    }

    public void go(){
        start();
    }

    public void run(){
        System.out.println("Thread is running...");
        send(message);
    }

    public void send(int level) {
        try {
            System.out.println("Start send.");

            PrintWriter printWriter = new PrintWriter(echoSocket.getOutputStream());
            printWriter.println(level);
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse(){
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(echoSocket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String response = bufferedReader.readLine();

            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return String.valueOf(e);
        }
    }

//    public static void main(String[] args) throws IOException {
//        ConnectToServer connectToServer = new ConnectToServer(7);
//        connectToServer.go();
//        int[][] map = connectToServer.getResponse();
//        System.out.println(map);
//    }
}
