package Factory;
import algorithms.mazeGenerators.MyMazeGenerator;
public class MyMazeCreator implements ICreator {

    @Override
    public MyMazeGenerator create() {
        return new MyMazeGenerator();
    }
}
