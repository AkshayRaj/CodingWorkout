package ark.coding.interviews.microsoft.sep_2020;

import ark.coding.interviews.microsoft.sep_2020.helper.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a tree, any tree, what is the largest perfect tree that can be "carved out" from it ? What would be its size?
 */
public class LargestCarvedOutPerfectBinaryTree {
    private TreeProperties maxSizePerfectTreeProperties;
    private Tree maxSizePerfectSubTree;

    public static void main(String[] args) {
        Tree t1 = new Tree();
        Tree t1l = new Tree(); t1.l = t1l; Tree t2 = t1l;
        Tree t1r = new Tree(); t1.r = t1r; Tree t3 = t1r;
        Tree t2l = new Tree(); t2.l = t2l; Tree t4 = t2l;
        Tree t3r = new Tree(); t3.r = t3r; Tree t5 = t3r;
        Tree t3l = new Tree(); t3.l = t3l; Tree t6 = t3l;
        Tree t5l = new Tree(); t5.l = t5l;
        Tree t5r = new Tree(); t5.r = t5r;
        Tree t6l = new Tree(); t6.l = t6l;
        Tree t6r = new Tree(); t6.r = t6r;

        LargestCarvedOutPerfectBinaryTree runner = new LargestCarvedOutPerfectBinaryTree();
        System.out.println("Max Size: " + runner.solution(t1));
    }

    public int solution(Tree root) {
        maxSizePerfectSubTree = root;
        maxSizePerfectTreeProperties = new TreeProperties(true, 0);
        subtreeSize(root);
        return maxSizePerfectTreeProperties.size;
    }

    /**
     * This solution was deduced after the test. I think it is a correct implementation.
     *
     * Calculate different subtree sizes by -
     * 1. Include your child
     * 2. Exclude your child
     *
     * @param subtree
     * @return
     */
    public TreeProperties subtreeSize(Tree subtree) {
        if (subtree == null) return new TreeProperties(true, 0);

        // include your children
        TreeProperties left = subtreeSize(subtree.l);
        TreeProperties right = subtreeSize(subtree.r);

        TreeProperties subTreeProperties;
        // if both children have same size perfect, then I am perfect
        if (left.size == right.size) {
            subTreeProperties = new TreeProperties(true, 1 + left.size + right.size); // include children
            if (subTreeProperties.size > maxSizePerfectTreeProperties.size) {
                maxSizePerfectTreeProperties = subTreeProperties;
                maxSizePerfectSubTree = subtree;
            }
        }
        // if they have different sizes, then "cut" the excess in one of the subtrees,
        // and keep processing.
        else {
            subTreeProperties = new TreeProperties(true, 1); // size is 1, as children are excluded
        }
        return subTreeProperties;
    }



    // Part of solution
    static class TreeProperties {
        final boolean isPerfect; // probably not necessary
        final int size;

        TreeProperties(boolean isPerfect, int size) {
            this.isPerfect = isPerfect;
            this.size = size;
        }
    }

    /**
     * This was what I submitted.
     * It was based on a "mathematical observation"
     *
     * It is clearly wrong.
     */
    public int submittedSolution(Tree T) {
        // Perfect subTree has 0 or 2 children only
        // All leaves at the same level

        LinkedList<Tree> currentLevelNodes = new LinkedList<Tree>(); // LinkedList is also queue in Java
        if (T != null) currentLevelNodes.offer(T);

        List<List<Tree>> nodesByLevel = new ArrayList<>();
        // BFS of tree
        while (!currentLevelNodes.isEmpty()) {
            nodesByLevel.add(currentLevelNodes);
            LinkedList<Tree> nextLevelNodes = new LinkedList<Tree>();
            while (!currentLevelNodes.isEmpty()) {
                Tree node = currentLevelNodes.poll();
                if (node.l != null) nextLevelNodes.offer(node.l);
                if (node.r != null) nextLevelNodes.offer(node.r);
            }
            currentLevelNodes = nextLevelNodes;
        }

        // Find level with max number of nodes.
        int maxNodes = 0;
        int maxNodesLevel = 0;
        int level = 0;
        for (List<Tree> nodes : nodesByLevel) {
            if (nodes.size() > maxNodes) {maxNodes = nodes.size(); maxNodesLevel = level; }
            level++;
        }

        // once we identify the max level, the next max level will be one lower than the max level.
        // Find highest power of 2 less than "maxNodes" = that is the max nodes we can count in the perfect subtree
        int highestPowerOfTwoSmallerThanMaxNodes = Integer.highestOneBit(maxNodes); // this gives the leftmost set bit, which is the highest power of two less than or equal to max nodes
        int solution = highestPowerOfTwoSmallerThanMaxNodes;
        while (highestPowerOfTwoSmallerThanMaxNodes > 0) {
            solution += (highestPowerOfTwoSmallerThanMaxNodes >> 1);
        }
        return solution;
    }
}
