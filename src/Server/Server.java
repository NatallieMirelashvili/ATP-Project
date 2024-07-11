package Server;

public class Server {
    IServerStrategy strategy;

    public Server(IServerStrategy strategy) {
        this.strategy = strategy;
    }

    public void start(){}



    public void setStrategy(IServerStrategy strategy) {
        this.strategy = strategy;
    }
}
