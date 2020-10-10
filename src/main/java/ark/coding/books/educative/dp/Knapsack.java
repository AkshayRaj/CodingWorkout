package ark.coding.books.educative.dp;

import java.util.HashSet;
import java.util.Set;

/**
 * Optimization Problem
 * - Maximize Profit
 * - Constrained on Capacity
 *
 * "Select any combination of items to maximize profit constrained on capacity".
 * Thus, this is a "Combination" problem.
 */
public class Knapsack {
    static Set<Set<Item>> itemCombinations = new HashSet<>();
    static int maxProfitGlobal = 0;
    static Integer[] memoizeConstraintSoln;

    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        memoizeConstraintSoln = new Integer[capacity+1];
        memoizeConstraintSoln[0] = 0;
        helper(getItems(profits, weights), 0, capacity);

        return memoizeConstraintSoln[capacity];
    }

    public static int helper(Item[] items, int currentIdx, int capacity) {
        // base check
        if (currentIdx >= items.length) return 0;

        // memoized solution
        if (memoizeConstraintSoln[capacity] != null) return memoizeConstraintSoln[capacity];

        // Exclude it
        int excludeItemProfit = helper(items, currentIdx + 1, capacity);

        // Include it
        int includeItemProfit = items[currentIdx].weight <= capacity
                        ? helper(items, currentIdx + 1, capacity - items[currentIdx].weight)
                        : 0;

        memoizeConstraintSoln[capacity] = Math.max(excludeItemProfit, items[currentIdx].profit + includeItemProfit);
        return memoizeConstraintSoln[capacity];
    }

//    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
//        combinatorialEnumerationWithConstraints(getItems(profits, weights), 0, capacity, 0);
//
//        return maxProfitGlobal;
//    }

    public static void combinatorialEnumerationWithConstraints(Item[] items, int currentIndex, int capacity, int profit) {
        if (capacity < 0) return; // Constraint Limitation is terminating condition
        if (currentIndex >= items.length || capacity == 0) { // Structural Limitation is termination condition
            maxProfitGlobal = Math.max(profit, maxProfitGlobal);
            return;
        }

        // exclude current item
        combinatorialEnumerationWithConstraints(
                items,
                currentIndex + 1,
                capacity,
                profit);

        // include current item
        combinatorialEnumerationWithConstraints(
                items,
                currentIndex + 1,
                capacity - items[currentIndex].weight,
                profit + items[currentIndex].profit);
    }

//    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
//        itemCombinations.add(new HashSet<>());
//
//        recursiveCombinatorialEnumeration(getItems(profits, weights), 0);
//        int maxProfit = 0;
//
//        for (Set<Item> combination : itemCombinations) {
//            int profit = 0;
//            int weight = 0;
//            for (Item item : combination) {
//                profit += item.profit;
//                weight += item.weight;
//            }
//            if (profit > maxProfit && weight <= capacity) maxProfit = profit;
//        }
//
//        return maxProfit;
//    }
    public static void recursiveCombinatorialEnumeration(Item[] items, int currentIndex) {
        if (currentIndex >= items.length) return; // Structural Limitation is terminating condition

        // Combination means include or exclude the current item
        Set<Set<Item>> temp = new HashSet<>();

        for (Set<Item> combination : itemCombinations) {
            // exclude it
            temp.add(new HashSet<>(combination));

            // include it
            combination.add(items[currentIndex]);
        }
        itemCombinations = temp;
        recursiveCombinatorialEnumeration(items, currentIndex + 1);
    }

    static class Item {
        final int profit;
        final int weight;

        Item(int profit, int weight) {
            this.profit = profit;
            this.weight = weight;
        }
    }

    private Item[] getItems(int[] profits, int[] weights) {
        Item[] items = new Item[profits.length];

        for (int idx = 0; idx < profits.length; idx++) {
            items[idx] = new Item(profits[idx], weights[idx]);
        }
        return items;
    }
}
