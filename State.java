/**
 * Created by Omer Dekel on 15/11/2018.
 * state class to present a state in the searchable .
 */
public class State {
    //members
    private int[][] state;    // the state represented by a 2-d array
    private int cost; // cost to reach this state (set by a setter)
    private String direction ; //the path we came from to this state
    private int insertTime; // insert time to the queue of the algorithm
    /**
     * Constructor .
     * @param state matrix game board .
     */
    public State(int[][] state)  // CTOR
    {
        cost = 0;
        this.direction = new String();
        this.state = state;
    }

    public int[][] getState() {
        return state;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * we overload Object's Equals method
     * @param s state to compare
     * @return true if equals, else false .
     */
    public Boolean Equals(State s)
    {
        if (s.getState().length != this.state.length) {
            return false;
        }
        for (int i = 0; i < this.state.length; i++) {
            for (int j = 0; j < this.state.length; j++) {
                if (this.state[i][j] != s.getNumber(i,j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * getter .
     * @return
     */
    public int getCost() {
        return cost;
    }

    /**
     * setter .
     * @param cost to set .
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * getNumber
     * @param i row index .
     * @param j column index .
     * @return the value in the matrix in index got as parameter .
     */
    public int getNumber(int i, int j) {
        return state[i][j];
    }
    public void setState(int[][] state) {
        this.state = state;
    }

    public int getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(int insertTime) {
        this.insertTime = insertTime;
    }


}
