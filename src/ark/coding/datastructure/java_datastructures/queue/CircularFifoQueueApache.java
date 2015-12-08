package ark.coding.datastructure.java_datastructures.queue;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.Iterator;


public class CircularFifoQueueApache {
    static CircularFifoQueue<Integer> integerCircularFifoQueue;

    public static void main(String[] args){
        integerCircularFifoQueue = new CircularFifoQueue<Integer>(3);
        insertIntoQueue(1);
        insertIntoQueue(2);
        insertIntoQueue(3);
        insertIntoQueue(4);
        insertIntoQueue(5);
        insertIntoQueue(6);
        insertIntoQueue(7);
    }

    private static void insertIntoQueue(int i) {
        integerCircularFifoQueue.add(i);
        Iterator<Integer> iterator = integerCircularFifoQueue.iterator();
        System.out.println("\nElements: ");
        while(iterator.hasNext()){
            System.out.println(iterator.next() + ", head: " + integerCircularFifoQueue.peek()
                    + ", size: " + integerCircularFifoQueue.size() + " " + (((float)144/150) == 0.96));
        }
    }
}
