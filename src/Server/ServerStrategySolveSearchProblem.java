package Server;
import IO.MyCompressorOutputStream;
import algorithms.Product;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;
import Factory.Factory;
import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    File cacheMemo;
    public ServerStrategySolveSearchProblem() {
        String path = System.getProperty("java.io.tmpdir");
        cacheMemo = new File(path);
    }


    @Override
    public void serverStrategy(InputStream fromClient, OutputStream toClient) {
        try{
            ObjectInputStream fromClientObj = new ObjectInputStream(fromClient);
            ObjectOutputStream toClientObj = new ObjectOutputStream(toClient);
            Maze toSolve = (Maze) fromClientObj.readObject();
            int hashMaze = toSolve.hashCode();
            Solution findSolution = find(Integer.toString(hashMaze));
            if(findSolution == null){
                findSolution = solveAndInsertDIR(toSolve);
            }
            toClientObj.writeObject(findSolution);
            toClient.flush();

            fromClient.close();
            toClient.close();
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }



    private Solution parseSTRToSol(File file){
        Solution solution = new Solution();
        try (BufferedReader reader = new BufferedReader(new FileReader(file.getPath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                AState node = new AState(line);
                solution.addNode(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return solution;
    }

    private Solution find(String fileName){
        File[] tempFiles = cacheMemo.listFiles();
        if (tempFiles != null) {
            for (File file : tempFiles) {
                if(file.getName().equals(fileName)){
                    return parseSTRToSol(file);
                }
            }
        }
        return null;
    }

    private Solution solveAndInsertDIR(Maze toSolve) {
        Configurations configurations = Configurations.getInstance();
        //Type Cast! unboxing Product to SM
        ASearchingAlgorithm SM;
        Factory factory = Factory.getInstance();
        Product factoryRes = factory.create(configurations.getProperty("mazeSearchingAlgorithm"));
        if(factoryRes instanceof ASearchingAlgorithm)
        {
            SM = (ASearchingAlgorithm) factoryRes;
        }
        else {
            System.out.println("config.properties contain invalid value in mazeSearchingAlgorithm property");
            return null;
        }
        ISearchable searchable = new SearchableMaze(toSolve);
        Solution solution = SM.solve(searchable);
        try {
            // Create a temporary file
            File tempFile = File.createTempFile(""+toSolve.hashCode()+"", ".txt", new File(cacheMemo.getPath()));
            try (PrintWriter writer = new PrintWriter(tempFile)) {
                for(AState step: solution.getSolutionPath()){
                    writer.println(step.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return solution;

    }


}
