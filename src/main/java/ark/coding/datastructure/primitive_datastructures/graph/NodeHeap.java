/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

import org.apache.commons.collections4.BidiMap;

import java.util.Set;

/**
 * Binary heap of {@link WeightedNode}s.
 */
public abstract class NodeHeap extends BinaryHeap {

    /**
     * Insert a new {@link WeightedNode} in the binary heap.
     *
     * @param node to add to the heap
     */
    public abstract void insert(final WeightedNode node);

    /**
     * Retrieve the minimum {@link WeightedNode} from the heap,
     * and remove it from the heap.
     *
     * @return the minimum {@link WeightedNode} in the heap.
     */
    public abstract WeightedNode extractMinimum();

    /**
     * Check if a particular node exists in the heap.
     *
     * @param name of the node
     * @return true, if the node exists in the heap;
     *         false, otherwise.
     */
    public abstract boolean contains(String name);

    /**
     * Decrease the weight of a node.
     *
     * @param nodeName name of the node
     * @param nodeWeight new weight of the node.
     */
    public abstract void decreaseElement(final String nodeName, final int nodeWeight);

    /**
     * Remove the node from the heap.
     *
     * @param nodeName name of the node to remove.
     */
    public abstract void remove(String nodeName);

    /**
     * Mapping between the name of the node, and the location (array-index) in {@link #heap}
     * where the node's weight is stored.
     */
    BidiMap<String, Integer> nodeKeyMap;

    /**
     * Array containing the weight of nodes.
     * The index of the array is mapped to the name of the {@link WeightedNode}, whose weight is stored in that index,
     * in {@link #nodeKeyMap}
     */
    int[] heap;

    public Set<String> keySet() {
        return nodeKeyMap.keySet();
    }

    public int getWeight(String nodeName) {
        return heap[nodeKeyMap.get(nodeName)];
    }

    public boolean isEmpty() {
        return nodeKeyMap.isEmpty();
    }
}
