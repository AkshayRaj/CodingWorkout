/**
 * Created by Akshayraj
 */
package ark.coding.algorithm.divideandconquer.smallestboundingbox;

import ark.coding.tools.Utils;

public class Solution {
    static int noOfComparisons;

    public static MinMaxPair getMinMax(int array[], int minIndex, int maxIndex) {
        System.out.print("\n"+"arrayLow[" + minIndex + "]: " + array[minIndex] + ", arrayHigh[" + maxIndex + "]: " + array[maxIndex]);
        MinMaxPair resultMinMax = new MinMaxPair();
        MinMaxPair leftArrayMinMax = new MinMaxPair();
        MinMaxPair rightArrayMinMax = new MinMaxPair();

        // if there is only one element
        if (minIndex == maxIndex) {
            resultMinMax.max = array[minIndex];
            resultMinMax.min = array[minIndex];
            return resultMinMax;
        }

        // if there are two elements
        if (maxIndex == minIndex + 1) {
            if (array[minIndex] > array[maxIndex]) {
                resultMinMax.max = array[minIndex];
                resultMinMax.min = array[maxIndex];
                noOfComparisons++;
            } else {
                resultMinMax.max = array[maxIndex];
                resultMinMax.min = array[minIndex];
            }
            return resultMinMax;
        }

        noOfComparisons++;
        // if there are more than 2 elements
        //----------DIVIDE----------
        int mid = (minIndex + maxIndex) / 2;
        leftArrayMinMax = getMinMax(array, minIndex, mid);
        rightArrayMinMax = getMinMax(array, mid + 1, maxIndex);

        //------COMBINE---------------------
        if (leftArrayMinMax.min < rightArrayMinMax.min) {
            resultMinMax.min = leftArrayMinMax.min;
            noOfComparisons++;
        } else {
            resultMinMax.min = rightArrayMinMax.min;
        }

        if (leftArrayMinMax.max > rightArrayMinMax.max) {
            resultMinMax.max = leftArrayMinMax.max;
            noOfComparisons++;
        } else {
            resultMinMax.max = rightArrayMinMax.max;
        }

        return resultMinMax;
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
