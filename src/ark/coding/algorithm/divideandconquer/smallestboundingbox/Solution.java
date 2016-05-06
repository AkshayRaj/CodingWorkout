/**
 * Created by Akshayraj
 */
package ark.coding.algorithm.divideandconquer.smallestboundingbox;

import ark.coding.tools.Utils;

public class Solution {
    static int noOfComparisons;

    public static MinMaxPair getMinMax(int array[], int minIndex, int maxIndex) {
        System.out.print("\n"+"arrayLow[" + minIndex + "]: " + array[minIndex] + ", arrayHigh[" + maxIndex + "]: " + array[maxIndex]);
        MinMaxPair result = new MinMaxPair();
        MinMaxPair leftArrayMinMax = new MinMaxPair();
        MinMaxPair rightArrayMinMax = new MinMaxPair();

        // if there is only one element
        if (minIndex == maxIndex) {
            result.max = array[minIndex];
            result.min = array[minIndex];
            return result;
        }

        // if there are two elements
        if (maxIndex == minIndex + 1) {
            if (array[minIndex] > array[maxIndex]) {
                result.max = array[minIndex];
                result.min = array[maxIndex];
                noOfComparisons++;
            } else {
                result.max = array[maxIndex];
                result.min = array[minIndex];
            }
            return result;
        }

        noOfComparisons++;
        // if there are more than 2 elements
        //----------DIVIDE----------
        int mid = (minIndex + maxIndex) / 2;
        leftArrayMinMax = getMinMax(array, minIndex, mid);
        rightArrayMinMax = getMinMax(array, mid + 1, maxIndex);

        //------COMBINE---------------------
        if (leftArrayMinMax.min < rightArrayMinMax.min) {
            result.min = leftArrayMinMax.min;
            noOfComparisons++;
        } else {
            result.min = rightArrayMinMax.min;
        }

        if (leftArrayMinMax.max > rightArrayMinMax.max) {
            result.max = leftArrayMinMax.max;
            noOfComparisons++;
        } else {
            result.max = rightArrayMinMax.max;
        }

        return result;
    }

    public static void main(String[] args) {
        int a1[] = {15,12, 11, 9,8,6,4,3, 2 };//descending order
        //Utils.reverseArray(a1); //ascending
        Utils.printArray(a1);
        MinMaxPair result = getMinMax(a1, 0, a1.length - 1);

        System.out.println("\nN: " + a1.length + " comparisons: " + noOfComparisons);
        System.out.println("Min: " + result.min);
        System.out.println("Max: " + result.max);
    }
}
