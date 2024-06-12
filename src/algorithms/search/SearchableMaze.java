package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    Maze myMaze;

    public SearchableMaze(Maze myMaze) {
        this.myMaze = myMaze;
    }

    @Override
    public AState getStartState() {
        return null;
    }

    @Override
    public AState getGoalState() {
        return null;
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState state) {
        return null;
    }
}
