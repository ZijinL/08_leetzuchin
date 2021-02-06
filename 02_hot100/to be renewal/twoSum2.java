
// https://leetcode-cn.com/problems/two-sum
import java.util.HashMap;
import java.util.Map;
/**
 * 哈希表法，时间复杂度O(N)，空间复杂度O(N)
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[] {hashtable.get(target - nums[i]), i};
            }
            // 注意这里存放的键值对顺序
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}