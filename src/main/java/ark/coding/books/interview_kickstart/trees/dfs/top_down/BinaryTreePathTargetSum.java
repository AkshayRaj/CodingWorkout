/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.trees.dfs.top_down;

import ark.coding.books.interview_kickstart.trees.TreeNode;

/**
 * https://leetcode.com/problems/path-sum/
 */
public class BinaryTreePathTargetSum {

    public static void main(String[] args) {
        System.out.println(hasPathSum(new TreeNode(1, null, null), 0));
    }

    private static int targetSum;
    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        targetSum = sum;
        return dfs(root, 0);
    }

    private static boolean dfs(TreeNode root, int partialSum) {
        int nodePartialSum = partialSum + root.val;
        // BACKTRACK case-1
        // For decision problems, finding a single path is enough.
        // So we backtrack, if a solution has already been found.
        /* We could use a global variable to check if a path has been found */

        // BACKTRACK case-2
        // If newPartialSum is greater than target, then solution does not exist in the "current sub-tree".
        // This applies only if we know that the keys will be non-negative.
        /*
        if (newSum > targetSum) {
            return false;
        }
        */

        boolean hasPath = false;
        // For a path, the last node should be a leaf node.
        // Therefore in this version of recursion, we will check for leaf node, and not for "null" nodes.
        // Because a node can have a single child, and checking for null would result in false positives.
        if (root.left == null & root.right == null & nodePartialSum == targetSum) hasPath = true;

        if (root.left != null && !hasPath) hasPath = dfs(root.left, nodePartialSum);
        if (root.right != null && !hasPath) hasPath = dfs(root.right, nodePartialSum);
        return hasPath;
    }
}
