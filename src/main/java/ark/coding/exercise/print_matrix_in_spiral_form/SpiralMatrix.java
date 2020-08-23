package ark.coding.exercise.print_matrix_in_spiral_form;
/* This code is adopted from the solution given
   @ http://effprog.blogspot.com/2011/01/spiral-printing-of-two-dimensional.html */

public class SpiralMatrix {
    private static final int ROW = 4;
    private static final int COLUMN = 6;

    public static void main(String[] args) {
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        int matrix[][]={
                {
                        01, 02, 03, 04, 05, 06
                },
                {
                        07,  8,  9, 10, 11, 12
                },
                {
                        13, 14, 15, 16, 17, 18
                },
                {
                        19, 20, 21, 22, 23, 24
                }
        };

        spiralMatrix.getSpiralOfMatrix(ROW, COLUMN, matrix);
    }

    public void getSpiralOfMatrix(int noOfRows, int noOfColumns, int matrix[][]) {
        int i; // int for iterations
        int rows = noOfRows;
        int columns = noOfColumns;
        int index_row = 0;
        int index_column = 0;

        while (index_row < rows && index_column < columns) {
            System.out.println("\nrows: " + (rows - index_row) + " columns: " + (columns - index_column));

        /* Print the first row from the remaining rows */
            System.out.println("\n");
            for (i = index_column; i < columns; i++) {
                System.out.print(" " + matrix[index_row][i]);
            }
            index_row++;

        /* Print the last column from the remaining columns */
            System.out.println("\n");
            for (i = index_row; i < rows; ++i) {
                System.out.print(" " + matrix[i][columns - 1]);
            }
            columns--;

        /* Print the last row from the remaining rows */
            System.out.println("\n");
            if (index_row < rows) {
                for (i = columns - 1; i >= index_column; --i) {
                    System.out.print(" " + matrix[rows - 1][i]);
                }
                rows--;
            }

        /* Print the first column from the remaining columns */
            System.out.println("\n");
            if (index_column < columns) {
                for (i = rows - 1; i >= index_row; --i) {
                    System.out.print(" " + matrix[i][index_column]);
                }
                index_column++;
            }
        }
    }
}