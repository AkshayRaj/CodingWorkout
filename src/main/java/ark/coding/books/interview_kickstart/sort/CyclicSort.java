package ark.coding.books.interview_kickstart.sort;

import ark.coding.tools.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Cyclic Sort uses the technique of finding cycles in an array.
 * One of the characteristics of using this pattern is that it works very well on consecutive numbers.
 *
 * By extension, this also works great work on Arithmetic & Geometric Progressions.
 * In AP & GP, the key (or in case of array, the index) can be found in O(1) time,
 * if you know the Value in the <K,V> pair
 */
public class CyclicSort {

    public static void cyclicSort(int[] nums) {
        for (int index = 0; index < nums.length; index++) {
            int noOfSmallerElementsThanCurrentElement;

            initiateCycle: do {
                noOfSmallerElementsThanCurrentElement = 0;

                findCorrectIndexForCurrentElement:
                for (int pointer = 0; pointer < nums.length; pointer++) {
                    if (nums[pointer] < nums[index]) {
                        noOfSmallerElementsThanCurrentElement++;
                    }
                }
                // At the end of `findCorrectIndexForCurrentElement` loop,
                // we know where current element should be placed in the array
                Utils.swapElements(nums, index, noOfSmallerElementsThanCurrentElement);
            } while (index != noOfSmallerElementsThanCurrentElement);
        }
    }

    /**
     * https://leetcode.com/problems/first-missing-positive/
     *
     * These category of problems can be identified by the following properties of the input array.
     * <b>Consecutive Numbers</b>
     * <b>Arithmetic/Geometric Progression</b>
     * <b>Sequence based off a formula, Knowing initial number gives the rest of the elements</b>
     *
     * Omkar's thoughts on this -
     * https://discordapp.com/channels/686708831467995260/730943380925841452/746221219933716550
     *
     * Solution of this problem can be derived by identifying an aberration in consecutive numbers.
     * Note: We do not know if the input is an arithmetic or geometric progression -
     * but thinking about consecutive numbers helps us attack the problem & derive solution faster.
     * https://leetcode.com/problems/first-missing-positive/solution/518189
     *
     * @param nums array containing unsorted numbers
     * @return the first missing positive number in the array
     */
    public int firstMissingPositive(int[] nums) {
        int min = 1;
        int commonDifference = 1;
        partitionInRangeElements(nums, min, commonDifference);

        int firstMissingPositiveNumber = min + (nums.length * commonDifference);
        findFirstMissingPositive: for (int index = 0; index < nums.length; index++) {
            int expectedValue = min + (index * commonDifference);
            if (nums[index] != expectedValue) {
                firstMissingPositiveNumber = expectedValue;
                break findFirstMissingPositive;
            }
        }
        return firstMissingPositiveNumber;
    }

    /**
     * Partitions the array into two -
     * 1. Consecutive elements in a range, starting from {@code min}
     * 2. Duplicates & out of range elements
     *
     * This method uses a dialect of cyclic sort, where each number in the array is put into
     * its expected location during an iteration.
     *
     * @param nums
     * @param min start of the arithmetic progression
     * @param commonDifference of the arithmetic progression
     */
    private void partitionInRangeElements(int[] nums, int min, int commonDifference) {
        int index = 0;
        while (index < nums.length) {
            int expectedIndex = (nums[index] - min)/commonDifference;
            boolean validTermInProgression = (nums[index] - min)%commonDifference == 0;
            if (validTermInProgression
                && 0 <= expectedIndex & expectedIndex < nums.length //filters out of range
                && nums[expectedIndex] != nums[index]) { //filters duplicates
                Utils.swapElements(nums, index, expectedIndex);
            }
            // Cycle is complete; move to next index
            else {
                index++;
            }
        }
    }

    /**
     * Find the missing number in the Arithmetic/Geometric progression
     *
     * @param min min element of the set
     * @param nums set containing the elements that are related by Arithmetic/Geometric Progression
     *             The set is scrambled (not sorted)
     * @param commonDifference Common difference
     * @return
     */
    private int smallestMissingNumberInArithmeticProgression(int min, int[] nums, int commonDifference) {
        // Since we are trying to find the "missing element" in the set,
        // the actual total number of elements is 1 more than the size of the `nums` array.
        // We start with assumption that missing number is the last number in the set.
        int totalIncludingMissingElement = nums.length + 1;
        int missingNumber = min + (totalIncludingMissingElement * commonDifference);

        for (int index = 0; index < nums.length; index++) {
            int correctLocation = (nums[index] - min)/commonDifference;
            cyclicSort: while (index != correctLocation) {
                if (isCorrectLocationValid(correctLocation, nums)) {
                    Utils.swapElements(nums, index, correctLocation);
                    correctLocation = (nums[index] - min) / commonDifference;
                }
                else {
                    continue;
                }
            }
        }
        // At the end of the cyclic sort of the progression, all elements will be placed at the correct location.
        // Except One.
        findAberration: for (int index = 0; index < nums.length; index++) {
            int expectedElement = min + (index * commonDifference);
            if (nums[index] != expectedElement) {
                missingNumber = expectedElement;
                break;
            }
        }
        // If all terms from 1 ~> n exist in the array, then the smallest missing number is obviously the n+1th term
        // which we had set as the default.

        return missingNumber;
    }

    private boolean isCorrectLocationValid(int correctLocation, int[] nums) {
        return 0 <= correctLocation & correctLocation < nums.length;
    }

    /**
     * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
     *
     * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
     * Find all the elements of [1, n] inclusive that do not appear in this array.
     * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // partition the array
        int index = 0;
        while (index < nums.length) {
            int expectedIndex = nums[index] - 1;
            if (0 <= expectedIndex & expectedIndex < nums.length
                && nums[expectedIndex] != nums[index]) {
                Utils.swapElements(nums, index, expectedIndex);
            }
            else {
                index++;
            }
        }

        // Assuming the array is sorted, & duplicates are moved towards end of array
        // if index+1 != nums[index], add that element to the list
        List<Integer> missingNumbers = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            int expectedValue = i+1;
            if (value != expectedValue) {
                missingNumbers.add(expectedValue);
            }
        }
        return missingNumbers;
    }

    /**
     * https://leetcode.com/problems/missing-number/
     * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        //1. do cyclic sort on array
        //2. missing element will not be placed at the right index
        //3. Look for this index
        int index = 0;
        while (index < nums.length) {
            int element = nums[index];
            int expectedIndex = element;
            if (0 <= expectedIndex & expectedIndex < nums.length
                && nums[index] != nums[expectedIndex]) {
                Utils.swapElements(nums, index, expectedIndex);
            }
            // Cycle is complete, move onto the next index.
            else {
                index++;
            }
        }

        int missingElement = nums.length;
        findMissingElement: for (int idx = 0; idx < nums.length; idx++) {
            if (nums[idx] != idx) {
                missingElement = idx;
                break findMissingElement;
            }
        }
        return missingElement;
    }

    /**
     * https://leetcode.com/problems/single-number/
     *
     * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     *
     * Note:
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     *
     * The Bit Manipulation approach works for this problem
     * {@link ark.coding.books.interview_kickstart.bit_manipulation.XOR#singleNumber(int[])}
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        // Try to do sort in O(n) time & O(1) space

        // This approach does not work - Impossible to sort in O(n) if its not an arithmetic progression


        // Assume the array is sorted
        // Then we check for consecutive occurrences of any number
        // Since, we iterate from 1 ~> penultimate element, if singleNumber is not found in the iteration,
        // then it exists in the last index.
        int singleNumber = nums[nums.length-1];
        int index = 0;
        findSingleNumber: while (index < nums.length-1) {
            if (nums[index] != nums[index+1]) {
                singleNumber = nums[index];
                break findSingleNumber;
            }
            else {
                index = index+2;
            }
        }
        return singleNumber;
    }

    /**
     * https://leetcode.com/problems/find-the-duplicate-number/
     *
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
     * prove that at least one duplicate number must exist.
     * Assume that there is only one duplicate number, find the duplicate one.
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int duplicate = 0;

        int idx = 0;
        cyclicSort: while (idx < nums.length) {
            int expectedIdx = nums[idx];
            if (nums[idx] == nums[expectedIdx]) {
                duplicate = nums[idx];
                break cyclicSort;
            }

            if (nums[idx] != nums[expectedIdx]) {
                Utils.swapElements(nums, expectedIdx, idx);
            }
            else {
                idx++;
            }
        }

        return duplicate;
    }

    /**
     * https://leetcode.com/problems/set-mismatch/
     *
     * The set S originally contains numbers from 1 to n.
     * But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set,
     * which results in repetition of one number and loss of another number.
     *
     * Given an array nums representing the data status of this set after the error.
     * Your task is to firstly find the number occurs twice and then find the number that is missing.
     * Return them in the form of an array.
     *
     * Approach:
     *  The input is an arithmetic progression. So we use cyclic sort swaps.
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int[] errorNums = new int[2];

        int idx = 0;
        cyclicSort: while (idx < nums.length) {
            int expectedIdx = nums[idx] - 1;
            if (expectedIdx != idx && nums[expectedIdx] == nums[idx]) {
                // there is a duplicate, as this number already exists at the expectedIdx
                // and the current idx != expectedIdx
                errorNums[0] = nums[expectedIdx];
                idx++;
                continue cyclicSort;
            }

            if (nums[expectedIdx] != nums[idx]) {
                Utils.swapElements(nums, idx, expectedIdx);
            }
            else {
                idx++;
            }
        }
        // at the end of this while loop, all numbers are located at the right position
        // except the duplicated number, or rather the index that contains the duplicated number

        findMissingNumber: for (idx = 0; idx < nums.length; idx++) {
            if (nums[idx]-1 != idx) {
                errorNums[1] = idx+1;
                break findMissingNumber;
            }
        }

        return errorNums;
    }
}
