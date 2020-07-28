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

    public static void printArray(Integer[] array){
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
        if (element1Index == element2Index) {
            return;
        }
        int tmp = array[element1Index];
        array[element1Index] = array[element2Index];
        array[element2Index] = tmp;
    }

    public static void swapElements(List<Integer> list, int element1Index, int element2Index) {
        if (element1Index == element2Index) {
            return;
        }
        int element1 = list.get(element1Index);
        int element2 = list.get(element2Index);
        list.set(element1Index, element2);
        list.set(element2Index, element1);
    }

    public static void printList(List list) {
        for (int obj = 0; obj < list.size(); obj++) {
            System.out.print(list.get(obj));
            if (obj < list.size()-1) {
                System.out.print("-");
            }
        }
    }

    public static void printListInReverse(List list) {
        for (int obj = list.size()-1; obj >= 0; obj--) {
            System.out.print(list.get(obj));
            if (obj > 0) {
                System.out.print("-");
            }
        }
    }

    public static void printBits(long number) {
        byte count = 64; // long is 64 bits

        StringBuilder string = new StringBuilder("Number [%d] is ");
        if (Byte.MIN_VALUE <= number & number <= Byte.MAX_VALUE ) {
            string.append("a 'byte'");
            count = 8; // byte is 8 bits
        }
        else if (Short.MIN_VALUE <= number & number <= Short.MAX_VALUE ) {
            string.append("a 'short'");
            count = 16; // short is 16 bits
        }
        else if (Integer.MIN_VALUE <= number & number <= Integer.MAX_VALUE ) {
            string.append("an 'int'");
            count = 32; // int is 32 bits
        }
        System.out.println(String.format(string.toString(), number));

        int printBitNo = count-1;
        while (printBitNo >= 0) {
            if (printBitNo < 10) {
                System.out.print(printBitNo + "  ");
            }
            else {
                System.out.print(printBitNo + " ");
            }
            printBitNo--;
        }

        long tmp = number;
        StringBuilder bits = new StringBuilder();
        while (count > 0) {
            bits.append("  " + (tmp & 1));
            tmp >>>= 1;
            count--;
        }
        System.out.println("\n" + bits.reverse().toString());
    }

    public static void printAllBits(long number) {
        byte count = 64; // long is 64 bits
        int printBitNo = count-1;
        while (printBitNo >= 0) {
            if (printBitNo < 10) {
                System.out.print(printBitNo + "  ");
            }
            else {
                System.out.print(printBitNo + " ");
            }
            printBitNo--;
        }

        long tmp = number;
        StringBuilder bits = new StringBuilder();
        while (count > 0) {
            bits.append("  " + (tmp & 1));
            tmp >>>= 1;
            count--;
        }
        System.out.println("\n" + bits.reverse().toString());

    }

    /**
     * Erases the rightmost set bit of the 64-bit integer.
     *
     * A bitwise operation between the number & another number, 1 less than the given number
     * erases the rightmost bit.
     *
     * @param number
     */
    public static long eraseRightMostSetBit(long number) {
        if (number == 0) {
            System.out.println("Number is " + number + ". No set bit to erase");
            return number;
        }
        return number & (number-1);
    }

    public static void swapElements(char[] array, int idx1, int idx2) {
        if (idx1 == idx2) {
            return;
        }
        char tmp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = tmp;
    }

    public static boolean isPalindrome(String string) {
        if (string.isEmpty()) {
            return false;
        }

        int fIdx = 0; // forward index
        int rIdx = string.length()-1; // reverse index
        while (fIdx < rIdx) {
            if (string.charAt(fIdx) != string.charAt(rIdx)) {
                return false;
            }
            fIdx++;
            rIdx--;
        }
        return true;
    }
}
