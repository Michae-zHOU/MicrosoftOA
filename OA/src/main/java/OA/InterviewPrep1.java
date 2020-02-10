package OA;

public class InterviewPrep1 extends OABaseClass {

    private class ListNode{
        public int val;
        public ListNode next;
        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.format("%d", val);
        }
    }

    private ListNode reverseLinkedList(ListNode head, ListNode last) {
        ListNode prev = last;
        ListNode curr = head;
        
        while(curr != last) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public ListNode reverseNodesinKGroup(ListNode head, int k) {
        if(head == null || k < 2) 
            return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode beg = dummy;
        ListNode curr = head;

        int i = 0;

        while(curr != null) {
            ++i;
            if(i % k == 0) {
                ListNode first = beg.next, last = curr.next;
                beg.next = reverseLinkedList(first, last);

                beg = first;
                curr = last;
            }
            else {
                curr = curr.next;
            }
        }

        return dummy.next;
    } 

}