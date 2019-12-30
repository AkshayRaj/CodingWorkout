/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Question: https://www.algoexpert.io/questions/Branch%20Sums
 * A Binary Tree branch is a path from a <b>leaf node</b> to the root node.
 *
 * The sum of a branch is the sum of values of all nodes on the path from the <b>leaf node</b> towards the root node.
 */
public class BranchSums {

    /**
     * Official approach of Algo-Expert. (I re-worked it)
     *
     * - As we go down and trace a branch, keep the sum of all ancestors, traversed so far in the path.
     * - First traverse the left child, and then the right child.
     * - We can implement this recursively; terminate the recursion when you encounter a null node.
     * - When you encounter a leaf node, add the sum to the list.
     *
     * @param root the root of the binary tree
     * @return list of sums of all branches, ordered from leftmost branch to the rightmost.
     */
    public static List<Integer> branchSumsRecursive(BinaryTree root) {
        List<Integer> solution = new ArrayList<>();

        int sumOfAllAncestorsInBranch = 0;
        calculateBranchSum(root, sumOfAllAncestorsInBranch, solution);

        return solution;
    }

    private static void calculateBranchSum(BinaryTree node, int sumOfAllAncestorsInBranch, List<Integer> solution) {
        if (node == null) {
            return;
        }

        // for a leaf node, calculate the final sum of the branch, and add it to the list
        if (node.left == null && node.right == null) {
            int branchSum = sumOfAllAncestorsInBranch + node.value;
            solution.add(branchSum);
        }

        int updatedRunningBranchSum = sumOfAllAncestorsInBranch + node.value;
        calculateBranchSum(node.left, updatedRunningBranchSum, solution);
        calculateBranchSum(node.right, updatedRunningBranchSum, solution);
    }

    /**================================================================================================================================
     * Approach:
     * One of the requirements for the solution is that the sum of branches have to be ordered from leftmost branch -> rightmost branch.
     *
     * - We maintain a stack of the nodes in the current branch.
     * - Get the leftmost leaf node, and calculate the branch sum.
     *   Repeat this process, as we move towards the right.
     * - To keep track of which child - left or right - of a given node has been visited,
     *   we use the data structure {@link NodeChildVisitTracker}
     *
     * @param root of the binary tree.
     * @return list of sum of all branches, ordered from the leftmost branch sum to the rightmost branch sum.
     */
    public static List<Integer> branchSums(BinaryTree root) {
        List<Integer> solution = new ArrayList<>();

        Stack<NodeChildVisitTracker> currentBranchNodes = new Stack<>();
        currentBranchNodes.push(new NodeChildVisitTracker(root, NodeChildVisitTracker.ChildVisitMarker.UNSET));
        while (!currentBranchNodes.isEmpty()) {
            NodeChildVisitTracker currentNode = currentBranchNodes.peek();
            switch (currentNode.childVisited) {
                case UNSET:
                    // no child of this node has been visited.
                    // visit left child first if it exists; if not, then right
                    // if not child exists, mark the node as a leaf node.
                    if (currentNode.node.left != null) {
                        currentBranchNodes.push(new NodeChildVisitTracker(currentNode.node.left, NodeChildVisitTracker.ChildVisitMarker.UNSET));
                        currentNode.childVisited = NodeChildVisitTracker.ChildVisitMarker.LEFT;
                    }
                    else if (currentNode.node.right != null) {
                        currentBranchNodes.push(new NodeChildVisitTracker(currentNode.node.right, NodeChildVisitTracker.ChildVisitMarker.UNSET));
                        currentNode.childVisited = NodeChildVisitTracker.ChildVisitMarker.RIGHT;
                    }
                    else {
                        currentNode.childVisited = NodeChildVisitTracker.ChildVisitMarker.LEAFNODE;
                    }
                    break;

                case LEFT:
                    // left child has already been visited;
                    // visit the right child, if it exists;
                    // if not, then remove the node from further consideration.
                    if (currentNode.node.right != null) {
                        currentBranchNodes.push(new NodeChildVisitTracker(currentNode.node.right, NodeChildVisitTracker.ChildVisitMarker.UNSET));
                        currentNode.childVisited = NodeChildVisitTracker.ChildVisitMarker.RIGHT;
                    }
                    else {
                        currentBranchNodes.pop();
                    }
                    break;

                case RIGHT:
                    // all nodes have been visited for this node;
                    // remove the node from further consideration.
                    currentBranchNodes.pop();
                    break;

                case LEAFNODE:
                    // find the branch sum, and remove the node from further consideration.
                    int sum = 0;
                    for (NodeChildVisitTracker nodeChildTracker : currentBranchNodes) {
                        sum = sum + nodeChildTracker.node.value;
                    }
                    solution.add(sum);
                    currentBranchNodes.pop();
                    break;
            }
        }

        return solution;
    }
    // Time Complexity:
    // n - the number of nodes in the tree.
    // O(n) - We traverse all the nodes in the tree atmost 3 times. This is a constant, so time complexity is O(n)
    //
    // Space Complexity:
    // O(n)
    // We maintain a stack of max size of n (in the worst case, if the tree i heavily skewed to left/right)
    // List of branch sums can be atmost O(logn)

    /**
     * Used to indicate which child of a node has been visited -
     * Left, Right or if the node is a leaf node.
     *
     * Default state is "unset".
     */
    static class NodeChildVisitTracker {
        BinaryTree node;

        enum ChildVisitMarker {
            UNSET,
            LEFT,
            RIGHT,
            LEAFNODE
        }
        ChildVisitMarker childVisited;

        NodeChildVisitTracker(BinaryTree node, ChildVisitMarker childVisited) {
            this.node = node;
            this.childVisited = childVisited;
        }
    }

    // This is the class of the input root. Do not edit it.
    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
