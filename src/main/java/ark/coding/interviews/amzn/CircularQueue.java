/**
 * Created by Akshayraj
 */
package ark.coding.interviews.amzn;

public class CircularQueue {

    int[] queue;
    int head = -1;
    int tail = -1;

    CircularQueue(int capacity) {
        queue = new int[capacity];
    }

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(4);

        System.out.println("Is queue empty : " + circularQueue.isQueueEmpty());
        System.out.println("Is queue full  : " + circularQueue.isQueueFull());

        circularQueue.push(1);
        circularQueue.push(2);

        System.out.println("Is queue empty : " + circularQueue.isQueueEmpty());
        System.out.println("Is queue full  : " + circularQueue.isQueueFull());

        circularQueue.push(3);
        circularQueue.push(4);

        System.out.println("Is queue empty : " + circularQueue.isQueueEmpty());
        System.out.println("Is queue full  : " + circularQueue.isQueueFull());

        circularQueue.pop();
        System.out.println("Is queue empty : " + circularQueue.isQueueEmpty());
        System.out.println("Is queue full  : " + circularQueue.isQueueFull());

        circularQueue.push(5);
        System.out.println("Is queue empty : " + circularQueue.isQueueEmpty());
        System.out.println("Is queue full  : " + circularQueue.isQueueFull());

        circularQueue.push(6);
        circularQueue.push(7);

        System.out.println("Is queue empty : " + circularQueue.isQueueEmpty());
        System.out.println("Is queue full  : " + circularQueue.isQueueFull());

        circularQueue.pop();
        circularQueue.pop();
        circularQueue.pop();
        circularQueue.pop();
        System.out.println("Is queue empty : " + circularQueue.isQueueEmpty());
        System.out.println("Is queue full  : " + circularQueue.isQueueFull());

        //circularQueue.pop();
    }

    public void push(int element) {
        System.out.println("Pushing [" + element + "] onto queue.");
        if (!isQueueFull()) {
            tail = (tail + 1) % queue.length;
            queue[tail] = element;

            // if this is the first element in the queue.
            if (head == -1) {
                head = tail;
            }
        }
        else {
            // queue is full
            // pop the head, and push in the new element
            // here, we replace the element in head,
            // and head points to the next oldest element in the queue.
            queue[head] = element;
            head = (head + 1) % queue.length;

            // CircularQueue is full when `tail` trails `head` by '1' cell.
            tail = (tail + 1) % queue.length;
        }
    }

    public int pop() {
        if (!isQueueEmpty()) {
            int popped = queue[head];
            if (head == tail) {
                // there was only 1 item, before the pop;
                // that means the queue is now empty.
                // reset head & tail
                head = -1;
                tail = -1;
            }
            else {
                head = (head + 1) % queue.length;
            }
            System.out.println("Popping [" + popped + "] from queue.");
            return popped;
        }
        else {
            throw new RuntimeException("Queue is empty. No item to return");
        }
    }

    private boolean isQueueEmpty() {
        return head == -1 && tail == -1;
    }

    private boolean isQueueFull() {
        return ((tail + 1) % queue.length) == head;
    }
}
