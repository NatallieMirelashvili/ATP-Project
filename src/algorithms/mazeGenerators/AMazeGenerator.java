package algorithms.mazeGenerators;

abstract public class AMazeGenerator implements IMazeGenerator {
    Maze maze;
    public long measureAlgorithmTimeMillis (int i, int j){
        Maze maze = generate(i,j);
        long time = System.currentTimeMillis();
        this.maze = maze;
        return time;
    }

}
