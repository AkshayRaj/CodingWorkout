package ark.coding.books.interview_kickstart.graphs;

import java.util.*;

/**
 * To understand the problem definition and domain, we need to understand the following concepts -
 * 1. Strongly Connected Components
 *    https://youtu.be/ZeDNSeilf-Y
 * 2. Articulation Points
 *    [Abdul Bari] https://youtu.be/jFZsDDB0-vo
 *    [Tech Dose] https://youtu.be/64KK9K4RpKE (At times the presenter gets confusing, but if the listener is alert, he can filter out the noise)
 * 3. Bridges (which is what {@link #findCriticalConnections(int, int, List)} is trying to find)
 *    https://youtu.be/Rhxs4k6DyMM
 *
 * CodeForces: Articulation Points and Bridges https://codeforces.com/blog/entry/71146 [FANTASTIC ARTICLE]
 // 1. DFS the graph
 // 2. Track when a node was "discovered"
 // 3. Visit next/child node
 //    - Track parent of each node
 //    - if next node is already visited, and has discovery time less than "lowest" time, then update lowest time
 //    - return the result (of lowest time) to parent after all child/next nodes have been visited
 // 4. CONDITION FOR -
 //      a) Strongly Connected Components
 //          - All nodes with the same earliestAncestor time belong to same StronglyConnectedComponent
 //      b) Articulation Points
 //        ### DFS Tree (of Graph Traversal)
 //          - HEAD NODE [parent is null]: more than one child.
 //            ** We cannot rely on this comparison - (discoverTime[parent] <= earliestAncestor[child]) - for head node.
 //               Eg, a two node graph will have discoverTime[head] < ancestorTime[child], but the head in this case is not an articulation point.
 //               Eg, Leaf nodes are not articulation points, and the head, if it has only one child would be a leaf node.
 //               Eg, if there are no articulation points, then discoverTime[head] == earliestAncestor[child]
 //          - NON-HEAD NODE:  (discoverTime[currentNode] <= earliestAncestor[childNode]) ~> currentNode is an articulationPoint
 //      c) Bridges
 //          - (discoveryTime[currentNode] < earliestAncestor[child])
 //
 // ==============================
 // The idea for the implementation is exactly the same as for articulation points except for one thing:-
 // To say that the edge 𝑈𝑉 is a bridge, the condition to satisfy is: discovery_time[U] < low[V] instead of discovery_time[U] <= low[V].
 // Notice that the only change was comparing strictly lesser instead of lesser of equal.
 */
public class CriticalConnections {
    static Set<Integer>[] serverGraph;

    // Required for Tarjan's algorithm
    static int[] discoverTime;
    static int[] earliestAncestor;
    static Integer[] parents;
    static boolean[] visited;
    static int time = 0;

    static List<List<Integer>> solution = new ArrayList<>();

    /**
     * Given:
     * Edge List (connections between servers)
     *
     *
     * @param noOfServers
     * @param noOfConnections
     * @param connections
     * @return
     */
    public static List<List<Integer>> findCriticalConnections(int noOfServers, int noOfConnections, List<List<Integer>> connections) {
        serverGraph = buildAdjacencyListForServers(noOfServers, connections);

        discoverTime = new int[noOfServers];
        earliestAncestor = new int[noOfServers];
        parents = new Integer[noOfServers];
        visited = new boolean[noOfServers];

        // the `for` loop is required in case the given graph is a disconnected one.
        checkBridgesInDisconnectedGraph: for (int node = 0; node < serverGraph.length; node++) {
            if (!visited[node]) { parents[node] = null; dfs(node); }
        }

        if (solution.size() == 0) solution.add(Arrays.asList(-1, -1));

        return solution;
    }

    private static void dfs(int node) {
        time++;
        discoverTime[node] = time;
        earliestAncestor[node] = time;
        visited[node] = true;

        for (Integer adjacentNode : serverGraph[node]) {
            if (!visited[adjacentNode]) {
                parents[adjacentNode] = node;
                dfs(adjacentNode);
                earliestAncestor[node] = Math.min(earliestAncestor[node], earliestAncestor[adjacentNode]);
                if (discoverTime[node] < earliestAncestor[adjacentNode]) {
                    List<Integer> bridge = new ArrayList<>();
                    bridge.add(node);
                    bridge.add(adjacentNode);
                    solution.add(bridge);
                }
            }
            // visited & not the parent
            else if (!adjacentNode.equals(parents[node])) {
                earliestAncestor[node] = Math.min(earliestAncestor[node], discoverTime[adjacentNode]);
            }
        }
    }

    private static Set<Integer>[] buildAdjacencyListForServers(int noOfServers, List<List<Integer>> connections) {
        Set<Integer>[] graph = new Set[noOfServers];
        for (int idx = 0; idx < graph.length; idx++) {
            graph[idx] = new HashSet<>();
        }

        for (List<Integer> edge : connections) {
            graph[edge.get(0)].add(edge.get(1));
            graph[edge.get(1)].add(edge.get(0));
        }

        return graph;
    }
}































