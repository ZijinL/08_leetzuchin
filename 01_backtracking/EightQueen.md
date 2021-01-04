# 经典的八皇后问题

## n-皇后

给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

> 示例
>
> 输入：4
> 输出：[
>  [".Q..",  // 解法 1
>   "...Q",
>   "Q...",
>   "..Q."],
>
>  ["..Q.",  // 解法 2
>   "Q...",
>   "...Q",
>   ".Q.."]
> ]
> 解释: 4 皇后问题存在两个不同的解法。

### 基于集合的回溯

#### 基本思路

 * 基本思路就是遍历，按行放置皇后，按列遍历，并检查是否和上面的皇后出现冲突；
 * 分为三种冲突：列，主对角，副对角；分别用三个哈希集合表示三种情况的占位；
 * 主要难点在于回溯，即如果无冲突则放置皇后，然后继续遍历下一行；
 * 如果出现冲突则跳过该列，如果该列放置后下面的行无法得到结果则要回退；

#### 时间复杂度

由于每个皇后必须位于不同列，因此已经放置的皇后所在的列不能放置别的皇后。第一个皇后有$N$列可以选择，第二个皇后最多有$N-1$列可以选择，第三个皇后最多有$N-2$列可以选择（如果考虑到不能在同一条斜线上，可能的选择数量更少），因此所有可能的情况不会超过$N!$ 种，遍历这些情况的时间复杂度是 $O(N!)$。

#### 空间复杂度

空间复杂度：$O(N)$，其中$N$是皇后数量。空间复杂度主要取决于递归调用层数、记录每行放置的皇后的列下标的数组以及三个集合，递归调用层数不会超过$N$，数组的长度为$N$，每个集合的元素个数都不会超过$N$。

#### cpp

```cpp
#include <iostream>
#include <Vector>
#include <unordered_set>
using namespace std;

/**
 * @brief 基本思路就是遍历，按行放置皇后，按列遍历，并检查是否和上面的皇后出现冲突；
 * 分为三种冲突：列，主对角，副对角；分别用三个哈希集合表示三种情况的占位；
 * 主要难点在于回溯，即如果无冲突则放置皇后，然后继续遍历下一行；
 * 如果出现冲突则跳过该列，如果该列放置后下面的行无法得到结果则要回退；
 */
class Solution {
public:
    vector<vector<string>> solveNQueens(int n) {
        auto solutions = vector<vector<string>>();
        auto queens = vector<int>(n, -1);
        auto columns = unordered_set<int>(); // c++中set基于红黑树，而unordered_set基于哈希表
        auto diagonals_1 = unordered_set<int>(); // 这里不需要有序，但需要小规模快速查找，用unordered_set
        auto diagonals_2 = unordered_set<int>();
        backTrack(solutions, queens, n, 0, columns, diagonals_1, diagonals_2);
        return solutions;
    }

    /**
     * @brief 
     * 
     * @param solutions 保存所有的解，每个解都是一个 vector<string>
     * @param queens 各行皇后所放置的列号，每个解的该数组都不同
     * @param n 皇后的个数
     * @param row 放置皇后所在的行数
     * @param columns 用于存放已经有皇后的列的列号
     * @param diagonals_1 用于存放已经有皇后的主对角线的编号
     * @param diagonals_2 用于存放已经有皇后的副对角线的编号
     */
    void backTrack(vector<vector<string>> &solutions, vector<int> &queens, int n, int row, unordered_set<int> &columns, unordered_set<int> &diagonals_1, unordered_set<int> &diagonals_2) {
        if (row == n) {
            vector<string> board = generateBoard(queens, n);
            solutions.push_back(board);
        } else {
            // 遍历第 row 行，第 i 列，尝试放置皇后
            for (int i = 0; i < n; i++) {
                if (columns.find(i) != columns.end()) continue;
                int diagonal_1 = i - row;
                if (diagonals_1.find(diagonal_1) != columns.end()) continue;
                int diagonal_2 = i + row;
                if (diagonals_2.find(diagonal_2) != columns.end()) continue;

                queens[row] = i;
                columns.insert(i)   ;
                diagonals_1.insert(diagonal_1);
                diagonals_2.insert(diagonal_2);

                backTrack(solutions, queens, n, row + 1, columns, diagonals_1, diagonals_2);

                queens[row] = -1;
                columns.erase(i);
                diagonals_1.erase(diagonal_1);
                diagonals_2.erase(diagonal_2);

            }
        }
    }

    /**
     * @brief 用于当遍历到最后一行的时候，生成一个该情况下的解     * 
     * @param queens 一个完整的皇后所在位置的数组，即不含初始值-1
     * @param n 皇后的个数
     * @return vector<string> 
     */
    vector<string> generateBoard(vector<int> queens, int n) {
        auto board = vector<string>();
        for (int i = 0; i < n; i++) {
            string row = string(n, '.');
            row[queens[i]] = 'Q';
            board.push_back(row);
        }
        return board;
    }  
};


/**
 * @brief 测试函数
 * 
 * @return int 皇后的个数
 */
int main()
{
    Solution a;
    for (auto each: a.solveNQueens(4)) {
        for (auto item: each) {
            cout << item << endl;
        }
    }
}
```

#### java

```java
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
```

### 基于位运算的回溯

#### 基本思路

将上述基于集合的回溯改成基于位运算的方法，即不再用三个集合来表示冲突，而用响应的位运算来计算是否符合条件，这样可以将空间复杂度从$O(N)$降到$O(1)$，时间复杂度不变

#### cpp

```cpp

```



