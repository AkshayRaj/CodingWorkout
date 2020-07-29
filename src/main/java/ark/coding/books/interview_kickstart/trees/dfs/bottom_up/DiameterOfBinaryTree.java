/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.trees.dfs.bottom_up;

import ark.coding.books.interview_kickstart.trees.TreeNode;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 */
public class DiameterOfBinaryTree {
    Integer maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        dfs(root);
        return this.maxDiameter;
    }

    private int dfs(TreeNode root) {
        if (root.left == null & root.right == null) return 0;

        int leftSubTreeHeight = 0;
        if (root.left != null) leftSubTreeHeight = dfs(root.left) + 1;
        int rightSubTreeHeight = 0;
        if (root.right != null) rightSubTreeHeight = dfs(root.right) + 1;

        int diameterAtCurrentNode = leftSubTreeHeight + rightSubTreeHeight;
        if (diameterAtCurrentNode > this.maxDiameter) {
            this.maxDiameter = diameterAtCurrentNode;
        }

        return Math.max(leftSubTreeHeight, rightSubTreeHeight);
    }
}
