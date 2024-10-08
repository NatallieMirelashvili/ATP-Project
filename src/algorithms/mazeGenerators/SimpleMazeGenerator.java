package algorithms.mazeGenerators;
import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int i, int j){
        if(!checkValidInput(i, j)){
            System.out.println("generate maze function expected positive values, got: " + i + ", " +  j + "\n");
            return null;
        }
//        using complex maze and add passes randomly.
        AMazeGenerator complexMaze = new MyMazeGenerator();
        Maze simpleMaze = complexMaze.generate(i, j);
        int[][] mazeMat = simpleMaze.getMazeMat();
        for (int row = 0; row < i; row++){
            for (int col = 0; col < j; col ++){
                if(mazeMat[row][col] == 1){
                    Random rand = new Random();
                    int choice = rand.nextInt(3);
                    if(choice == 0){
                        mazeMat[row][col] = 0;
                    }
                }
            }
        }
        return simpleMaze;
    }
}
