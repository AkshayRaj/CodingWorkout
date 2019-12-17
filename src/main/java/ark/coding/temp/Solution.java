/**
 * Created by Akshayraj
 */
package ark.coding.temp;
import java.util.Scanner;

import static ark.coding.algorithm.sorting_algorithms.Sort.bubbleSort;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the maximumToys function below.
    static int maximumToys(int[] prices, int k) {
        bubbleSort(prices);

        int maxToys = 0;
        int costOfToys = 0;
        int index = 0;
        while (costOfToys + prices[index] <= k
                && index < prices.length) {
            costOfToys = costOfToys + prices[index];
            maxToys++;
            index++;
        }
        return maxToys;
    }

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.close();
    }
}
