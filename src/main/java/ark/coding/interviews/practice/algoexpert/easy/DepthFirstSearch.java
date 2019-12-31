/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A node can have many children, defined in a list.
 * The leftmost children are near the start of this list, while the rightmost child at the end of the list.
 * A node also has a "name" property.
 */
public class DepthFirstSearch {
    // Do not edit the class below except
    // for the depthFirstSearch method.
    // Feel free to add new properties
    // and methods to the class.
    static class Node {
        String name;
        List<Node> children = new ArrayList<Node>();

        public Node(String name) {
            this.name = name;
        }

        /**
         * Official Approach: Recursive
         *
         * @param array
         * @return
         */
        public List<String> depthFirstSearch(List<String> array) {
            array.add(this.name);
            for (int index = 0; index < this.children.size(); index++) {
                this.children.get(index).depthFirstSearch(array);
            }
            return array;
        }


        /**
         * Question: Fill this function. The {@link Node} class was provided by the question.
         * Fill in the array with names of the nodes traversed in DFS manner.
         *
         * @param array fill this array
         * @return
         */
        public List<String> depthFirstSearch_MyAnswer(List<String> array) {
            // Write your code here.]
            Stack<NodeChildTracker> nodesAtCurrentDepth = new Stack<>();
            nodesAtCurrentDepth.push(new NodeChildTracker(this));

            NodeChildTracker currentNode;
            while (!nodesAtCurrentDepth.isEmpty()) {
                currentNode = nodesAtCurrentDepth.peek();

                // check if this node is seen for the first time
                // are we seeing this after traversing its parent, or after coming back from its child.
                if (!currentNode.traversed) {
                    array.add(currentNode.node.name);
                    currentNode.traversed = true;
                }

                int noOfChildren = currentNode.node.children.size();
                int nextChildToTraverse = currentNode.nextChildIndex + 1;
                if (nextChildToTraverse < noOfChildren) {
                    Node nextNode = currentNode.node.children.get(nextChildToTraverse);
                    nodesAtCurrentDepth.push(new NodeChildTracker(nextNode));
                    // update child tracker for the current node.
                    currentNode.nextChildIndex = nextChildToTraverse;
                }
                else {
                    nodesAtCurrentDepth.pop();
                }
            }

            return array;
        }

        public Node addChild(String name) {
            Node child = new Node(name);
            children.add(child);
            return this;
        }
    }

    /**
     * I created this class for my solution
     */
    static class NodeChildTracker {
        final Node node;
        int nextChildIndex = -1; // unset
        boolean traversed = false;

        NodeChildTracker(Node node) {
            this.node = node;
        }
    }
}
