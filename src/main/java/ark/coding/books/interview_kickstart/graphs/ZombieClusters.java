package ark.coding.books.interview_kickstart.graphs;

import java.util.List;
import java.util.Stack;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-1705-9596-54-335
 */
public class ZombieClusters {
    public static int zombieCluster(List<String> zombies) {
        int noOfNodes = zombies.size();

        int clusters = 0;
        boolean[] visited = new boolean[noOfNodes];
        Stack<Integer> stack = new Stack<>();
        for (int node = 0; node < noOfNodes; node++) {
            if (!visited[node]) {
                clusters++;

                // DFS and traverse the graph
                stack.push(node);
                while (!stack.isEmpty()) {
                    Integer currentNode = stack.pop();
                    visited[currentNode] = true;
                    for (int neighbour = 0; neighbour < noOfNodes; neighbour++) {
                        if (zombies.get(currentNode).charAt(neighbour) == '1' && !visited[neighbour]) {
                            stack.push(neighbour);
                        }
                    }
                }
            }
        }
        return clusters;
    }
}
