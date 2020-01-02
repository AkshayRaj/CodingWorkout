/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

public class ThreeLargestNumbers {
    public static int[] findThreeLargestNumbers(int[] array) {
        // Write your code here.
        int max3 = Integer.MIN_VALUE+2; // Largest of 3
        int max2 = Integer.MIN_VALUE+1;
        int max1 = Integer.MIN_VALUE; // smallest of 3 numbers.

        for (int number : array) {
            if (number > max3) {
                // if something is bigger than max3, then it is bigger than max2, max1
                // The 3 largest numbers now are -
                // number > max3 > max2
                max1 = max2;
                max2 = max3;
                max3 = number;
            } else if (number > max2) {
                // number is smaller than max3, but bigger than max2, max1
                // 3 largest numbers are -
                // max3 > number > max2
                max1 = max2;
                max2 = number;
            } else if (number > max1) {
                // number is smaller than max3 & max2, but bigger than max1.
                // 3 largest numbers are -
                // max3 > max2 > number
                max1 = number;
            }
            // number is smaller than all max3, max2 & max1
            // 3 largest numbers are -
            // max3 > max2 > max1
        }

        int[] threeLargestNumbers = new int[3];
        threeLargestNumbers[0] = max1;
        threeLargestNumbers[1] = max2;
        threeLargestNumbers[2] = max3;
        return threeLargestNumbers;
    }
}
