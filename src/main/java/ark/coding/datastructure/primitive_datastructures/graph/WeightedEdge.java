/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

import java.util.Objects;

/**
 * Represents an un-directed, weighted edge in a Graph.
 */
public class WeightedEdge implements Comparable<WeightedEdge> {
    private final String node1;
    private final String node2;
    private final Integer weight;

    private WeightedEdge(String node1, String node2, Integer weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    /**
     * The natural ordering of the weighted edges - by their weights.
     * This method is inherited from {@link Comparable#compareTo(Object)} for use in Collections framework.
     * This helps us in sorting edges by their weights.
     *
     * @param otherEdge the other {@link WeightedEdge} to which this edge is compared against.
     * @return 1, if this edge has more weight than the {@code otherEdge}
     *         -1, if this edge has less weight than the {@code otherEdge}
     *         0, if the weights of this and the {@code otherEdge} are equal.
     */
    @Override
    public int compareTo(WeightedEdge otherEdge) {
        if (weight > otherEdge.getWeight()) {
            return 1;
        }
        if (weight < otherEdge.getWeight()) {
            return -1;
        }

        return 0;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getNode1() {
        return node1;
    }

    public String getNode2() {
        return node2;
    }

    public Integer getWeight() {
        return weight;
    }

    public boolean exists() {
        return weight > 0;
    }

    public static class Builder {
        private String node1;
        private String node2;
        private Integer weight;

        public WeightedEdge.Builder node1(String node1) {
            this.node1 = node1;
            return this;
        }

        public WeightedEdge.Builder node2(String node2) {
            this.node2 = node2;
            return this;
        }

        public WeightedEdge.Builder weight(Integer weight) {
            this.weight = weight;
            return this;
        }

        public WeightedEdge build() {
            return new WeightedEdge(node1, node2, weight);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightedEdge that = (WeightedEdge) o;
        return
                // this class represents an undirected weighted edge.
                // the pair of nodes should be the same; hence the OR condition when comparing the two nodes.
                (Objects.equals(node1, that.node1) || Objects.equals(node1, that.node2))
                && (Objects.equals(node2, that.node2) || Objects.equals(node2, that.node1))
                // weights should be equal as well
                && Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node1, node2, weight);
    }
}
