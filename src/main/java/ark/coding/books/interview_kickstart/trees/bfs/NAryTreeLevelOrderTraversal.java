/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.trees.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 */
public class NAryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> frontier = new LinkedList<>();
        if (root != null) {
            frontier.add(root);
        }

        List<List<Integer>> solution = new ArrayList<>();
        while (!frontier.isEmpty()) {

            List<Integer> currentLevelKeys = new ArrayList<>();
            for (Node node : frontier) {
                if (node != null) {
                    currentLevelKeys.add(node.val);
                }
            }
            solution.add(currentLevelKeys);

            Queue<Node> nextFrontier = new LinkedList<>();
            while (!frontier.isEmpty()) {
                Node head = frontier.poll();
                nextFrontier.addAll(head.children);
            }
            frontier = nextFrontier;
        }

        return solution;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

}
