/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

import ark.coding.tools.Utils;

/**
 * "Select" the smallest number from the unsorted sub-array, and then add it to the sorted sub-array.
 *
 * Selection sort is kinda like the opposite of {@link InsertionSort}.
 * Insert sort takes the first element of the unsorted sub-array, i.e. whatever number the "outside" gives to the
 * sorted array, and then tries to find the right place for it in the sorted sub-array.
 *
 * Selection already knows what it wants: It wants the smallest number from the "outside", i.e the unsorted sub-array.
 * Once is gets that, it inserts that number at the end of the sorted sub-array.
 * Do this ^^^ n times, and you have a sorted array.
 *
 * This algorithm is called "{@link SelectionSort}" as we "select" the number we want to append at the end of the
 * sorted sub-array.
 *
 * Time Complexity:
 * Worst Case: O(n^2)
 *
 * Space Complexity:
 * O(1)
 */
public class SelectionSort {
    public static int[] selectionSort(int[] array) {
        for (int sortedSubArrayNextElementIndex = 0;
             sortedSubArrayNextElementIndex < array.length - 1;
             sortedSubArrayNextElementIndex++) {

            // find the smallest number in the rest of the array; i.e. the unsorted sub-array
            int nextSmallestNumber = array[sortedSubArrayNextElementIndex];
            int nextSmallestNumberIndex = sortedSubArrayNextElementIndex;
            for (int unSortedSubArrayIndex = sortedSubArrayNextElementIndex;
                 unSortedSubArrayIndex < array.length;
                 unSortedSubArrayIndex++) {
                if (array[unSortedSubArrayIndex] < nextSmallestNumber) {
                    nextSmallestNumber = array[unSortedSubArrayIndex];
                    nextSmallestNumberIndex = unSortedSubArrayIndex;
                }
            }
            Utils.swapElements(array, sortedSubArrayNextElementIndex, nextSmallestNumberIndex);
        }

        return array;
    }
}
