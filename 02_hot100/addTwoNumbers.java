//https://leetcode-cn.com/problems/add-two-numbers/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

 /**
  * 题解给出的方法大多都是需要新建链表，但是这样空间就上去了，这里的方法是原地工作
  * 也就是利用原有链表中较长的一个用于存储结果，需要增加一次循环知道哪个链表更长
  */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 找到更长的链表
        ListNode i = l1, j = l2;
        while (i != null && j != null) {
            i = i.next;
            j = j.next;
        }
        if (j != null) {
            ListNode mid;
            mid = l1;
            l1 = l2;
            l2 = mid;
        }
        ListNode head = l1;

        // 以更长的链表为主链表l1，开始遍历
        int addvalue = 0;
        while(l1 != null && l2 != null) {
            int temp = (l1.val + l2.val + addvalue) % 10;
            addvalue = (l1.val + l2.val + addvalue) / 10;
            l1.val = temp;
            if (l1.next == null && addvalue == 1) {
                ListNode post = new ListNode(addvalue);
                l1.next = post;
                l1 = l1.next;
                System.out.println(l1.val);
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int temp = (l1.val + addvalue) % 10;
            addvalue = (l1.val + addvalue) / 10;
            l1.val = temp;
            if (l1.next == null && addvalue == 1) {
                ListNode post = new ListNode(addvalue);
                l1.next = post;
                l1 = l1.next;
            }
            l1 = l1.next;
        }
        return head;
    }
}