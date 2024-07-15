//package Server;
//
//import IO.MyCompressorOutputStream;
//import algorithms.mazeGenerators.Maze;
//import algorithms.mazeGenerators.MyMazeGenerator;
//
//import java.io.*;
//
//public class ServerStrategyGenerateMaze implements IServerStrategy{
//    @Override
//    public void serverStrategy(InputStream fromClient, OutputStream toClient) {
//        try{
//            ObjectInputStream fromClientObj = new ObjectInputStream(fromClient);
//            ObjectOutputStream toClientObj = new ObjectOutputStream(toClient);
//            int[] params = (int[]) fromClientObj.readObject();
//            int rows = params[0];
//            int columns = params[1];
//            //TO DO: find the asked Maze style from configuration.
//            MyMazeGenerator GM = new MyMazeGenerator();
//            Maze maze = GM.generate(rows, columns);
//            MyCompressorOutputStream CM = new MyCompressorOutputStream(toClient);
//            //TO DO: shrink maze and send to Client throw toClientObj stream
//            //toClientObj.writeObject(byte array representing maze);
//
//        }
//        catch (IOException | ClassNotFoundException e){
//            e.printStackTrace();
//        }
//
//    }
//}
