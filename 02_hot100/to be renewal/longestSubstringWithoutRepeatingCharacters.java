// https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
import java.util.HashSet;
import java.lang.Math;

class Solution {
    /**
     *  滑动窗口法，其实就是两个指针，两层循环
     * 不同于一般循环的是，两个指针都单向右移不回退
     * 由于指针的这种特性，因此考虑对左侧指针用while循环
     * 即右侧指针单向挨个移动，左侧指针需要判断是否移动
     * 注意指针不回退是移动窗口的一个突出特点
     */
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> hashtable = new HashSet<Character>();
        int leftBorder = 0;
        int res = 0;
        // 滑动窗口的右侧指针，即最长字符串的右边界
        for (int rightBorder = 0; rightBorder < s.length(); rightBorder++) {
            while(leftBorder < rightBorder && hashtable.contains(s.charAt(rightBorder))) {
                hashtable.remove(s.charAt(leftBorder));
                leftBorder++;
            }
            hashtable.add(s.charAt(rightBorder));
            res = Math.max(res, rightBorder - leftBorder + 1);
        }
        return res;
    }
}