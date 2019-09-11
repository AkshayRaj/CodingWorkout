/**
 * Created by Akshayraj
 */
package ark.coding.algorithm.dynamic_programming.cutting_a_rod;

import ark.coding.Solution;

import java.util.Arrays;
import java.util.List;

public class RodCutter implements Solution<Integer> {

    public static void main(String[] args) {
        RodCutter rodCutter = new RodCutter();
        List<Integer> prices = Arrays.asList(new Integer[] {1, 5, 8, 9, 10, 17, 17, 20});
        int length = prices.size();
        System.out.println("Max cost that can be extracted is: " + rodCutter.solution(prices, length));
    }

    /**
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

        int maxCost = -1;
        for (int lengthOfCut = 1; lengthOfCut <= rodLength; lengthOfCut++) {
            int lengthOfRemainingCut = rodLength - lengthOfCut;
            maxCost = Math.max(
                    maxCost,
                    getPriceOfCut(prices, lengthOfCut) + maxCostOfRod(prices, lengthOfRemainingCut));
            System.out.println("Max cost for rod of length " + rodLength + ","
                    + " is " + maxCost);
        }
        return maxCost;
    }

    private Integer getPriceOfCut(final List<Integer> prices, final int cutLength) {
        return prices.get(cutLength - 1);
    }

    @Override
    public Integer solution(Object... args) {
        return maxCostOfRod((List<Integer>) args[0], (Integer) args[1]);
    }
}
