// https://leetcode-cn.com/problems/longest-substring-without-repeating-characters

import java.util.*;
import java.lang.Math;
class Solution {
    /**
     * 由于字符是在128个范围以内的ASCII码组成，因此想到以空间换时间
     * 最长字符串起点是max(上一次起点，当前字符上一次出现位置）
     * 最长字符串的长度max(上一个长度，当前位置往前的最大长度)
     */
    public int lengthOfLongestSubstring(String s) {
        int[] charLastOccurence = new int [128];
        Arrays.fill(charLastOccurence, -1); // 之前都没有出现过则记为-1
        int res = 0;
        int start = 0;
        for (int currentPosition = 0; currentPosition < s.length(); currentPosition++) {
            int currentChar = s.charAt(currentPosition); // 获取当前字符的ASCII码值
            start = Math.max(start, charLastOccurence[currentChar] + 1);
            res = Math.max(res, currentPosition - start + 1);
            charLastOccurence[currentChar] = currentPosition;
        }
        return res;
    }
}