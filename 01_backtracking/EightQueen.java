import java.util.*;

public class EightQueen {

    public EightQueen() {
    }

    /**
     * solve the n-size queen problem and print all solution
     * @param n the size of the map
     * @return all solutions of n-size queen, each solution is a {@code List<string>}
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int [] queens = new int[n]; // save the position of queen
        Arrays.fill(queens, -1); // the default position is -1
        Set<Integer> columns = new HashSet<Integer>(); // save the distinct columns that has been occupied
        Set<Integer> diagonals_1 = new HashSet<Integer>(); // save the distinct main diagonal that has been occupied
        Set<Integer> diagonals_2 = new HashSet<Integer>(); // save the distinct sub-diagonal that has been occupied
        backtrack(solutions, queens, n, 0, columns, diagonals_1, diagonals_2);
        return solutions;
    }

    /**
     * use backtracking to get the final solution
     * @param solutions store the final solution, dfs, find one add one
     * @param queens temporalily store the position of each queen, only one soltion
     * @param n the size of this problem
     * @param row current row, start from 0, end with n-1
     * @param columns save the distinct columns that has been occupied
     * @param diagonals_1 save the distinct main diagonal that has been occupied
     * @param diagonals_2 save the distinct sub-diagonal that has been occupied
     */
    public void backtrack(List<List<String>> solutions, int [] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals_1, Set<Integer> diagonals_2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        }
        else {
            // try to put queen each columns of this row
            for (int i = 0; i < n; i++) {
                // if collision happens, then skip this column
                if (columns.contains(i))
                    continue;
                int diagonal_1 = i + row;
                if (diagonals_1.contains(diagonal_1))
                    continue;
                int diagonal_2 = i - row;
                if (diagonals_2.contains(diagonal_2))
                    continue;

                // if no collision, then put queen here
                queens[row] = i;
                columns.add(i);
                diagonals_1.add(diagonal_1);
                diagonals_2.add(diagonal_2);

                // then try to find the position on the next row
                backtrack(solutions, queens, n, row + 1, columns, diagonals_1, diagonals_2);

                // set the back route, that is, skip this position and try next position
                queens[row] = -1;
                columns.remove(i);
                diagonals_1.remove(diagonal_1);
                diagonals_2.remove(diagonal_2);
            }
        }  
    }

    /**
     * generate the board according to the queens location and size
     * @param queens the column of queens of each row, for example: <code>queens[2] = 3</code> means the queen of the third row lies in the fourth column.
     * @param n the size of the board
     * @return one solution of this size, 'Q' means queen's location 
     */
    public List<String> generateBoard(int [] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        if (input.hasNextInt()) {
            int n = input.nextInt();
            // 静态方法中不可以直接调用非静态方法和成员，需要先实例化
            EightQueen test = new EightQueen();
            System.out.println(test.solveNQueens(n));
        }
        input.close();
    }
}