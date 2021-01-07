// 中心拓展法，注意当回文字符串是偶数的时候，要单独讨论，可以封装优化一下
// 状态转移方程 dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
// 边界条件 dp[i][i] = true; dp[i][i+1] == (s[i] == s[i+1])

public class longestPalindromicSubstring2 {
    public static String longestPalindrome(String s) {
        int n = s.length();
        String res = "";
        for (int i = 0; i < n; i++) {

            int length1 = 0, length2 = 0;

            // 正常如果回文字符串是奇数
            for (int j = 1; j <= i; j++) {
                if (i + j == n) break;
                else if (s.charAt(i-j) != s.charAt(i+j)) break;
                else length1 ++;
            }
            if ((2*length1+1) > res.length()) res = s.substring(i - length1, i + length1 + 1);

            // 如果存在偶数个回文字符串，前提是必有两个连续相等的字符
            if (i+1<n && s.charAt(i) == s.charAt(i+1)) {
                for (int j = 1; j <= i; j++) {
                    if (i + j + 1 == n) break;
                    else if (s.charAt(i-j) != s.charAt(i+j+1)) break;
                    else length2 ++;
                }
                if ((2*length2+2) > res.length()) res = s.substring(i - length2, i + length2 + 2);
            }
            
        }
        return res;
    }
    public static void main(String [] args) {
        String test = "cbbd";
        String res = longestPalindromicSubstring2.longestPalindrome(test);
        System.out.println(res);
    }
}

// class Solution {
//     public String longestPalindrome(String s) {

//     }
// }