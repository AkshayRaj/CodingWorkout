/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

/**
 * Given a target integer, find which of the nodes in a BST tree has the "closest" node.
 * A {@link BST} node has left, right child, and a value assigned to it.
 *
 */
public class ClosestValueInBST {

    /**
     * Approach:
     * 1. Traversing a tree, is analogous to iterating over an array.
     *    For traversing a tree, we keep track of the next node to consider.
     * 2. Track the closest node identified so far, in a tmp variable.
     *    Whenever we find a new node, that is closer, we update the tmp variable.
     *
     * @param tree
     * @param target
     * @return
     */
    public static int findClosestValueInBst(BST tree, int target) {
        BST candidate = tree; // tmp variable, to track the minimum distance we have identified so far.
        BST newRoot = tree; // reference to the next node to traverse.

        while (newRoot.left != null || newRoot.right != null) {
            if (target == newRoot.value) {
                return newRoot.value;
            }
            else if (target < newRoot.value) {
                if (newRoot.left == null) {
                    break; // target is less than newRoot, but no left child exists. We are done traversing the tree.
                }
                newRoot = newRoot.left;
            }
            else if (target > newRoot.value) {
                if (newRoot.right == null) {
                    break; // target is more than newRoot, but no right child exists. We are done traversing the tree.
                }
                newRoot = newRoot.right;
            }
            candidate = Math.abs(newRoot.value - target) <= Math.abs(candidate.value - target)
                    ? newRoot
                    : candidate;
        }

        return candidate.value;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }
}
