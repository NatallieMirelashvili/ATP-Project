package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;

public class SearchableMaze implements ISearchable{

//    Adapter field - adapt maze being searchable!
    private Maze myMaze;
    private MazeState startState;
    private MazeState goalState;

//    ******Contracture******

    public SearchableMaze(Maze myMaze) {
        if(myMaze == null || myMaze.getMazeMat() == null)
        {
            System.out.println("SearchableMaze constructor expected to not null maze input and maze with proper matrix\n");
            return;
        }
        this.myMaze = myMaze;
        if(myMaze.getStartPosition() == null)
        {
            System.out.println("SearchableMaze constructor expected to not null start point of the maze\n");
            return;
        }
        startState = new MazeState(myMaze.getStartPosition().toString());
        if(myMaze.getGoalPosition() == null)
        {
            System.out.println("SearchableMaze constructor expected to not null goal point of the maze\n");
            return;
        }
        goalState = new MazeState(myMaze.getGoalPosition().toString());

    }



//   searchable maze getters
    @Override
    public AState getStartState() {
        return startState;
    }

    @Override
    public AState getGoalState() {
        return goalState;
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        if(state == null){
            System.out.println("getAllPossibleStates expected to not null input\n");
            return null;
        }
        Position statePos = parseToPos(state);
        ArrayList<AState> res = new ArrayList<>();
        ArrayList<Position> neighbors = myMaze.getMyNeighborsInFrame(statePos);
        ArrayList<Position> diagonals =  getDiagonalsInFrame(state);
        ArrayList<Position> goodNeighbors = getDoors(neighbors);
        ArrayList<Position> goodDiagonal = getDoors(diagonals);
        res.addAll(parsePositionsToStates(goodNeighbors, 10));
        res.addAll(parsePositionsToStates(goodDiagonal, 15));
        return res;
    }


    //    ***Help Functions***

    /**getDoors - A function which filter a positions in the maze which are not passes (filled with 0)
     * Args: ArrayList<Position> optional - An array of neighbors to filter.
     *return: ArrayList<Position> - filtered array.
     * */
    private ArrayList<Position> getDoors(ArrayList<Position> optional){
        ArrayList<Position> passed = new ArrayList<>();
        for(Position pos: optional){
            if(pos.myVal(myMaze) == 0)
                passed.add(pos);
        }
        return passed;
    }

    /**
     * parsePositionsToStates - A function which parse a given array of Positions to AState type.
     *Args: ArrayList<Position> positions - array to parse.
     *      int cost - the cost it takes to get to these positions from the current position (can be 0).
     *return -  ArrayList<AState> - Parsed array.
     * */
    private ArrayList<AState> parsePositionsToStates(ArrayList<Position> positions, int cost){
        ArrayList<AState> res = new ArrayList<>();
        for(Position pos:positions){
            AState newState = new MazeState(pos.toString());
            newState.setCost(cost);
            res.add(newState);
        }
        return res;
    }


    /**
     * getDiagonalsInFrame - A function which find all the valid neighbors of current state which located diagonally to it.
     * Args: AState state - current state.
     * return: ArrayList<Position> - Array of current's diagonal neighbors.
     * */

    private ArrayList<Position> getDiagonalsInFrame(AState state){
        ArrayList<Position> res = new ArrayList<>();
        Position statePos = parseToPos(state);
        ArrayList<Position> neighbors = myMaze.getMyNeighbors(statePos); //[up,down,left,right]
        ArrayList<Position> toCheckDiagonals = new ArrayList<>();
        Position upRight = new Position(statePos.getRowIndex() - 1, statePos.getColumnIndex() + 1);
        Position upLeft = new Position(statePos.getRowIndex() - 1, statePos.getColumnIndex() - 1);
        Position downRight = new Position(statePos.getRowIndex() + 1, statePos.getColumnIndex() + 1 );
        Position downLeft = new Position(statePos.getRowIndex() + 1, statePos.getColumnIndex() - 1 );
        toCheckDiagonals.add(upRight);
        toCheckDiagonals.add(upLeft);
        toCheckDiagonals.add(downRight);
        toCheckDiagonals.add(downLeft);
        ArrayList<Position> goodDiagonals = myMaze.checkInFrame(toCheckDiagonals);
        for (Position goodDiagonal : goodDiagonals) {
            if (goodDiagonal.myVal(myMaze) == 0) {
                if (goodDiagonal.equals(upRight)) {
                    if (neighbors.get(0).myVal(myMaze) == 0 || neighbors.get(3).myVal(myMaze) == 0)
                        res.add(goodDiagonal);
                }
                if (goodDiagonal.equals(upLeft)) {
                    if (neighbors.get(0).myVal(myMaze) == 0 || neighbors.get(2).myVal(myMaze) == 0)
                        res.add(goodDiagonal);
                }
                if (goodDiagonal.equals(downRight)) {
                    if (neighbors.get(1).myVal(myMaze) == 0 || neighbors.get(3).myVal(myMaze) == 0)
                        res.add(goodDiagonal);
                }
                if (goodDiagonal.equals(downLeft)) {
                    if (neighbors.get(1).myVal(myMaze) == 0 || neighbors.get(2).myVal(myMaze) == 0)
                        res.add(goodDiagonal);
                }
            }
        }
        return res;
    }

    /**
     * parseToPos - A function which parse asked state to it position performance (place in the maze's matrix)
     * Args: AState state - the state you want to parse.
     * return: Position - parsed state.
     * */
    private Position parseToPos(AState state){
        MazeState ms = new MazeState(state.getState());
        int[] pos = ms.parseStateName();
        return new Position(pos[0], pos[1]);
    }



}
