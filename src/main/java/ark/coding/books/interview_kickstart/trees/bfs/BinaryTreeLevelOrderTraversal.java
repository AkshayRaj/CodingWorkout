/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.trees.bfs;

import ark.coding.books.interview_kickstart.trees.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> frontier = new LinkedList<>();
        if (root != null)
        frontier.add(root);

        List<List<Integer>> solution = new ArrayList<>();
        while (!frontier.isEmpty()) {
            List<Integer> currentLevelKeys = new ArrayList<>();
            for (TreeNode node : frontier) {
                if (node != null)
                currentLevelKeys.add(node.val);
            }
            solution.add(currentLevelKeys);

            Queue<TreeNode> nextFrontier = new LinkedList<>();
            while (!frontier.isEmpty()) {
                TreeNode head = frontier.poll();
                if (head.left != null) {
                    nextFrontier.add(head.left);
                }
                if (head.right != null) {
                    nextFrontier.add(head.right);
                }
            }
            frontier = nextFrontier;
        }

        return solution;
    }
}
