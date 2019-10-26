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

    public int[] buildHeap(final int[] array) {
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

        for (int index = 0; index < array.length; index++) {
            heapify(heap, array[index]);
        }
        return heap;
    }

    private void heapify(final int[] heap, int index) {
        // TODO: Implement the heapify algorithm.
    }

    public int insert(final int element) {
        int elementKey = -1;
        // TODO: Insert the element, and re-balance the heap

        return elementKey;
    }

    /**
     * Decreases value of the element at index {@code elementKey} to {@code newValue}
     *
     * @param elementKey key of the element whose value is to be decreased.
     * @param newValue the new value of the element
     * @return the new key (array index) of the element.
     */
    public int decreaseKey(final int elementKey, final int newValue) {
        int newKey = -1;
        // TODO: Decreases value of element to newValue

        return newKey;
    }

    public int[] delete(final int elementKey) {
        // TODO: re-balance the heap after deleting the element from the heap.

        return heap;
    }

    /**
     * Pop the root, and push the new element.
     * Re-balances the heap after pop & push.
     *
     * @param elementValue the element to replace the root.
     *                Note that, the new element may not become the new root,
     *                as we still have to maintain the Min-Heap property.
     * @return the re-balanced heap after replacing the root node with the new element.
     */
    public int[] replace(int elementValue) {
        // calling deleteMinimum() & then insert() will re-balance the heap twice.
        // So will replace the root with the new element, & "heapify" the array.
        heap[0] = elementValue;

        return buildHeap(heap);
    }

    /**
     * Get the value in the root node, & delete it.
     * Re-balances the heap after deleting the root node.
     *
     * @return the value in the root node.
     */
    public int extractMinimum() {
        int minimum = findMinimum();
        deleteMinimum();
        return minimum;
    }

    /**
     * Delete the root node & re-balance the heap.
     *
     * @return the re-balanced heap after removing the root node.
     */
    public int[] deleteMinimum() {
        return delete(0);
    }

    /**
     * Fetch the minimum value in the heap.
     * For a MinHeap, it is the root node.
     *
     * @return the minimum value in the heap, i.e. the value in root node
     */
    public int findMinimum() {
        return heap[0];
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
