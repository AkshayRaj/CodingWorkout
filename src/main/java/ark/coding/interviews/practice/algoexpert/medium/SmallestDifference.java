/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import java.util.Arrays;

/**
 * Question: https://www.algoexpert.io/questions/Smallest%20Difference
 */
public class SmallestDifference {


    /**
     *
     * @param arrayOne
     * @param arrayTwo
     * @return
     */
    public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
        int smallestDifference = Integer.MAX_VALUE;
        int[] solution = new int[2];

        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);

        int index1 = 0;
        int index2 = 0;
        while (index1 < arrayOne.length && index2 < arrayTwo.length) {
            // if numbers are equal, then we are done. There is no smaller distance than zero.
            if (arrayOne[index1] == arrayTwo[index2]) {
                solution[0] = arrayOne[index1];
                solution[1] = arrayTwo[index2];
                return solution;
            }

            // If numbers are not equal, keep track of the smallest number.
            int diff = Math.abs(arrayOne[index1] - arrayTwo[index2]);
            if (diff <= smallestDifference) {
                smallestDifference = diff;
                solution[0] = arrayOne[index1];
                solution[1] = arrayTwo[index2];
            }
            // continue with comparisons, moving index of first or the other array at a time.
            if (arrayOne[index1] < arrayTwo[index2]) {
                index1++;
            }
            else {
                index2++;
            }
        }
        // ^^^ while-loop Big-O analysis:
        // Time Complexity:
        // O (n + m); n is length of arrayOne,
        //            m is length of arrayTwo
        // -----------------------------------------
        // It is not O(n * m), because we are not comparing each element of one array, with each element of the other array.
        // The comparisons here are discriminate and judicious.
        // At the most, we compare an element of `arrayOne` with all elements of `arrayTwo`.
        // But once an index of either array reaches a particular position, the elements before that index are not visited again.
        // That is why time complexity is O(n + m).
        // In O(n * m) time complexity, each element of `arrayTwo` is visited n times (length of `arrayOne`),
        // and each element of `arrayOne` is visited m times (length of `arrayTwo`). That is why we get (n * m) time complexity.
        //
        // In the optimal approach,
        // We visit each element of either array only once.
        // After that the comparisons might occur with more elements of the other array; but the element itself is visited once.
        // After at-most (n + m) comparisons, we have our solution.

        return solution;
    }
    // ^^^
    // Time Complexity:
    // the time complexity of the while loop is O(n + m),
    // while time complexity of the sort of two arrays is O(n log(n) + m log(m))
    // Thus, time complexity is dominated by the sort, i.e. final time complexity is O(n log(n) + m log(m))

    /**
     * My first approach.
     *
     * Time Complexity:
     * O (n * m)
     *
     * Space Complexity:
     * O(1)
     *
     * @param arrayOne
     * @param arrayTwo
     * @return
     */
    public static int[] smallestDifferenceBruteForce(int[] arrayOne, int[] arrayTwo) {
        int difference = Integer.MAX_VALUE;
        int[] solution = new int[2];

        for (int index1 = 0; index1 < arrayOne.length; index1++) {
            for (int index2 = 0; index2 < arrayTwo.length; index2++) {
                if (Math.abs(arrayOne[index1] - arrayTwo[index2]) < difference) {
                    difference = Math.abs(arrayOne[index1] - arrayTwo[index2]);
                    solution[0] = arrayOne[index1];
                    solution[1] = arrayTwo[index2];
                }
            }
        }

        return solution;
    }
}
