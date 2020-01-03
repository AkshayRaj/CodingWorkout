/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.hard;

/**
 * https://www.algoexpert.io/questions/Subarray%20Sort
 *
 * Find the smallest sub-array in a given array, which if sorted, will also sort the original array.
 */
public class SubArraySort {

    /**
     * Official Approach: (& re-worked out by me)
     *
     * ****************************************************************
     * <b>Some thoughts & learnings, before I discuss the approach.</b>
     * ****************************************************************
     * If we "jump-in" & try to brute-force our way out of this problem, its gonna be a frustrating outcome.
     * We have to step back, and <i>observe</i> what the outcome of this problem looks like.
     *
     * If we try to find the first and last index of this sub-array by just iterating over the original array,
     * and doing some heuristics with conditionals along the way, we can either get stuck, or worse go with the wrong approach.
     *
     * Take a step back. Breathe. Now observe the pattern.
     * ****************************************************************
     * This sub-array can exist anywhere :
     * 1. start of the array to somewhere before the end
     * 2. from somewhere in the middle to the end of the array
     * 3. or, in-between.
     * 4. Or, it can coincide with the whole array.
     *
     * For now, lets assume its in the middle of the array, not touching the edges of the original array.
     * If we think about it, the smallest & biggest numbers of this sub-array are definitely out of place in the original array.
     * And, if there are more than 2 numbers in the sub-array, then all these numbers are in-between the smallest & biggest numbers.
     *
     * So, we break down our algorithm into two parts:-
     * 1. Find the biggest & smallest numbers of the sub-array
     *    (We used some heuristics to get the smallest & biggest numbers of the sub-array)
     * 2. Find <b>WHERE</b> the smallest & biggest numbers belong in the original array.
     *    That is our answer.
     *
     * Now, if the array is sorted, then we don't need to find a sub-array.
     * But if it isn't then a sub-array <i>must</i> exist. (The whole array is a sub-array of itself)
     * We just want to find the bounds of that sub-array.
     *
     * @param array
     * @return
     */
    public static int[] subarraySort(int[] array) {
        // 1. Find the smallest & biggest numbers of the sub-array
        boolean isArraySortedSoFar = true; // this flag is important for our heuristics to work.
        int biggestNumberInSubArray = Integer.MIN_VALUE;
        int smallestNumberInSubArray = Integer.MAX_VALUE;
        for (int index = 0; index < array.length - 1; index++) {
            if (isArraySortedSoFar) {
                if (array[index] > array[index + 1]) {
                    isArraySortedSoFar = false;
                    biggestNumberInSubArray = array[index];
                    smallestNumberInSubArray = array[index+1];
                    index++; // this will jump the index to index+2, after considering another increment in the for-loop.
                }
            }
            // once we identify that an ordering violation exists, i.e. the original array is not sorted,
            // we want to keep track of the biggest & smallest number of the sub-array.
            else {
                // The rest of the array could be sorted, with the rest of numbers bigger than `biggestNumberInSubArray`.
                // But if not, i.e. rest of the array is not sorted, & bigger numbers than `biggestNumberInSubArray` exist,
                // then those numbers belong to the sub-array.
                // So, we update the `biggestNumberInSubArray`
                if (array[index] > array[index + 1]) {
                    if (array[index] > biggestNumberInSubArray) {
                        biggestNumberInSubArray = array[index];
                    }
                }
                // if there is a smaller number than `smallestNumberInSubArray`, after recognizing the sorting violation,
                // then that number belongs in the sub-array.
                // So, update the `smallestNumberInSubArray`
                if (array[index] < smallestNumberInSubArray){
                    smallestNumberInSubArray = array[index];
                }
            }
        }

        // 2. Find "WHERE" the smallest & biggest numbers should be in the original array
        // By this point, we have biggest & smallest numbers in the subArray.
        // Now we find the right position for them in the original array, if the original array is not sorted.
        int firstIndex = -1;
        int endIndex = -1;
        if (!isArraySortedSoFar) {
            // we check until array.length-2 for this.
            // So we want to add another check for the last element also.
            if (array[array.length - 1] < smallestNumberInSubArray) {
                smallestNumberInSubArray = array[array.length-1];
            }
            for (int index = 0; index < array.length; index++) {
                if (smallestNumberInSubArray < array[index]) {
                    firstIndex = index;
                    break;
                }
            }
            for (int index = array.length-1; index >= 0; index--) {
                if (biggestNumberInSubArray > array[index]) {
                    endIndex = index;
                    break;
                }
            }
        }

        return new int[]{firstIndex, endIndex};
    }
}
