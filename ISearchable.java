import java.util.List;
import java.util.Queue;

/**
 * Created by Omer Dekel on 14/11/2018.
 * the searchable interface where we operate the searching algorithm .
 */
public interface ISearchable {
    /**
     * getInitialState.
     * @return the initial state .
     */
    State getInitialState();

    /**
     * getGoalState .
     * @return the goal state .
     */
    State getGoalState();

    /**
     * getAllPossibleStates .
     * @param s state .
     * @return queue all Possible States from state s .
     */
    Queue<State> getAllPossibleStates(State s);
}