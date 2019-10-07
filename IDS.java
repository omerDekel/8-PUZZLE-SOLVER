import java.util.Queue;

/**
 * Created by Omer Dekel on 16/11/2018.
 * implement of ids algorithm .
 */
public class IDS implements ISearcher {
    private int numberOfNodesEvaluated;
    private int depth;
    private boolean successFlag;

    /**
     * constructor .
     */
    public IDS() {
        numberOfNodesEvaluated = 0;
        depth = 0;
        successFlag = false;
    }

    @Override
    public Solution search(ISearchable searchable) {
        while (true) {
            State found = DfsLimit(searchable.getInitialState(), depth,searchable);
            if (found != null) {
                return new Solution(found.getDirection(),numberOfNodesEvaluated, depth);
            } else {
                if (!successFlag) {
                    return null;
                }
            }
            // initialize the number of nodes evaluated for the new iteration
            numberOfNodesEvaluated = 1;
            depth++;
        }
    }

    /**
     *dfs-l searcher.
     * @param node initial state board .
     * @param limit to run the code.
     * @param searchable .
     * @return goal state if found, else null
     */
    public State DfsLimit(State node, int limit,  ISearchable searchable) {
        if (limit == 0) {
            if ((searchable.getGoalState()).Equals(node)) {
                successFlag = true;
                return node;
            } else {
                // not found ,but may have children
                successFlag = true;
                return null;
            }
        } else {
            boolean any_remaining = false;
            if (limit > 0) {
                Queue<State> stateQueue = searchable.getAllPossibleStates(node);
                while(!stateQueue.isEmpty()) {
                    numberOfNodesEvaluated++;
                    State child = stateQueue.remove();
                    State found = DfsLimit(child,limit - 1  , searchable);
                    if (found != null) {
                        successFlag = true;
                        return found;
                    } if (successFlag) {
                        //At least one node found at depth, let IDDFS deepen
                        any_remaining = true;
                    }

                }
                successFlag = any_remaining;
                return null;
            }
        }
        return null;
    }
    @Override
    public int getNumberOfNodesEvaluated() {
        return this.numberOfNodesEvaluated;
    }
}
