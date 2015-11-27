package ark.coding.datastructure.primitive_datastructures.arraysandstrings.rotate_nxn_matrix;

import ark.coding.Solution;

/**
 * Created by Akshayraj on 11/26/15.
 */
public class MatrixRotation implements Solution<int[][]> {
    private static final int[][] MATRIX = {
            {1,2,3},
            {5,6,7},
            {8,9,10}
    };

    @Override
    public int[][] solution(Object... args) {
        return rotateMatrix((int[][]) args[0]);//throws - java.lang.ClassCastException: [I cannot be cast to [[I
    }

    private int[][] rotateMatrix(int[][] matrix) {
        int matrixSize = matrix.length;
        int[][] rotatedMatrix = new int[matrixSize][matrixSize];
        for(int i = 0; i < matrixSize ; i ++){
            for(int j = 0; j < matrixSize ; j ++){
                rotatedMatrix[j][matrixSize-(i+1)] = matrix[i][j];
            }
        }
        System.out.println("");
        for(int i = 0; i < matrixSize ; i ++){
            System.out.print("\n");
            for(int j = 0; j < matrixSize ; j ++){
                System.out.print(" " + rotatedMatrix[i][j]);
            }
        }
        return rotatedMatrix;
    }

    public static void main(String[] args){
        MatrixRotation matrixRotation = new MatrixRotation();
        int[][] newMatrix = MATRIX;
        for(int i = 0 ; i < 4 ; i++) {
            newMatrix = matrixRotation.rotateMatrix(newMatrix);
        }
    }
}
