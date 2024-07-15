package Factory;
import algorithms.search.BreadthFirstSearch;
public class BFSCreator implements ICreator{
    @Override
    public BreadthFirstSearch create() {
        return new BreadthFirstSearch();
    }
}
