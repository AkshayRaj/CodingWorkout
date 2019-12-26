/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TargetSum {

    /**
     * Brute Force:
     * O(n^2) time complexity
     * O(1) space complexity
     *
     * @param array
     * @param targetSum
     * @return
     */
    public static int[] twoNumberSum(int[] array, int targetSum) {
        // Write your code here.
        for (int index1 = 0; index1 < array.length-1; index1++) {
            for (int index2 = index1 + 1; index2 < array.length; index2++) {
                if (array[index1] + array[index2] == targetSum) {
                    return new int[]{array[index1], array[index2]};
                }
            }
        }
        return new int[]{};
    }

    /**
     * O(n) time complexity
     * O(n) space complexity
     *
     *
     * @param array
     * @param targetSum
     * @return
     */
    public static int[] twoNumberSumLinearTimeLinearSpace(int[] array, int targetSum) {
        Set<Integer> set = new HashSet<Integer>(); // linear space complexity
        for (int number : array) {
            int potentialMatch = targetSum - number;
            if (set.contains(potentialMatch)) {
                return new int[] {number, potentialMatch};
            } else {
                set.add(number);
            }
        }
        return new int[]{};
    }

    /**
     * O(n log(n)) time complexity
     * O(1) space complexity
     * 
     * @param array
     * @param targetSum
     * @return
     */
    public static int[] twoNumberSumLogNTime(int[] array, int targetSum) {
        Arrays.sort(array); // n log(n) time complexity of sort

        int leftPointer = 0;
        int rightPointer = array.length - 1;


        while (leftPointer < rightPointer) {
            if (array[leftPointer] + array[rightPointer] > targetSum) {
                rightPointer--;
            } else if (array[leftPointer] + array[rightPointer] < targetSum){
                leftPointer++;
            } else if (array[leftPointer] + array[rightPointer] == targetSum) {
                return new int[]{array[leftPointer], array[rightPointer]};
            }

        }
        // the ^^^while-loop^^^ will take atmost O(n) time.
        // Therefore, time complexity is O(n) + O(n log(n)) ~> O(n log(n)) time complexity
        // Space Complexity is O(1)

        return new int[]{};
    }
}
