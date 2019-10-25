/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

/**
 * https://www.topcoder.com/community/competitive-programming/tutorials/disjoint-set-data-structures/
 * In a data structure of disjoint sets every set contains a representative, which is one member of the set.
 */

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * What is Disjoint-set data structure?
 *
 * Represents a mathematics concept Set.
 * A disjoint-set data structure, also called a union–find data structure or merge–find set.
 * A disjoint-set data structure that keeps track of a set of elements partitioned into a number of disjoint or non-overlapping subsets.
 * It provides near-constant-time operations to add new sets, to merge existing sets, and to determine whether elements are in the same set.
 * Plays a key role in Kruskal’s algorithm for finding the minimum spanning tree of a graph.
 * This can also be used to detect cycle in the graph.
 */
public class DisjointSet {

    Map<Integer, Integer> disjointSet;

    /**
     * Creates a new element, and adds it to a single element set, containing the new element.
     * O(1) create time.
     *
     * @param newElement the new element to create
     * @return the representative of the new set, i.e. the element itself.
     *         Since the element is in its "own" set, it is its own representative.
     */
    public int create(int newElement) {
        if (disjointSet.containsKey(newElement)) {
            throw new RuntimeException(String.format("Element-[%d] already exists in the DisjointSet.\n"
                    + "Its representative is [%d]", newElement, disjointSet.get(newElement)));
        }

        disjointSet.put(newElement, newElement);
        return newElement;
    }

    /**
     * Merges the set containing {@code element1} with the set containing {@code element2}.
     * O(N) ; N is the total number of elements in all the subsets.
     *
     * @param element1 an element of one of the sets.
     * @param element2 another element of one of the sets.
     * @return the representative of the merged set.
     */
    public int mergeSet(int element1, int element2) {
        int mergedSetRep = -1;

        int representative1 = disjointSet.get(element1);
        int representative2 = disjointSet.get(element2);

        // construct set which contains element1 & element2
        // Optimization: which set to merge (or append to the other set)
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (Map.Entry entry : disjointSet.entrySet()) {
            if (entry.getValue().equals(representative1)) {
                set1.add((Integer) entry.getKey());
            }
            if (entry.getValue().equals(representative2)) {
                set2.add((Integer) entry.getKey());
            }
        }

        if (set1.size() < set2.size()) {
            // append elements of set1 to set2
            mergedSetRep = representative2;
            for (Integer element : set1) {
                disjointSet.replace(element, mergedSetRep);
            }
        }
        else {
            // append elements of set2 to set1
            mergedSetRep = representative1;
            for (Integer element : set2) {
                disjointSet.replace(element, mergedSetRep);
            }
        }

        //check state
        if (mergedSetRep == -1) {
            throw new RuntimeException("Something went wrong when merging sets.");
        }

        return mergedSetRep;
    }

    /**
     * Find the representative of the set, of which the {@code element} is a part of.
     * O(1) fetch time.
     *
     * @param element of a set, whose representative is to be searched.
     * @return the representative of the set that contains the element;
     *         {@literal -1}, if the element does not exist.
     */
    public int find(int element) {
        if (!disjointSet.containsKey(element)) {
            throw new RuntimeException(String.format("Element-[%d] does not exist in the DisjointSet."));
        }

        return disjointSet.get(element);
    }
}
