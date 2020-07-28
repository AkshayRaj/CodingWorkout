/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.trees;

import ark.coding.tools.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> frontier = new LinkedList<>();
        if (root != null) {
            frontier.add(root);
        }

        List<List<Integer>> solution = new ArrayList<>();
        boolean leftRightTraversal = true; // true stands for left to right; false for reverse.
        while (!frontier.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>(frontier.size());
            for (TreeNode node : frontier) {
                currentLevel.add(node.val);
            }

            // reverse for odd levels
            if (!leftRightTraversal) {
                int leftIdx = 0;
                int rightIdx = currentLevel.size()-1;
                while (leftIdx < rightIdx) {
                    Utils.swapElements(currentLevel, leftIdx, rightIdx);
                    leftIdx++;
                    rightIdx--;
                }
            }
            solution.add(currentLevel);
            leftRightTraversal = !leftRightTraversal;

            Queue<TreeNode> nextFrontier = new LinkedList<>();
            while (!frontier.isEmpty()) {
                TreeNode head = frontier.poll();
                if (head.left != null) nextFrontier.add(head.left);
                if (head.right != null) nextFrontier.add(head.right);
            }
            frontier = nextFrontier;
        }

        return solution;
    }
}
