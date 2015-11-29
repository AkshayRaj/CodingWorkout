package ark.coding.datastructure.primitive_datastructures.arraysandstrings.matrix.column.row;

import ark.coding.Solution;
import ark.coding.datastructure.primitive_datastructures.arraysandstrings.MatrixUtil;

/**
 * Created by Akshayraj on 11/28/15.
 */
public class MatrixColumnRow implements Solution<int[][]> {
    private static final int[][] MATRIX = {
            {1,2,2,4},
            {4,5,0,6},
            {4,0,7,8}
    };

    @Override
    public int[][] solution(Object... args) {
        return replaceRowColumnWithZero((int[][]) args[0]);
    }

    public int[][] replaceRowColumnWithZero(int[][] matrix) {
        int rows = matrix.length;//number of rows is the height of the matrix
        int columns = matrix[0].length;//number of columns is length of the first element(assuming all rows have equal length)
        int[] rowNoForZeros = new int[rows];
        int[] columnNoForZeros = new int[columns];
        //find zeros in the matrix
        for(int row = 0; row < rows; row++){
            System.out.print("\n");
            for(int column = 0 ; column < columns; column++){
                System.out.print(" " + matrix[row][column]);
                if(matrix[row][column] == 0){
                    rowNoForZeros[row] = 1;
                    columnNoForZeros[column] = 1;
                }
            }
        }
        //make the row Zero
        for(int row = 0; row < rows; row++){
            if(rowNoForZeros[row] == 1){
                for(int column = 0; column < columns; column++){
                    matrix[row][column] = 0;
                }
            }
        }
        //make the column Zero
        for(int column = 0; column < columns; column++){
            if(columnNoForZeros[column] == 1){
                for(int row = 0; row < rows; row++){
                    matrix[row][column] = 0;
                }
            }
        }
        return matrix;
    }

    public static void main(String[] args){
        MatrixColumnRow matrixColumnRow = new MatrixColumnRow();
        int[][] modifiedMatrix = matrixColumnRow.replaceRowColumnWithZero(MATRIX);
        MatrixUtil.printMatrix(modifiedMatrix);
    }
}
