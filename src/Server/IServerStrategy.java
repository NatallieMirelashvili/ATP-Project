package Server;

import java.io.InputStream;
import java.io.OutputStream;

public interface IServerStrategy {
//    fromClient to get a massages (asked stuff) from client.
//    toClient to write a massages (answers) to client.
    void serverStrategy(InputStream fromClient, OutputStream toClient);
}
