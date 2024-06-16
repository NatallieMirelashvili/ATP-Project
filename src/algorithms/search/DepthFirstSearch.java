package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {

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
        if(!checkSolInput(domain)){
            return null;
        }
        Solution sol;
        AState start = domain.getStartState();
        start.setCameFrom(start);
        open.push(start);
        while (!open.isEmpty()){
            AState current = open.pop();
            NumOfNodes++;
            close.put(current.toString(), current);
            if(!current.equals(domain.getGoalState()))
            {
                ArrayList<AState> successors = domain.getAllPossibleStates(current);
                for (AState suc: successors){
                    if(close.get(suc.toString()) != null)
                        continue;
                    suc.setCameFrom(current);
                    open.push(suc);

                }
            }
            else {
                close.put(current.toString(), current);
                break;
            }
        }
        sol = restorePath(close.get(domain.getGoalState().toString()), start);
        return sol;
    }
}
