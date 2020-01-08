/**
 * Created by Akshayraj
 */
package ark.coding.tools;

import java.util.List;

public class Utils {

    public static void printArray(char[] array){
        for (int index = 0; index  < array.length; index ++){
            if (index == array.length-1) {
                System.out.print("'" + array[index] + "'.");
            }
            else {
                System.out.print("'" + array[index] + "', ");
            }
        }
    }

    public static void printArray(int[] array){
        for (int index = 0; index  < array.length; index ++){
            System.out.print(array[index] + " ");
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

    public static void swapElements(List<Integer> list, int element1Index, int element2Index) {
        int element1 = list.get(element1Index);
        int element2 = list.get(element2Index);
        list.set(element1Index, element2);
        list.set(element2Index, element1);
    }
}
