/**
 * Created by Akshayraj
 */
package ark.coding.algorithm.dynamic_programming.cutting_a_rod;

import ark.coding.Solution;

import java.util.Arrays;
import java.util.List;

public class RodCutter implements Solution<Integer> {
    /**
     * An array, which stores corresponding maxCost for a given rod length;
     * where index (i.e. index+1) corresponds to length of the rod,
     * and the value in array[index] denotes the maxCost possible for that length.
     */
    private static int[] maxCostComputations;
    public static void main(String[] args) {
        RodCutter rodCutter = new RodCutter();
        List<Integer> prices = Arrays.asList(new Integer[] {1, 5, 8, 9, 10, 17, 17, 20});
        int length = prices.size();
        maxCostComputations = rodCutter.getMaxCostStore(length);
        System.out.println("Max cost that can be extracted is: " + rodCutter.solution(prices, length));
        for (int index = 1; index < maxCostComputations.length; index++) {
            System.out.println("Max cost for length [" + index + "] is: " + maxCostComputations[index]);
        }
    }

    @Override
    public Integer solution(Object... args) {
        //return maxCostOfRod((List<Integer>) args[0], (Integer) args[1]);
        return iterativeMaxProfitFromRod((List<Integer>) args[0], (Integer) args[1]);
    }

    /**
     * Iterative solution.
     * Its important that we first calculate the maxCost of lower length rods,
     * and then calculate maxCost of lengthier rods.
     */
    private Integer iterativeMaxProfitFromRod(final List<Integer> prices, final Integer rodLength) {
        maxCostComputations = new int[rodLength+1];
        Arrays.fill(maxCostComputations, Integer.MIN_VALUE);

        // start calculating max cost of smaller length rods.
        for (int length = 1; length <= rodLength; length++) {
            maxCostComputations[length] = prices.get(length-1);

            // for each given length, calculate "which" splits lead to max cost.
            // since we are calculating splits, we start this loop with a split of 2 pieces,
            // with lengths n-1 and 1 respectively.
            for (int lengthOfPiece1 = length-1; lengthOfPiece1 >= 1; lengthOfPiece1--) {
                int lengthOfPiece2 = length - lengthOfPiece1;
                maxCostComputations[length] = Math.max(
                        maxCostComputations[length],
                        maxCostComputations[lengthOfPiece1] + maxCostComputations[lengthOfPiece2]);
            }
        }

        return maxCostComputations[rodLength];
    }

    /**
     * Recursive solution.
     *
     * Find the max price the rod of a given length can be sold at, given the prices for lesser length rods.
     * @param prices list of prices for different lengths of the rod.
     *               (contains prices for lesser lengths of the rod)
     * @param rodLength length of the rod to sell
     * @return max price possible.
     */
    private Integer maxCostOfRod(final List<Integer> prices, final Integer rodLength) {
        if (rodLength <= 0) {
            // Dynamic programming problems always have a halting condition
            // Recursive methods should always have a halting condition to avoid Stack Overflow.
            return 0; // cost of a rod with zero or negative length is zero (does not exist.)
        }
        int maxCost = maxCostComputations[rodLength-1]; // memoization

        // if cost is not already computed, then do it now.
        if (maxCost == Integer.MIN_VALUE) {
            System.out.println("Length of rod: " + rodLength);
            for (int lengthOfCut = 1; lengthOfCut <= rodLength; lengthOfCut++) {
                int lengthOfRemainingCut = rodLength - lengthOfCut;
                maxCost = Math.max(
                        maxCost,
                        getPriceOfCut(prices, lengthOfCut) + maxCostOfRod(prices, lengthOfRemainingCut));
            }
            // store the computation for later use. (memoization)
            maxCostComputations[rodLength-1] = maxCost;
        }
        return maxCost;
    }

    private Integer getPriceOfCut(final List<Integer> prices, final int cutLength) {
        return prices.get(cutLength - 1);
    }

    private int[] getMaxCostStore(int length) {
        int[] storeForMaxCost = new int[length];
        Arrays.fill(storeForMaxCost, Integer.MIN_VALUE);
        return storeForMaxCost;
    }
}
