/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

import java.util.logging.Logger;

/**
 * Min-Max Heap - https://en.wikipedia.org/wiki/Min-max_heap
 * A heap that has alternating levels of min & max numbers.
 */
public class MinMaxHeap {

    int[] heap = new int[]{};

    /**
     * Heapifies a MinMax Heap from the provided array of positive integers.
     *
     * @param array of the positive integers to heapify.
     * @return the heapified version of the array of positive integers.
     */
    public int[] build(final int[] array) {
        return heap;
    }

    /**
     * Inserts the provided element into the heap, & balances the tree after insertion.
     * @param element the element to insert into the heap
     * @return the new heap
     */
    public int[] insert(int element) {
        // TODO: Implement #insert element in the heap
        return heap;
    }

    /**
     * Removes the current minimum value in the heap, i.e. the root.
     * It then balances the heap & calculates the new minimum of the heap and returns it to the caller.
     *
     * @return the new minimum value of the heap.
     */
    public int removeMinimum() {
        // TODO: Balance the heap after removing the minimum value.
        return getMinimum();
    }

    /**
     * Removes the maximum value in the heap, i.e. either of the two children of the root.
     * It then balances the tree after removing the maximum & returns the new maximum of the heap to the caller.
     *
     * @return the new maximum of the heap.
     */
    public int removeMaximum() {
        // TODO: Balance the heap after removing the maximum value.
        return getMaximum();
    }

    /**
     * Fetch the minimum value in the heap
     *
     * @return the minimum value stored in the heap.
     */
    public int getMinimum() {
        int min = Integer.MAX_VALUE;
        if (heap.length == 0) {
            Logger.getGlobal().warning("Invalid request. Heap is not initialized. Cannot return min value.");
            return min;
        }

        return heap[0];
    }

    /**
     * Fetch the maximum value in the heap
     *
     * @return the maximum value stored in the heap
     */
    public int getMaximum() {
        int max = Integer.MIN_VALUE;
        if (heap.length == 0) {
            Logger.getGlobal().warning("Invalid request. Heap is not initialized. Cannot return max value.");
            return max;
        }

        switch (heap.length) {
            case 1: max = heap[0];
            case 2: max = heap[1];
            default: max = heap[heap[1] >= heap[2] ? 1 : 2];
        }
        return max;
    }

    void pushDown(final int[] heap, final int index) {
        if (isIndexOnMinLevel(index)) {
            pushDownMin(heap, index);
        }
        else {
            pushDownMax(heap, index);
        }
    }

    void pushUp(final int[] heap, int index) {

    }

    private void pushDownMax(int[] heap, int index) {
        // TODO: Implement #pushDownMax
    }

    private void pushDownMin(int[] heap, int index) {
        // TODO: Implement #pushDownMin
    }

    private boolean isIndexOnMinLevel(int index) {
        // TODO: Identify whether the element at #index is at a min or max level of the heap.
        return false;
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
