/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

/**
 * https://www.algoexpert.io/questions/Kadane's%20Algorithm
 *
 * Given a <b>non empty</b> input array of integers, find maximum possible sum from a sub-array of consecutive integers.
 * sub-array should also be <b>non empty</b>
 */
class KadanesAlgorithm {

    /**
     * the key is to realize that we want to keep adding numbers in the array, unless the sum drops below 0,
     * after which, we simply reset the maxSumCalculatedSoFar ({@code tmpSum} in the solution)
     *
     * The only exception is the first element in the array, which can be negative, but we still initialize
     * the maxSum to it. After that, we keep looking for  non-negative sums.
     *
     * @param array
     * @return
     */
    public static int kadanesAlgorithm(int[] array) {
        // Write your code here.
        int maxSum = array[0]; // array is always non empty; so there is at least 1 element present
        int tmpSum = maxSum;

        for (int index = 1; index < array.length; index++) {
            tmpSum = tmpSum + array[index];
            if (tmpSum > maxSum) {
                maxSum = tmpSum;
            }
            if (tmpSum < 0) {
                tmpSum = 0; // sum of consecutive integers so far has gone below 0, so we aren't gonna find max sum
                            // by including the numbers that led to this. Thus we reset tmpSum.
                // if tmpSum >= 0, then it allows us to pass thru the intermittent negative integers.
            }
        }

        return maxSum;
    }
    // Space : O(1)
    // Time  : O(n)

    public static int kadanesAlgorithmQuadraticTime(int[] array) {
        // Write your code here.
        int maxSum = Integer.MIN_VALUE;
        subArrayStartLoop:
        for (int index = 0; index < array.length; index++) {
            int sum = array[index];
            if (sum > maxSum) {
                maxSum = sum;
            }

            subArrayEndLoop:
            for (int end = index + 1; end < array.length; end++) {
                sum = sum + array[end];
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }

        }

        return maxSum;
    }
    // Space : O(1)
    // Time  : O(n^2)
}
