package algorithms.search;

import java.util.Objects;

public class AState {
    protected String State;
    protected double cost;
    protected AState cameFrom;

    public AState(String state) {
        State = state;
    }

//    Getters:

    public String getState() {
        return State;
    }

    public double getCost() {
        return cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCameFrom(AState cameFrom) {
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
}
