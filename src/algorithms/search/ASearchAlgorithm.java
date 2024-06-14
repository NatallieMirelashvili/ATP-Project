package algorithms.search;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public abstract class ASearchAlgorithm implements ISearchingAlgorithm{
//    Fields:
    protected String Name;
    protected int NumOfNodes;

//    Constructor:
    public ASearchAlgorithm(){
        setYourNames();
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

    protected abstract void setYourNames();



}
