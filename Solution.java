import java.util.Stack;

/**
 * Created by Omer Dekel on 14/11/2018.
 * Solution class responsible to the values of the searching solution.
 */
public class Solution {
    private String path;
    private int numOfNodes;
    private int depth;
    //constructor .
    public Solution(String path, int numOfNodes, int depth) {
        this.path = path;
        this.numOfNodes = numOfNodes;
        this.depth = depth;
    }

    /**
     * @return all members of the solution as one string .
     */
    @Override
    public String toString() {
        return path+" "+numOfNodes+" "+depth;
    }
}
