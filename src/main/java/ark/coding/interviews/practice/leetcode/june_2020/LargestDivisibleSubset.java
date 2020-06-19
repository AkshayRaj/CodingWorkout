/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.leetcode.june_2020;

import ark.coding.tools.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
 *
 * Si % Sj = 0 or Sj % Si = 0.
 *
 * If there are multiple solutions, return any subset is fine.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: [1,2] (of course, [1,3] will also be ok)
 * Example 2:
 *
 * Input: [1,2,4,8]
 * Output: [1,2,4,8]
 */
public class LargestDivisibleSubset {

    public static void main(String[] args) {
        //Utils.printList(largestDivisibleSubset(new int[]{4,8,10,240}));
        Utils.printList(largestDivisibleSubset(new int[]{3,4,16,8}));
    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int[] sortedNumbers = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNumbers);

        // BRUTE-FORCE
        // 1. get all subsets of nums (2^n)
        // 2. Start from subsets of largest size ~> size of 1
        //    Whenever we see that a subset satisfies the pair property, (nested fors, n^2)
        //    return it.
        //    Time : 2^n * n^2
        // 2-3-9-16-27-30-32-64

        int[][] matrix = new int[nums.length][nums.length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = row; col < matrix.length; col++) {
                int colNum = sortedNumbers[col];
                int rowNum = sortedNumbers[row];
                if (colNum % rowNum != 0) {
                    if (row != 0) {
                        matrix[row][col] = matrix[row - 1][col];
                    }
                }
                else {
                    // divisible

                    // check if current colNum is divisible by any smaller numbers
                    boolean isDivisibleBySmallerNum = rowNum == colNum;

                    int previousRow = row - 1;
                    while (previousRow >= 0) {
                        if (rowNum % sortedNumbers[previousRow] == 0) {
                            isDivisibleBySmallerNum = true;
                            break;
                        }
                        previousRow--;
                    }

                    if (row != 0) {
                        if (isDivisibleBySmallerNum) {
                            matrix[row][col] = matrix[row - 1][col] + 1;
                        } else {
                            if (matrix[row - 1][col] == 0) {
                                matrix[row][col] = matrix[row - 1][col] + 1;
                            }
                            else {
                                matrix[row][col] = matrix[row - 1][col];
                            }
                        }
                    }
                    else {
                        if (colNum % rowNum == 0) {
                            matrix[row][col] = 1;
                        }
                    }
                }
            }
        }

        int largestSubsetCol = -1;
        int max = -1; // all numbers are positive
        int lastRow = matrix.length - 1;
        for (int col = 0; col < matrix.length; col++) {
            int lastRowElement = matrix[lastRow][col];
            if (lastRowElement > max) {
                max = lastRowElement;
                largestSubsetCol = col;
            }
        }

        // move only left & up
        int row = matrix.length-1;
        int col = largestSubsetCol;
        List<Integer> largestDivisibleSubset = new ArrayList<>();
        while (row >= 0) {
            if (row > 0) {
                xyz:
                while (matrix[row][col] == matrix[row - 1][col]) {
                    row--;
                    if (row == 0) {
                        break xyz;
                    }
                }
            }
            if (matrix[row][col] != 0) {
                largestDivisibleSubset.add(sortedNumbers[row]);
            }
            row--;
        }

        return largestDivisibleSubset;
    }
}
