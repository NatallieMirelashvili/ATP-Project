package algorithms.search;


public class AState implements Comparable<AState> {
    protected String State;
    protected int cost;
    protected AState cameFrom;

    public AState(String state) {
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
}
