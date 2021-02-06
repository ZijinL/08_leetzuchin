/**
 * 20210126 https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/
 */

public class Domino {

}

/**
 * 统计相同二元组的个数，由于都是个位数的数字型，可以适当以空间换时间
 */
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int res = 0;
        int[] cp = new int[100];
        for (int[] temp : dominoes) {
            Arrays.sort(temp);
            res += cp[arr[0] * 10 + arr[1]]++;
        }
        return res;
    }
}
