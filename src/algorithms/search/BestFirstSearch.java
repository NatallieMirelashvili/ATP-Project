package algorithms.search;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{

//    Constructor:

    public BestFirstSearch() {
        this.open =  new PriorityQueue<>(Comparator.comparingDouble(AState::getCost));
    }

    @Override
    protected void setYourNames(){
        Name = "Best First Search";
    }

    @Override
    protected void dealWithIt(AState closing, AState father){
        if(closing.getCost() - closing.getCameFrom().getCost() + father.getCost() < closing.getCost()) {
            closing.setCost(closing.getCost() - closing.getCameFrom().getCost() + father.getCost());
            closing.setCameFrom(father);
        }
    }


}
