package ark.coding.books.interview_kickstart.recursion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-1690-9581-34-240
 */
public class NoOfBSTsWithNNodes {
    /**
     *
     * @param n 1 ~>> 16
     * @return
     */
    /*
     * Complete the function below.
     */
//    static long how_many_BSTs(int n) {
//        return how_many_BSTs_helper(n);
//    }
//
//    static long how_many_BSTs_helper(int n) {
//        if (n==0) return 1L; // a BST is formed at this point
//
//        long count = 0L;
//        for (int noOfleftNodes = 0; noOfleftNodes < n; noOfleftNodes++){
//            int noOfRightNodes = n-noOfleftNodes-1; // -1 to account for root.
//            count += how_many_BSTs_helper(noOfleftNodes) * how_many_BSTs_helper(noOfRightNodes);
//        }
//        return count;
//    }
    static int[] memo;
    /**
     *
     * @param n 1 ~>> 16
     * @return
     */
    public static long how_many_BSTs(int n) {
        memo = new int[n+1]; Arrays.fill(memo, -1);

        return recursive(n);
    }

    /**
     * No of BSTs possible for a given "root" = leftSubTreeSize * rightSubTreeSize
     *
     * @param treeSize treeSize including the root
     * @return number of BSTs possible for a tree with given {@code treeSize}
     */
    static long recursive(int treeSize) {
        if (memo[treeSize] != -1) return memo[treeSize];
        if (treeSize == 0) return 1;

        int count = 0;
        for (int leftSubTreeSize = 0; leftSubTreeSize < treeSize; leftSubTreeSize++) {
            int rightSubTreeSize = treeSize - leftSubTreeSize - 1;
            count += (recursive(leftSubTreeSize) * recursive(rightSubTreeSize));
        }

        memo[treeSize] = count;
        return memo[treeSize];
    }

    static class NodeSetItem {
        final int value;
        NodeSetItem left;
        NodeSetItem right;

        NodeSetItem(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof NodeSetItem)) return false;
            NodeSetItem that = (NodeSetItem) o;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    private static Set<NodeSetItem> getNodeSet(int n) {
        NodeSetItem[] set = new NodeSetItem[n];
        for (int i = 0; i < n; i++) {
            set[i] = new NodeSetItem(i+1);
        }
        return new HashSet<NodeSetItem>(Arrays.asList(set));
    }

}

/***************************************************************
 * *************************************************************
 *                    FIRST WORKING VERSION
 * *************************************************************
 * *************************************************************
public static long how_many_BSTs(int n) {
    // Assign subtree root
    // Assign left child (less than root)
    // Assign right child (greater than root)
    //
    Set<NodeSetItem> nodes = getNodeSet(n);

    int count = 0;
    for (NodeSetItem node : nodes) {
        Set<NodeSetItem> remaining = new HashSet<>(nodes); remaining.remove(node);
        count += recursive(remaining, node);
    }
    return count;
}

    static long recursive(Set<NodeSetItem> nodes, NodeSetItem root) {
        if (nodes.size() == 0) return 1;

        // all possible left child; if left child possible from set, then it is null
        Set<NodeSetItem> possibleLeftNodes = new HashSet<>();
        Set<NodeSetItem> possibleRightNodes = new HashSet<>();
        for (NodeSetItem node : nodes) {
            if (node.value < root.value) {
                possibleLeftNodes.add(node);
            }
            else {
                possibleRightNodes.add(node);
            }
        }

        int leftCount = 0;
        for (NodeSetItem node : possibleLeftNodes) {
            Set<NodeSetItem> remaining = new HashSet<>(possibleLeftNodes); remaining.remove(node);
            leftCount += recursive(remaining, node);
        }
        leftCount = Math.max(1, leftCount);

        int rightCount = 0;
        for (NodeSetItem node : possibleRightNodes) {
            Set<NodeSetItem> remaining = new HashSet<>(possibleRightNodes); remaining.remove(node);
            rightCount += recursive(remaining, node);
        }
        rightCount = Math.max(1, rightCount);

        return leftCount * rightCount;
    }
 */
