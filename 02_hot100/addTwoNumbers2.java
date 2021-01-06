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

  class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode cur = head;

        int addvalue = 0;
        while(l1 != null || l2 != null) {
            int n1 = (l1==null)? 0 : l1.val;
            int n2 = (l2==null)? 0 : l2.val;
            int n3 = (n1 + n2 + addvalue) % 10;
            addvalue = (n1 + n2 + addvalue) / 10;
            cur.next = new ListNode(n3);
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            cur = cur.next;
        }
        if (addvalue == 1) {
            cur.next = new ListNode(addvalue);
        }
        return head.next;
    }
}