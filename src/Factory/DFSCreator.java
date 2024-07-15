package Factory;
import algorithms.search.DepthFirstSearch;

public class DFSCreator implements ICreator{

    @Override
    public DepthFirstSearch create() {
        return new DepthFirstSearch();
    }
}
