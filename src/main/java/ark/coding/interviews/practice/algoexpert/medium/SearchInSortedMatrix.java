/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

/**
 * https://www.algoexpert.io/questions/Search%20In%20Sorted%20Matrix
 *
 * Given a two dimensional array, which contains unique integers,
 * find the location of the target number as an integer array [row, col], if it exists
 * [-1, -1] otherwise.
 */
class SearchInSortedMatrix {

    public static int[] searchInSortedMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return new int[]{-1, -1};
        }

        // Write your code here.
        // 1. check mid of the rectangle
        //    Value can exist in 1,2,3 or 4th quadrant
        //    Adjust low & high of row/col accordingly until you get the value or low > high
        //
        int targetRow = -1;
        int targetCol = -1;

        int noOfRows = matrix.length;
        int noOfColumns = matrix[0].length;
        int rowIndex = 0;
        int colIndex = noOfColumns-1;

        // - Start with topRight corner.
        //   if target is equal, we have the answer
        //   if target is less, then go left of the row
        //   if target is more, then go down the column
        // Loop until you find solution, or break bounds.
        outerloop:
        while (rowIndex < noOfRows && colIndex >= 0) {
            int currentElement = matrix[rowIndex][colIndex];
            if (target == currentElement) {
                targetRow = rowIndex;
                targetCol = colIndex;
                break outerloop;
            }

            if (target < currentElement) {
                colIndex--;
            } else {
                rowIndex++;
            }
        }

        return new int[]{targetRow, targetCol};
    }
    // Space : O(1)
    // Time  : O(R + C); R is no of rows
    //                 ; C is no of columns

    public static int[] searchInSortedMatrixWithNestedForLoops(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return new int[]{-1, -1};
        }

        // Write your code here.
        // 1. check mid of the rectangle
        //    Value can exist in 1,2,3 or 4th quadrant
        //    Adjust low & high of row/col accordingly until you get the value or low > high
        //
        int targetRow = -1;
        int targetCol = -1;

        int noOfRows = matrix.length;
        int noOfColumns = matrix[0].length;

        rowLoop:
        for (int rowIndex = 0; rowIndex < noOfRows; rowIndex++) {
            for (int colIndex = 0; colIndex < noOfColumns; colIndex++) {
                if (matrix[rowIndex][colIndex] == target) {
                    targetRow = rowIndex;
                    targetCol = colIndex;
                    break rowLoop;
                }
            }
        }

        return new int[]{targetRow, targetCol};
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {1, 4, 7, 12, 15, 1000},
                {2, 5, 19, 31, 32, 1001},
                {3, 8, 24, 33, 35, 1002},
                {40, 41, 42, 44, 45, 1003},
                {99, 100, 103, 106, 128, 1004}
        };

        searchInSortedMatrix(matrix, 35);
    }
}
