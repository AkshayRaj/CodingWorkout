/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import java.util.ArrayList;
import java.util.List;

import static ark.coding.tools.Utils.swapElements;

public class MoveElementToEnd {

    /**
     * Time Complexity:
     * O(n); but messes with the ordering of the other numbers.
     *
     * @param array
     * @param toMove the element (integer) to move towards the end of the array.
     * @return
     */
    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        // Write your code here.
        int start = 0;
        int end = array.size() - 1;
        while (start < end) {
            while (end >= 0) {
                if (array.get(end).equals(toMove)) {
                    end--;
                }
                else {
                    break;
                }
            }
            while (start <= array.size() - 1) {
                if (!array.get(start).equals(toMove)) {
                    start++;
                }
                else {
                    break;
                }
            }

            // `start` & `end` can cross each other within the while loop,
            // so we got to check it before the swap
            if (start < end) {
                if (start <= array.size() - 1 && end >= 0) {
                    swapElements(array, start, end);
                }
            }
        }
        return array;
    }

    /**
     * Better Approach:
     * This solution is inspired from {@link ark.coding.interviews.pramp.Solution#moveZerosToEnd(int[])}
     * Time Complexity is still O(n), but this solution also maintains the order of the other elements
     *
     * @param array
     * @param toMove
     * @return
     */
    public static List<Integer> moveElementToEndMaintainsOrder(List<Integer> array, int toMove) {
        int nextIndexForNonZeroNumber = 0;
        for (int index = 0; index < array.size(); index++) {
            if (array.get(index) != toMove) {
                swapElements(array,nextIndexForNonZeroNumber, index);
                nextIndexForNonZeroNumber++;
            }
        }
        return array;
    }


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        for (Integer integer : moveElementToEnd(list, 3)) {
            System.out.print(integer + " ");
        }
    }
}

