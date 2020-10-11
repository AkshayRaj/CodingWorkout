package ark.coding.books.interview_kickstart.graphs;

import java.util.*;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-1705-9596-54-334
 */
public class KeysAndDoorsShortestPath {
    static final Set<Character> DOOR_SET = getDoorSet();

    static final Set<Character> KEY_SET = getKeySet();

    static int[][] find_shortest_path(String[] grid) {
        // 1. find start and stop
        int[] start = new int[2];
        int[] stop = new int[2];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length(); col++) {
                if (grid[row].charAt(col) == '@') {start[0] = row; start[1] = col;}
                if (grid[row].charAt(col) == '+') {stop[0] = row; stop[1] = col;}
            }
        }

        int[][] solution = new int[][]{start, stop};
        // 2. bfs start ~> stop
        //    - keep finding neighbours
        //    - track path from start ~> intermediary node
        //    - track set of keys collected from start ~> intermediary node
        Node startingNode = new Node(start[0],start[1], grid, new ArrayList<int[]>(), new HashSet<>());
        Queue<Node> frontier = new LinkedList<>();
        frontier.add(startingNode);
        while (!frontier.isEmpty()) {
            Node head = frontier.poll();

            if (isFinalNode(head)) {
                solution = new int[head.path.size()][2];
                for (int idx = 0; idx < head.path.size(); idx++) solution[idx] = head.path.get(idx);
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
            Node neighbour = new Node(head.row-1, head.col, grid, head.path, head.keys);
            addToList(neighbour, neighbours);
        }
        // down
        if (head.row+1 < grid.length) {
            Node neighbour = new Node(head.row+1, head.col, grid, head.path, head.keys);
            addToList(neighbour, neighbours);
        }
        // left
        if (head.col-1 >= 0) {
            Node neighbour = new Node(head.row, head.col-1, grid, head.path, head.keys);
            addToList(neighbour, neighbours);
        }
        // right
        if (head.col+1 < grid[head.row].length()) {
            Node neighbour = new Node(head.row, head.col+1, grid, head.path, head.keys);
            addToList(neighbour, neighbours);
        }

        return neighbours;
    }

    private static void addToList(Node neighbour, ArrayList<Node> neighbours) {
        if (DOOR_SET.contains(neighbour.datum)) {
            // check if we have the corresponding key
            if (neighbour.keys.contains(Character.toLowerCase(neighbour.datum))) {
                neighbours.add(neighbour);
            }
        }
        else {
            if (neighbour.datum != '#') neighbours.add(neighbour);
        }
    }

    private static boolean isFinalNode(Node head) {
        return head.datum == '+';
    }

    private static Set<Character> getKeySet() {
        Set<Character> keys = new HashSet<>();
        keys.add('a');
        keys.add('b');
        keys.add('c');
        keys.add('d');
        keys.add('e');
        keys.add('f');
        keys.add('g');
        keys.add('h');
        keys.add('i');
        keys.add('j');
        return keys;
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

    static class Node {
        final int row;
        final int col;
        final char datum;
        final List<int[]> path;
        final Set<Character> keys;

        Node(int row, int col, String[] grid, List<int[]> path, Set<Character> keys) {
            this.row = row;
            this.col = col;
            this.datum = grid[row].charAt(col);
            this.path = new ArrayList<>(path);
            this.path.add(new int[]{row, col});
            if(KEY_SET.contains(this.datum)) {
                this.keys = new HashSet<>(keys);
                this.keys.add(this.datum);
            }
            else {
                this.keys = keys;
            }
        }
    }
}
