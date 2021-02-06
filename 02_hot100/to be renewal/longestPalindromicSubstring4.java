import java.util.*;
import java.lang.*;

public class longestPalindromicSubstring4 {

    /**
     * 统一奇数和偶数个字符串长度，添加一个特殊符号作为标记
     * @return 返回插空填满的字符串，添加的特殊符号是'#'
     */
    public static String preprocess(String s1) {
        StringBuilder s2 = new StringBuilder("#");
        for (int i = 0; i < s1.length(); i++) {
            s2.append(s1.charAt(i));
            s2.append("#");
        }
        return s2.toString();
    }

    /**
     * 求解该点的臂展长度
     * @param s 待匹配的字符串
     * @param left 左边开始位置
     * @param right 右边开始位置
     * @return 返回臂展长度，即(全长+1)/2
     */
    public static int lengthofarm(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return (right - left + 2) / 2; 
    }

    /**
     * 根据计算的结果从预处理的字符串中得到新的子串
     * @param s_ 预处理之后的字符串
     * @param start 回文序列开始位置（含）
     * @param end 回文序列结束位置（含）
     * @return 符合输出要求的字串
     */
    public static String generateRes(String s_, int start, int end) {
        StringBuilder res = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (s_.charAt(i) != '#') {
                res.append(s_.charAt(i));
            }
        }
        return res.toString();
    }

    public static String longestPalindrome(String s) {
        String s_ = preprocess(s);
        String res = "";
        List<Integer> arm_len = new ArrayList<Integer>();





        return res;
    }

    public static void main(String[] args) {
        String test = "cbbd";
        System.out.println(longestPalindromicSubstring4.lengthofarm(test,3,3)); 
    }
}

// class Solution {
// public String longestPalindrome(String s) {

// }
// }