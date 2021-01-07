# include<iostream>
# include<vector>
# include<climits>
using namespace std;

class Solution {
public:
    /**
     * @brief 题目给了提示说要在log(m+n)时间复杂度内完成，那必然是二分查找，否则时间复杂度不匹配
     * 刚开始的想法不太对，于是修改了一下，看了题解的思路，现在来复述一遍，改成cpp来写
     * 中位数的定义，就是把左边和右边分开，如果奇数个则左边多一个，偶数个则两边相等
     * 奇数个就是左边的最大值，偶数个就是左右两边最大值和最小值的平均数
     * 类似于快速排序中的pivot值一样，需要在上下两个数组中找出相应的划分，然后使左边都小于右边
     * 这个算法的本质就是在一个数组中，通过二分查找，找到一个合适的划分点，使得两边元素满足中位数条件
     * 由于在两个数组中划分点的位置相关，即只需要对一个数组进行二分查找即可，时间复杂度O(log(min(m, n)))
     * @param nums1 
     * @param nums2 
     * @return double 
     */
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {


        int size_nums1 = nums1.size();
        int size_nums2 = nums2.size();
        if (size_nums1 > size_nums2) return findMedianSortedArrays(nums2, nums1);

        int left_cursor_nums1 = 0;
        int right_cursor_nums1 = size_nums1;
        int curPos_nums1, curPos_nums2;

       
        while(left_cursor_nums1  < right_cursor_nums1) {
            // 定义划分线在指针所指元素的右侧
            curPos_nums1 = (left_cursor_nums1 + right_cursor_nums1 + 1) / 2;
            curPos_nums2 = (size_nums1 + size_nums2 + 1) / 2 - curPos_nums1;
            
            if (nums1[curPos_nums1 - 1] > nums2[curPos_nums2]) {
                right_cursor_nums1 = curPos_nums1 - 1;
            }
            else {
                left_cursor_nums1 = curPos_nums1 ;
                
            }
        }
        curPos_nums1 = (left_cursor_nums1 + right_cursor_nums1) / 2;
        curPos_nums2 = (size_nums1 + size_nums2 + 1) / 2 - curPos_nums1;
        
        

        int nums1_left = (curPos_nums1 == 0)? INT_MIN : nums1[curPos_nums1 - 1];
        int nums1_right = (curPos_nums1 == size_nums1)? INT_MAX : nums1[curPos_nums1];
        int nums2_left = (curPos_nums2 == 0)? INT_MIN : nums2[curPos_nums2 - 1];
        int nums2_right = (curPos_nums2 == size_nums2)? INT_MAX : nums2[curPos_nums2];

        if (((size_nums1 + size_nums2) % 2) == 1) return max(nums1_left, nums2_left);
        else return (max(nums1_left, nums2_left) + min(nums1_right, nums2_right)) / 2.0;

    }
};

int main() {
    Solution a;
    vector<int> nums1 = {1, 2};
    vector<int> nums2 = {3, 4, 5};
    cout << a.findMedianSortedArrays(nums1, nums2);
}