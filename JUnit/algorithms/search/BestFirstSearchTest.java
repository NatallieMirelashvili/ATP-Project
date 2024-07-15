package algorithms.search;

import algorithms.mazeGenerators.*;
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
    public void nullChecks(){
        AMazeGenerator simpleNull = new SimpleMazeGenerator();
        AMazeGenerator emptyNull = new EmptyMazeGenerator();
        AMazeGenerator complexNull = new MyMazeGenerator();
        Assertions.assertEquals(-1, simpleNull.measureAlgorithmTimeMillis(-5, -5));
        Assertions.assertNull(simpleNull.generate(-5, -5), "Expected to be null");
        Assertions.assertNull(emptyNull.generate(-5, -5), "Expected to be null");
        Assertions.assertNull(complexNull.generate(-5, -5), "Expected to be null");
        Maze wrongInput = new Maze(-5, -5);
        Assertions.assertNull(wrongInput.getMazeMat(), "Expected to be null");
        MazeState nullState = new MazeState("");
        Assertions.assertNull(nullState.State, "Expected to be null");
        ISearchable searchMazeNull = new SearchableMaze(wrongInput);
        Assertions.assertNull(searchMazeNull.getStartState(), "Expected to be null");
        Assertions.assertNull(bestToTest.solve(searchMazeNull), "Expected to be null");
        ISearchable nullS = new SearchableMaze(null);
        Assertions.assertNull(nullS.getStartState(), "Expected to be null");
        Assertions.assertNull(bestToTest.solve(nullS), "Expected to be null");



    }

    @Test
    public void tesInputValidity(){
        Assertions.assertNull((bestToTest.solve(null)));
        Assertions.assertEquals(bestToTest.solve(new SearchableMaze(new Maze(-1,-3))), "can't build maze with negative values.\n");

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