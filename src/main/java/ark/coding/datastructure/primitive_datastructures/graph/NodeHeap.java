/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

import org.apache.commons.collections4.BidiMap;

public abstract class NodeHeap extends BinaryHeap {

    /**
     * Insert the given {@link WeightedNode} in the binary heap.
     *
     * @param node to add to the heap
     */
    abstract void insert(final WeightedNode node);

    /**
     * Retrieve the minimum {@link WeightedNode} from the heap,
     * and remove it from the heap.
     *
     * @return the minimum {@link WeightedNode} in the heap.
     */
    abstract WeightedNode extractMinimum();

    /**
     * Check if a particular node exists in the heap.
     *
     * @param name of the node
     * @return true, if the node exists in the heap;
     *         false, otherwise.
     */
    abstract boolean contains(String name);

    /**
     * Decrease the weight of a node.
     *
     * @param nodeName name of the node
     * @param nodeWeight new weight of the node.
     */
    abstract void decreaseElement(final int nodeName, final int nodeWeight);

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
}
