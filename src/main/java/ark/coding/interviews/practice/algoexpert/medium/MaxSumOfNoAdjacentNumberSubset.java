/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

/**
 * https://www.algoexpert.io/questions/Max%20Subset%20Sum%20No%20Adjacent
 * Given an array of <i>positive</i> integers, find max sum that can be obtained by a subset of non-adjacent integers
 */
public class MaxSumOfNoAdjacentNumberSubset {

    /**
     * Space : O(1) ; we use 3 additional variables to get the solution.
     * TIme  : O(n) ; we iterate thru the entire array.
     *
     * @param array
     * @return
     */
    public static int maxSubsetSumNoAdjacent(int[] array) {

        // solution will always contain max possible value at the current index in iteration.
        int solution = 0;
        if (array.length > 0) {
            solution = array[0];
        }

        int maxSumAtPreviousIndex = -1;
        if (array.length > 1) {
            maxSumAtPreviousIndex = solution;
            solution = array[1];
            solution = Math.max(maxSumAtPreviousIndex, solution);
        }

        int maxSumTwoIndicesBefore = -1;
        for (int index = 2; index < array.length; index++) {
            maxSumTwoIndicesBefore = maxSumAtPreviousIndex;
            maxSumAtPreviousIndex = solution;
            solution = Math.max(array[index] + maxSumTwoIndicesBefore, maxSumAtPreviousIndex);
        }

        return solution;
    }

    /**
     * Initial approach -
     * Space : O(n) ; optimal solution gives O(1) space
     * Time  : O(n)
     */
    public static int maxSubsetSumNoAdjacentLinearSpaceAndTime(int[] array) {
        if (array.length == 0) {
            return 0;
        }

        int[] solution = new int[array.length];

        if (array.length >= 1) {
            solution[0] = array[0];
        }
        if (array.length >= 2) {
            solution[1] = Math.max(array[1], solution[0]);
        }

        for (int index = 2; index < array.length; index++) {
            solution[index] = Math.max(array[index] + solution[index-2], solution[index-1]);
        }

        return solution[array.length-1];
    }
}

