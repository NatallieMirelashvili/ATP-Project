package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    /**
     * This class generating an empty maze - all cells in this maze are passes (0).
     * */
    @Override
    public Maze generate(int i, int j) {
        if(!checkValidInput(i, j)){
            System.out.println("generate maze function expected positive values, got: " + i + ", " +  j + "\n");
            return null;
        }
        Maze empty = new Maze(i, j);
        empty.setValueAllPos(0);
        return empty;
    }

}
