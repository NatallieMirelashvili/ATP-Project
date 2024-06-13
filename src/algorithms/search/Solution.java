package algorithms.search;

import java.util.ArrayList;

public class Solution {

    private ArrayList<AState> SolutionPath = new ArrayList<>();


    public ArrayList<AState> getSolutionPath(){
        return SolutionPath;
    }
    public void addNode(AState toAdd){
        SolutionPath.add(toAdd);
    }



}
