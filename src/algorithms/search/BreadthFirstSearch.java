package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchAlgorithm{

    protected Queue<AState> open;

//    Constructor:

    public BreadthFirstSearch() {
        this.open= new LinkedList<>();
    }

    @Override
    protected void setYourNames() {
        Name = "Breadth First Search";
    }

    public Solution solve(ISearchable domain){
        Solution sol;
        AState start = domain.getStartState();
        start.setCameFrom(start);
        start.setCost(0);
        open.add(start);
        while (!open.isEmpty()){
            AState current = open.poll();
            NumOfNodes++;
            close.put(current.toString(), current);
            if(!current.equals(domain.getGoalState()))
            {
                ArrayList<AState> successors = domain.getAllPossibleStates(current);
                for (AState suc: successors){
                    if(close.get(suc.toString()) != null)
                        continue;
                    if(!open.contains(suc)){
                        suc.setCost(suc.getCost() + current.getCost());
                        suc.setCameFrom(current);
                        open.add(suc);
                    }
                    else {
                        AState neighborInOpen = closingCircle(suc);
                        dealWithIt(neighborInOpen, current);
                    }
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

    protected void dealWithIt(AState closing, AState father) {}
    protected AState closingCircle(AState toFind){return null;}


}
