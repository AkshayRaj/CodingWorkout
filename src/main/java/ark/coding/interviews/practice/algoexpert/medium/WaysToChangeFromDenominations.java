/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import java.util.Arrays;

/**
 * https://www.algoexpert.io/questions/Number%20Of%20Ways%20To%20Make%20Change
 */
class WaysToChangeFromDenominations {
    public static int numberOfWaysToMakeChange(int n, int[] denoms) {
        // Write your code here.
//        if (n == 0) {
//            return 1; // I don't agree that there is 1 way to make a 0 from given coins. Thats absurd.
//        }
        if (denoms.length == 0) {
            return 0;
        }

        int[] denominations = Arrays.copyOf(denoms, denoms.length);
        Arrays.sort(denominations);

        int[] solution = new int[n + 1];
        for (int denomIndex = 0; denomIndex < denominations.length; denomIndex++) {
            int denomValue = denominations[denomIndex];

            if (n >= denomValue) {
                int solutionIndex = denomValue;
                solution[solutionIndex] = solution[solutionIndex] + 1;
                while (solutionIndex <= n) {
                    solution[solutionIndex] = solution[solutionIndex] + solution[solutionIndex - denomValue];
                    solutionIndex++;
                }
            }
        }

        return solution[n];
    }
    // Space : O(n)
    // Time  : O(nd)
    // n - the amount to change
    // d - number of different denominations
}
