package algorithms.mazeGenerators;

public interface IMazeGenerator {

    /**
     * generate - A function which all MazeGenerator must implement! Generating a valid maze with main condition to
     * enable a pass from point to point.
     * Pass <=> point filled with 0.
     * Wall <=> point filled with 1.
     *  Args: int i - number of rows in the maze.
     *        int j - number of columns in the maze.
     *  return: Maze - the maze which the class generated.
     * */
    Maze generate (int i, int j);

    /**
     * measureAlgorithmTimeMillis - A function which measure the time it take the class to create a maze.
     *  Args: int i - number of rows in the maze.
     *          int j - number of columns in the maze.
     *  return: time to create a maze in the class in milliseconds.
     * */
    long measureAlgorithmTimeMillis (int i, int j); //measure how much time take build a maze
}
