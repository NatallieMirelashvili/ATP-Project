package Factory;
import algorithms.search.BestFirstSearch;
public class BestCreator implements ICreator{
    @Override
    public BestFirstSearch create() {
        return new BestFirstSearch();
    }
}
