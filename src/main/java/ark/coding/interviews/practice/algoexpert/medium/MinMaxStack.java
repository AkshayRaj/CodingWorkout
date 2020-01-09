/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.algoexpert.io/questions/Min%20Max%20Stack%20Construction
 *
 * Implement the following methods as exercise.
 * The methods independently should have O(1) time & space complexity respectively.
 */
public class MinMaxStack {
    private int[] array = new int[10];
    private int head = -1;

    // We could use min, max variables to track min/max numbers in the stack.
    // However, after a pop we need to iterate O(n) to find min/max
    //
    // The MinMaxPair represents the min/max of the stack after a number has been pushed to the stack.
    // We keep a list of such pairs, which map to the element at the head of the stack.
    private List<MinMaxPair> minMaxPairs = new ArrayList<>();

    /**
     * Exercise: Implement this method
     * @return
     */
    public int peek() {
        // Write your code here.
        return array[head];
    }

    /**
     * Exercise: Implement this method
     * @return
     */
    public int pop() {
        // Write your code here.
        int popped = array[head];
        minMaxPairs.remove(head);
        head--;
        if (head <= array.length/4 && array.length > 20) {
            // this means size of array is way more than required, and halving it would make the size to 10.
            array = Arrays.copyOfRange(array,0, array.length/2);
        }


        return popped;
    }

    /**
     * Exercise: Implement this method
     * @param number
     */
    public void push(Integer number) {
        // Write your code here.
        if (isStackFull()) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        head++;
        array[head] = number;

        //================================
        // Update MinMaxPair
        if (head == 0) {
            minMaxPairs.add(new MinMaxPair(number, number));
        }
        if (head > 0) {
            MinMaxPair previousMinMax = minMaxPairs.get(head-1);
            int newMin = Math.min(number, previousMinMax.min);
            int newMax = Math.max(number, previousMinMax.max);
            minMaxPairs.add(new MinMaxPair(newMin, newMax));
        }
    }

    /**
     * Exercise: Implement this method
     * @return
     */
    public int getMin() {
        // Write your code here.
        return minMaxPairs.get(head).min;
    }

    /**
     * Exercise: Implement this method
     * @return
     */
    public int getMax() {
        // Write your code here.
        return minMaxPairs.get(head).max;
    }

    private boolean isStackFull() {
        return head >= (array.length - 1)
                && head >= 0;
    }

    /**
     * Helper class.
     * Represents the min & max numbers in the stack after a certain number is pushed.
     */
    static class MinMaxPair {
        final int min;
        final int max;

        MinMaxPair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
