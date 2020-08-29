package ark.coding.books.interview_kickstart.sort;

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
    public int minSwapsCouples(int[] row) {
        int noOfSwaps = 0;

        return noOfSwaps;
    }
}
