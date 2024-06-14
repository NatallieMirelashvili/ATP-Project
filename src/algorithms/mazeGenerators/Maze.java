package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Maze {
    private static final String RESET = "\033[0m";
    private static final String GREEN = "\033[0;32m";
    public static final String RED = "\033[0;31m";
    private int[][] mazeMat;
    private Position start;
    private Position end;
    private int rows;
    private int columns;


//    ***Help Functions***

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

    public ArrayList<Position> FramePosDoors(){
        ArrayList<Position> allDoorsInFrame = new ArrayList<>();
        allDoorsInFrame.addAll(FrameTopDownDoors());
        allDoorsInFrame.addAll(FrameLeftRightDoors());
        return allDoorsInFrame;
    }

    private void chooseStart(){
        ArrayList<Position> allPossibleStartPoint = FramePosDoors();
        Random rand = new Random();
        int randIDX = rand.nextInt(allPossibleStartPoint.size());
        start = allPossibleStartPoint.get(randIDX);
    }

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
//        if (start.equals(end)){
//            if (start.getRowIndex() == end.getRowIndex()){
//                if(start.getRowIndex() == rows - 1){
//                    end = new Position(rows - 2, end.getColumnIndex());
//                }
//                end = new Position(end.getRowIndex() + 1, end.getRowIndex());
//            }
//            else{
//                if(start.getColumnIndex() == columns -1){
//                    end = new Position(end.getRowIndex(), columns - 2);
//                }
//                end = new Position(end.getRowIndex(), end.getColumnIndex() + 1);
//            }
//        }
    }



//   ****Constructor****
    public Maze(int row, int col) {
        this.rows = row;
        this.columns = col;
        this.mazeMat = new int[row][col];
        chooseStart();
        chooseEnd();
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

    public ArrayList<Position> getMyNeighborsInFrame(Position pos){
        ArrayList<Position> res = getMyNeighbors(pos);
        return checkInFrame(res);
    }
    public ArrayList<Position> getMyNeighbors(Position pos){
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

    public ArrayList<Position> checkInFrame (ArrayList<Position> posToCheck) {
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
                        out.append(GREEN + "S" + RESET).append(",");
                    else{
                    out.append(GREEN + "S" + RESET);
                    }
                }
                else if(end.getRowIndex() == i && end.getColumnIndex() == j ){
                    if (j != columns -1 )
                        out.append(RED + "E" + RESET).append(",");
                    else {
                        out.append(RED + "E" + RESET);
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
