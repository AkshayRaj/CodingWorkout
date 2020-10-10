package ark.coding.books.interview_kickstart.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class KnightTour {

    static int find_minimum_number_of_moves(int rows, int cols, int start_row, int start_col, int end_row, int end_col) {
        int noOfSteps = 0;
        boolean[][] isVisited = new boolean[rows][cols];

        Queue<Square> frontier = new LinkedList<>();
        frontier.add(new Square(start_row, start_col));
        isVisited[start_row][start_col] = true;
        while (!frontier.isEmpty()) {
            Queue<Square> nextFrontier = new LinkedList<>();
            while (!frontier.isEmpty()) {
                Square head = frontier.poll();
                if (head.row == end_row && head.col == end_col) return noOfSteps;

                // get all neighbouring squares
                Square s1 = new Square(head.row+1, head.col+2);//ru
                Square s2 = new Square(head.row-1, head.col+2);//rd
                Square s3 = new Square(head.row+1, head.col-2);//lu
                Square s4 = new Square(head.row-1, head.col-2);//ld
                Square s5 = new Square(head.row+2, head.col+1);//ur
                Square s6 = new Square(head.row+2, head.col-1);//ul
                Square s7 = new Square(head.row-2, head.col+1);//dr
                Square s8 = new Square(head.row-2, head.col-1);//dl

                if (isSquareValid(s1, isVisited)) {nextFrontier.add(s1); isVisited[s1.row][s1.col] = true;}
                if (isSquareValid(s2, isVisited)) {nextFrontier.add(s2); isVisited[s2.row][s2.col] = true;}
                if (isSquareValid(s3, isVisited)) {nextFrontier.add(s3); isVisited[s3.row][s3.col] = true;}
                if (isSquareValid(s4, isVisited)) {nextFrontier.add(s4); isVisited[s4.row][s4.col] = true;}
                if (isSquareValid(s5, isVisited)) {nextFrontier.add(s5); isVisited[s5.row][s5.col] = true;}
                if (isSquareValid(s6, isVisited)) {nextFrontier.add(s6); isVisited[s6.row][s6.col] = true;}
                if (isSquareValid(s7, isVisited)) {nextFrontier.add(s7); isVisited[s7.row][s7.col] = true;}
                if (isSquareValid(s8, isVisited)) {nextFrontier.add(s8); isVisited[s8.row][s8.col] = true;}
            }
            frontier = nextFrontier;
            noOfSteps++;
        }
        return -1;
    }

    private static boolean isSquareValid(Square square, boolean[][] isVisited) {
        return 0 <= square.row && square.row < isVisited.length
                && 0 <= square.col && square.col < isVisited[0].length
                && !isVisited[square.row][square.col];
    }

    static class Square {
        final int row;
        final int col;

        Square(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}
