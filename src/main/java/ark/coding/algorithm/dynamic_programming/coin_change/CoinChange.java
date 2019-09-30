/**
 * Created by Akshayraj
 */
package ark.coding.algorithm.dynamic_programming.coin_change;

import ark.coding.Solution;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CoinChange implements Solution<Integer> {

    @Override
    public Integer solution(Object... args) {
        return noOfMoneyChangeCombinationsWithCoins((Set<Integer>) args[0], (Integer) args[1]);
    }

    /**
     * Calculates the different combinations, the given amount of money can be changed,
     * given the set of different coin values.
     * @param coinValues Set of different coin values available.
     * @param moneyToChange the amount of money to change.
     * @return the no of different combinations possible, to change the given amount with coins.
     */
    private Integer noOfMoneyChangeCombinationsWithCoins(Set<Integer> coinValues, Integer moneyToChange) {
        boolean isValidInput = true;

        int[] sortedCoinValues = getSortedCoinArray(coinValues);
        int[] waysToSplitN = new int[moneyToChange+1];
        Arrays.fill(waysToSplitN, 0);

        for (int i = 0; i < sortedCoinValues.length; i++) {
            int coinValue = sortedCoinValues[i];
            if (coinValue <= moneyToChange) {
                waysToSplitN[coinValue] = waysToSplitN[coinValue] + 1;
                for (int N = coinValue; N <= moneyToChange; N++) {
                    waysToSplitN[N] = waysToSplitN[N] + waysToSplitN[N - coinValue];
                }
            }
        }

        if (isValidInput) {
            int lastElementIndex = moneyToChange;
            return waysToSplitN[lastElementIndex];
        }
        else {
            return Integer.MIN_VALUE;// split not possible; invalid input
        }
    }

    /**
     * Sort the given set of coin values in ascending order
     * @param coinValues set of coin values
     * @return sorted coin values in ascending order, as an array.
     */
    private int[] getSortedCoinArray(Set<Integer> coinValues) {
        int index = 0;
        int[] coinValuesArray = new int[coinValues.size()];
        for (Integer coinValue : coinValues) {
            coinValuesArray[index] = coinValue;
            index++;
        }

        return coinValuesArray;
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();

        int moneyToChange = 4;
        Set<Integer> coinValues = getCoinValuesSet();

        int noOfWays = coinChange.solution(coinValues, moneyToChange);
        System.out.println("No of ways the given amount of money can be changed: " + noOfWays);
    }

    private static Set<Integer> getCoinValuesSet() {
        Set<Integer> coinValues =  new HashSet<>();
        coinValues.addAll(Arrays.asList(new Integer[]
                        {1, 2, 3, 4, 6}
                ));
        return coinValues;
    }
}
