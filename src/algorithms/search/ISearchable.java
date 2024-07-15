package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    /***
     * getStartState - getter
     * Args: None
     * return: AState - start point of the maze.
     */

    AState getStartState();

    /***
     * getStartState - getter
     * Args: None
     * return: AState - goal point of the maze.
     */
    AState getGoalState();

    /***
     * getAllPossibleStates - A function which return all the valid steps possible from a given point
     * Args: AState state - current state.
     * return:ArrayList<AState> - possible steps you can precede from the given state.
     */
    ArrayList<AState> getAllPossibleStates (AState state);

}
