/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.arraysandstrings.minimum_swaps;

/**
 * Problem Statement:-
 * https://www.hackerrank.com/challenges/minimum-swaps-2/problem
 */
public class Solution {

    /**
     * Approach:-
     * https://www.hackerrank.com/challenges/minimum-swaps-2/forum/comments/643993
     *
     * 1. You pick a index
     *    a) If the value at the given index matches our expectations, we move on to the next element.
     *    b) If the value does not matches, we do the following -
     *       Get the value in the index.
     *       Swap: values between two indexes at {@code currentIndex, valueAtCurrentIndex - 1}
     *       We iteratively "correct" the values to where they belong.
     *       - Repeat 1.b until the value in the currentIndex matches our expectations
     *         (i.e. value at currentIndex == currentIndex+1)
     *
     * @param array which consists consecutive integers from 1..N, in unordered fashion
     * @return the minimum number of swaps required to sort the array in ascending/descending order.
     */
    static int minimumSwaps(int[] array) {
        int swaps = 0;
        int index = 0;
        while (index < array.length - 1) {
            int expected = index + 1;
            int actual = array[index];
            if (actual != expected) {
                int tmp = array[actual-1];
                array[actual-1] = actual;
                array[index] = tmp;
                swaps++;
            }
            else {
                index++;
            }
        }
        return swaps;
    }


    /**
     * Approach: (My original approach)
     * I came to this solution, after a "full circle" of reasoning.
     * There are few things we know about the paramters - the array cells are pigeonholed, and they expect their respective numbers;
     * 0 -> 1, 1 -> 2.
     *
     * 1. One observation about this unordered, but consecutive set of numbers is that, if they are in the expected cell,
     *    then no swap is needed.
     * 2. If the numbers are not in the right cell, then we can "trace" a cycle, by following the values in the cells.
     *    This gives us all the numbers in a single cycle.
     *    {
     *      index = 0;
     *      nextIndex = arr[index]
     *      index = nextIndex;
     *    }
     *    We count the number of different "cycles", or a disjoint set of numbers.
     *    The minimum number of swaps required is - noOfElementsInArray - noOfCycles.
     *
     * @param array which consists consecutive integers from 1..N, in unordered fashion
     * @return the minimum number of swaps required to sort the array in ascending/descending order.
     */
    static int minimumSwaps(int[] array, Object dummy) {
        int length = array.length;

        boolean[] visited = new boolean[length];

        int noOfCycles = 0;
        int index = 0;
        int elementsTraversed = 0;
        do {
            while (!visited[index]) {
                visited[index] = true;
                index = array[index] - 1;
                elementsTraversed++;
            }
            noOfCycles++;

            // Get the start of the next cycle;
            // i.e. if there exists another, find a cell which has not been visited
            int nextIndex = 0;
            while (visited[nextIndex] && nextIndex < length - 1) {
                nextIndex++;
            }
            index = nextIndex;
        } while (elementsTraversed < length);
        return length - noOfCycles;
    }
}
