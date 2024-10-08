package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {
    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRowIndex() {
        return row;
    }


    public int getColumnIndex() {
        return col;
    }

    @Override
    public String toString() {
        return "{" + row + "," + col + "}";
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }



    /**
     *myVal -  A function which find this position's value in a given maze.
     * Args: Maze maze - A given maze to check this position's value in.
     * return - int - the value (0 or 1).
     * */
    public int myVal(Maze maze){
        if (row>=0 && row < maze.getRows() && col>=0 && col < maze.getColumns())
            return maze.getMazeMat()[row][col];
        return -1;
    }



}
