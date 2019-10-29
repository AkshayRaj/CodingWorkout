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
public final class MinHeap extends BinaryHeap {

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

    /**
     * An unsorted, unordered array of positive integers, that are arranged in a "heap" pattern within the array.
     *
     * After heapifying the array, the root element will be at Arr[0].
     * Below table shows indexes of other nodes for the ith node, i.e., Arr[i]:
     * - Arr[(i-1)/2]	Returns the parent node
     * - Arr[(2*i)+1]	Returns the left child node
     * - Arr[(2*i)+2]	Returns the right child node
     *
     * @param array to heapify.
     * @return the heapified array.
     */
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

    /**
     * Merges the two heaps, represented by their corresponding array representations,
     * into a single minimum binary heap.
     *
     * @param heap1 a minimum binary heap;
     *              The root of heap1 is smaller than the root of heap2
     * @param heap2 a minimum binary heap;
     *              The root of heap1 is smaller than the root of heap2
     * @return the merged heap.
     */
    public int[] mergeHeaps(int[] heap1, int[] heap2) {
        if (heap1[0] > heap2[0]) {
            return mergeHeaps(heap2, heap1);
        }
        // TODO: Implement merge of two min binary heaps

        return heap;
    }

    /**
     * Heapifies the subtree rooted at {@code index}.
     *
     * This assumes that the heap, except <i>only</i> the subtree rooted at {@code index}
     * does not conform to the heap property, and hence the subtree rooted at {@code index}
     * needs to be heapified.
     *
     * <b>Note: To heapify the entire array, use {@link #buildHeap(int[])}</b>
     *
     * @param heap the heap, whose one & only one subtree rooted at a given index does not obey the heap property.
     * @param index of the subtree which does not conform to the heap property.
     */
    private void heapify(final int[] heap, int index) {
        // TODO: Implement the heapify algorithm.
    }

    /**
     * Insert an element with the given value.
     *
     * @param elementValue
     * @return the array index, where the new element is stored.
     */
    public int insert(final int elementValue) {
        int elementKey = -1;

        int newLengthOfHeap = heap.length + 1;
        int newElementIndex = newLengthOfHeap - 1; // place the new element at the end of the heap (or array)
        int[] tmpHeap = Arrays.copyOf(heap, newLengthOfHeap);
        tmpHeap[newElementIndex] = elementValue; // add the new element in the last location

        // pull-up if value of new element is less than the parent
        int parentIndex = parent(tmpHeap, newElementIndex);
        while (tmpHeap[parentIndex] > tmpHeap[newElementIndex]) {
            swap(tmpHeap, parentIndex, newElementIndex);
            newElementIndex = parentIndex;
            parentIndex = parent(tmpHeap, newElementIndex);
        }
        // ^^^ while loop takes O(log(n)) operations
        // to insert a new element & re-balance the heap.
        heap = tmpHeap;
        elementKey = newElementIndex;

        return elementKey;
    }

    /**
     * Decreases value of the element at index {@code elementKey} to {@code newValue}
     *
     * @param elementKey key of the element whose value is to be decreased.
     * @param newValue the new value of the element
     * @return the new key (array index) of the element.
     */
    public int decreaseElement(final int elementKey, final int newValue) {
        int newKey = -1;
        // TODO: Decreases value of element to newValue

        return newKey;
    }

    /**
     * Delete an element at a given index.
     *
     * @param elementKey the key (or the index in array) of the element
     * @return the re-balanced heap, after deleting the element.
     */
    public int[] delete(final int elementKey) {
        // TODO: re-balance the heap after deleting the element from the heap.

        return heap;
    }

    /**
     * Replaces the root element with the given element.
     * This does not mean the given element becomes the new root; rather we are replacing the element at root node
     * with the new element.
     *
     * This pops the root, and pushes the new element. Re-balances the heap after pop & push.
     *
     * @param newElementValue the element to replace the root.
     *                Note that, the new element may not become the new root,
     *                as we still have to maintain the Min-Heap property.
     * @return the re-balanced heap after replacing the root node with the new element.
     */
    public int[] replace(int newElementValue) {
        // calling deleteMinimum() & then insert() will re-balance the heap twice.
        // So will replace the root with the new element, & "heapify" the array.
        heap[0] = newElementValue;

        heapify(heap, 0);
        return heap;
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

    private static void swap(int[] array, int index1, int index2) {
        int valueAtIndex1 = array[index1];
        int valueAtIndex2 = array[index2];
        array[index1] = valueAtIndex2;
        array[index2] = valueAtIndex1;
    }
}
