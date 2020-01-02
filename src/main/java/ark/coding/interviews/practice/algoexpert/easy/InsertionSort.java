/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

import ark.coding.tools.Utils;

/**
 * Insertion Sort :
 *
 * "Insert" an element from the outside into the sorted array.
 *  - Since we are inserting a single element into an already sorted array,
 *    we find the position in the sorted array where the new element needs to be placed
 *
 * 1. Divide the array into two parts -
 *    a) the Sorted sub-array
 *       This sub-array is always sorted.
 *       It might not contain the largest or the smallest element of the original array at all times,
 *       but whatever the elements it has at any given point, they are stored in sorted order
 *
 *    b) sub-array comprising the rest of the array
 *       This sub-array has all the elements of the original array, which are not in the sorted sub-array,
 *       in the original order.
 *
 * In the worst case, we'd have to perform `N` insertions into the sorted sub-array;
 * which would then trigger atmost another `N` swaps to trickle down the inserted element.
 *
 * Thus, Time Complexity is O(n^2)
 *
 * Space Complexity: O(1)
 * We perform in-place swaps, and the segregation of the sorted & unsorted sub-arrays by use of indexes.
 * So no additional space is consumed during the execution of this algorithm.
 */
public class InsertionSort {

    public static int[] insertionSort(int[] array) {
        final int firstElementOfSortedSubArray = 0; // this is always 0.
        int lastElementOfSortedSubArray = 0;
        for (int index = 0; index < array.length; index++) {
            lastElementOfSortedSubArray = index;

            // check if the "inserted" element is in the right place in the sorted sub-array
            int sortedSubArrayIndex = lastElementOfSortedSubArray;
            while (sortedSubArrayIndex > 0) {
                if (array[sortedSubArrayIndex] < array[sortedSubArrayIndex - 1]) {
                    Utils.swapElements(array, sortedSubArrayIndex, sortedSubArrayIndex-1);
                }
                sortedSubArrayIndex--;
            }
        }
        return array;
    }
}
