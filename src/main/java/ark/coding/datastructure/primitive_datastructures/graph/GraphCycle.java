package ark.coding.datastructure.primitive_datastructures.graph;
//a=0
//b=1
//c=2
//d=3
//e=4
//f=5

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphCycle {
    HashMap<Integer, List<Integer>> mGraph = new HashMap<Integer, List<Integer>>();
    List<Integer> node0List = new ArrayList<Integer>();
    List<Integer> node1List = new ArrayList<Integer>();
    List<Integer> node2List = new ArrayList<Integer>();
    List<Integer> node3List = new ArrayList<Integer>();
    List<Integer> node4List = new ArrayList<Integer>();
    List<Integer> node5List = new ArrayList<Integer>();

    public GraphCycle() {
        node0List.add(1);
        node1List.add(2);
        node2List.add(3);
        node2List.add(4);
        node3List.add(5);
        node5List.add(0);
        mGraph.put(0,node0List);
        mGraph.put(1,node1List);
        mGraph.put(2,node2List);
        mGraph.put(3,node3List);
        mGraph.put(4,node4List);
        mGraph.put(5,node5List);
    }

    public boolean hasCycle(){
        return hasCycle(mGraph);
    }

    /**
     * checks if the GraphCycle has a cycle ; O(N) + O(V)
     * @param graph - directed, graph
     * @return - true, if the graph contains a cycle
     */
    private boolean hasCycle(HashMap<Integer, List<Integer>> graph){
        //IMP: for loop required to check cycles in disconnected graphs.
        //Without 'for' loop, cycles only in connected graphs can be detected.
        for(int i = 0 ; i < graph.size(); i++) {
            List<Integer> visitedNodes = new ArrayList<Integer>();
            visitedNodes.add(i);
            if (checkCycleInSubGraph(graph, graph.get(i), visitedNodes)) {
                return true;
            }
        }
        //this 'for' loop is linear to O(N); N -> no of vertices
        return false;
    }

    /**
     * internal method which checks sub-graphs of current graph recursively to determine if cycle exists in graph
     * @param graph
     * @param outboundNodes
     * @param visitedNodesTillPreviousNode
     * @return
     */
    private boolean checkCycleInSubGraph(HashMap<Integer, List<Integer>> graph, List<Integer> outboundNodes, List<Integer> visitedNodesTillPreviousNode){
        List<Integer> visitedNodes = new ArrayList<Integer>(visitedNodesTillPreviousNode);
        //this is a leaf node, then no cycle exists at this node level
        if(outboundNodes.isEmpty()){
            return false;
        }
        //check cycle for each and every outbound node of the currentNode
        for(Integer node : outboundNodes){
            if(visitedNodes.contains(node)){
                return true;
            }
            visitedNodes.add(node);
            //DFS
            if(checkCycleInSubGraph(graph, graph.get(node), visitedNodes)){
                return true;
            }
        }
        //recursive function nested inside the 'for' loop check for edge, this making it O(V)
        return false;
    }

    public static void main(String[] args) {
        GraphCycle graph = new GraphCycle();
        System.out.println("hasCycle: " + graph.hasCycle());
    }
}
