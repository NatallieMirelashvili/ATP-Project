package algorithms.mazeGenerators;

public interface IMazeGenerator {

    public Maze generate (int i, int j); //return maze with i rows and j columns
    public long measureAlgorithmTimeMillis (int i, int j); //measure how much time take build a maze
}
