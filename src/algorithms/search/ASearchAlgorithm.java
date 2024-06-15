package algorithms.search;
import java.util.ArrayList;
import java.util.HashMap;


public abstract class ASearchAlgorithm implements ISearchingAlgorithm{
//    Fields:
    protected String Name;
    protected int NumOfNodes;
    protected HashMap<String, AState> close;


    //    Constructor:
    public ASearchAlgorithm(){
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



    // Father part in solution
    @Override
    public Solution solve(ISearchable domain) {
        return null;
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
