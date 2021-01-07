// 中心拓展法，注意当回文字符串是偶数的时候，要单独讨论，可以封装优化一下
// 状态转移方程 dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
// 边界条件 dp[i][i] = true; dp[i][i+1] == (s[i] == s[i+1])

public class longestPalindromicSubstring3 {


    public static String longestPalindrome(String s) {
         int n = s.length();
        String res = "";

        return res;
    }

    public static void main(String[] args) {
        String test = "cbbd";
        String res = longestPalindromicSubstring3.longestPalindrome(test);
        System.out.println(res);
    }
}

// class Solution {
// public String longestPalindrome(String s) {

// }
// }