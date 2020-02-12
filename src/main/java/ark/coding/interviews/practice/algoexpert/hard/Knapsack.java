/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.hard;

import ark.coding.tools.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.algoexpert.io/questions/Knapsack%20Problem
 */
public class Knapsack {

    public static void main(String[] args) {
        int[][] input = {{1,2}, {4,3}, {5,6}, {6,7}};
        List<List<Integer>> sol = knapsackProblem(input, 10);

        Utils.printList(sol);
        System.out.println();
    }

    /**
     *
     * @param itemValuesAndWeights
     * @param maxCapacity The constraint, on which to maximize the <b>value</b> of items
     * @return
     */
    public static List<List<Integer>> knapsackProblem(int[][] itemValuesAndWeights, int maxCapacity) {
        // Write your code here.
        int[][] dpMaximizeValue = new int[itemValuesAndWeights.length+1][maxCapacity+1];
        int noOfItems = itemValuesAndWeights.length;

        int items = 0;
        for (int capacity = 0; capacity <= maxCapacity; capacity++) {
            dpMaximizeValue[items][capacity] = 0;
        }

        items = 1;
        while (items <= noOfItems) {
            int itemArrayIndex = items - 1;
            int currentItemValue = itemValuesAndWeights[itemArrayIndex][0];
            int currentItemWeight = itemValuesAndWeights[itemArrayIndex][1];
            for (int capacity = 1; capacity <= maxCapacity; capacity++) {
                if (currentItemWeight > capacity) {
                    dpMaximizeValue[items][capacity] = dpMaximizeValue[items-1][capacity];
                }
                if (currentItemWeight <= capacity) {
                    int valueWithoutCurrentItemForGivenCapacity = dpMaximizeValue[items-1][capacity];
                    int valueWithCurrentItemForGivenCapacity = currentItemValue + dpMaximizeValue[items-1][capacity-currentItemWeight];
                    dpMaximizeValue[items][capacity] = Math.max(
                            valueWithoutCurrentItemForGivenCapacity,
                            valueWithCurrentItemForGivenCapacity);
                }
            }
            items++;
        }

        // Get the items that constitute the maximum value.
        List<List<Integer>> solution = new ArrayList<>();
        List<Integer> maximumValuePossible = new ArrayList<>();
        maximumValuePossible.add(dpMaximizeValue[noOfItems][maxCapacity]);
        solution.add(maximumValuePossible);

        List<Integer> itemsThatMaximizeValue = new ArrayList<>();
        int itemsIndex = noOfItems;
        int capacityIndex = maxCapacity;
        while (itemsIndex > 0) {
            if (dpMaximizeValue[itemsIndex][capacityIndex] == dpMaximizeValue[itemsIndex-1][capacityIndex]) {
                itemsIndex--;
            }
            else {
                itemsThatMaximizeValue.add(0, itemsIndex-1);

                int itemValueWeightArrayIndex = itemsIndex-1;
                capacityIndex = capacityIndex - itemValuesAndWeights[itemValueWeightArrayIndex][1];
                itemsIndex--;
            }
            if (capacityIndex <= 0) {
                break;
            }
        }
        solution.add(itemsThatMaximizeValue);

        return solution;
    }
}
