/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import java.util.Arrays;

/**
 * https://www.algoexpert.io/questions/Min%20Number%20Of%20Coins%20For%20Change
 */
class MinCoinsToMakeChange {

    /**
     * Given a list of positive numbered denominations,
     * Find the minimum number number of coins required to make change for {@param n} amount of money.
     *
     * @param n Amount of money, which we want to change
     * @param denoms list of coin denominations available to make change
     * @return minimum number of coins, if it is possible to make change with given list of coin denominations.
     *         {@literal -1} if it is not possible to make change.
     */
    public static int minNumberOfCoinsForChange(int n, int[] denoms) {
        // Write your code here.
        int[] denominations = Arrays.copyOf(denoms, denoms.length);
        Arrays.sort(denominations);

        int nextHighestDenomIndex = denoms.length-1;
        int minCoins = n+1; // at the least there will be coins with "1" denomination, which should result in n no of min coins.

        outerloop:
        while (nextHighestDenomIndex >= 0) {
            int denomIndex = nextHighestDenomIndex;
            int remainingAmount = n;
            int coins = 0;
            while (remainingAmount > 0 && denomIndex >= 0) {
                int highestDenomination = denominations[denomIndex];

                coins = coins + remainingAmount / highestDenomination;
                remainingAmount = remainingAmount % highestDenomination;

                denomIndex--;
            }
            if (coins < minCoins && remainingAmount == 0) {
                minCoins = coins;
            }
            nextHighestDenomIndex--;
        }

        return minCoins == n+1
                ? -1 // this means min coins is not possible.
                : minCoins;
    }
    // Space : O(1)
    // Time  : O(d^2)
    // d - number of denominations given in the list.
}

