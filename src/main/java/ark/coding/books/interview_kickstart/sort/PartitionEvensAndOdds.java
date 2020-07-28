/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.sort;

import static ark.coding.tools.Utils.swapElements;

public class PartitionEvensAndOdds {

    static int[] solve(int[] arr) {
        int leftIdx = 0;
        int rightIdx = arr.length-1;

        // Two pointers
        // evenPointer, Odd Pointer
        // evenPtr pulls even on the left side
        // oddPtr pulls odd on the right side
        // Ptrs start at opposite ends and travel towards each other
        // Partitioning ends when ptrs cross each other.
        while (leftIdx <= rightIdx) {
            checkForOdd: while (leftIdx < arr.length && arr[leftIdx]%2 == 0) leftIdx++;
            checkForEven: while (rightIdx >= 0 && arr[rightIdx]%2 == 1) rightIdx--;

            if (leftIdx < rightIdx
                && arr[leftIdx]%2 == 1 & arr[rightIdx]%2 == 0) {
                swapElements(arr, leftIdx, rightIdx);
            }
        }

        return arr;
    }
}
