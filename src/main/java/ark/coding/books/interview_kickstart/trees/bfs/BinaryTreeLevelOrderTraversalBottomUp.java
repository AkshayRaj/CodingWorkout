/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.trees.bfs;

import ark.coding.books.interview_kickstart.trees.TreeNode;
import ark.coding.tools.Utils;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 */
public class BinaryTreeLevelOrderTraversalBottomUp {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> solution = new ArrayList<>();
        Deque<TreeNode> frontier = new LinkedList<>();

        if (root != null) frontier.add(root);

        while (!frontier.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            for (TreeNode node : frontier) {
                currentLevel.add(node.val);
            }
            solution.add(currentLevel);

            Deque<TreeNode> nextFrontier = new LinkedList<>();
            while (!frontier.isEmpty()) {
                TreeNode head = frontier.pollFirst();
                if (head.left != null) nextFrontier.add(head.left);
                if (head.right != null) nextFrontier.add(head.right);
            }
            frontier = nextFrontier;
        }

        // reverse the solution to make it bottomUp
        int leftIdx = 0;
        int rightIdx = solution.size()-1;
        while (leftIdx < rightIdx) {
            Utils.swapElements(solution, leftIdx, rightIdx);
            leftIdx++;
            rightIdx--;
        }

        return solution;
    }
}
