/**
 * Created by Akshayraj
 */
package ark.coding.interviews.pramp;

public class Solution {

    public static void main(String[] args) {
        //Utils.printArray(moveZerosToEnd(new int[] {1, 10, 0, 2, 8, 3, 0, 0, 6, 4, 0, 5, 7, 0}));
        System.out.println(root(7,3));
    }

    /**
     * This function will return an approximate value of the nth root of a given number x.
     * @param x The number whose nth root is to be identified.
     * @param n Find the `n`th root of x.
     * @return An approximate value of the nth root of x, within an {@code errorMargin} of 0.001
     */
    public static double root(double x, int n) {
        if (x == 0.0) {
            return 0;
        }

        double start = 0.0;
        double end = Math.max(1,x); // if 0 < x <= 1
        double mid = (end + start) / 2;
        double errorMargin = 0.001;

        while (mid - start > errorMargin) {
            if (Math.pow(mid, n) > x) {
                end = mid;
            } else if (Math.pow(mid, n) < x) {
                start = mid;
            }
            mid = (end + start) / 2; // this iteratively reduces the window in which the nth root exists.
        }

        return mid;
    }

    /**
     * Approach:-
     * Non-zero number centric approach.
     * Keep a pointer to the next index {@code nextIndexForNonZeroNumber}, where a non-zero number is to be swapped for a zero.
     * Whenever you get a number that is not a zero, swap it with the nextIndex pointer.
     * If the value in {@code nextIndexForNonZeroNumber} pointer is not zero, then we maintain order (even after the swap),
     * and return the original array.
     *
     * Space Complexity:-
     * O(1)
     *
     * "First Instinct" Approach:-
     *  Create an empty array, all elements initialized to zero.
     *  Add items to it, whenever we encounter a non-zero number in the original array.
     *  We thus maintain order.
     *  This would result in a O(n) space complexity. With the optimal approach, we reduce the need for creating an additional array.
     *
     * @param array
     * @return
     */
    static int[] moveZerosToEnd(int[] array) {
        int nextIndexForNonZeroNumber = 0;
        for (int index = 0; index < array.length; index++) {
            if (array[index] != 0) {
                int tmp = array[nextIndexForNonZeroNumber];
                array[nextIndexForNonZeroNumber] = array[index];
                array[index] = tmp;
                nextIndexForNonZeroNumber++;
            }
        }
        return array;
    }

}
