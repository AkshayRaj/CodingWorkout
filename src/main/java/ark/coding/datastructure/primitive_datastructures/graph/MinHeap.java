/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * A MinHeap is different from a Min-Max Heap.
 * - Min-Max Heap - https://en.wikipedia.org/wiki/Min-max_heap
 * - Min Heap - https://en.wikipedia.org/w/index.php?title=Min-heap&redirect=no
 *
 * All children of a MinHeap are larger than the root. Thus, the minimum value of the heap is at the root.
 *
 * Analogous to a MinHeap is MaxHeap, where all the nodes of the heap are smaller than the root node.
 * Thus, the maximum value of the heap is at the root, in case of a MaxHeap.
 * {@see https://www.educative.io/edpresso/min-heap-vs-max-heap}
 */
public class MinHeap {

    /**
     * https://www.geeksforgeeks.org/array-representation-of-binary-heap/
     *
     * A Binary Heap is a Complete Binary Tree. A binary heap is typically represented as array.
     * The representation is done as:
     *
     * The root element will be at Arr[0].
     * Below table shows indexes of other nodes for the ith node, i.e., Arr[i]:
     * - Arr[(i-1)/2]	Returns the parent node
     * - Arr[(2*i)+1]	Returns the left child node
     * - Arr[(2*i)+2]	Returns the right child node
     *
     * Another great resource, which discusses tree/heap representation using arrays -
     * https://web.cecs.pdx.edu/~sheard/course/Cs163/Doc/HeapReview.html
     */
    int[] heap = new int[]{};

    public int[] build(final int[] array) {
        // array representing the heap will have empty cells,
        // if the number of nodes in heap are not exactly equal to 2 raised to some power, i.e (2^power)
        int heapArrayLength = array.length;
        for (int power = 0; power < array.length; power++) {
            if ((2^power) >= array.length) {
                heapArrayLength = (2^power);
            }
        }
        heap = Arrays.copyOf(array, heapArrayLength);
        Arrays.fill(heap, array.length, heapArrayLength-1, Integer.MAX_VALUE);

        for (int index = (heap.length/2); index >= 0; index--) {

        }
        return heap;
    }

    /**
     * Getter for minMaxHeap
     * @return A heap-ified integer array.
     */
    public int[] getHeap() {
        if (heap.length == 0) {
            Logger.getGlobal().warning("Heap is not initialized. Returning empty array.");
            return new int[]{};
        }
        return heap;
    }
}
