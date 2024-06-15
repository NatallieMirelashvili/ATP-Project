package algorithms.search;

public interface ISearchingAlgorithm {
    /**
     * solve - A function which finding a solution for a given Searchable problem.
     * Args: ISearchable domain - Searchable problem such as a Maze, 8-piece puzzle, etc.
     * return: Solution - The steps this solution style chose.
     * */
    Solution solve(ISearchable domain);

    /**
     * getName - getter
     * Args: None
     * return: String - this solution name.
     * */
    String getName();

    /**
     * getNumberOfNodesEvaluated - getter
     * Args: None
     * return: int - the number of nodes this solution added to the solution.
     * */
    int getNumberOfNodesEvaluated();
}
