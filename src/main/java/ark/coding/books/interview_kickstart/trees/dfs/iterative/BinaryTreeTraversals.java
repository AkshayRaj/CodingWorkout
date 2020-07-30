/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.trees.dfs.iterative;

import ark.coding.books.interview_kickstart.trees.TreeNode;
import ark.coding.tools.Utils;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class BinaryTreeTraversals {

    /**
     * https://leetcode.com/problems/binary-tree-inorder-traversal/
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> solution = new ArrayList<>();
        if (root == null) return solution;

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode subtreeRoot = root;
        while (subtreeRoot != null || !stack.isEmpty()) {
            while (subtreeRoot != null) {
                stack.push(subtreeRoot);
                subtreeRoot = subtreeRoot.left;
            }

            subtreeRoot = stack.pop();

            // For inorder, add value AFTER looking at left child.
            solution.add(subtreeRoot.val);

            subtreeRoot = subtreeRoot.right;
        }

        return solution;
    }

    /**
     * https://leetcode.com/problems/binary-tree-preorder-traversal/
     * root -> left -> right
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> solution = new ArrayList<>();
        if (root == null) return solution;

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode subtreeRoot = root;
        while (subtreeRoot != null || !stack.isEmpty()) {
            while (subtreeRoot != null) {
                // For preorder add value BEFORE looking at left child.
                solution.add(subtreeRoot.val);

                stack.push(subtreeRoot);
                subtreeRoot = subtreeRoot.left;
            }

            subtreeRoot = stack.pop();
            subtreeRoot = subtreeRoot.right;
        }

        return solution;
    }

    /**
     * https://leetcode.com/problems/binary-tree-postorder-traversal/
     *
     * A post order sequence is a a preOrder sequence with right child in reverse.
     * i.e, root -> right -> left in reverse is left -> right -> root.
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> solution = new ArrayList<>();
        if (root == null) return solution;

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode subtreeRoot = root;
        while (subtreeRoot != null || !stack.isEmpty()) {
            while (subtreeRoot != null) {
                // Like the preorder traversal, add the parent before looking at right child
                solution.add(subtreeRoot.val);

                stack.push(subtreeRoot);
                subtreeRoot = subtreeRoot.right;
            }

            subtreeRoot = stack.pop();
            subtreeRoot = subtreeRoot.left;
        }

        Utils.reverseList(solution);
        return solution;
    }
}
