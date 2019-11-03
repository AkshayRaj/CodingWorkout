/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.Arrays;
import java.util.List;

/**
 * A minimum binary heap of {@link WeightedNode}s.
 */
public final class MinNodeHeap extends NodeHeap {

    public MinNodeHeap(int initialCapacity) {
        nodeKeyMap = new DualHashBidiMap<>();
        heap = new int[initialCapacity];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(WeightedNode node) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WeightedNode extractMinimum() {
        String name = nodeKeyMap.getKey(0);
        Integer weight = heap[0];
        WeightedNode minWeightedNode = new WeightedNode(name, weight);

        deleteNode(0);

        return minWeightedNode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(String name) {
        return nodeKeyMap.containsKey(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void decreaseElement(int nodeName, int nodeWeight) {

    }

    public void initialize(final List<WeightedNode> weightedNodes) {
        int count = 0;
        for (WeightedNode node : weightedNodes) {
            heap[count] = node.getWeight();
            nodeKeyMap.put(node.getName(), count);
            count++;
        }
        buildNodeHeap();
    }

    /**
     * An optimized implementation, uses push down on all levels, except last -
     * 1. Get the last non-leaf node. T
     *    This is the parent of the last element, i.e. element at (n-1) index
     * 2. Once you get this node, iteratively call push-down from this node until you reach root node
     *    i.e. element at array index '0'
     */
    private void buildNodeHeap() {
        int parentIndexOfLastElement = parentIndex(heap, heap.length - 1);
        for (int nonLeafNodeIndex = parentIndexOfLastElement; nonLeafNodeIndex >= 0; parentIndexOfLastElement--) {
            heap = pushDown(nonLeafNodeIndex);
        }
    }

    private int[] pushDown(final int index) {
        int elementIndex = index;
        int leftChildIndex = leftChildIndex(heap, elementIndex);
        int rightChildIndex = rightChildIndex(heap, elementIndex);

        // if value at current node is greater than value in either of its children;
        // then keep PUSHING-DOWN
        while (heap[elementIndex] > heap[rightChildIndex]
                || heap[elementIndex] > heap[leftChildIndex]) {

            // rightChild is smallest of the 3 nodes
            if (heap[leftChildIndex] > heap[rightChildIndex]) {
                swap(elementIndex, rightChildIndex);
                elementIndex = rightChildIndex;
            }
            // Or leftChild is smallest of the 3 nodes
            else {
                swap(index, leftChildIndex);
                elementIndex = leftChildIndex;
            }
            leftChildIndex = leftChildIndex(heap, elementIndex);
            rightChildIndex = rightChildIndex(heap, elementIndex);
        }
        // ^^^ pushing-down while loop complexity is O(2log(n)) [since we compare with both children]
        // but since 2 is constant, time complexity of pushing-down while loop is O(log(n))

        return heap;
    }

    private void swap(int indexNode1, int indexNode2) {
        String nameNode1 = nodeKeyMap.getKey(indexNode1);
        int valueAtIndex1 = heap[indexNode1];
        String nameNode2 = nodeKeyMap.getKey(indexNode2);
        int valueAtIndex2 = heap[indexNode2];

        // swap weights in heap; indexes in nodeKeyMap
        heap[indexNode1] = valueAtIndex2;
        heap[indexNode2] = valueAtIndex1;
        nodeKeyMap.put(nameNode1, indexNode2);
        nodeKeyMap.put(nameNode2, indexNode1);
    }

    private void deleteNode(int index) {
        int lastElementIndex = heap.length - 1;
        String lastElementName = nodeKeyMap.getKey(lastElementIndex);
        int lastElementWeight = heap[lastElementIndex];

        heap[index] = lastElementWeight;
        heap = Arrays.copyOf(heap, lastElementIndex);
        nodeKeyMap.removeValue(index);
        nodeKeyMap.replace(lastElementName, index);

        pushDown(index);
    }

}
