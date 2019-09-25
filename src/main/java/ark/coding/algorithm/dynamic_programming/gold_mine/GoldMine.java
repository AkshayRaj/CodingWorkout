/**
 * Created by Akshayraj
 */
package ark.coding.algorithm.dynamic_programming.gold_mine;

import java.util.Arrays;

public class GoldMine {

    public Long solution(int[][] args) {
        return calculateMaxPossibleGoldExtraction(args);
    }

    private Long calculateMaxPossibleGoldExtraction(int[][] mine) {
        validate(mine);

        // edge case
        if (mine.length == 0) {
            return 0L;
        }
        // edge case
        if (mine.length == 1) {
            long maxGold = 0;
            for (int column = 0; column < mine[0].length; column++) {
                maxGold = maxGold + mine[0][column];
            }
            return maxGold;
        }

        // start of main solution logic
        long[][] maxGoldTable = getArrayForTabulation(mine);

        //base condition - gold extracted from the first column
        for (int row = 0; row < mine.length; row ++) {
            maxGoldTable[row][0] = mine[row][0];
        }

        if (mine.length >=2 || mine[0].length >=2) {
            for (int column = 1; column < mine[0].length; column++) {
                for (int row = 0; row < mine.length; row++) {
                    int noOfRows = mine.length;
                    if (noOfRows >= 2) {
                        if (row == 0) {
                            // this means, previous step was right, or right diagonally up
                            long previousMax = Math.max(maxGoldTable[row][column - 1], maxGoldTable[row + 1][column - 1]);
                            maxGoldTable[row][column] = previousMax + mine[row][column];
                        } else if (row == noOfRows - 1) {
                            // this means, previous step was right, or right diagonally down
                            maxGoldTable[row][column] = (Math.max(maxGoldTable[row][column - 1], maxGoldTable[row - 1][column - 1]))
                                    + mine[row][column];
                        } else {
                            maxGoldTable[row][column] = Math.max(
                                    Math.max(maxGoldTable[row - 1][column - 1], maxGoldTable[row][column - 1]),
                                    maxGoldTable[row + 1][column - 1])
                                    + mine[row][column];
                        }
                    }
                }
            }
        }

        // The last column in all the rows, will contain max gold extracted after reaching that location,
        // i.e. after reaching mine[row][lastColumn], maxGoldTable[row][lastColumn] will contain corresponding max values.
        // Now to extrac the maxGold, we see which of those values is highest.
        long maxGold = Integer.MIN_VALUE;
        for (int row = 0; row < mine.length; row++) {
            int lastColumn = mine[0].length - 1;
            maxGold = Math.max(maxGold, maxGoldTable[row][lastColumn]);
        }


        for (int row = 0; row < mine.length; row++) {
            for (int column = 0; column < mine[row].length; column++) {
                System.out.print(String.format("%d  ", maxGoldTable[row][column]));
            }
            System.out.println("");
        }

        return maxGold;
    }

    private long[][] getArrayForTabulation(int[][] mine) {
        long[][] array = new long[mine.length][mine[0].length];
        for (int row = 0; row < mine.length; row++) {
            Arrays.fill(array[row], Integer.MIN_VALUE);
        }
        return array;
    }

    public static void main(String[] args) {
        GoldMine goldMine = new GoldMine();

        int mine[][] = new int[][]{
                {1, 6}
        };
//                new int[][]{
//                {1, 3, 1, 5},
//                {2, 2, 4, 1},
//                {5, 0, 2, 3},
//                {0, 6, 1, 2}
//        };

        long max = goldMine.solution(mine);
        System.out.println("MAX gold that can extracted is: " + max);
    }

    private void validate(int[][] mine) {
        int noOfColumns = mine[0].length;
        for (int row = 0; row < mine.length; row++) {
            if (mine[row].length != noOfColumns) {
                throw new RuntimeException("Matrix is not valid. No of columns differs across rows.");
            }
        }
    }
}
