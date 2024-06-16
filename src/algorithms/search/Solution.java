package algorithms.search;

import java.util.ArrayList;

public class Solution {

    private ArrayList<AState> SolutionPath;

    public Solution() {
        SolutionPath = new ArrayList<>();
    }

    public ArrayList<AState> getSolutionPath(){
        return SolutionPath;
    }
    public void addNode(AState toAdd){
        if(toAdd == null)
        {
            System.out.println("addNode expected to not null node input\n");
            return;
        }
        SolutionPath.add(toAdd);
    }



}
