/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.trees.bfs;

import ark.coding.books.interview_kickstart.trees.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 */
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> solution = new ArrayList<>();
        Deque<TreeNode> frontier = new LinkedList<>();

        if (root != null) frontier.add(root);

        while (!frontier.isEmpty()) {
            solution.add(frontier.peekLast().val);

            Deque<TreeNode> nextFrontier = new LinkedList<>();
            while (!frontier.isEmpty()) {
                TreeNode head = frontier.pollFirst();
                if (head.left != null) nextFrontier.add(head.left);
                if (head.right != null) nextFrontier.add(head.right);
            }
            frontier = nextFrontier;
        }

        return solution;
    }
}
