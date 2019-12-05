/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.trees;

public class Node implements Comparable<Node> {

    private final Integer data;
    private final Node leftChild;
    private final Node rightChild;

    public Node(Integer data, Node leftChild, Node rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    /**
     * Compares this {@link Node} with other {@link Node}s in a {@link java.util.Collection}
     *
     * @param otherNode the other node to which this node is being compared with.
     * @return If the data of this node is greater than the other node, returns {@literal 1}
     *         If the data of this node is less than the other node, returns {@literal -1}
     *         If the data of this node is equal to the data of the other node, returns {@literal 0}
     */
    @Override
    public int compareTo(Node otherNode) {
        if (this.getData() < otherNode.getData()) {
            return -1;
        }
        if (this.getData() > otherNode.getData()) {
            return 1;
        }

        return 0;
    }

    public Integer getData() {
        return data;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

}
