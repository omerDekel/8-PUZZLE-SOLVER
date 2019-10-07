import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Omer Dekel on 18/11/2018.
 * AStar algorithm searcher  .
 */
public class AStar implements ISearcher {
    //members
    private int numberOfNodesEvaluated;
    private PriorityQueue<State> openList;
    private int sizeMatrix;
    private State goal;
    /**
     * constructor .
     * @param goal State .
     * @param sizeMatrix size of the matrix in the searchable.
     */
    public AStar(State goal, int sizeMatrix) {
        this.sizeMatrix = sizeMatrix;
        this.goal = goal;
        this.numberOfNodesEvaluated = 0;
        // the PriorityQueue sorted by f(n)=g(n)+h(n) values
        this.openList = new PriorityQueue<State>(new Comparator<State>() {
            @Override
            public int compare(State node0, State node1) {
                // if they have the same f value , we compare by the insert time
                if (getF(node0) == getF(node1)) {
                    if (node0.getInsertTime() < node1.getInsertTime()) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                return getF(node0) - getF(node1);
            }
        });
    }

    /**
     * getF
     * @param node .
     * @return the sum f(n) = g(n) + h(n)
     */
    public int getF(State node) {
        return Heuristic(node) + node.getCost();
    }

    /**
     * the Heuristic function .
     *
     * @param node state.
     * @return sum "Manhattan distances" from the current slot to the place in position.
     */
    public int Heuristic(State node) {
        int sum = 0;
        for (int i = 0; i < sizeMatrix; i++) {
            for (int j = 0; j < sizeMatrix; j++) {
                sum += getManhatanDist(i,j,node.getNumber(i,j));
            }
        }
        return sum;
    }

    /**
     * calcuting the "Manhattan distance" from the current slot to the place in the goal state.
     * @param row index of the current slot .
     * @param column index of the current slot .
     * @param val value in the current slot .
     * @return the "Manhattan distance" from the current slot to the place in the goal state.
     */
    public int getManhatanDist(int row,int column,int val){
        if (val == 0) {
            return 0;
        }
        // search the value in the goal state matrix.
        for (int i = 0; i < sizeMatrix; i++) {
            for (int j = 0; j < sizeMatrix; j++) {
                if (goal.getNumber(i,j) == val) {
                    //the manhattan distance .
                    return Math.abs(i - row) + Math.abs(j - column);
                }
            }

        }
        return val;
    }
    @Override
    public Solution search(ISearchable searchable) {
        int insertTime = 1;
        //initialize the queue with initial state .
        searchable.getInitialState().setInsertTime(1);
        openList.add(searchable.getInitialState());
        numberOfNodesEvaluated = 1;
        // going through the open list queue .
        while (!this.openList.isEmpty()) {
            State node = openList.remove();
            // if it's goal state .
            if (node.Equals(goal)){
                return new Solution(node.getDirection(),numberOfNodesEvaluated,node.getCost());
            }
            Queue<State> stateQueue = searchable.getAllPossibleStates(node);
            //Going through the succesors .
            while (!stateQueue.isEmpty()) {
                State child = stateQueue.remove();
                insertTime++;
                child.setInsertTime(insertTime);
                child.setCost(node.getCost()+1);
                openList.add(child);
            }
            numberOfNodesEvaluated++;
        }
        //no goal found
        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return this.numberOfNodesEvaluated;
    }
}
