package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Maze {

//    fields:
    private static final String RESET = "\033[0m";
    private static final String GREEN = "\033[0;32m";
    public static final String RED = "\033[0;31m";
    private int[][] mazeMat;
    private Position start;
    private Position end;
    private int rows;
    private int columns;


    //   ****Constructors****
    public Maze(int row, int col) {
        if(row < 0 || col < 0)
        {
            System.out.println("Maze constructor expected a positive value, got " + row + ", " +  col + "\n");
            return;
        }
        this.rows = row;
        this.columns = col;
        this.mazeMat = new int[row][col];
        chooseStart();
        chooseEnd();
    }
//  default maze:
    public Maze(int[][] specificMat){
        if(specificMat == null)
        {
            System.out.println("Maze default constructor expected not null argument\n");
            return;
        }
        mazeMat = specificMat;
        rows = specificMat.length;
        columns = specificMat[0].length;
        start = new Position(0,0);
        end = new Position(rows - 1, columns - 1);
    }


//    ***Help Functions***
        /**
         * FrameTopDownDoors - An help function which help you find all the passes in the top and down frame of the maze
         * Args: None
         * return: ArrayList<Position> - positions which located on the frame and demonstrate a pass(filled with 0).
         * * */
    private ArrayList<Position> FrameTopDownDoors(){
        ArrayList<Position> res = new ArrayList<>();
        for (int j = 0; j < columns; j++){
            if (mazeMat[0][j] == 0)
                res.add(new Position(0, j));
            if (mazeMat[rows - 1][j] == 0)
                res.add(new Position(rows - 1, j));
        }
        return res;
    }

    /**
     * FrameLeftRightDoors - An help function which help you find all the passes on the left and right frame of the maze
     * Args: None
     * return: ArrayList<Position> - positions which located in the frame and demonstrate a pass(filled with 0).
     * * */
    private ArrayList<Position> FrameLeftRightDoors(){
        ArrayList<Position> res = new ArrayList<>();
        for (int i = 0; i < rows; i++){
            if (mazeMat[i][0] == 0)
                res.add(new Position(i, 0));
            if (mazeMat[i][columns - 1] == 0)
                res.add(new Position(i, columns - 1));
        }
        return res;
    }

    /**
     * FramePosDoors - An help function which help you find all the passes on the whole frame of the maze
     * Args: None
     * return: ArrayList<Position> - positions which located in the frame and demonstrate a pass(filled with 0).
     * * */

    public ArrayList<Position> FramePosDoors(){
        ArrayList<Position> allDoorsInFrame = new ArrayList<>();
        allDoorsInFrame.addAll(FrameTopDownDoors());
        allDoorsInFrame.addAll(FrameLeftRightDoors());
        return allDoorsInFrame;
    }

    /**
     * chooseStart - chose a random and valid (on frame and filled with 0) start position.
     * Args: None
     * return: None
     * * */

    private void chooseStart(){
        ArrayList<Position> allPossibleStartPoint = FramePosDoors();
        Random rand = new Random();
        int randIDX = rand.nextInt(allPossibleStartPoint.size());
        start = allPossibleStartPoint.get(randIDX);
    }

    /**
     * chooseEnd - chose a random and valid (on frame, not equal to start position and filled with 0) goal position.
     * Args: None
     * return: None
     * * */

    public void chooseEnd(){
        ArrayList<Position> allPossibleEndPoint = FramePosDoors();
        Random rand = new Random();
        int randIDX = rand.nextInt(allPossibleEndPoint.size());
        end = allPossibleEndPoint.get(randIDX);
//        checking end != start
        while (end.equals(start)) {
            randIDX = rand.nextInt(allPossibleEndPoint.size());
            end = allPossibleEndPoint.get(randIDX);
        }
    }




//    ***Getters****

    public Position getStartPosition() {
        return start;
    }

    public Position getGoalPosition() {
        return end;
    }

    public int[][] getMazeMat() {
        return mazeMat;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    /**getMyNeighbors - A function which find all the given position's valid (inside the matrix) neighbors.
     * Args: Position pos - the position you want to find its neighbors.
     * return:  ArrayList<Position> - the valid neighbors.
     * */
    public ArrayList<Position> getMyNeighbors(Position pos){
        if(pos == null){
            System.out.println("getMyNeighbors expected to not null input\n");
            return null;
        }
        Position up = new Position(pos.getRowIndex() - 1, pos.getColumnIndex());
        Position down = new Position(pos.getRowIndex() + 1, pos.getColumnIndex());
        Position left = new Position(pos.getRowIndex(), pos.getColumnIndex() -1 );
        Position right = new Position(pos.getRowIndex(), pos.getColumnIndex() + 1 );
        ArrayList<Position> toCheck = new ArrayList<>();
        toCheck.add(up);
        toCheck.add(down);
        toCheck.add(left);
        toCheck.add(right);
        return toCheck;
    }

    /**
     * getMyNeighborsInFrame - A function which find the given position neighbors on the frame only.
     *  Args: Position pos - the position you want to find its neighbors.
     * return:  ArrayList<Position> - the valid on frame neighbors.
     * */

    public ArrayList<Position> getMyNeighborsInFrame(Position pos){
        if(pos == null){
            System.out.println("getMyNeighborsInFrame expected to not null input\n");
            return null;
        }
        ArrayList<Position> res = getMyNeighbors(pos);
        return checkInFrame(res);
    }

    /**
     * checkInFrame - A function which filter all the out of frame positions in a given array.
     *Args: ArrayList<Position> posToCheck - given position array.
     * returns: ArrayList<Position> in frame (include) positions.
     * */

    public ArrayList<Position> checkInFrame (ArrayList<Position> posToCheck) {
        if(posToCheck == null){
            System.out.println("checkInFrame expected to not null input\n");
            return null;
        }
        int i;
        ArrayList<Position> res = new ArrayList<>();
        for (i = 0; i < posToCheck.size(); i++) {
            if (0 <= posToCheck.get(i).getRowIndex() && posToCheck.get(i).getRowIndex() < rows &&
                    0 <= posToCheck.get(i).getColumnIndex() && posToCheck.get(i).getColumnIndex() < columns) {
                res.add(posToCheck.get(i));
            }
        }
        return res;
    }


    //    ***Setters****

    public void setValueAllPos(int fill){
        for (int[] ints : mazeMat) {
            Arrays.fill(ints, fill);
        }
    }

    public void setValueByPos(Position asked, int val){
        mazeMat[asked.getRowIndex()][asked.getColumnIndex()] = val;
    }


    public void print() {
        StringBuilder out = new StringBuilder();
        out.append("{\n");
        int i;
        int j;
        for (i = 0; i < rows; i++){
            out.append("{");
            for (j = 0 ; j < columns; j ++){
                if(start.getRowIndex() == i && start.getColumnIndex() == j){
                    if (j != columns -1 )
                        out.append(RED + "S" + RESET).append(",");
                    else{
                    out.append(RED + "S" + RESET);
                    }
                }
                else if(end.getRowIndex() == i && end.getColumnIndex() == j ){
                    if (j != columns -1 )
                        out.append(GREEN + "E" + RESET).append(",");
                    else {
                        out.append(GREEN + "E" + RESET);
                    }
                }
                else {
                    if (j != columns - 1)
                        out.append(mazeMat[i][j]).append(",");
                    else{
                    out.append(mazeMat[i][j]);
                    }
                }
            }
            out.append("}\n");
        }
        out.append("}");
        System.out.println(out);
    }

    /**
     * ExceptPosToMaze - A function which return if the given position is okay to join to one path solution maze.
     * If the position has only one neighbor filled with 0 -> it means that this position have only one way reaching to.
     * So it will join the path.
     *Args: ArrayList<Position> neighbors - neighbors of the tested position.
     * returns: boolean: okay to join the path?
     * */
    public boolean ExceptPosToMaze(ArrayList<Position> neighbors) {
        int counter = 0;
        for (Position neighbor: neighbors){
            if(neighbor.myVal(this) == 0){
                counter++;
            }
        }
        return counter == 1;
    }
}
