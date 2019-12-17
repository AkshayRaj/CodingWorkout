/**
 * Created by Akshayraj on 3/25/16.
 */
package ark.coding.algorithm.sorting_algorithms;

import static ark.coding.tools.Utils.swapElements;

public class Sort {
    static int[] mArray = {5, 2, 4, 6, 1, 3};

    public static void main(String[] args){
        insertSort(mArray);
        printArray(mArray, "insertSort Finish: ");
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
