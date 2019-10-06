/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

import java.util.Queue;
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
        boolean[] verticesVisited = traverseGraph(graph);

        for (boolean visited : verticesVisited) {
            if (visited == false) {
                isDisconnected = true;
            }
        }
        return isDisconnected ? GraphConnection.DISCONNECTED
                              : GraphConnection.CONNECTED;
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
    private static boolean[] traverseGraph(int[][] graph) {
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
        int[][] graph1 = new int[][] {
                {0, 1, 1, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] graph2 = new int[][] {
                {0, 1, 1, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 1},
                {0, 0, 1, 0}
        };

        System.out.println("Graph-1 is: " + isGraphDisconnected(graph1));
        System.out.println("Graph-2 is: " + isGraphDisconnected(graph2));
    }
}
