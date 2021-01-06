// https://leetcode-cn.com/problems/two-sum
/**
 * 哈希表法，时间复杂度O(N)，空间复杂度O(N)
 */
#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        vector<int> res;
        unordered_map<int, int> hashtable;
        for (int i = 0; i < nums.size(); i++) {
            auto got = hashtable.find(target-nums[i]);
            if(got != hashtable.end()) {
                res.push_back(got->second);
                res.push_back(i);
            }
            // hashtable.insert(make_pair(nums[i], i));
            hashtable[nums[i]] = i;
        }
        return res;
    }
};  