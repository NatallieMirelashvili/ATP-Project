package algorithms.search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;

import algorithms.mazeGenerators.Position;

public class SearchableMaze implements ISearchable{
//    Adapter field - adapt maze being searchable!
    Maze myMaze;

//    ******Contracture******

    public SearchableMaze(Maze myMaze) {
        this.myMaze = myMaze;
    }



//   searchable maze getters
    @Override
    public AState getStartState() {
        return new MazeState(myMaze.getStartPosition().toString());
    }

    @Override
    public AState getGoalState() {
        return new MazeState(myMaze.getGoalPosition().toString());
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        Position statePos = parseToPos(state);
        ArrayList<AState> res = new ArrayList<>();
        ArrayList<Position> neighbors = myMaze.getMyNeighborsInFrame(statePos);
        ArrayList<Position> diagonals =  getDiagonals(state);
        res.addAll(parsePositionsToStates(neighbors, 10));
        res.addAll(parsePositionsToStates(diagonals, 15));
        return res;
    }


//    ***Help Functions***

    private ArrayList<AState> parsePositionsToStates(ArrayList<Position> positions, int cost){
        ArrayList<AState> res = new ArrayList<>();
        for(Position pos:positions){
            AState newState = new MazeState(pos.toString());
            newState.setCost(cost);
            res.add(newState);
        }
        return res;
    }

    private ArrayList<Position> getDiagonals(AState state){
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

    private Position parseToPos(AState state){
        MazeState ms = new MazeState(state.getState());
        int[] pos = ms.parseStateName();
        return new Position(pos[0], pos[1]);
    }

    private int getMyVal(MazeState state){
        int[][] mazeMat = myMaze.getMazeMat();
        int[] position = state.parseStateName();
        return mazeMat[position[0]][position[1]];
    }

    public boolean isValid(MazeState state){
        return getMyVal(state) != 1;
    }

    public boolean isGoal(MazeState state){
        return state.equals(getGoalState());
    }




}
