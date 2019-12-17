/**
 * Created by Akshayraj on 3/25/16.
 */
package ark.coding.algorithm.sorting_algorithms;

import java.util.Arrays;

import static ark.coding.tools.Utils.swapElements;

public class Sort {
    static int[] mArray = {5, 2, 4, 6, 1, 3};

    public static void main(String[] args){
        insertSort(mArray);
        printArray(mArray, "insertSort Finish: ");
    }

    public static void mergeSort(int[] array) {
        
    }

    /**
     * Merges the two <b>sorted</b> sub-arrays (of {@code array}) at -
     * 1. From {@code leftIndex} to {@code middleIndex}
     * 2. From {@code middleIndex} + 1 to {@code rightIndex}
     *
     * The two sub-arrays are two parts of another sub-array of the original larger array,
     * from {@code leftIndex} to {@code rightIndex}, inclusive.
     * The left sub-array & right sub-array do not overlap, and are a contiguous block of the sub-array array.
     *
     * Left & right sub-array are already sorted.
     * The resultant merge should be a sorted sub-array from {@code leftIndex} to {@code rightIndex}, inclusive.
     *
     * @param array The original large array, whose sub-arrays have to be merged.
     * @param leftIndex starting index of the left sub-array
     * @param middleIndex the index that splits the left & right sub-array.
     *                    Usually this is [{@code leftIndex} + {@code rightIndex} / 2]
     * @param rightIndex ending index of the right sub-array
     */
    private static void mergeSubArrays(int[] array, int leftIndex, int middleIndex, int rightIndex) {
        int leftSubArraySize = middleIndex - leftIndex + 1; // left sub-array from l -> m;
                                                            // add 1 to accommodate for inclusion of the element at mth index.
        int rightSubArraySize = rightIndex - middleIndex; // right sub-array from m+1 --> r.

        int[] leftSubArray = Arrays.copyOfRange(array, leftIndex, middleIndex+1);
        int[] rightSubArray = Arrays.copyOfRange(array, middleIndex+1, rightIndex+1);

        int leftSubArrayPointer = 0;
        int rightSubArrayPointer = 0;
        int mainArrayPointer = leftIndex;
        while (leftSubArrayPointer < leftSubArraySize
                && rightSubArrayPointer < rightSubArraySize) {
            if (leftSubArray[leftSubArrayPointer] <= rightSubArray[rightSubArrayPointer]) {
                array[mainArrayPointer] = leftSubArray[leftSubArrayPointer];
                leftSubArrayPointer++;
            }
            else {
                array[mainArrayPointer] = rightSubArray[rightSubArrayPointer];
                rightSubArrayPointer++;
            }
            mainArrayPointer++;
        }

        // copy the remaining elements of either sub-arrays.
        // At this point, only 1 sub-array has been copied completely; The other one is not.
        while (leftSubArrayPointer < leftSubArraySize) {
            array[mainArrayPointer] = leftSubArray[leftSubArrayPointer];
            leftSubArrayPointer++;
            mainArrayPointer++;
        }
        // OR ->
        while (rightSubArrayPointer < rightSubArraySize) {
            array[mainArrayPointer] = rightSubArray[rightSubArrayPointer];
            rightSubArrayPointer++;
            mainArrayPointer++;
        }
    }


    /**
     * Sorts the given array using bubble Sort
     * @param array the array to be sorted
     */
    public static void bubbleSort(int[] array) {
        int length = array.length;

        for (int iterationCount = 0; iterationCount < length; iterationCount++) {
            for (int index = 0; index < length - 1; index++) {
                if (array[index] > array[index + 1]) {
                    swapElements(array, index, index + 1);
                }
            }
        }
    }

    /**
     * Sorts the given array using insertion Sort
     * @param array the array to be sorted
     */
    public static void insertSort(int[] array){
        printArray(mArray, "insertSort Start: ");
        for(int currentIndex = 1; currentIndex < array.length; currentIndex++){

            int previousIndex = currentIndex - 1;

            while(previousIndex >= 0 && array[currentIndex] < array[previousIndex]) {
                swapElements(array, currentIndex, previousIndex);

                currentIndex--;
                previousIndex --;
            }
        }
    }

    /**
     * prints the elements in array at the current state
     * @param array array whose elements are to be printed
     * @param sortAlgorithm optional; the sortAlgorithm used on the array
     */
    private static void printArray(int[] array, String sortAlgorithm){
        System.out.println("\nPrinting Array: " + sortAlgorithm);
        for(int index = 0; index < array.length; index++){
            System.out.print(array[index] + " ");
        }
    }
}
