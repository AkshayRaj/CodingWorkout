/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

/**
 * https://www.algoexpert.io/questions/Remove%20Kth%20Node%20From%20End
 */
class RemoveKthNodeFromLinkedList {
    public static void removeKthNodeFromEndByShiftingValuesToLeft(LinkedList head, int k) {
        // Write your code here.
        LinkedList currentNode = head;
        int noOfNodes = 1;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            noOfNodes++;
        }

        int nodeFromHead = noOfNodes - k + 1;
        if (nodeFromHead < 1 || noOfNodes < nodeFromHead) {
            return;
        }
        int count = 1;
        LinkedList parent = null;
        LinkedList nodeToRemove = head;
        LinkedList next = nodeToRemove.next;
        while (count < nodeFromHead) {
            parent = nodeToRemove;
            nodeToRemove = next;
            next = next.next;
            count++;
        }

        if (nodeToRemove.next == null) {
            // if we are removing the last element,
            parent.next = null;
        }
        else {
            // left shift the values, starting from nodeToRemove
            while (next.next != null) {
                nodeToRemove.value = next.value;
                nodeToRemove = next;
                next = nodeToRemove.next;
            }
            nodeToRemove.value = next.value;
            nodeToRemove.next = null;
        }
    }

    /**
     *
     * @param head
     * @param k
     */
    public static void removeKthNodeFromEndByChangingNextPointer(LinkedList head, int k) {
        // Write your code here.
        LinkedList currentNode = head;
        int noOfNodes = 1;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            noOfNodes++;
        }

        int nodeFromHead = noOfNodes - k + 1;
        if (nodeFromHead < 1 || noOfNodes < nodeFromHead) {
            return;
        }
        int count = 1;
        LinkedList nodeToRemove = head;
        LinkedList parent = null;
        while (count < nodeFromHead) {
            parent = nodeToRemove;
            nodeToRemove = nodeToRemove.next;
            count++;
        }

        if (parent != null) {
            parent.next = nodeToRemove.next;
            nodeToRemove.next = null;
        }
        else {
            head = nodeToRemove.next;
            nodeToRemove.next = null;
        }
    }

    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }
}
