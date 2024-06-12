package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int i, int j) {
        Maze newEmpty = new Maze(i, j);
        newEmpty.setValueAllPos(0);
        return newEmpty;
    }

}
