package algorithms.mazeGenerators;

abstract public class AMazeGenerator implements IMazeGenerator {

    public long measureAlgorithmTimeMillis (int i, int j){
        long start = System.currentTimeMillis();
        generate(i,j);
        long end = System.currentTimeMillis();
        return end - start;
    }


}
