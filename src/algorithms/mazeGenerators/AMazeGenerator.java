package algorithms.mazeGenerators;

abstract public class AMazeGenerator implements IMazeGenerator {

    public long measureAlgorithmTimeMillis (int i, int j){
        long start = System.currentTimeMillis();
        generate(i,j);
        long end = System.currentTimeMillis();
        return end - start;
    }


    protected boolean checkValidInput(int i, int j){
        if(i > 0 && j > 0)
        {
            return true;
        }
        System.out.println("Please enter positive number of rows and columns.\n");
        return false;
    }


}
