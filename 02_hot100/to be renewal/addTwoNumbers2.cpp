struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode* head1 = l1;
        ListNode* head2 = l2;
        int flag1 = 0;
        int flag2 = 0;
        int carry = 0;
        while (l1 || l2) {
            int n1 = l1? l1->val : 0;
            int n2 = l2? l2->val : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            if (!l1 && !l2->next && carry == 1) {
                l2->val = sum % 10;
                l2->next = new ListNode(carry);
                return head2;
            }
            else if (!l2 && !l1->next && carry == 1) {
                l1->val = sum % 10;
                l1->next = new ListNode(carry);
                return head1;
            }
            if (!l2->next && !l1->next && carry == 1) {
                l1->val = sum % 10;
                l1->next = new ListNode(carry);
                return head1;
            }
            if (l1) {
                l1->val = sum % 10;
                l1 = l1->next;
                flag1 = 1;
            }
            if (l2) {
                l2->val = sum % 10;
                l2 = l2->next;
                flag2 = 1;
            }
        }
    return (flag1 == 1)? head1 : head2;
    }
};
