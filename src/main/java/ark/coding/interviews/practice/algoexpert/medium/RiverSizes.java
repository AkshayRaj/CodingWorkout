/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import java.util.*;

/**
 * https://www.algoexpert.io/questions/River%20Sizes
 */
class RiverSizes {
    public static List<Integer> riverSizes(int[][] matrix) {
        // Write your code here.
        List<Integer> riverSizes = new ArrayList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                if (matrix[row][column] == 1) {
                    riverSizes.add(getRiverSize(matrix, row, column));
                }
            }
        }
        return riverSizes;
    }

    static int getRiverSize(int[][] matrix, int row, int column) {
        if (row >= matrix.length || row < 0
                || column >= matrix[0].length || column < 0) {
            return 0;
        }
        if (matrix[row][column] == 0) {
            return 0;
        }
        matrix[row][column] = 0;
        return 1
                + getRiverSize(matrix, row+1, column)
                + getRiverSize(matrix, row-1, column)
                + getRiverSize(matrix, row, column+1)
                + getRiverSize(matrix, row, column-1);
    }
}
