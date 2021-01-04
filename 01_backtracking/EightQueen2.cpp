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