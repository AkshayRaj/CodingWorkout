/**
 * Created by Akshayraj on 3/25/16.
 */
package ark.coding.algorithm.sorting_algorithms;

public class Sort {
    static int[] mArray = {5, 2, 4, 6, 1, 3};

    public static void main(String[] args){
        insertSort(mArray);
    }

    /**
     * sorts the given array using insertion Sort
     * @param array the array to be sorted
     */
    public static void insertSort(int[] array){
        printArray(mArray, "insertSort Start: ");
        for(int currentIndex = 1; currentIndex < array.length; currentIndex++){

            int previousIndex = currentIndex - 1;

            while(previousIndex >= 0 && array[currentIndex] < array[previousIndex]){
                array = swapElements(currentIndex, previousIndex, array);

                currentIndex--;
                previousIndex --;
            }
        }
        printArray(mArray, "insertSort Finish: ");
    }

    /**
     * swaps the elements in currentIndex and previous Index in the given array
     * @param currentIndex points to the element in array[currentIndex]
     * @param previousIndex points to the element in array[previousIndex]
     * @param array the array whose elements are to be swapped
     * @return the array that was passed to this method with the elements swapped
     */
    private static int[] swapElements(int currentIndex, int previousIndex, int[] array) {

        int temp = array[currentIndex];
        array[currentIndex] = array[previousIndex];
        array[previousIndex] = temp;

        return array;
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
