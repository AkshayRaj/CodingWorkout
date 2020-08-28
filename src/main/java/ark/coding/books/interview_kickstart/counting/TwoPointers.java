package ark.coding.books.interview_kickstart.counting;

import ark.coding.books.interview_kickstart.ListNode;

public class TwoPointers {

    /**
     * https://leetcode.com/problems/linked-list-cycle/solution/
     * Given a linked list, determine if it has a cycle in it.
     * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed)
     * in the linked list where tail connects to.
     * If pos is -1, then there is no cycle in the linked list.
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode hare = head;
        ListNode tortoise = head;

        do {
            if (hare != null && hare.next != null) hare = hare.next.next;
            if (tortoise != null) tortoise = tortoise.next;
        } while (hare != null && hare.next != null && hare != tortoise);

        if (hare == null || hare.next == null) return false;
        return true;
    }


    /**
     * An extension (harder version) of the previous problem {@link #hasCycle(ListNode)}
     * In this problem we are also expected to find the node where the cycle begins.
     *
     * https://leetcode.com/problems/linked-list-cycle-ii/solution/
     *
     * Follow-up:
     * Can you solve it without using extra space?
     *
     * Approaches:
     * 1. Use {@link java.util.HashSet} Time: O(n) Space O(n)
     * 2. Two pointers
     *    - Tortoise & Hare Time:O(n) Space:O(1)
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode hare = head;
        ListNode tortoise = head;

        do {
            if (hare != null && hare.next != null) hare = hare.next.next;
            if (tortoise != null) tortoise = tortoise.next;
        } while (hare != null && hare.next != null && hare != tortoise);

        // If the list is acyclic, return null
        if (hare == null || hare.next == null) {
            return null;
        }

        ListNode tortoise2 = head;
        while (tortoise2 != hare) {
            tortoise2 = tortoise2.next;
            hare = hare.next;
        }
        return hare;
    }
}
