/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import ark.coding.interviews.practice.algoexpert.easy.TargetSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Question: https://www.algoexpert.io/questions/Three%20Number%20Sum
 *
 * Given an array, and a target sum, find 3 numbers in the given array that add up to the target sum.
 * Return all such triplets. The numbers within the triplet must be sorted in ascending order.
 * The triplets themselves must be sorted in the solution list.
 *
 * <i>Note:</i> This problem is an extension of {@link TargetSum}, which asked for 2 numbers.
 */
public class ThreeNumberSum {
    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        List<Integer[]> solution = new ArrayList<>();

        int length = array.length;
        if (length < 3) {
            solution.add(new Integer[]{});
            return solution;
        }

        Arrays.sort(array);
        for (int index = 0; index <= length-3; index++) {
            int lowIndex = index + 1;
            int highIndex = length-1;
            while (lowIndex < highIndex) {
                int currentSum = array[index] + array[lowIndex] + array[highIndex];
                if (currentSum == targetSum) {
                    solution.add(new Integer[]{array[index], array[lowIndex], array[highIndex]});
                    highIndex--;
                }
                else if (currentSum > targetSum) {
                    highIndex--;
                }
                else if (currentSum < targetSum) {
                    lowIndex++;
                }
            }
        }
        return solution;
    }
    // Time Complexity:
    // O(n^2) ; [O(n log(n)) for sort is eclipsed by the nested while-loop]
    // Space Complexity: O(1)

    public static void main(String[] args) {
        int[] array = new int[]{8, 10, -2, 49, 14};
        for (Integer integer : threeNumberSum(array, 57).get(0)) {
            System.out.print(integer + " ");
        }
    }
}
