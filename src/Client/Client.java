package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
//    Server IP to connect to:
    private InetAddress serverIP;

    // Server Port to connect to:
    private int serverPort;

    // Client Strategy:
    private IClientStrategy strategy;

    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    public void communicateWithServer(){
//        Trying to connect Server by IP & Port
        try(Socket serverSocket = new Socket(serverIP, serverPort)){
            // ***Maybe swap input serverSocket with output serverSocket***
            strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
