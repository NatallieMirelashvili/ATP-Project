package Factory;
import algorithms.mazeGenerators.SimpleMazeGenerator;
public class SimpleMazeCreator implements ICreator{
    @Override
    public SimpleMazeGenerator create() {
        return new SimpleMazeGenerator();
    }
}
