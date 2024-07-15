package algorithms.mazeGenerators;
import algorithms.Product;

import java.io.Serializable;

abstract public class AMazeGenerator implements IMazeGenerator, Product, Serializable {

    public long measureAlgorithmTimeMillis (int i, int j){
        if(!checkValidInput(i, j)){
            System.out.println("measureAlgorithmTimeMillis expected positive values, got: " + i + ", " + j + "\n");
            return -1;
        }
        long start = System.currentTimeMillis();
        generate(i,j);
        long end = System.currentTimeMillis();
        return end - start;
    }


    protected boolean checkValidInput(int i, int j){
        return i > 0 && j > 0;
    }


}
