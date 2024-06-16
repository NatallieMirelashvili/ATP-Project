package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int i, int j) {
        if (!checkValidInput(i, j)){
            System.out.println("generate maze function expected positive values, got: " + i + ", " +  j + "\n");
            return null;
        }
        Maze oneSul = new Maze(i, j);
        oneSul.setValueAllPos(1);
        Position start = oneSul.getStartPosition();
        oneSul.setValueByPos(start, 0);
        Stack<Position> myS =new Stack<>();
        myS.push(start);
        while (!myS.isEmpty()){
            Position cur = myS.pop();
            ArrayList<Position> neighbors = oneSul.getMyNeighborsInFrame(cur);
            Collections.shuffle(neighbors, new Random());
            for(Position pos: neighbors){
                if(oneSul.ExceptPosToMaze(oneSul.getMyNeighborsInFrame(pos))){
                    //  current position enter to the maze
                    oneSul.setValueByPos(pos, 0);
                    myS.push(pos);
                }
            }

        }
        oneSul.chooseEnd();
        return oneSul;
    }
}
