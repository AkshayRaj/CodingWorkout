/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class Graph {
    enum GraphConnection {
        DISCONNECTED,
        CONNECTED
    }

    /**
     * Given an undirected graph, represented by an adjacency matrix, identify if the graph is disconnected or not
     * @param graph to check
     * @return true, if the graph is disconnected or disjoint.
     *         false, otherwise
     */
    private static GraphConnection isGraphDisconnected(int[][] graph) {
        boolean isDisconnected = false;

        // if any node is not traversed, graph is disconnected
        //boolean[] verticesVisited = traverseGraphUsingAdjacencyMatrix(graph);
        boolean[] verticesVisited = traverseGraphUsingDFSAndAdjacencyList(graph);

        for (boolean visited : verticesVisited) {
            if (visited == false) {
                isDisconnected = true;
            }
        }
        return isDisconnected ? GraphConnection.DISCONNECTED
                              : GraphConnection.CONNECTED;
    }

    /**
     * Recursively traverse the graph, by visiting the child nodes of the current node.
     *
     * The depth traversal stops when either of the following two conditions is met -
     * 1. A leaf node is reached
     * 2. All the child nodes of a given node have been visited.
     *
     * When these "terminating" conditions are met, the algorithm pops the leaf node or
     * the "root of the sub-graph" that has been traversed, and goes back to the parent node
     * to check for more nodes.
     *
     * @param graph to traverse using Depth-First-Search;
     *              An <b>Adjacency List</b> representation of the graph.
     * @return vertices visited during the traversal.
     */
    private static boolean[] traverseGraphUsingDFSAndAdjacencyList(int[][] graph) {
        int totalNodes = graph.length;
        boolean[] verticesVisited = new boolean[totalNodes];

        Stack<Integer> hierarchy = new Stack<>();
        // start with '0' node, as the root.
        hierarchy.push(0);

        do {
            int currentNode = hierarchy.peek();
            verticesVisited[currentNode] = true;

            // if currentNode has any children, then traverse them until all are visited
            // DFS essentially traverses the "sub-graph" under a given node.
            // - ark
            if (graph[currentNode].length > 0) {
                // traverse childNode, that has not been visited yet.
                boolean allChildNodesVisited = true;
                childIterator: for (int childNode : graph[currentNode]) {
                    if (verticesVisited[childNode] == false) {
                        currentNode = childNode;
                        hierarchy.push(currentNode);
                        allChildNodesVisited = false;
                        break childIterator;
                    }
                }
                if (allChildNodesVisited) {
                    hierarchy.pop();

                    // A non-empty hierarchy at this point indicates that there are more nodes to be visited.
                    // If the "sub-graph" under the current node has been traversed completely,
                    // then the hierarchy will be empty.
                    if (!hierarchy.empty()) {
                        currentNode = hierarchy.peek();
                    }
                }
            }
            // leaf node
            else {
                hierarchy.pop();

                // A non-empty hierarchy at this point indicates that there are more nodes to be visited.
                if (!hierarchy.empty()) {
                    currentNode = hierarchy.peek();
                }
            }
        }
        while (!hierarchy.empty());

        return verticesVisited;
    }

    /**
     * Traverse the given graph, and return a list of traversed nodes.
     * We use and implement the "Frontier" approach described in CLRS to traverse the graph.
     * The "Frontier" is a set of nodes, at any given time during the graph traversal process,
     * that divide the nodes which are already <b>visited</b> from the ones which have not been visited.
     *
     * @param graph the graph to traverse, represented as an <b>Adjacency List</b>>
     * @return array of vertices marked as visited or not.
     */
    private static boolean[] traverseGraphUsingAdjacencyList(int[][] graph) {
        int totalNodes = graph.length;
        boolean[] verticesVisited = new boolean[totalNodes];

        Queue<Integer> frontier = new LinkedBlockingQueue<>();
        frontier.add(0);

        do {
            int currentNode = frontier.remove();
            verticesVisited[currentNode] = true;

            for (int adjacentNode : graph[currentNode]) {
                if (verticesVisited[adjacentNode] == false) {
                    frontier.add(adjacentNode);
                }
            }
        }
        while(!frontier.isEmpty());

        return verticesVisited;
    }

    /**
     * Traverse the given graph, and return a list of traversed nodes.
     * We use and implement the "Frontier" approach described in CLRS to traverse the graph.
     * The "Frontier" is a set of nodes, at any given time during the graph traversal process,
     * that divide the nodes which are already <b>visited</b> from the ones which have not been visited.
     *
     * @param graph the graph to traverse, represented as an <b>Adjacency Matrix</b>>
     * @return array of vertices marked as visited or not.
     */
    private static boolean[] traverseGraphUsingAdjacencyMatrix(int[][] graph) {
        int totalNodes = graph.length;
        boolean[] verticesVisited = new boolean[totalNodes];

        Queue<Integer> frontier = new LinkedBlockingQueue<>();

        // The initial frontier is a single node, i.e. the starting node.
        // For simplicity, we start with node '0'.
        frontier.add(0);
        do {
            int currentNode = frontier.remove();
            verticesVisited[currentNode] = true;

            // visit all adjacent nodes of the current node.
            for (int adjacentNode = 0; adjacentNode < totalNodes; adjacentNode++) {
                if (graph[currentNode][adjacentNode] == 1
                    && verticesVisited[adjacentNode] == false) {
                    // add adjacent nodes for the new frontier
                    // skip nodes that are visited.
                    frontier.add(adjacentNode);
                }
            }
        }
        // iterate until we have traversed all nodes reachable from the starting node (i.e. root)
        // An empty frontier indicates that no more children nodes remain to be visited.
        while (!frontier.isEmpty());

        return verticesVisited;
    }

    public static void main(String[] args) {
        int[][] graph1Matrix = new int[][] {
                {0, 1, 1, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] graph2Matrix = new int[][] {
                {0, 1, 1, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 1},
                {0, 0, 1, 0}
        };

        //System.out.println("Graph-1 (adjacency matrix) is: " + isGraphDisconnected(graph1Matrix));
        //System.out.println("Graph-2 (adjacency matrix) is: " + isGraphDisconnected(graph2Matrix));

        int[][] graph1List = new int[][] {
                {1, 2},
                {0},
                {0},
                {}
        };

        int[][] graph2List = new int[][] {
                {1, 2},
                {0},
                {0, 3},
                {2}
        };

        System.out.println("Graph-1 (adjacency list) is: " + isGraphDisconnected(graph1List));
        System.out.println("Graph-2 (adjacency list) is: " + isGraphDisconnected(graph2List));
    }
}
