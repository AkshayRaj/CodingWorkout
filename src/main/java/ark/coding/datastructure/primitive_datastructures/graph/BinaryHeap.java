/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

import java.util.logging.Logger;

public class BinaryHeap {

    /**
     * Returns the index of the parent of the element situated at {@code elementIndex}
     *
     * @param binaryHeap array representation of the binary heap
     * @param elementIndex array index of the element
     * @return index of the parent of the element.
     */
    protected static int parentIndex(int[] binaryHeap, int elementIndex) {
        if (binaryHeap.length == 0) {
            Logger.getGlobal().warning(String.format("Heap is not initialized. " +
                    "Parent does not exist for element at index[%d]", elementIndex));
            return -1;
        }
        if (elementIndex == 0) {
            return 0;
        }
        int parent = (elementIndex - 1)/2;

        return parent;
    }

    /**
     * Returns the index to the left child of the current element of a {@link BinaryHeap}
     *
     * @param binaryHeap array representation of the binary heap
     * @param elementIndex array index of the element
     * @return index of the left child of the current element;
     *         {@literal -1} if the element is a leaf node or left child does not exist.
     */
    protected static int leftChildIndex(int[] binaryHeap, int elementIndex) {
        int leftChild = 2 * elementIndex + 1;
        if (binaryHeap.length <= leftChild) {
            return -1;
        }

        return leftChild;
    }

    /**
     * Returns the index to the right child of the current element of a {@link BinaryHeap}
     *
     * @param binaryHeap array representation of the binary heap
     * @param elementIndex array index of the element
     * @return index of the right child of the current element;
     *         {@literal -1} if the element is a leaf node or right child does not exist.
     */
    protected static int rightChildIndex(int[] binaryHeap, int elementIndex) {
        int rightChild = 2 * elementIndex + 2;
        if (binaryHeap.length <= rightChild) {
            return -1;
        }

        return rightChild;
    }
}
