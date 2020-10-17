package ark.coding.books.interview_kickstart.graphs;

import java.util.List;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-1705-9596-54-335
 */
public class ZombieClusters {
    static boolean[][] visited;

    public static int zombieCluster(List<String> zombies) {
        int size = zombies.size();

        int clusters = 0;
        visited = new boolean[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (zombies.get(row).charAt(col) == '1' && !visited[row][col]) {
                    dfs(zombies, row, col);
                    clusters++;
                }
            }
        }

        return clusters;
    }

    static void dfs(List<String> zombies, int row, int col) {
        if (visited[row][col]) return;

        visited[row][col] = true;
        visited[col][row] = true;
        for (int neighbour = 0; neighbour < zombies.size(); neighbour++) {
            if (zombies.get(col).charAt(neighbour) == '1') {
                dfs(zombies, col, neighbour);
            }
        }
    }
}
