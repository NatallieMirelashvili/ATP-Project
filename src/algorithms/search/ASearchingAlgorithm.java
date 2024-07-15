package algorithms.search;
import algorithms.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public abstract class ASearchingAlgorithm implements ISearchingAlgorithm, Product, Serializable {
//    Fields:
    protected String Name;
    protected int NumOfNodes;
    protected HashMap<String, AState> close;


    //    Constructor:
    public ASearchingAlgorithm(){
        setYourNames();
        close = new HashMap<>();
    }

//    Getters:
    @Override
    public String getName() {
        return Name;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return NumOfNodes;
    }


    protected boolean checkSolInput(ISearchable searchable){
        if(searchable == null || searchable.getGoalState() == null || searchable.getStartState() == null){
            System.out.println("solve function expected to not null searchable input update with start and goal state\n");
            return false;
        }
        return true;
    }

    /***
     * restorePath - A function which restore the solution from the Goal state to the Start state.
     *Args: AState tail - Goal state.
     *      AState start - Start state.
     * return: the flow of the state this solution went on.
     */

    protected Solution restorePath(AState tail, AState start){
        ArrayList<AState> backwards = new ArrayList<>();
        AState runner = tail;
        backwards.add(tail);
        while(!runner.equals(start)){
            backwards.add(runner.cameFrom);
            runner = runner.cameFrom;
        }
        Solution sol = new Solution();
        for(int i = backwards.size() - 1; i >= 0; i--){
            sol.addNode(backwards.get(i));
        }
        return sol;
    }


// Children must implement
    /**
     * setYourNames - A method which set the algorithm for solution name.
     * */
    protected abstract void setYourNames();



}
