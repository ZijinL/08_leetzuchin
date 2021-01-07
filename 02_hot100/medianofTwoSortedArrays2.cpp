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
     * 就像快排一样，这里也需要认为定义其实条件，那必然是两个数组的中间了
     * @param nums1 
     * @param nums2 
     * @return double 
     */
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        // 初始化指针的值，左边的总个数确定，知道一个就方便求另一个下标
        int m = nums1.size();
        int n = nums2.size();
        if (m > n) return findMedianSortedArrays(nums2, nums1);
        int i, j;
        int a, b, c, d;
        int l = 0;
        int r = m;
        // 边界条件的情况
        while (l <= r) {
            i = (l + r - 1) / 2;
            if (m == 0) i = -1;
            j = (m + n - 1) / 2 - i - 1;
            
            a = (i < 0)? INT_MIN : nums1[i];
            b = (i >= (m-1))? INT_MAX : nums1[i+1];
            c = (j < 0)? INT_MIN : nums2[j];
            d = (j >= (n-1))? INT_MAX : nums2[j+1];

            if (a > d) {
                r = i;
            }
            else if (c > b) {
                l = i + 1;
            }
            else {
                break;
            }
        }

        // 最终结果
        if ((m + n) % 2 == 1) {
            return (a > c)? a : c;
        }
        else {
            int temp1 = (a > c)? a : c;
            int temp2 = (b < d)? b : d;
            return (temp1 + temp2) / 2.0;
        } 
    }
};