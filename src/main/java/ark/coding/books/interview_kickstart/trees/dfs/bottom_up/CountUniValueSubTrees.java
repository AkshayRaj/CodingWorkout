/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.trees.dfs.bottom_up;

import ark.coding.books.interview_kickstart.trees.TreeNode;

public class CountUniValueSubTrees {
    Integer univalSubTrees = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;

        bottomUpDfs(root);
        return this.univalSubTrees;
    }

    private boolean bottomUpDfs(TreeNode root) {
        // A leaf node is uni-val.
        if (root.left == null & root.right == null) {
            this.univalSubTrees++;
            return true;
        }

        // A subtree is uni-val, if all nodes in subtree have the same value
        // An internal node can -
        // - Check value of immediate left & right child
        // - if left & right Subtrees themselves are uni-val.
        //   Thus we can use the associativity of the uni-val property.
        boolean isLeftTreeUnival = true;
        if (root.left != null) isLeftTreeUnival = bottomUpDfs(root.left) & root.val == root.left.val;
        boolean isRightTreeUnival = true;
        if (root.right != null) isRightTreeUnival = bottomUpDfs(root.right) & root.val == root.right.val;

        if (isLeftTreeUnival & isRightTreeUnival) this.univalSubTrees++;
        return isLeftTreeUnival & isRightTreeUnival;
    }
}
