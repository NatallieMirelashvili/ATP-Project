package algorithms.search;

import algorithms.mazeGenerators.Maze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BestFirstSearchTest {
    private final BestFirstSearch bestToTest = new BestFirstSearch();
//    155
    private final int[][] matToTest = {
                        {0,1,1,0,1,1,1,1,1,1},
                        {0,0,0,0,1,1,1,1,1,1},
                        {1,0,1,0,1,1,1,1,1,1},
                        {1,0,0,0,1,1,1,1,1,1},
                        {1,0,1,0,0,0,0,0,1,1},
                        {1,1,1,1,1,1,1,0,0,0},
                        {1,1,1,1,1,1,1,1,0,0},
                        {1,1,1,1,1,1,1,0,0,1},
                        {1,1,1,1,1,1,1,0,0,0},
                        {1,1,1,1,1,1,1,1,0,0},
                        };
    private final Maze mazeToTestOn = new Maze(matToTest);
    @Test
    public void checkBestName(){
        Assertions.assertEquals(bestToTest.getName(), "Best First Search");
        Assertions.assertNotEquals(bestToTest.getName(), "Breath First Search" );
    }

    @Test
    public void testCorrectCalcSol(){
        ISearchable maze = new SearchableMaze(mazeToTestOn);
//        Running best solution:
        Assertions.assertEquals(maze.getStartState().toString(), "{0,0}");
        Assertions.assertEquals(maze.getGoalState().toString(), "{9,9}");
        Solution sol = bestToTest.solve(maze);
        Assertions.assertEquals( 0, sol.getSolutionPath().get(0).getCost());
        Assertions.assertEquals(maze.getStartState().toString(), sol.getSolutionPath().get(0).getCameFrom().toString());
        Assertions.assertEquals(150,sol.getSolutionPath().get(sol.getSolutionPath().size() - 1 ).getCost());
    }



}