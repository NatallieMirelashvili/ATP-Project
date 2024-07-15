package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {

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
        if(!checkSolInput(domain)){
            return null;
        }
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

    /**
     * dealWithIt - Note that the main different between Best and BFS is the way they deal with circles.
     *  Best consider to perform an edge relaxation in reaching to the closing circle node (Is the current cameFrom cost
     *  less than the other potential cameFrom?). Although BFS not interested with this issue therefor it will be
     *  nothing about it.
     *So, BFS doesn't have any implementation in this function, and Best perform his edge relaxation if it needed.
     * Args: AState closing - The node that closing circle (discovered at least twice by different neighbors)
     *      AState father - The second (or more) potential cameFrom.
     *return: None
     * */
    protected void dealWithIt(AState closing, AState father) {}

    /**
     * closingCircle - A methods which find the closing circle node if there is any in the open queue.
     * Args: AState toFind - The node that need to be found in the open queue.
     * return: BFS - null (not interested to deal with such that nodes).
     *          Best - The founded closing circle node. Not that it will be return null if the node is not in the open
     *          BUT this case will never happen because if we got into this function according the if - else flow in the
     *          algorithm the closing circle node is in the open queue (else, it is not a closing circle node!).
     * */
    protected AState closingCircle(AState toFind){return null;}


}
