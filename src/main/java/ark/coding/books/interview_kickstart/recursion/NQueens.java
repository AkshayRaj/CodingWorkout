package ark.coding.books.interview_kickstart.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-1690-9581-34-239
 */
public class NQueens {
    static List<List<String>> solution = new ArrayList<>();

    public static String[][] find_all_arrangements(int n) {
        if (1 < n && n < 4) {
            return new String[][]{};
        }

        boolean[][] board = new boolean[n][n];
        findValidArrangements(board, 0);

        return getSolution(n);
    }

    private static void findValidArrangements(boolean[][] board, int row) {
        // recursive condition
        if (row == board.length) {
            if (isValidArrangement(board)) addArrangement(board);
            return;
        }

        // backtracking condition
        if (!isValidArrangement(board)) return;

        for (int col = 0; col < board.length; col++) {
            board[row][col] = true;
            findValidArrangements(board, row+1);
            board[row][col] = false;
        }
    }

    private static void addArrangement(boolean[][] board) {
        List<String> arrangement = new ArrayList<>(board.length);
        for (int row = 0; row < board.length; row++) {
            StringBuilder rowArrngmnt = new StringBuilder();
            for (int col = 0; col < board.length; col++) {
                if (board[row][col]) rowArrngmnt.append("q");
                else rowArrngmnt.append("-");
            }
            arrangement.add(rowArrngmnt.toString());
        }
        solution.add(arrangement);
    }

    private static boolean isValidArrangement(boolean[][] board) {
        // check if more than 1 queen on a given row
        for (int row = 0; row < board.length; row++) {
            int count = 0;
            for (int col = 0; col < board.length; col++) {
                if (board[row][col]) count++;
                if (count > 1) return false;
            }
        }

        // check if more than 1 queen on a given column
        for (int col = 0; col < board.length; col++) {
            int count = 0;
            for (int row = 0; row < board.length; row++) {
                if (board[row][col]) count++;
                if (count > 1) return false;
            }
        }

        // check if more than 1 queen on diagonal (top left ~> bottom right)
        {
            int row = 0;
            for (int col = 0; col < board.length; col++) {
                // check diagonal
                int dRow = row;
                int dCol = col;
                int count = 0;
                while (dRow < board.length && dCol < board.length) {
                    if (board[dRow][dCol]) count++;
                    if (count > 1) return false;
                    dRow++;
                    dCol++;
                }
            }

            int col = 0;
            for (row = 1; row < board.length; row++) {
                // check diagonal
                int dRow = row;
                int dCol = col;
                int count = 0;
                while (dRow < board.length && dCol < board.length) {
                    if (board[dRow][dCol]) count++;
                    if (count > 1) return false;
                    dRow++;
                    dCol++;
                }
            }

        }

        // check if more than 1 queen on diagonal (top right ~> bottom left)
        {
            int row = 0;
            for (int col = board.length-1; col >= 0; col--) {
                // check diagonal
                int dRow = row;
                int dCol = col;
                int count = 0;
                while (dRow < board.length && dCol >= 0) {
                    if (board[dRow][dCol]) count++;
                    if (count > 1) return false;
                    dRow++;
                    dCol--;
                }
            }

            int col = board.length-1;
            for (row = 1; row < board.length; row++) {
                // check diagonal
                int dRow = row;
                int dCol = col;
                int count = 0;
                while (dRow < board.length && dCol >= 0) {
                    if (board[dRow][dCol]) count++;
                    if (count > 1) return false;
                    dRow++;
                    dCol--;
                }
            }
        }

        return true;
    }

    private static String[][] getSolution(int n) {
        String[][] solutionArray = new String[solution.size()][n];

        for (int boardNo = 0; boardNo < solution.size(); boardNo++) {
            String[] board = new String[n];
            for (int rowNo = 0; rowNo < n; rowNo++) {
                board[rowNo] = solution.get(boardNo).get(rowNo);
            }
            solutionArray[boardNo] = board;
        }
        return solutionArray;
    }
}
