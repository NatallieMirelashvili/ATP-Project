package algorithms.search;

import java.util.Stack;

public class DepthFirstSearch extends ASearchAlgorithm{

    private Stack<AState> open;

    public DepthFirstSearch() {
        this.open = new Stack<>();
    }

    @Override
    protected void setYourNames() {
        Name = "Depth First Search";
    }

    @Override
    public Solution solve(ISearchable domain) {
        return null;
    }
}
