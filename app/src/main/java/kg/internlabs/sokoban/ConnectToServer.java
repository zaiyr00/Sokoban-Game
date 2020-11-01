package kg.internlabs.sokoban;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Sokoban Game
 * Intern Labs 2.0
 * @version 1.0
 * @author Zaiyr Sharsheev <zaiyr.00@gmail.com>
 * 01.11.2020
 */

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

    // request on server
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

    // response from server
    public String getResponse(){
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(echoSocket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder content = new StringBuilder();
            String line;
            // while output isn't empty, add every line of output in content obj
            while (!(line = bufferedReader.readLine()).isEmpty()) {
                content.append(line);
                content.append(System.lineSeparator());
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return String.valueOf(e);
        }
    }
}
