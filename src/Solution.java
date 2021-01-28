import java.util.List;

public class Solution {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resList = new ListNode();
        ListNode pre = resList;
        int carry = 0;
        while (true) {
            if (l1 != null) {
                carry = carry + l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry = carry + l2.val;
                l2 = l2.next;
            }
            pre.val = carry % 10;
            carry = carry / 10;
            if (l1 == null && l2 == null) {
                if (carry != 0) {
                    pre.next = new ListNode(carry);
                }
                return resList;
            }
            pre.next = new ListNode();
            pre = pre.next;
        }
    }


    public static void main(String[] arg) {
        ListNode a = new ListNode(9);
        ListNode b = new ListNode(9);
        ListNode c = new ListNode(9);
        ListNode d = new ListNode(9);
        ListNode e = new ListNode(9);
        ListNode f = new ListNode(9);

        a.next = b;
        b.next = c;

        d.next = e;
        e.next = f;

        addTwoNumbers(a, d);
    }


    static class ListNode {
        public int val;
        public ListNode next = null;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }
    }
}