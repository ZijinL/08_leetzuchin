// https://leetcode-cn.com/problems/two-sum
/**
 * 两层循环法，时间复杂度O(N^2)，空间复杂度O(1)
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                if (nums[i] + nums[j] == target) {
                    int [] temp = {i, j};
                    return temp;
                }
            }
        }
        return null;
    }
}