/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

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
public class SpanningTree {

    /**
     * Given a weighted, connected & undirected graph, find its Minimum Spanning Tree
     * using Kruskal's algorithm.
     *
     * Kruskal's Algorithm:
     *
     * 1. Sort the graph edges with respect to their weights.
     * 2. Start adding edges to the MST from the edge with the smallest weight until the edge of the largest weight.
     *    Only add edges which doesn't form a cycle , edges which connect only disconnected components.
     *
     * @param graph An <b>Adjacency Matrix</b> representation of the graph.
     *              The weight of a given edge is the value in the cell corresponding to the two nodes.
     * Graph representation:-
     *  A non-zero weight indicates that an edge exists between the two nodes;
     * The non-zero value in `graph[node][adjacentNode]` cell, represents the weight of the edge.
     * A zero in the `graph[node][adjacentNode]` cell indicates that the edge does not exist.
     *
     * For eg,
     * An edge from '0' -> '1' can be found in either {@code graph[0][1]} cell or in {@code graph[1][0]}
     * <i>Note: As the graph is undirected, weights can be found in either <b>0,1</b> or <b>1,0</b> cell.</i>
     *
     * @return The minimum spanning tree of the graph, as identified by Kruskal's algorithm.
     *         The minimum spanning tree is represented as an <b>Adjacency Matrix</b>.
     */
    public int[][] findMinimumSpanningTreeUsingKruskalsAlgorithm(int[][] graph) {
        int totalNodes = graph.length;
        int[][] minimumSpanningTree = new int[totalNodes][totalNodes];// adjacency matrix.

        // 1. Sort the edges w.r.t their weights
        List<WeightedEdge> sortedEdges = getWeightedEdgesFromGraph(graph);
        Collections.sort(sortedEdges);

        // 2. Add edges from the sorted list (asc order) to the MST
        //    Do not add an edge, if its two nodes are already connected.
        for (WeightedEdge edge : sortedEdges) {
            int node1 = Integer.parseInt(edge.getNode1());
            int node2 = Integer.parseInt(edge.getNode2());
            if (!areNodesConnectedAdjMatrix(minimumSpanningTree, node1, node2)) {
                addWeightedEdgeToGraph(minimumSpanningTree, edge);
            }
        }

        return minimumSpanningTree;
    }

    /**
     * Given a graph, find a spanning tree.
     *
     * @param graph an unweighted, undirected, connected graph, represented as an <b>Adjacency List</b>.
     * @return any spanning tree of the given graph.
     */
    public int[][] findRandomSpanningTree(int[][] graph) {
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

    /**
     * Helper method to check whether a given pair of nodes are connected in the graph.
     * The nodes are said to be "connected" if there exists a path between the two nodes.
     *
     * @param graph represented by an <b>Adjacency Matrix</b>
     * @param node1 will act as the initial frontier during the graph traversal.
     * @param node2 the other node, to check for existence of a path.
     * @return true, if a path exists from node1 to node2;
     *         false, otherwise.
     */
    private static boolean areNodesConnectedAdjMatrix(int[][] graph, int node1, int node2) {
        int totalNodes = graph.length;

        // traverse using BFS
        // initialize the frontier to node1
        Queue<Integer> frontier = new LinkedTransferQueue<>();
        frontier.add(node1);

        boolean[] nodesVisited = new boolean[totalNodes];

        do {
            int node = frontier.poll();
            nodesVisited[node] = true;

            // if we visit node2 during the traversal, we are done checking.
            if (node == node2) {
                return true;
            }

            for (int adjacentNode = 0; adjacentNode < graph.length; adjacentNode++) {
                if (graph[node][adjacentNode] > 0) {
                    if (!nodesVisited[adjacentNode]) {
                        frontier.add(adjacentNode);
                    }
                }
            }
        }
        while (!frontier.isEmpty());

        return false;
    }

    /**
     * Helper method to get a list of {@link WeightedEdge}s from an <b>Adjacency Matrix</b> representation
     * of a weighted, undirected graph.
     *
     * @param graph represented by an <b>Adjacency Matrix</b>
     * @return list of {@link WeightedEdge}s of the weighted graph.
     */
    private static List<WeightedEdge> getWeightedEdgesFromGraph(int[][] graph) {
        Set<WeightedEdge> edges = new HashSet<>();
        for (int node = 0; node < graph.length; node++) {
            for (int adjacentNode = 0; node < graph.length; adjacentNode++) {
                if (graph[node][adjacentNode] > 0) {
                    edges.add(WeightedEdge.builder()
                            .node1(String.valueOf(node))
                            .node2(String.valueOf(adjacentNode))
                            .weight(graph[node][adjacentNode])
                            .build());
                }
            }
        }

        return new ArrayList<>(edges);
    }

    /**
     * Helper method to add a weighted edge to a graph.
     *
     * @param graph to which the weighted edge is to be added.
     * @param edge the edge to add in the graph. A weighted wedge.
     *             A non-zero value in `graph[node1][node2]` indicates an edge exists between node-1 & node-2
     *             The non-zero value in `graph[node1][node2]` indicates the weight of the graph.
     */
    private static void addWeightedEdgeToGraph(int[][] graph, WeightedEdge edge) {
        int node1 = Integer.parseInt(edge.getNode1());
        int node2 = Integer.parseInt(edge.getNode2());
        int weight = edge.getWeight();

        graph[node1][node2] = weight;
        graph[node2][node1] = weight;
    }

    /**
     * Helper method to add an edge to the graph, when provided with a pair of nodes.
     * @param graph the graph to which to add the node.
     *              Represented by an <b>Adjancency List</b>
     * @param node node-1
     * @param adjacentNode node-2
     * @return The graph, with an edge to it.
     */
    private static int[][] addEdgeForSpanningTree(int[][] graph, Integer node, Integer adjacentNode) {
        int[] nodeAdjacencyList = Arrays.copyOf(graph[node],
                graph[node].length + 1);
        int[] adjacentNodeAdjacencyList = Arrays.copyOf(graph[adjacentNode],
                graph[adjacentNode].length + 1);

        nodeAdjacencyList[nodeAdjacencyList.length] = adjacentNode;
        adjacentNodeAdjacencyList[adjacentNodeAdjacencyList.length] = node;

        graph[node] = nodeAdjacencyList;
        graph[adjacentNode] = adjacentNodeAdjacencyList;

        return graph;
    }

    /**
     * Helper method to check whether a given graph is a Spanning Tree or not.
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
