/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart;

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
        hoaresPartition(array, 0, array.length-1);
    }

    private static void lomutosPartition(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = ((int)Math.random()%(high-low+1)) + low;
        swapElements(array, pivot, low); // store pivot at lowIndex

        int index = low+1; // iterates from low+1 to end of array
        int partitionIndex = low; // pointer keeps track of elements smaller than pivot
        while (index <= high) {
            if (array[index] <= array[low]) {
                partitionIndex++;
                swapElements(array, index, partitionIndex);
            }
            index++;
        }
        swapElements(array, low, partitionIndex);

        // sort left & right sub-arrays
        lomutosPartition(array, low, partitionIndex-1);
        lomutosPartition(array, partitionIndex+1, high);
    }

    private static void hoaresPartition(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }

        // Place the pivot to the start of the sub-array.
        int pivotIdx = ((int)(Math.random()*Integer.MAX_VALUE)%(high-low+1)) + low;
        swapElements(array, low, pivotIdx);
        int pivot = array[low];

        // Hoare's Partition :
        // Two pointers go in opposite direction.
        // Pointers stop after they "cross" each other (not when they meet, they have to cross)
        int leftIndex = low+1; // ~>> left to right
        int rightIndex = high; // <<~ right to left
        partitionLoop: while (leftIndex <= rightIndex) {
            checkForBiggerNumber:  while (leftIndex <= high && array[leftIndex] < pivot)
                                            leftIndex++;
            checkForSmallerNumber: while (rightIndex > low && array[rightIndex] >= pivot)
                                            rightIndex--;

            // swap elements in wrong positions.
            // At this point leftIdx & rightIdx _could_ have crossed each other. So check for that.
            if (leftIndex < rightIndex) {
                swapElements(array, leftIndex, rightIndex);
            }
        }

        // at the end of `partitionLoop`, rightIdx will be pointing to the partitionIdx.
        // Partition Index is the index that partitions the array into two parts.
        int partitionIndex = rightIndex;
        swapElements(array, low, partitionIndex);

        // sort left & right sub-arrays
        hoaresPartition(array, low, partitionIndex-1);
        hoaresPartition(array, partitionIndex+1, high);
    }

}
