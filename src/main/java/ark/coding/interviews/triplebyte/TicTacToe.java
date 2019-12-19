/**
 * Created by Akshayraj
 */
package ark.coding.interviews.triplebyte;
import java.util.Scanner;

public class TicTacToe {

    private static final Scanner scanner = new Scanner(System.in);

    String[][] board = new String [][] {
        {"-", "-", "-"},
        {"-", "-", "-"},
        {"-", "-", "-"}
    };

    /**
     * STEP - 1
     */
    public void addToken(int row, int column) {
        board[row][column] = "X";
    }

    // print the board
    public void printBoard() {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }

    /**
     * STEP - 2
     */
    public boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (board[row][column].equalsIgnoreCase("-")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void makeMoveAI() {
        if (!isBoardFull()) {
            // make a move
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    if (board[row][column].equals("-")) {
                        board[row][column] = "O";
                        return;
                    }
                }
            }
        }
        else {
            throw new RuntimeException("The board is full. No legal move exists.");
        }
    }

    /**
     * STEP - 3
     */
    public void startGame() {
        boolean humanTurn = true;

        while (!isBoardFull()) {
            if (humanTurn) {
                System.out.println("Enter your move; row: ");
                int row = scanner.nextInt();
                System.out.println("Enter column: ");
                int column = scanner.nextInt();

                // assuming entry is legal
                addToken(row, column);
                printBoard();
                humanTurn = false;
            }
            else {
                makeMoveAI();
                printBoard();
                humanTurn = true;
            }
        }
    }

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.close();

        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.printBoard();
        ticTacToe.addToken(0, 1);
        ticTacToe.printBoard();
        System.out.println("===========================");

        ticTacToe.startGame();

        while (true) {
            ticTacToe.makeMoveAI();
            ticTacToe.printBoard();
        }
    }
}
