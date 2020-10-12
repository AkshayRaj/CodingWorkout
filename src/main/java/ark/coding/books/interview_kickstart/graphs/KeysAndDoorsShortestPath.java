package ark.coding.books.interview_kickstart.graphs;

import java.util.*;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-1705-9596-54-334
 */
public class KeysAndDoorsShortestPath {
    static final Set<Character> DOOR_SET = getDoorSet();
    static final int[] KEY_SET = getKeySet();
    static boolean[][][] visited;

    static int[][] find_shortest_path(String[] grid) {
        // 1. find start
        int[] start = new int[2];
        outer: for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length(); col++) {
                if (grid[row].charAt(col) == '@') {
                    start[0] = row;
                    start[1] = col;
                    break outer;
                }
            }
        }

        int[][] solution = new int[][]{}; // initialize to no solution exists state.
        // 2. bfs start ~> stop
        //    - keep finding neighbours
        //    - track parent of each neighbour; from start ~> intermediary node
        //    - track set of keys collected from start ~> intermediary node (as bitmask)
        //    - track cells visited with same keyset
        visited = new boolean[grid.length][grid[0].length()][1024];
        Queue<Node> frontier = new LinkedList<>();
        frontier.add(new Node(start[0],start[1], grid, null, 0));
        visited[start[0]][start[1]][0] = true;
        while (!frontier.isEmpty()) {
            Node head = frontier.poll();

            if (isFinalNode(head)) {
                List<int[]> path = new ArrayList<>();
                while (head != null) {
                    path.add(new int[]{head.row, head.col});
                    head = head.parent;
                }

                solution = new int[path.size()][2];
                for (int idx = path.size()-1; idx >= 0; idx--) solution[path.size()-1-idx] = path.get(idx);
                return solution;
            }

            frontier.addAll(getNeighboursOfNode(head, grid));
        }

        return solution;
    }

    private static Collection<Node> getNeighboursOfNode(Node head, String[] grid) {
        ArrayList<Node> neighbours = new ArrayList<>();

        // up
        if (head.row-1 >= 0) {
            Node neighbour = new Node(head.row-1, head.col, grid, head, head.keyset);
            addNeighbourIfNotVisitedWithKeySuperset(neighbour, neighbours);
        }
        // down
        if (head.row+1 < grid.length) {
            Node neighbour = new Node(head.row+1, head.col, grid, head, head.keyset);
            addNeighbourIfNotVisitedWithKeySuperset(neighbour, neighbours);
        }
        // left
        if (head.col-1 >= 0) {
            Node neighbour = new Node(head.row, head.col-1, grid, head, head.keyset);
            addNeighbourIfNotVisitedWithKeySuperset(neighbour, neighbours);
        }
        // right
        if (head.col+1 < grid[head.row].length()) {
            Node neighbour = new Node(head.row, head.col+1, grid, head, head.keyset);
            addNeighbourIfNotVisitedWithKeySuperset(neighbour, neighbours);
        }

        return neighbours;
    }

    private static void addNeighbourIfNotVisitedWithKeySuperset(Node neighbour, ArrayList<Node> neighbours) {
        // if the node was already visited with current keyset, then no need to visit again
        if (isNodeVisitedWithKeyset(neighbour)) {
            return;
        }

        if (DOOR_SET.contains(neighbour.datum)) {
            // check if we have the corresponding key
            if (neighbour.contains(Character.toLowerCase(neighbour.datum))) {
                neighbours.add(neighbour);
                markVisited(neighbour);
            }
        } else {
            if (neighbour.datum != '#') {
                neighbours.add(neighbour);
                markVisited(neighbour);
            }
        }
    }

    private static void markVisited(Node node) {
        visited[node.row][node.col][node.keyset] = true;

        int bitmask = 512;
        while (bitmask > 0) {
            if ((node.keyset & bitmask) > 0) visited[node.row][node.col][bitmask] = true;
            bitmask >>= 1;
        }
    }

    private static boolean isNodeVisitedWithKeyset(Node neighbour) {
        int row = neighbour.row;
        int col = neighbour.col;
        return visited[row][col][neighbour.keyset];
    }

    private static boolean isFinalNode(Node head) {
        return head.datum == '+';
    }

    static class Node {
        final int row;
        final int col;
        final char datum;
        final Node parent;
        final int keyset; //bitmask 0 ~> 1023 (2^10); there are 10 keys from 'a' ~> 'j'

        Node(int row, int col, String[] grid, Node parent, int neighboursKeyset) {
            this.row = row;
            this.col = col;
            this.datum = grid[row].charAt(col);
            this.parent = parent;
            if(isKey(datum)) {
                // add key to keyset
                this.keyset = neighboursKeyset | KEY_SET[datum - 'a'];
            }
            else {
                this.keyset = neighboursKeyset;
            }
        }

        private boolean isKey(char datum) {
            int diff = datum - 'a';
            return 0 <= diff && diff < 10;
        }

        public boolean contains(char key) {
            int bitmask = KEY_SET[key-'a'];
            return (keyset & bitmask) > 0;
        }
    }

    private static int[] getKeySet() {
        int[] keyset = new int[10];
        keyset[0] = 1;// a
        keyset[1] = 2;// b
        keyset[2] = 4;// c
        keyset[3] = 8;// d
        keyset[4] = 16;// e
        keyset[5] = 32;// f
        keyset[6] = 64;// g
        keyset[7] = 128;// h
        keyset[8] = 256;// i
        keyset[9] = 512;// j
        return keyset;
    }

    private static Set<Character> getDoorSet() {
        Set<Character> doors = new HashSet<>();
        doors.add('A');
        doors.add('B');
        doors.add('C');
        doors.add('D');
        doors.add('E');
        doors.add('F');
        doors.add('G');
        doors.add('H');
        doors.add('I');
        doors.add('J');
        return doors;
    }
}
