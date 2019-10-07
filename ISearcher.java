/**
 * Created by Omer Dekel on 14/11/2018.
 * the searcher algorithm interface .
 */
public interface ISearcher {
// the search method
Solution search (ISearchable searchable);
// get how many nodes were evaluated by the algorithm
 int getNumberOfNodesEvaluated();
}
