package Server;

import Factory.Factory;
import IO.MyCompressorOutputStream;
import algorithms.Product;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;


import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void serverStrategy(InputStream fromClient, OutputStream toClient) {
        try{
            ObjectInputStream fromClientObj = new ObjectInputStream(fromClient);
            ObjectOutputStream toClientObj = new ObjectOutputStream(toClient);
            int[] params = (int[])fromClientObj.readObject();
            int rows = params[0];
            int columns = params[1];
            Configurations configurations = Configurations.getInstance();
            Factory factory = Factory.getInstance();
            Product factoryRes = factory.create(configurations.getProperty("mazeGeneratingAlgorithm"));
            AMazeGenerator GM;
            if(!(factoryRes instanceof AMazeGenerator)){
                System.out.println("config.properties contain invalid value in mazeGeneratingAlgorithm property");
                return;
            }
            GM = (AMazeGenerator) factoryRes;
            Maze maze = GM.generate(rows, columns);
            ByteArrayOutputStream byteArrOS = new ByteArrayOutputStream();
            MyCompressorOutputStream CM = new MyCompressorOutputStream(byteArrOS);
            CM.write(maze.toByteArray());
            toClientObj.writeObject(byteArrOS.toByteArray());
            toClient.flush();

            toClient.close();
            fromClient.close();

        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}

