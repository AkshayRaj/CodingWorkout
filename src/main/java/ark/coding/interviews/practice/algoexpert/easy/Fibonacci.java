/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

public class Fibonacci {

    /**
     * Implement fibonacci solution NOT using recursion.
     * Some test cases threw {@link StackOverflowError} when using the recursion implementation.
     *
     * @param n return the fibonacci number of n.
     * @return
     */
    public static int getNthFib(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }

        int[] fibonacciSequence = new int[n];
        fibonacciSequence[0] = 0;
        fibonacciSequence[1] = 1;

        int index = 2;
        while (index < n) {
            fibonacciSequence[index] = fibonacciSequence[index-1] + fibonacciSequence[index-2];
            index++;
        }
        return fibonacciSequence[n-1];
    }
}
