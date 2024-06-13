package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstSearch extends BreadthFirstSearch{
    PriorityQueue<AState> open = new PriorityQueue<>(Comparator.comparingDouble(AState::getCost));
    ArrayList<AState> close = new ArrayList<>();

    public Solution solve(ISearchable domain){
        Solution sol = new Solution();
        AState start = domain.getStartState();
        start.setCameFrom(start);
        start.setCost(0);
        open.add(start);
        while (!open.isEmpty()){
            AState current = open.poll();
            NumOfNodes++;
            close.add(current);
            sol.addNode(current);
            if(!current.equals(domain.getGoalState())){
                ArrayList<AState> successors = domain.getAllPossibleStates(current);
                for (AState suc: successors){
                    if(close.contains(suc))
                        continue;
                    if(!open.contains(suc)){
                        suc.setCost(suc.getCost() + current.getCost());
                        suc.setCameFrom(current);
                        open.add(suc);
                    } else if (open.contains(suc)){
                        if(suc.getCost() - suc.getCameFrom().getCost() + current.getCost() < suc.getCost()){
                            suc.setCost(suc.getCost() - suc.getCameFrom().getCost() + current.getCost());
                            suc.setCameFrom(current);
                        }
                    }
                }

            }
        }
        return sol;
    }



}
