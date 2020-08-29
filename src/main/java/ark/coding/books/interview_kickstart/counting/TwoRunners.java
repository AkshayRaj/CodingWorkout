package ark.coding.books.interview_kickstart.counting;

import ark.coding.books.interview_kickstart.ListNode;

public class TwoRunners {

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

    /**
     * https://leetcode.com/problems/happy-number/
     *
     * Approach:
     * - For a problem like this, we need to get a definitive answer on whether it leads to infinity.
     *   An interview question either will not be a non-terminating problem, or there is a terminating condition
     *   which the interviewee will have to identify.
     *   Because if the problem is truly non-terminating, then there is no coding to be done !
     * - Having said that, check https://leetcode.com/problems/happy-number/solution/
     *   for theory on this problem.
     *   In short, come up with extreme inputs for the problem , i.e. the max sum of squares of digits of the number
     *   i.e. 81 (square of 9). The max sum is going to be a multiple of 81, number of digits * 81
     *   {@link Integer#MAX_VALUE} has 10 digits in it, so the max value of sum of squares will be 810.
     * - So, either the sum of squares is going to end in 1, or it will be stuck in an endless "cycle"
     *   Our problem now narrows down to finding to detecting the cycle or ending the sequence in 1.
     *
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
        int hare = n;
        int tortoise = n;
        do {
            hare = squareAndSum(squareAndSum(hare));
            tortoise = squareAndSum(tortoise);
        } while (hare != 1 && hare != tortoise);

        return hare == 1;
    }

    private static int squareAndSum(int hare) {
        int sum = 0;
        int quotient = hare;
        while (quotient != 0) {
            sum = sum + (int) (Math.pow((quotient%10),2));
            quotient = quotient/10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(19));
        System.out.println((19%10));
    }
}
