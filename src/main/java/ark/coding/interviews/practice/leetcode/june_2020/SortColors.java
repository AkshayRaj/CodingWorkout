/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.leetcode.june_2020;

import static ark.coding.tools.Utils.printArray;

/**
 * Given an array with n objects colored red, white or blue,
 * sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 *
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Follow up:
 *
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 */
public class SortColors {

    public static void main(String[] args) {
        //int[] array = {0,1,0,1,0,1,1,1,0,0,0,1,1,0};
        //int[] array = {1,1,1,1,1,1,0,0,0,0,0};
        //int[] array = {2,0,2,1,1,0};

        //groupZeroesAndOnes(array);
        //sortColors(array);

        Integer[] array = new Integer[5];
        printArray(array);
        System.out.println((int) Math.ceil(26 * 1.7));
    }

    public static void sortColors(int[] nums) {
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < nums.length; i++) {
            switch (nums[i]) {
                case 0: count0++;
                    break;
                case 1: count1++;
                    break;
                case 2: count2++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            while (count0 > 0) {
                nums[i] = 0;
                count0--;
                i++;
            }
            while (count1 > 0) {
                nums[i] = 1;
                count1--;
                i++;
            }
            while (count2 > 0) {
                nums[i] = 2;
                count2--;
                i++;
            }
        }


        int i0 = -1, i1 = -1, i2 = -1;
        for (int i = 0; i < nums.length; i++) {
            switch(nums[i]) {
                case 0:
                    i0 = i;
                    if (i0 > i2 && i2 != -1) {
                        int tmp = nums[i0];
                        nums[i0] = nums[i2];
                        nums[i2] = tmp;
                        i0 = i2;
                        i2++;
                    }
                    if (i0 > i1 && i1 != -1) {
                        int tmp = nums[i0];
                        nums[i0] = nums[i1];
                        nums[i1] = tmp;
                        i1++;
                    }
                    break;
                case 1:
                    i1 = i;
                    if (i1 > i2 && i2 != -1) {
                        int tmp = nums[i1];
                        nums[i1] = nums[i2];
                        nums[i2] = tmp;
                        i1 = i2;
                        i2++;
                    }
                    break;
                case 2:
                    if (i2 == -1) {
                        i2 = i;
                    }
                    break;
            }
            System.out.println("");
            printArray(nums);
        }
    }

    public static void groupZeroesAndOnes(int[] nums) {
        int i1 = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (i > i1 && i1 != -1) {
                    int tmp = nums[i];
                    nums[i] = nums[i1];
                    nums[i1] = tmp;
                    i1++;
                }
            }
            if (nums[i] == 1 && i1 == -1) {
                i1 = i;
            }
            System.out.println("");
            printArray(nums);
        }

        printArray(nums);
    }
}
