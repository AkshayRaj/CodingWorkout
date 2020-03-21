/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.coding_patterns_course.dynamic_programming;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Knapsack {

    public int solveKnapsack(int[] profits, int[] weights, int maxCapacity) {
        int noOfItems = weights.length;
        int lastItem = noOfItems - 1;

        int[][] profitCapacityMatrix = new int[noOfItems][maxCapacity];

        // if knapsack has no capacity, profit is ZERO
        for (int item = 0; item <= noOfItems; item++) {
            profitCapacityMatrix[item][0] = 0;
        }

        // when no items are selected, profit is ZERO
        for (int capacity = 0; capacity <= maxCapacity; capacity++) {
            profitCapacityMatrix[0][capacity] = 0;
        }

        return recursive(profits, weights, maxCapacity, lastItem);
    }

    private int recursive(int[] profits, int[] weights, int remainingCapacity, int itemNumber) {



        return -1;
    }
}
