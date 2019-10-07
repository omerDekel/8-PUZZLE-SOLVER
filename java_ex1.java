import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Omer Dekel on 15/11/2018.
 * Main class reading the input file and initialize the values  accordingly.
 */
public class java_ex1 {
    public enum SearcherTypeEnum {IDS , BFS , ASTAR}
    public static void main(String[] args) {
        BufferedReader reader;
        ISearcher searcher;
        int size;
        SearcherTypeEnum searcherType;
        int[][] initialStateBoard;
        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            //first line is for the searcher type.
            searcherType = SearcherTypeEnum.values()[Integer.parseInt(reader.readLine()) - 1];
            //second line for puzzle size .
            size = Integer.parseInt(reader.readLine());
            initialStateBoard = new int[size][size];
            //third line is the matrix.
            String line1 = reader.readLine();
            //set values of the matrix according to the input .
            List<Integer> initialBoard = Arrays.stream(line1.split("-")).map(Integer::parseInt).collect(Collectors.toList());
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    initialStateBoard[i][j] = initialBoard.get(j + i*size);
                }
            }
            State iniState = new State(initialStateBoard);
            TheXPuzzle pz = new TheXPuzzle(size, iniState);
            //find which searcher is.
            switch (searcherType) {
                case IDS:
                    searcher = new IDS();
                    break;
                case BFS:
                    searcher = new BFS();
                    break;
                case ASTAR:
                    searcher = new AStar(pz.getGoalState(), size);
                    break;
                default:
                    return;
            }
            Solution solution = searcher.search(pz);
            String solutionStr = solution.toString();
            // write the solution to file .
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(new FileWriter("output.txt"));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            printWriter.write(solutionStr);
            printWriter.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
