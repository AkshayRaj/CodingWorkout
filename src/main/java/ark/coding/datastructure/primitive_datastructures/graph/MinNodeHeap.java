/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.Arrays;

/**
 * A minimum binary heap of {@link WeightedNode}s.
 */
public final class MinNodeHeap extends NodeHeap {

    public MinNodeHeap(int[][] graph) {
        nodeKeyMap = new DualHashBidiMap<>();
        heap = new int[graph.length];
        initializeNodeHeap(graph);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(WeightedNode node) {
        int noOfNodes = heap.length + 1;
        heap = Arrays.copyOf(heap, noOfNodes);

        heap[noOfNodes - 1] = node.getWeight();
        nodeKeyMap.put(node.getName(), noOfNodes - 1);

        pullUp(noOfNodes - 1);
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
    public void decreaseElement(String nodeName, int nodeWeight) {
        int key = nodeKeyMap.get(nodeName);
        int currentWeight = heap[key];
        if (nodeWeight > currentWeight) {
            throw new RuntimeException(String.format("Current weight [%d] of node [%s] is less than the new weight[%d].",
                    currentWeight, nodeName, nodeWeight));
        }

        heap[key] = nodeWeight;
        pullUp(key);
    }

    @Override
    public void remove(String nodeName) {
        int lastElementIndex = heap.length - 1;
        int nodeKey = nodeKeyMap.get(nodeName);

        // 1. swap with lastElement
        // 2.then remove the lastElement entries from the map and heap
        swap(nodeKey, lastElementIndex);
        nodeKeyMap.remove(nodeName);
        heap = Arrays.copyOf(heap, heap.length - 1);
        // 3. pushDown the original last element
        pushDown(nodeKey);
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
            pushDown(nonLeafNodeIndex);
        }
    }

    private void pushDown(final int index) {
        if (heap.length == 0) {
            return;
        }
        int elementIndex = index;
        int leftChildIndex = leftChildIndex(heap, elementIndex);
        int rightChildIndex = rightChildIndex(heap, elementIndex);
        int elementWeight = getWeight(elementIndex);
        int rightChildWeight = getWeight(rightChildIndex);
        int leftChildWeight = getWeight(leftChildIndex);

        // if value at current node is greater than value in either of its children;
        // then keep PUSHING-DOWN
        while (elementWeight > rightChildWeight
                || elementWeight > leftChildWeight) {

            // rightChild is smallest of the 3 nodes
            if (leftChildWeight > rightChildWeight) {
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
            leftChildWeight = getWeight(leftChildIndex);
            rightChildWeight = getWeight(rightChildIndex);
        }
        // ^^^ pushing-down while loop complexity is O(2log(n)) [since we compare with both children]
        // but since 2 is constant, time complexity of pushing-down while loop is O(log(n))
    }

    private int getWeight(int index) {
        return index >= 0
                ? heap[index]
                : Integer.MAX_VALUE;
    }

    private void pullUp(int index) {
        int elementIndex = index;
        int parentIndex = parentIndex(heap, elementIndex);

        // if value at current node is smaller than value in the parent,
        // then keep PULLING-UP
        while (heap[elementIndex] < heap[parentIndex]) {
            swap(index, parentIndex);
            elementIndex = parentIndex;
            parentIndex = parentIndex(heap, elementIndex);
        }
        // ^^^ pulling-up while loop complexity is O(log(n))
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

    private void initializeNodeHeap(int[][] graph) {
        for (int node = 0; node < graph.length; node++) {
            String nodeName = String.valueOf(node);
            int nodeWeight = Integer.MAX_VALUE;
            heap[node] = nodeWeight;
            nodeKeyMap.put(nodeName, node);
        }
    }
}
