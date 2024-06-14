package algorithms.search;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{

//    Constructor:

    public BestFirstSearch() {
        this.open =  new PriorityQueue<>(Comparator.comparingDouble(AState::getCost));;
    }

    @Override
    protected void setYourNames(){
        Name = "Best First Search";
    }

//    public Solution solve(ISearchable domain){
//        Solution sol;
//        AState start = domain.getStartState();
//        start.setCameFrom(start);
//        start.setCost(0);
//        open.add(start);
//        while (!open.isEmpty()){
//            AState current = open.poll();
//            NumOfNodes++;
//            close.add(current);
//            if(!current.equals(domain.getGoalState())){
//                ArrayList<AState> successors = domain.getAllPossibleStates(current);
//                for (AState suc: successors){
//                    if(close.contains(suc))
//                        continue;
//                    if(!open.contains(suc)){
//                        suc.setCost(suc.getCost() + current.getCost());
//                        suc.setCameFrom(current);
//                        open.add(suc);
//                    } else {
//                        AState neighborInOpen = closingCircle(suc);
//                        if(neighborInOpen.getCost() - neighborInOpen.getCameFrom().getCost() + current.getCost() < neighborInOpen.getCost()){
//                            neighborInOpen.setCost(suc.getCost() - neighborInOpen.getCameFrom().getCost() + current.getCost());
//                            neighborInOpen.setCameFrom(current);
//                        }
//                    }
//                }
//
//            }
//            else {
//                close.add(current);
//                break;
//            }
//        }
//
//        sol = restorePath(close.getLast(), start);
//        return sol;
//    }

//    private AState closingCircle(AState toFind){
//        for (AState state : open){
//            if (state.equals(toFind))
//                return state;
//        }
//        return null;
//    }
    @Override
    protected void dealWithIt(AState closing, AState father){
        if(closing.getCost() - closing.getCameFrom().getCost() + closing.getCost() < closing.getCost()) {
            closing.setCost(closing.getCost() - closing.getCameFrom().getCost() + father.getCost());
            closing.setCameFrom(father);
        }
    }


}
