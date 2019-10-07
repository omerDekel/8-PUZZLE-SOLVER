import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by Omer Dekel on 16/11/2018.
 * implement the bfs searcher .
 */
public class BFS implements ISearcher {
    private int numberOfNodesEvaluated;

    /**
     * constructor .
     */
    public BFS() {
        this.numberOfNodesEvaluated = 0;
    }
    @Override
    public Solution search(ISearchable searchable) {
        Queue<State> stateQueue = new LinkedTransferQueue<>();
        stateQueue.add(searchable.getInitialState());
        while (!stateQueue.isEmpty()) {
            State s = stateQueue.remove();
            numberOfNodesEvaluated ++ ;
            if ((searchable.getGoalState()).Equals(s)) {
                return new Solution(s.getDirection(),numberOfNodesEvaluated, 0);
            }
            stateQueue.addAll(searchable.getAllPossibleStates(s));
        }
        return null;
    }
    @Override
    public int getNumberOfNodesEvaluated() {
        return 0;
    }
}
