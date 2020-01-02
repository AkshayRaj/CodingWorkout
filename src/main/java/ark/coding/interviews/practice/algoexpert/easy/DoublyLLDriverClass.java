/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

/**
 * Given the {@link DoublyLinkedList} & {@link Node} classes,
 * implement the body of the following functions -
 * 1. {@link DoublyLinkedList#setHead(Node)}
 * 2. {@link DoublyLinkedList#setTail(Node)}
 * 3. {@link DoublyLinkedList#insertBefore(Node, Node)}
 * 4. {@link DoublyLinkedList#insertAfter(Node, Node)}
 * 5. {@link DoublyLinkedList#insertAtPosition(int, Node)}
 * 6. {@link DoublyLinkedList#removeNodesWithValue(int)}
 * 7. {@link DoublyLinkedList#remove(Node)}
 * 8. {@link DoublyLinkedList#containsNodeWithValue(int)}
 */
public class DoublyLLDriverClass {

    static class DoublyLinkedList {
        public Node head;
        public Node tail;

        public void setHead(Node node) {
            // 1. Remove the node from its current position
            remove(node);

            // 2. Change the head to the provided node
            Node previousHead = head;
            head = node;
            if (previousHead != null) {
                previousHead.prev = head;
                head.next = previousHead;
                head.prev = null;
                if (previousHead.next == null) {
                    // there are only 2 nodes in the list; update tail
                    tail = previousHead;
                }
            }
            else {
                // there was no node in the list before adding this node.
                // head and tail will point to the same node
                tail = node;
            }
        }

        public void setTail(Node node) {
            // 1. Remove the node from its current position
            remove(node);

            // 2. Change the tail to the provided node
            Node previousTail = tail;
            tail = node;
            if (previousTail != null) {
                previousTail.next = tail;
                tail.prev = previousTail;
                tail.next = null;
                if (previousTail.prev == null) {
                    // there are only 2 nodes in the list; update head
                    head = previousTail;
                }
            }
            else {
                // there was no node in the list before adding this node.
                // head and tail will point to the same node
                head = node;
            }
        }

        public void insertBefore(Node node, Node nodeToInsert) {
            // Edge case
            if (node == nodeToInsert) {
                return;
            }

            // 1. Remove the nodeToInsert from its current position.
            remove(nodeToInsert);

            // 2. Insert nodeToInsert before the node.
            Node before = node.prev;
            nodeToInsert.prev = before;
            nodeToInsert.next = node;
            node.prev = nodeToInsert;
            if (before != null) {
                before.next = nodeToInsert;
            }

            // Special Case:
            // If node is head, then adding another node before it will become the new head
            // If node is tail, it will remain the tail after inserting another node before it.
            if (node == head) {
                head = nodeToInsert;
            }
        }

        public void insertAfter(Node node, Node nodeToInsert) {
            // Edge case
            if (node == nodeToInsert) {
                return;
            }

            // 1. Remove the nodeToInsert from its current position.
            remove(nodeToInsert);

            // 2. Insert nodeToInsert after the node.
            Node after = node.next;
            nodeToInsert.next = after;
            nodeToInsert.prev = node;
            node.next = nodeToInsert;
            if (after != null) {
                after.prev = nodeToInsert;
            }

            // Special Case:
            // If node is tail, then adding another node after it, will become the new tail.
            // If node is head, it will remain the head after inserting another node after it.
            if (node == tail) {
                tail = nodeToInsert;
            }
        }

        public void insertAtPosition(int position, Node nodeToInsert) {
            // We are adding a new node at the given position (nodeToInsert does not exist in the list)
            if (position == 1 || head == null) {
                setHead(nodeToInsert);
                return;
            }
            int previousPosition = 1;
            Node nodeBeforePosition = head;
            while (previousPosition < position - 1) {
                nodeBeforePosition = nodeBeforePosition.next;
                previousPosition++;
            }
            insertAfter(nodeBeforePosition, nodeToInsert);
        }

        public void removeNodesWithValue(int value) {
            // Write your code here.
            Node nodeToRemove = head;
            while (nodeToRemove != null) {
                if (nodeToRemove.value == value) {
                    break;
                }
                nodeToRemove = nodeToRemove.next;
            }
            if (nodeToRemove != null) {
                remove(nodeToRemove);

                // look for more nodes with the given value
                removeNodesWithValue(value);
            }
            // if nodeToRemove is null, then no more nodes exist with the given value.
        }

        public void remove(Node node) {
            // 1. Remove incoming references to the node
            Node previous = node.prev;
            Node next = node.next;

            if (previous != null) {
                previous.next = next;
            }
            if (next != null) {
                next.prev = previous;
            }

            // 2. Remove outgoing references from the node.
            node.prev = null;
            node.next = null;

            // Special Case:
            // node is either head or tail; then we should update the respective head/tail pointers.
            if (node == head) {
                head = next;
            }
            if (node == tail) {
                tail = previous;
            }
        }

        public boolean containsNodeWithValue(int value) {
            Node nodeWithValue = head;
            while (nodeWithValue != null) {
                if (nodeWithValue.value == value) {
                    return true;
                }
                nodeWithValue = nodeWithValue.next;
            }
            return false;
        }
    }

    // Do not edit the class below.
    static class Node {
        public int value;
        public Node prev;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
