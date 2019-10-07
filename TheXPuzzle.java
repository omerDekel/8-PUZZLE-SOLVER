import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by Omer Dekel on 15/11/2018.
 * the XPuzzle implement of the ISearchable.
 */
public class TheXPuzzle implements ISearchable {
    private int size;
    private State initialeState;
    private State goalState;
    //ctor
    public TheXPuzzle(int size, State initialeState) {
        this.size = size;
        this.initialeState = initialeState;
        //COUNTER FOR MATRIX VALUES.
        int count = 1;
        // calculate the goal state .
        int[][] goalMat = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                goalMat[i][j] = count;
                count++;
            }
        }
        goalMat[size-1][size-1] = 0;
        goalState = new State(goalMat);
    }
    @Override
    public State getInitialState() {
        return this.initialeState;
    }
    @Override
    public State getGoalState() {
        return this.goalState;
    }
    @Override
    public Queue<State> getAllPossibleStates(State s) {
        Queue<State> possibleStates= new LinkedTransferQueue<>();
        // go through the matrix and check all possible directions .
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (s.getNumber(i,j) == 0){
                    // up
                    if (i + 1  < this.size ) {
                        int[][] possibleUp;
                        possibleUp = copy(s);
                        possibleUp[i][j] = s.getNumber(i+1, j);
                        possibleUp[i+1][j] = 0;
                        State possibleStateUp = new State(possibleUp);
                        possibleStateUp.setDirection(s.getDirection() + "U");
                        possibleStates.add(possibleStateUp);
                    }
                    //Down
                    if (i - 1 >= 0) {
                        int[][] possibleD;
                        possibleD = copy(s);
                        possibleD[i][j] = s.getNumber(i - 1, j);
                        possibleD[i-1][j] = 0;
                        State possibleStateD = new State(possibleD);
                        possibleStateD.setDirection(s.getDirection() + "D");
                        possibleStates.add(possibleStateD);
                    }
                    //left
                    if (j + 1 < size ) {
                        int[][] possibleLeft;
                        possibleLeft = copy(s);
                        possibleLeft[i][j] = s.getNumber(i , j + 1 );
                        possibleLeft[i][j + 1] = 0;
                        State possibleStateL = new State(possibleLeft);
                        possibleStateL.setDirection(s.getDirection() + "L");
                        possibleStates.add(possibleStateL);
                    }
                    //right
                    if ( j - 1 >= 0) {
                        int[][] possibleR;
                        possibleR = copy(s);
                        possibleR[i][j] = s.getNumber(i, j - 1);
                        possibleR[i][j-1] = 0;
                        State possibleStateR = new State(possibleR);
                        possibleStateR.setDirection(s.getDirection() + "R");
                        possibleStates.add(possibleStateR);
                    }
                }
            }
        }
        return possibleStates;
    }

    /**
     * get a State and copy its matrix.
     * @param s State .
     * @return copy of the matrix of s State .
     */
    public int[][] copy (State s) {
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copy[i][j] = s.getNumber(i,j);
            }
        }
        return copy;
    }
}
