package ark.coding.books.interview_kickstart.sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-1683-9573-4-86
 *
 * A 2-sum can be found in O(n) - linear time - if the array is already sorted using the two pointer technique
 * For the 3-sum, we have an outer loop that goes from 0 ~> n-1, and the inner loop is now a 2-sum problem,
 * which can be solved in O(n)
 *
 * Thus, the time complexity of this problem is O(n^2)
 */
public class ThreeSum {
    static String[] findZeroSum(int[] arr) {
        Arrays.sort(arr);

        // using set, so that triplets are not duplicated, even if duplicate elements exist in original array
        Set<String> solution = new HashSet<>();
        for (int idx = 0; idx < arr.length; idx++) {
            int forwardPtr = idx+1;
            int backwardPtr = arr.length-1;
            findDuplet: while (forwardPtr < backwardPtr) {
                if (arr[forwardPtr] + arr[backwardPtr] + arr[idx] == 0) {
                    solution.add(arr[idx] + "," + arr[forwardPtr] + "," + arr[backwardPtr]);
                    forwardPtr++;
                    backwardPtr--;
                }
                else {
                    if (arr[forwardPtr] + arr[backwardPtr] + arr[idx] < 0) {
                        forwardPtr++;
                    } else {
                        backwardPtr--;
                    }
                }
            }
        }
        return solution.toArray(new String[]{});
    }
}
