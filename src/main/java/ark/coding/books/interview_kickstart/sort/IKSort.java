/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.sort;

import static ark.coding.tools.Utils.printArray;
import static ark.coding.tools.Utils.swapElements;

public class IKSort {

    public static void main(String[] args) {
        int[] array = new int[]{111,12,39,14,157,16,217,183,19,2};

        //System.out.println(getKthSmallestElement(array, 5));
        printArray(array); System.out.println();
        quickSort(array);
        printArray(array);
    }

    private static int getKthSmallestElement(int[] array, int k) {
        if (1 > k || k > array.length) {
            throw new IllegalArgumentException(String.format("K[%d] should be from 1 ~> %d", k, array.length));
        }

        return partition(array, 0, array.length - 1, k);
    }

    private static int partition(int[] array, int low, int high, int k) {
        int pivot = ((int)Math.random()/(high-low+1)) + low;
        swapElements(array, pivot, low);

        int index = low+1;
        int partitionIndex = low;
        while (index <= high) {
            if (array[index] <= array[low]) {
                partitionIndex++;
                swapElements(array, index, partitionIndex);
            }
            index++;
        }
        swapElements(array, low, partitionIndex);
        if (partitionIndex == k-1) {
            return array[partitionIndex];
        }

        if (k-1 < partitionIndex) {
            // discard right sub-array
            return partition(array, low, partitionIndex-1, k);
        }
        else { // partitionIndex-1 < k
            // discard left sub-array
            return partition(array, partitionIndex+1, high, k);
        }
    }

    private static void quickSort(int[] array) {
        lomutosPartition(array, 0, array.length-1);
        //hoaresPartition(array, 0, array.length-1);
    }

    /**
     * Lomuto's partition maintains the order of lower numbers than pivot, if index goes from 0 ~> n
     * It will maintain the order of bigger elements, if index goes from n ~> 0
     *
     * In other words, it will maintain order of the partition whose index it tracks.
     * For a left ~> right traversal, we maintain the {@code smallerIdx}, so this will maintain order of smaller elements.
     *
     * @param subArray The subarray bounded by the low & high
     * @param low start of the sub-array; inclusive.
     * @param high end of the sub-array; inclusive.
     */
    private static void lomutosPartition(int[] subArray, int low, int high) {
        if (low >= high) {
            return;
        }
        // Place the pivot at the start of the sub-array.
        int pivotIdx = ((int)(Math.random()*Integer.MAX_VALUE)%(high-low+1)) + low;
        swapElements(subArray, low, pivotIdx);
        int pivot = subArray[low];

        // Lomuto's Partition:
        // Two pointers start from same end, but in successive positions & go in same direction at different speeds.
        // 1. The first pointer iterates over the array - so its faster
        // 2. The second pointer is used to keep track of elements smaller than pivot.
        //    All elements to left of this pointer are smaller than pivot
        //    Thus, the second pointer is slower.
        int smallerIdx = low; // keeps track of rightest element less than pivot
        for (int index = low+1; index <= high; index++) {
            if (subArray[index] < pivot) {
                smallerIdx++;
                swapElements(subArray, index, smallerIdx);
            }
        }
        // at the end of `partitionLoop`, smallerIdx will be pointing to the partitionIdx.
        // Partition Index is the index that partitions the array into two parts.
        int partitionIdx = smallerIdx;
        swapElements(subArray, low, partitionIdx);

        // sort left & right sub-arrays
        lomutosPartition(subArray, low, smallerIdx-1);
        lomutosPartition(subArray, smallerIdx+1, high);
    }

    private static void hoaresPartition(int[] subArray, int low, int high) {
        if (low >= high) {
            return;
        }

        // Place the pivot to the start of the sub-array.
        int pivotIdx = ((int)(Math.random()*Integer.MAX_VALUE)%(high-low+1)) + low;
        swapElements(subArray, low, pivotIdx);
        int pivot = subArray[low];

        // Hoare's Partition :
        // Two pointers go in opposite direction, and start from opposite ends towards each other.
        // Pointers stop after they "cross" each other (not when they meet, they have to cross)
        int leftIndex = low+1; // ~>> left to right
        int rightIndex = high; // <<~ right to left
        partitionLoop: while (leftIndex <= rightIndex) {
            checkForBiggerNumber:  while (leftIndex <= high && subArray[leftIndex] < pivot)
                                            leftIndex++;
            checkForSmallerNumber: while (rightIndex > low && subArray[rightIndex] >= pivot)
                                            rightIndex--;

            // swap elements in wrong positions.
            // At this point leftIdx & rightIdx _could_ have crossed each other. So check for that.
            if (leftIndex < rightIndex) {
                swapElements(subArray, leftIndex, rightIndex);
            }
        }
        // at the end of `partitionLoop`, rightIdx will be pointing to the partitionIdx.
        // Partition Index is the index that partitions the array into two parts.
        int partitionIndex = rightIndex;
        swapElements(subArray, low, partitionIndex);

        // sort left & right sub-arrays
        hoaresPartition(subArray, low, partitionIndex-1);
        hoaresPartition(subArray, partitionIndex+1, high);
    }
}
