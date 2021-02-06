// dynamic programming
// 状态转移方程 dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
// 边界条件 dp[i][i] = true; dp[i][i+1] == (s[i] == s[i+1])
// 由于要构造一个矩阵，所以时间复杂度和空间复杂度都是n方的
// 重点在掌握状态转移方程的书写和最简单形式的动态规划问题描述
// 边界条件！
public class longestPalindromicSubstring {
    public static String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return s;
        boolean [][] dp = new boolean [n][n];
        String res = "";
        // 状态转移方程的建立
        // 这里不是直接建立，而是借助于前后两个数的距离l来实现
        for (int l = 0; l < n; l++) {
            for (int i = 0; i + l < n; i++) { // 注意此处循环结束的条件是j不越界
                int j = i + l;
                if (l == 0) dp[i][j] = true;
                else if (j >= n) break;
                else if (l == 1) dp[i][j] = (s.charAt(i) == s.charAt(j));
                else dp[i][j] = (dp[i+1][j-1] && s.charAt(i) == s.charAt(j));

                // 检查该情况下的i和j的运行
                if (dp[i][j] == true && (j-i+1) > res.length()) {
                    res = s.substring(i, j+1);
                }
            }
        }
        return res;
 
    }
    public static void main(String [] args) {
        String test = "abba";
        String res = longestPalindromicSubstring.longestPalindrome(test);
        System.out.println(res);
    }
}

// class Solution {
//     public String longestPalindrome(String s) {

//     }
// }