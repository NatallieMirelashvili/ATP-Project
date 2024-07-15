package Factory;
import algorithms.mazeGenerators.EmptyMazeGenerator;
public class EmptyMazeCreator implements ICreator{
    @Override
    public EmptyMazeGenerator create() {
        return new EmptyMazeGenerator();
    }
}
