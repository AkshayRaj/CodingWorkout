/**
 * Created by Akshayraj
 */
package ark.coding.interviews.pramp;

import java.util.HashSet;
import java.util.Set;

/**
 * pramp.com/challenge/O5PGrqGEyKtq9wpgw6XP
 * Sudoku Solver
 * Write the function sudokuSolve that checks whether a given sudoku board (i.e. sudoku puzzle) is solvable.
 * If so, the function will returns true. Otherwise (i.e. there is no valid solution to the given sudoku board), returns false.
 *
 * In sudoku, the objective is to fill a 9x9 board with digits so that each column, each row, and each of the nine 3x3 sub-boards
 * that compose the board contains all of the digits from 1 to 9.
 * The board setter provides a partially completed board, which for a well-posed board has a unique solution.
 * As explained above, for this problem, it suffices to calculate whether a given sudoku board has a solution.
 * No need to return the actual numbers that make up a solution.
 *
 * A sudoku board is represented as a two-dimensional 9x9 array of the characters ‘1’,‘2’,…,‘9’ and the '.' character, which represents a blank space.
 * The function should fill the blank spaces with characters such that the following rules apply:
 *
 * In every row of the array, all characters ‘1’,‘2’,…,‘9’ appear exactly once.
 * In every column of the array, all characters ‘1’,‘2’,…,‘9’ appear exactly once.
 * In every 3x3 sub-board that is illustrated below, all characters ‘1’,‘2’,…,‘9’ appear exactly once.
 * A solved sudoku is a board with no blank spaces, i.e. all blank spaces are filled with characters that abide to the constraints above.
 * If the function succeeds in solving the sudoku board, it’ll return true (false, otherwise).
 *
 * Example (more examples can be found here<href>https://www.sudokukingdom.com/</href> ):
 *
 * Write a readable an efficient code, explain how it is built and why you chose to build it that way.
 */
public class Sudoku {

    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'.','.','3','8','.','.','4','.','.'},
                {'.','.','.','.','1','.','.','7','.'},
                {'.','6','.','.','.','5','.','.','9'},
                {'.','.','.','9','.','.','6','.','.'},
                {'.','2','.','.','.','.','.','1','.'},
                {'.','.','4','.','.','3','.','.','2'},
                {'.','.','2','.','.','.','8','.','.'},
                {'.','1','.','.','.','.','.','5','.'},
                {'9','.','.','.','.','7','.','.','3'}
        };

        System.out.println(sudokuSolve(board));
    }
    
    /**
     * Approach: (Back-tracking)
     * For each blank space, fill in a character from 1 ~> 9.
     * - If there are not collisions, within a row, column & 3x3 sub-board, then answer exists for that cell.
     * - Repeat this for all cells, i.e. (9x9)
     *
     * Time Complexity:
     * There are 9x9 cells in a Sudoku board.
     * In worst case, we iteratively will check if 9 digits (i.e. from 1 ~> 9) can be filled in each of the 9x9 cells.
     *
     * So, time complexity is 9 x 9 x 9. But this is a constant. So, time complexity is O(1).
     *
     * Checks if the current Sudoku board is solvable.
     *
     * Space Complexity:
     * We use a Set of size 9, i.e. the number of chars that are used on the board, for some calculations.
     * O(1)
     *
     * @param board A two dimensional {@link Character} matrix, that represents the current state of the Sudoku game.
     * @return true, if the games is solvable from the current state of the board.
     *         false, otherwise.
     */
    static boolean sudokuSolve(char[][] board) {
        // Check if filling in blank space with any character works or not for a given row + column.
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                Character currentChar = board[row][column];

                if (currentChar.equals('.')) {
                    boolean isSolvable = false;
                    for (int possibleChar = 1; possibleChar <= 9; possibleChar++) {
                        if (isSolvableWithGivenChar(board, row, column, convertSingleDigitToChar(possibleChar))) {
                            isSolvable = true;
                        }
                    }
                    if (!isSolvable) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    static boolean isSolvableWithGivenChar(char[][] board, int row, int column, char possibleChar) {
        board[row][column] = possibleChar;

        //1. Row
        Set<Character> charSet =  new HashSet<>();
        for (int rowCell = 0; rowCell < 9; rowCell++) {
            Character currentChar = board[row][rowCell];
            if (!currentChar.equals('.')) {
                if (charSet.contains(currentChar)) {
                    // re-state the original board
                    board[row][column] = '.';
                    return false;
                }
                charSet.add(currentChar);
            }
        }

        //2. Column
        charSet.clear();
        for (int columnCell = 0; columnCell < 9; columnCell++) {
            Character currentChar = board[columnCell][column];
            if (!currentChar.equals('.')) {
                if (charSet.contains(currentChar)) {
                    // re-state the original board
                    board[row][column] = '.';
                    return false;
                }
                charSet.add(currentChar);
            }
        }

        //3. 3x3 sub-board
        charSet.clear();
        int subBoardRowStart = (row/3) * 3;
        int subBoardColumnStart = (column/3) * 3;
        for (int rowCell = subBoardRowStart; rowCell < subBoardRowStart + 3; rowCell++) {
            for (int columnCell = subBoardColumnStart; columnCell < subBoardColumnStart + 3; columnCell++) {
                Character currentChar = board[rowCell][columnCell];
                if (!currentChar.equals('.')) {
                    if (charSet.contains(currentChar)) {
                        // re-state the original board position.
                        board[row][column] = '.';
                        return false;
                    }
                    charSet.add(currentChar);
                }
            }
        }
        charSet.clear();

        // reset board with original state
        board[row][column] = '.';
        return true;
    }

    private static char convertSingleDigitToChar(int i) {
        if (i >= 0 && i <= 9) {
            return (char) (i + '0');
        }
        throw new RuntimeException(String.format("Integer [%d] is not a single digit.", i));
    }

}
