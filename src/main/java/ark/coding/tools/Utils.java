/**
 * Created by Akshayraj
 */
package ark.coding.tools;

public class Utils {

    public static void printArray(int[] array){
        for (int index = 0; index  < array.length; index ++){
            System.out.print( array[index] + " ");
        }
    }

    public static void reverseArray(int[] array){
        int lastElementIndex = array.length - 1;

        for (int index = 0; index < array.length / 2; index++) {
            swapElements(array, index, lastElementIndex - index);
        }
        return;
    }

    /**
     * Swaps elements at the given two indices in the array
     * @param array in which the elements are to be swapped
     * @param element1Index index of an element; order of the element arguments does not matter.
     * @param element2Index index of the other element; order of the element arguments does not matter.
     */
    public static void swapElements(int[] array, int element1Index, int element2Index) {
        int tmp = array[element1Index];
        array[element1Index] = array[element2Index];
        array[element2Index] = tmp;
    }
}
