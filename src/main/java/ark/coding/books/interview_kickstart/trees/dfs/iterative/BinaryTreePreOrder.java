/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.trees.dfs.iterative;

import ark.coding.books.interview_kickstart.trees.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 */
public class BinaryTreePreOrder {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> solution = new ArrayList<>();

        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode head = stack.pop();
            solution.add(head.val);

            if (head.right != null) stack.push(head.right);
            if (head.left != null) stack.push(head.left);
        }

        return solution;
    }
}
