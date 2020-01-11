/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://www.algoexpert.io/questions/Youngest%20Common%20Ancestor
 */
class YoungestCommonAncestor {
    public static AncestralTree getYoungestCommonAncestor(
            AncestralTree topAncestor, AncestralTree descendantOne, AncestralTree descendantTwo) {
        // Write your code here.
        List<AncestralTree> lineage1 = getLineageOfDescendant(descendantOne, new ArrayDeque<>())
                                    .stream()
                                    .collect(Collectors.toList());
        List<AncestralTree> lineage2 = getLineageOfDescendant(descendantTwo, new ArrayDeque<>())
                                    .stream()
                                    .collect(Collectors.toList());
        int commonAncestorMaxLevel = lineage1.size() < lineage2.size()
                                    ? lineage1.size() - 1
                                    : lineage2.size() - 1;

        for (int ancestorLevel = commonAncestorMaxLevel; ancestorLevel >= 0; ancestorLevel--) {
            if (lineage1.get(ancestorLevel).name == lineage2.get(ancestorLevel).name) {
                return lineage1.get(ancestorLevel);
            }
        }
        return null;
    }
    // Time : O(d1 + d2)
    // Space: O(1) ; we do create two lists, but the lists themselves do not create copies of the descendants.
    //               Rather the lists add reference to the actual AncestralTree objects (as is the case in JAVA :))
    // d1 & d2 are depths of the descendants
    //

    static Deque<AncestralTree> getLineageOfDescendant(AncestralTree descendant, Deque<AncestralTree> lineage) {
        if (descendant == null) {
            return lineage;
        }
        lineage.push(descendant);
        return getLineageOfDescendant(descendant.ancestor, lineage);
    }

    static class AncestralTree {
        public char name;
        public AncestralTree ancestor;

        AncestralTree(char name) {
            this.name = name;
            this.ancestor = null;
        }

        // This method is for testing only.
        void addAsAncestor(AncestralTree[] descendants) {
            for (AncestralTree descendant : descendants) {
                descendant.ancestor = this;
            }
        }
    }
}
