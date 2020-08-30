package ark.coding.books.interview_kickstart.sort;

import ark.coding.tools.Utils;

/**
 * Selection sort, alongwith {@link CyclicSort} does the least number of swaps to get a sorted array
 * While {@link SelectionSort} is "index oriented" algorithm, {@link CyclicSort} is "value oriented" one.
 * - {@link SelectionSort} picks an index, and then tries to find the value in the rest of the array that should go
 *   into that index.
 * - On the contrary, {@link CyclicSort} picks an index, and then tries to find the correct index in the rest of the array
 *   for the value in the current index.
 */
public class SelectionSort {

    public static void selectionSort(int[] array) {
        // sort the array
        for (int idxUnderFocus = 0; idxUnderFocus < array.length; idxUnderFocus++) {
            int idxOfMin = idxUnderFocus;
            for (int ptr = idxUnderFocus+1; ptr < array.length; ptr++) {
                if (array[ptr] < array[idxOfMin]) idxOfMin = ptr;
            }
            Utils.swapElements(array, idxUnderFocus, idxOfMin);
         }
    }

    /**
     * https://leetcode.com/problems/couples-holding-hands/
     *
     * N couples sit in 2N seats arranged in a row and want to hold hands.
     * We want to know the minimum number of swaps so that every couple is sitting side by side.
     * A swap consists of choosing any two people, then they stand up and switch seats.
     *
     * The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order,
     * the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
     *
     * The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.
     *
     * ARK NOTE:
     * Any problem that asks for "min number of swaps" points to the technique used in selection sort.
     *
     * @param row
     * @return
     */
    public int minSwapsCouples_LinearTimeLinearSpace(int[] row) {
        // Use an auxiliary array, that is a reverse index map
        // the elements go from 0 ~> row.length-1
        int[] rowElementReverseMap = new int[row.length];
        for (int idx = 0; idx < row.length; idx++) {
            int element = row[idx];
            rowElementReverseMap[element] = idx;
        }

        int noOfSwaps = 0;
        for (int idx = 0; idx < row.length-1; idx = idx+2) {
            int currentElement = row[idx];
            int actualPartner = row[idx+1];
            int expectedPartner = currentElement^1;
            if (actualPartner != expectedPartner) {
                Utils.swapElements(row, idx+1, rowElementReverseMap[expectedPartner]);
                Utils.swapElements(rowElementReverseMap, expectedPartner, actualPartner);
                noOfSwaps++;
            }
        }

        return noOfSwaps;
    }

    public int minSwapsCouples_QuadraticTimeLinearSpace(int[] row) {
        int noOfSwaps = 0;
        for (int idx = 0; idx < row.length-1; idx = idx+2) {
            int currentElement = row[idx];
            int expectedPartner = currentElement^1;
            if (row[idx+1] != expectedPartner) {

                // `findPartner` loop takes additional O(n) time for each unhappy couple,
                // thus making Time: O(n^2), Space O(1)
                findPartner: for (int ptr = idx + 2; ptr < row.length; ptr++) {
                    if (row[ptr] == expectedPartner) {
                        Utils.swapElements(row, ptr, idx+1);
                        noOfSwaps++;
                        break findPartner;
                    }
                }
            }
        }

        return noOfSwaps;
    }

}
