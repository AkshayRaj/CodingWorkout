package ark.coding.books.interview_kickstart.sort;

import ark.coding.tools.Utils;

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
        partitionInRangeElements(nums, 1);

        int firstMissingPositiveNumber = nums.length + 1;
        findFirstMissingPositive: for (int index = 0; index < nums.length; index++) {
            int expectedValue = index + 1;
            if (nums[index] != expectedValue) {
                firstMissingPositiveNumber = expectedValue;
                break findFirstMissingPositive;
            }
        }
        return firstMissingPositiveNumber;
    }

    /**
     * Paritions the array into two -
     * 1. Consecutive elements
     * @param nums
     */
    private void partitionInRangeElements(int[] nums, int min) {
        int index = 0;
        while (index < nums.length) {
            int expectedIndex = nums[index] - min;
            if (0 <= expectedIndex & expectedIndex < nums.length //filters out of range
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
}
