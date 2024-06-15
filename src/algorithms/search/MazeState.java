package algorithms.search;

public class MazeState extends AState{


    public MazeState(String state) {
        super(state);
    }
    /**
     * parseStateName - A function which turn this MazeState name to an array of two numbers - left is the row number,
     * and right is the column number.
     * */

    public int[] parseStateName(){
        String stateName = State;
        String without = stateName.substring(1,stateName.length() - 1);
        String[] split  = without.split(",");
        int row = Integer.parseInt(split[0]);
        int col = Integer.parseInt(split[1]);
        return new int[]{row, col};
    }

}
