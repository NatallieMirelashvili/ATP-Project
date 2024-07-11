package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private boolean stop;
    private ExecutorService threadPool;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
//       TO DO:  read nThreads from config later.
       int nThreads = 2;
        this.threadPool = Executors.newFixedThreadPool(nThreads);
        stop = false;

    }



    public void start(){
        try {
            //Create new ServerSocket:
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            // handle client:
            // using setSoTimeout function, if it takes listeningIntervalMS milliseconds for client to connect Server,
            // Server won't wait!
            while (!stop) {
                try {
//                    Trying to serve client:
                    Socket clientSocket = serverSocket.accept();
                    // Insert the new task into the thread pool:
                    threadPool.submit(() -> {
                        handleClient(clientSocket);
                    });


                }
                catch (SocketTimeoutException e){
//                    No clients getting in time!
                    System.out.println(e.getMessage());;
                }
            }
//            closing server if stopped! To do another Server's task - make a new connection.
            serverSocket.close();
//            ShutDown threadPool without stopping any interrupts or killing running threads
            threadPool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



//  start request (as strategy) to please clients (this function is the task of all Threads!)
    private void handleClient(Socket clientSocket) {
        try {
//
            strategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void stop(){
        stop = true;
    }


}
