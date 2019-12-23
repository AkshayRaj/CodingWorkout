/**
 * Created by Akshayraj on 3/25/16.
 */
package ark.coding.algorithm.sorting_algorithms;

import java.util.Arrays;

import static ark.coding.tools.Utils.swapElements;

public class Sort {
    static int[] mArray = {5, 2, 4, 6, 1, 3, 10, 7, 9, 8, 12, 13, 14,16, 11, 15};

    public static void main(String[] args){
        //insertSort(mArray);
        //printArray(mArray, "insertSort Finish: ");

        mergeSort(mArray);
        printArray(mArray, "mergeSort: ");
    }

    public static void mergeSort(int[] array) {
        int arrayLength = array.length;
        int lastElementIndex = arrayLength - 1;

        /**
         * Merge sub-arrays in bottom up manner.
         * Iteratively merge left-right pair of sub-arrays -
         * Beginning from the smallest size of 1, to create sorted sub-arrays of size 2,
         * then merge sub-arrays of size 2 to create sorted sub-arrays of size 4,
         * and so on.
        **/
        /**
         * {@code subArraySplitSize} is used to keep track of the size of the sub-arrays after merges.
         * After every merge, the size of the sub-arrays increases by a factor of 2.
         *
         * If the size of the original {@code array} is not an exponent of 2 (i.e. 2,4,8,32 etc),
         * then the merges might get skewed near the start of the array.
         * Thus {@code subArraySplitSize} ranges from 1 to {@code arrayLength} - 1, in those scenarios.
         *
         * <b>Note:</b> Since we start identifying left-right sub-array pairs from the left,
         * our algorithm can get skewed sub-arrays on the left.
         * In other words, as we approach indices towards the end of array,
         * we might not find corresponding right sub-array for a left sub-array.
         * This can easily be corrected, by modifying the inner <i>for</i> loop.
         */
        for (int currentSubArraySize = 1; currentSubArraySize <= arrayLength - 1;
             currentSubArraySize =  2 * currentSubArraySize) {
            System.out.print("\n=========================================");
            System.out.print("\nCurrent size of sub-arrays: " + currentSubArraySize);
            System.out.print("\n=========================================");

            // Pick starting point of different
            // sub-arrays of current size
            /**
             * DIVIDE & CONQUER -
             * Identify left-right sub-array <b>PAIRS</b> (divide),
             * & merge them (conquer)
             *
             * Dividing involves identifying the following indices -
             * 1. index where the left sub-array starts
             * 2. mid-point = (leftStart + rightEnd)/2;
             *    OR mid-point = (leftStart + subArraySize) - 1
             * 3. index where the right aub-array ends.
             *    Right sub-array ends at (2 * subArraySize) from the start of the left sub-array
             * <i>Note:- </i>We do not need to actually create new smaller array objects.
             */
            int leftSubArrayStartIndex = 0;
            for (int nextLeftSubArrayStartIndex = leftSubArrayStartIndex; nextLeftSubArrayStartIndex < lastElementIndex;
                 nextLeftSubArrayStartIndex = nextLeftSubArrayStartIndex + (2 * currentSubArraySize)) {
                // `mid` is ending point of left sub-array.
                // (`mid` + 1) is starting point of right sub-array
                int mid = Math.min((nextLeftSubArrayStartIndex + currentSubArraySize) - 1,
                                   lastElementIndex);// Math.min takes care of the edge case,
                                                     // where `mid` goes beyond the last index of the array

                int rightSubArrayEndIndex = Math.min(
                        nextLeftSubArrayStartIndex + (2 * currentSubArraySize) - 1,
                        arrayLength - 1);// Math.min takes care of the edge case,
                                         // where `rightSubArrayEndIndex` goes beyond the last index of the array

                // Merge Sub-arrays arr[left_start...mid]
                // & arr[mid+1...right_end]
                mergeSubArrays(array, nextLeftSubArrayStartIndex, mid, rightSubArrayEndIndex);
            }
            System.out.println("\n");
        }
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
     * @param leftStartIndex starting index of the left sub-array
     * @param middleIndex the index that splits the left & right sub-array.
     *                    Usually this is [{@code leftIndex} + {@code rightIndex} / 2]
     * @param rightEndIndex ending index of the right sub-array
     */
    private static void mergeSubArrays(int[] array, int leftStartIndex, int middleIndex, int rightEndIndex) {
        int leftSubArraySize = middleIndex - leftStartIndex + 1; // left sub-array from l -> m;
                                                            // add 1 to accommodate for inclusion of the element at mth index.
        int rightSubArraySize = rightEndIndex - middleIndex; // right sub-array from m+1 --> r.

        int[] leftSubArray = Arrays.copyOfRange(array, leftStartIndex, middleIndex + 1);
        int[] rightSubArray = Arrays.copyOfRange(array, middleIndex + 1, rightEndIndex + 1);

        int leftSubArrayPointer = 0;
        int rightSubArrayPointer = 0;
        int mainArrayPointer = leftStartIndex;
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

        /**
         * ------------- Printing logic -------------
         */
        System.out.println("\nLeft sub-array:  [" + leftStartIndex + " - " + middleIndex + "]:");
        System.out.print("[");
        for (int index = leftStartIndex; index <= middleIndex; index++) {
            System.out.print(array[index]);
            if (index != middleIndex) {
                System.out.print(", ");
            }
        }
        System.out.print("] ");

        System.out.println("\nRight sub-array: [" + (middleIndex + 1) + " - " + rightEndIndex +"]: ");
        System.out.print("[");
        int rightSubArrayStart = Math.min(middleIndex + 1, rightEndIndex);
        for (int index = rightSubArrayStart; index <= rightEndIndex; index++) {
            System.out.print(array[index]);
            if (index != rightEndIndex) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
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
