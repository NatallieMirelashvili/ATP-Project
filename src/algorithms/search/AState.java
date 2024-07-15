package algorithms.search;


import java.io.Serializable;

public class AState implements Comparable<AState>, Serializable {

//    Fields:
    protected String State;
    protected int cost;
    protected AState cameFrom;

//    Constructor:

    public AState(String state) {
        if(state.equals(""))
        {
            System.out.println("AState expected to not empty string input\n");
            return;
        }
        State = state;
   }

//    Getters:

    public String getState() {
        return State;
    }

    public int getCost() {
        return cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

//    Setters:

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCameFrom(AState cameFrom) {
        if(cameFrom != null)
            this.cameFrom = cameFrom;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState otherState = (AState) o;
        return State.equals(otherState.getState());
    }

    @Override
    public int hashCode() {
        return State != null ? State.hashCode() : 0;
    }

    @Override
    public int compareTo(AState o) {
        return  Integer.compare(this.cost, o.cost);
    }

    @Override
    public String toString() {
        return State;
    }
}
