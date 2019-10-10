/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * What is Minimum Spanning Tree?
 * Given a connected and undirected graph, a spanning tree of that graph is a sub-graph that is a tree and
 * connects all the vertices together.
 *
 * A single graph can have many different spanning trees.
 *
 *  A minimum spanning tree (MST) or minimum weight spanning tree for a weighted, connected and undirected graph
 * is a spanning tree with weight less than or equal to the weight of every other spanning tree.
 *
 * The weight of a spanning tree is the sum of weights given to each edge of the spanning tree.
 */
public class MinimumSpanningTree {

    

    /**
     * Given a graph, find a spanning tree.
     * @param graph an unweighted, undirected, connected graph, represented as an <b>Adjacency List</b>.
     * @return any spanning tree of the given graph.
     */
    public static int[][] findSpanningTree(int[][] graph) {
        int[][] spanningTree = new int[graph.length][];

        if (isSpanningTree(graph)) {
            return graph;
        }
        if (!Graph.isConnected(graph)) {
            throw new RuntimeException("Graph is not connected. We cannot find spanning tree of an unconnected graph.");
        }

        // traverse the graph, by selecting an arbitrary starting node
        // add nodes as we find them and add corresponding edges
        // We will not add edges from new nodes to nodes that have already been visited.
        // i.e. nodes that will create a cycle, will not be added.

        Queue<Integer> frontier = new LinkedBlockingQueue<>();
        boolean[] nodesVisited = new boolean[graph.length];
        frontier.add(0);
        do {
            int node = frontier.poll();
            nodesVisited[node] = true;
            for (Integer adjacentNode : graph[node]) {
                if (!nodesVisited[adjacentNode]) {
                    frontier.add(adjacentNode);

                    // add the edge from node -> adjacentNode
                    spanningTree = addEdgeForSpanningTree(spanningTree, node, adjacentNode);
                }
                else {
                    // do not add the edge from node -> adjacentNode in the spanning tree
                }
            }
        }
        while (!frontier.isEmpty());

        // check state
        if (!isSpanningTree(spanningTree)) {
            throw new RuntimeException("Sub-graph is not a spanning tree of the given graph.");
        }

        return spanningTree;
    }

    private static int[][] addEdgeForSpanningTree(int[][] spanningTree, int node, Integer adjacentNode) {
        int[] nodeAdjacencyList = Arrays.copyOf(spanningTree[node],
                spanningTree[node].length + 1);
        int[] adjacentNodeAdjacencyList = Arrays.copyOf(spanningTree[adjacentNode],
                spanningTree[adjacentNode].length + 1);

        nodeAdjacencyList[nodeAdjacencyList.length] = adjacentNode;
        adjacentNodeAdjacencyList[adjacentNodeAdjacencyList.length] = node;

        spanningTree[node] = nodeAdjacencyList;
        spanningTree[adjacentNode] = adjacentNodeAdjacencyList;

        return spanningTree;
    }


    /**
     * Checks whether a given graph is a Spanning Tree or not.
     * A graph is spanning, if it
     * - is connected
     * - has n-1 edges (n is the number of nodes)
     *
     * @param graph represented as an <b>Adjacency List</b>
     * @return true, if the graph is a Spanning Tree;
     *         false, otherwise.
     */
    private static boolean isSpanningTree(int[][] graph) {
        boolean isGraphConnected = Graph.isConnected(graph);

        int noOfNodes = graph.length;
        int noOfEdges = Graph.countGraphEdges(graph);

        return isGraphConnected && (noOfEdges == noOfNodes - 1);
    }
}
