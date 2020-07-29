/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.trees.dfs.top_down;

import ark.coding.books.interview_kickstart.trees.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeTargetSumPaths {
    private List<List<Integer>> paths = new ArrayList<>();
    private int targetSum;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        this.targetSum = sum;

        if (root != null) dfs(root, 0, new ArrayList<>());

        return this.paths;
    }

    private void dfs(TreeNode root, int partialSum, List<Integer> path) {
        int nodePartialSum = partialSum + root.val;
        path.add(root.val);
        // In this version of recursion, we check for leaf nodes, instead of "null"s.
        // We can get single-child nodes, which would throw off this kind of recursion if we checked for "null"s.
        if (root.left == null & root.right == null & nodePartialSum == this.targetSum)
            this.paths.add(new ArrayList<>(path));

        if (root.left != null) dfs(root.left, nodePartialSum, path);
        if (root.right != null) dfs(root.right, nodePartialSum, path);

        path.remove(path.size()-1);
    }
}
