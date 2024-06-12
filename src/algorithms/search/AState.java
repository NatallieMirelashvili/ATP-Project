package algorithms.search;

import java.util.Objects;

public class AState {
    private String State;
    private double cost;
    private AState cameFrom;

    public AState(String state) {
        State = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState otherState = (AState) o;
        return Objects.equals(State, otherState.State);
    }

    @Override
    public int hashCode() {
        return State != null ? State.hashCode() : 0;
    }
}
