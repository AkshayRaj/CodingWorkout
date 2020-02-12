/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import java.util.Arrays;

/**
 * https://www.algoexpert.io/questions/Number%20Of%20Ways%20To%20Make%20Change
 */
class WaysToChangeFromDenominations {

    /**
     * Find number of ways to make change of a given amount, from the set of denominations
     * @param amountToChange the amount to change using the denominations
     * @param denoms set of denominations
     * @return
     */
    public static int numberOfWaysToMakeChange(int amountToChange, int[] denoms) {
        // Write your code here.
//        if (n == 0) {
//            return 1; // I don't agree that there is 1 way to make a 0 from given coins. Thats absurd.
//        }
        if (denoms.length == 0) {
            return 0;
        }

        int[] denominations = Arrays.copyOf(denoms, denoms.length);
        Arrays.sort(denominations);

        int[] solution = new int[amountToChange + 1];
        for (int denomIndex = 0; denomIndex < denominations.length; denomIndex++) {
            int denomValue = denominations[denomIndex];

            if (amountToChange >= denomValue) {
                int currentAmount = denomValue;
                solution[currentAmount] = solution[currentAmount] + 1;
                while (currentAmount <= amountToChange) {
                    solution[currentAmount] = solution[currentAmount] + solution[currentAmount - denomValue];
                    currentAmount++;
                }
            }
        }

        return solution[amountToChange];
    }
    // Space : O(n)
    // Time  : O(nd)
    // n - the amount to change
    // d - number of different denominations
}
