/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

import java.util.Objects;

public class WeightedNode implements Comparable<WeightedNode> {
    private String name;
    private Integer weight;

    public WeightedNode(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }

    /**
     * The natural ordering of the weighted nodes - by their weights.
     * This method is inherited from {@link Comparable#compareTo(Object)} for use in Collections framework.
     * This helps us in sorting the nodes by their weights.
     *
     * @param otherNode the other {@link WeightedNode} to which this node is compared against.
     * @return 1, if this node has more weight than the {@code otherNode}
     *         -1, if this node has less weight than the {@code otherNode}
     *         0, if the weights of this and the {@code otherNode} are equal.
     */
    @Override
    public int compareTo(WeightedNode otherNode) {
        if (weight > otherNode.getWeight()) {
            return 1;
        }
        if (weight < otherNode.getWeight()) {
            return -1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightedNode that = (WeightedNode) o;
        return name.equalsIgnoreCase(that.name) &&
                Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
}
