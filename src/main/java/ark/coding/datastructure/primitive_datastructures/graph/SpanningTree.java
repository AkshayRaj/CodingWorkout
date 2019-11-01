/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
 * A minimum spanning tree (MST) or minimum weight spanning tree for a weighted, connected and undirected graph
 * is a spanning tree with weight less than or equal to the weight of every other spanning tree.
 *
 * The weight of a spanning tree is the sum of weights given to each edge of the spanning tree.
 */
public class SpanningTree {

    public static void main(String[] args) {
        int[][] graph = new int[][] {
                {0,2,3},
                {2,0,2},
                {3,2,0}
        };

        SpanningTree spanningTree = new SpanningTree();

        int[][] mst = spanningTree.findMinimumSpanningTreeUsingKruskalsAlgorithm(graph);
        //int[][] mst = spanningTree.findMinimumSpanningTreeUsingPrimsAlgorithm(graph);

        int mstWeight = 0;
        for (int node = 0; node < mst.length; node++) {
            for (int adjacentNode = 0; adjacentNode < mst.length; adjacentNode++) {
                mstWeight = mstWeight + mst[node][adjacentNode];
            }
        }

        System.out.println("Weight of the minimum spanning tree(MST): " + mstWeight/2);
    }

    /**
     * Given a weighted, connected & undirected graph, find its Minimum Spanning Tree
     * using Prim's algorithm.
     *
     * 1. Start from <i>any</i> vertex.
     * 2. Select the least {@link WeightedEdge} from that node
     * 3. Select the other vertex from the edge selected in step 2
     * 4. GOTO step-2 and repeat, until we have a spanning tree.
     *
     * Textbook definition of Prim's algorithm -
     * - A group of edges that connects two set of vertices in a graph is called cut in graph theory.
     * - So, at every step of Prim’s algorithm, we find a cut (of two sets, one contains the vertices already included in MST
     * and other contains rest of the vertices).
     * - Pick the minimum weight edge from the cut and include this vertex to MST Set (the set that contains already included vertices).

     * Implementation considerations:
       1. Start with the empty spanning tree.
       2. Maintain a set mst[] to keep track to vertices included in minimum spanning tree.
       3. Assign a key value to all the vertices, (say key []) and initialize all the keys with +∞ (Infinity) except the first vertex.
         (We will start with this vertex, for which key will be 0).
       4. Key value in step 3 will be used in making decision that which next vertex and edge will be included in the mst[].
          We will pick the vertex which is not included in mst[] and has the minimum key.
          So at the beginning the first vertex will be picked first.
       5. Repeat the following steps until all vertices are processed
         a) Pick the vertex u which is not in mst[] and has minimum key.
         b) Add vertex u to mst[].
         c) Loop over all the adjacent vertices of u
            For adjacent vertex v, if v is not in mst[] and edge u-v weight is less than the key of vertex u,
            key[u] then update the key[u]= edge u-v weight.
         d) Return mst[].
     *
     * @return The minimum spanning tree of the graph, as identified by Prim's algorithm.
     *         The minimum spanning tree is represented as an <b>Adjacency Matrix</b>.
     */
    public int[][] findMinimumSpanningTreeUsingPrimsAlgorithm(int[][] graph) {
        int totalNodes = graph.length;
        int[][] minimumSpanningTree = new int[totalNodes][totalNodes];// adjacency matrix.

        Set<Integer> mstNodesSet = new HashSet<>();
        Map<Integer, Integer> nodeWeightMap = getInitializedNodeWeightMap(graph);

        int startingNode = 0;
        mstNodesSet.add(startingNode);
        nodeWeightMap.remove(startingNode);

        int currentNode = startingNode;
        do {
            // find min edge from current node;
            // the other end of the edge should not be in mstNodesSet (to keep MST acyclic)
            int adjacentNode = findMinWeightedAdjacentNode(currentNode, graph, mstNodesSet, nodeWeightMap);

            // add the edge for both - currentNode & adjacentNode
            minimumSpanningTree[currentNode][adjacentNode] = graph[currentNode][adjacentNode];
            minimumSpanningTree[adjacentNode][currentNode] = minimumSpanningTree[currentNode][adjacentNode];

            mstNodesSet.add(adjacentNode);
            nodeWeightMap.remove(adjacentNode);
            currentNode = adjacentNode;
        }
        while (!nodeWeightMap.isEmpty());

        return minimumSpanningTree;
    }

    /**
     * Gets the minAdjacentNode in O(n) time.
     * With minBinaryHeap, we can get it in O(log(n))
     *
     * @param currentNode the node from which we want to find a minimum weighted edge
     * @param graph represented as <b>Adjacency Matrix</b>
     * @param mstNodesSet contains nodes that will be part of the MST;
     *                    the set is expected to be incomplete.
     * @param nodeWeightMap Map, which assigns weight to a particular node.
     *                      The mapping is between the node & its corresponding weight.
     *                      The weight is calculated by finding the minimum adjacent node of the current node.
     * @return node adjacent to {@code currentNode}, that is not part of the {@code mstNodesSet},
     *         and has the minimum weighted edge from the {@code currentNode}
     */
    private static int findMinWeightedAdjacentNode(
            int currentNode,
            int[][] graph,
            Set<Integer> mstNodesSet,
            Map<Integer, Integer> nodeWeightMap) {
        int[] adjacentNodesOfCurrentNode = graph[currentNode];

        int minWeightedEdgeFromCurrentNode = Integer.MAX_VALUE;
        int minAdjacentNode = -1;
        for (int adjacentNode = 0; adjacentNode < graph.length; adjacentNode++) {
            if (!mstNodesSet.contains(adjacentNode)) {
                int adjacentNodeWeight = adjacentNodesOfCurrentNode[adjacentNode];
                if (adjacentNodeWeight < minWeightedEdgeFromCurrentNode) {
                    minWeightedEdgeFromCurrentNode = adjacentNodeWeight;
                    minAdjacentNode = adjacentNode;
                }
            }
        }
        nodeWeightMap.replace(minAdjacentNode, minAdjacentNode);

        //check state
        if (minAdjacentNode == -1) {
            throw new RuntimeException("Something went wrong when trying to find minAdjacentNode.");
        }
        return minAdjacentNode;
    }

    /**
     * Get a mapping between the nodes of a graph, and their respective weights.
     * The weights are initialized to {@link Integer#MAX_VALUE}
     *
     * @param graph
     * @return An initialized {@link Map} containing all the nodes of the graph as keys,
     *         and their respective weights as the values of the map.
     */
    private static Map<Integer, Integer> getInitializedNodeWeightMap(int[][] graph) {
        Map<Integer, Integer> nodeWeightMap = new HashMap<>();
        for (int node = 0; node < graph.length; node++) {
            nodeWeightMap.put(node, Integer.MAX_VALUE);
        }

        return nodeWeightMap;
    }

    /*************************************************************************************************
    ******************************************* KRUSKAL'S ********************************************
    **************************************************************************************************/

    /**
     * Given a weighted, connected & undirected graph, find its Minimum Spanning Tree
     * using Kruskal's algorithm.
     *
     * Kruskal's Algorithm:
     *
     * 1. Sort the graph edges with respect to their weights.
     * 2. Start adding edges to the MST from the edge with the smallest weight until the edge of the largest weight.
     *    Only add edges which doesn't form a cycle , edges which connect only disconnected sub-graphs.
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

        DisjointSet disjointSet = new DisjointSet(graph);

        // 2. Add edges from the sorted list (asc order) to the MST
        //    Do not add an edge, if its corresponding two nodes are already "connected".
        for (WeightedEdge edge : sortedEdges) {
            int node1 = Integer.parseInt(edge.getNode1());
            int node2 = Integer.parseInt(edge.getNode2());
            if (!areNodesConnectedDisjointSet(disjointSet, node1, node2)) {
                addWeightedEdgeToGraph(minimumSpanningTree, edge);
                disjointSet.mergeSet(node1, node2);
            }
            //^^^ merge operation of DisjointSet is O(n), whereas find is O(1)
            // Therefore, Kruskal's algorithm finds MST in O(n)
        }

        // check state - validation (of sorts) of this implementation
        checkDisjointSetStateForMST(disjointSet);

        return minimumSpanningTree;
    }

    private void checkDisjointSetStateForMST(DisjointSet disjointSet) {
        List<Integer> reps = Arrays.asList(disjointSet.getReps().toArray(new Integer[]{}));
        int firstRep = reps.get(0);
        for (int rep : reps) {
            if (rep != firstRep) {
                throw new RuntimeException("Reps are not same in DisjointSet for MST.");
            }
        }
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
     * <b>Note: This uses the {@link DisjointSet} data structure.</b>
     *
     *
     * @param disjointSet
     * @param node1 will act as the initial frontier during the graph traversal.
     * @param node2 the other node, to check for existence of a path.
     * @return true, if a path exists from node1 to node2;
     *         false, otherwise.
     */
    private static boolean areNodesConnectedDisjointSet(DisjointSet disjointSet, int node1, int node2) {
        return disjointSet.find(node1, node2);
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

        // this do-while loop traverses the graph in BFS-order from `node1`
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
            //^^^ this `for` loop has O(n) time complexity; is the number of nodes
        }
        while (!frontier.isEmpty());
        //^^^ the while + nested-for loop has O(n^2) time complexity

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
            for (int adjacentNode = 0; adjacentNode < graph.length; adjacentNode++) {
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
