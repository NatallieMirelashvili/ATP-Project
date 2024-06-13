package algorithms.mazeGenerators;

public class Position {
    private int row;
    private int col;

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

    public boolean equals(Position other) {
        return row == other.getRowIndex() && col == other.getColumnIndex();
    }

    public int myVal(Maze maze){
        if (row>=0 && row < maze.getRows() && col>=0 && col < maze.getColumns())
            return maze.getMazeMat()[row][col];
        return -1;
    }



}
