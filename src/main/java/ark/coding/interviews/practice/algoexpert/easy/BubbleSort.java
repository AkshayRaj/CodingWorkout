/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

import ark.coding.algorithm.sorting_algorithms.Sort;
import ark.coding.tools.Utils;

/**
 * I had implemented a crude implementation of {@link Sort#bubbleSort} before.
 */
public class BubbleSort {

    /**
     * Even thought the time complexity in worst case still remains O(n^2) in this implementation,
     * there are some practical optimizations, which reduce the number of operations a bit.
     *
     * @param array unordered list of integers.
     * @return sorted array
     */
    public static int[] bubbleSort(int[] array) {

        int iterationCount = 0;
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            // After the first iteration, the highest number will be swapped to the end of array
            // After 2nd iteration, the second-highest number will be swapped to the second-last position in the array
            // ..... 3rd ........., 3rd highest ............................. to the 3rd-last position of the array
            // Hence, after i-th iteration, we can swap until (n - i)th element, i.e (n-1)-i)th index of the array
            //
            // Reducing the number of checks to (n-1)-i), does not affect the time complexity of this bubble sort implementation;
            // but for practical purposes, it reduces the redundant checks we'd be doing.
            for (int index = 0; index < array.length - 1 - iterationCount; index++) {
                int nextIndex = index + 1;
                if (array[index] > array[nextIndex]) {
                    Utils.swapElements(array, index, nextIndex);
                    isSorted = false; // a swap implies that we should iterate one more time,
                                      // to confirm the array is sorted.
                }
                // if there are no swaps in the present iteration, it means the array is sorted.
            }
            iterationCount++;
        }

        return array;
    }
}
