/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

/**
 * Given a sorted array, and a target integer, check if the target integer exists in the array.
 * If it does, return the index of the integer.
 * If it doesn't then, return {@literal -1}
 */
public class BinarySearch {

    public static int binarySearch(int[] array, int target) {
        // low, high, mid are indexes of the array
        if (array.length == 0) {
            return -1;
        }

        int low = 0;
        int high = array.length -1;
        int mid = (low + high)/2;

        while (low <= high) {
            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] < target) {
                low = mid + 1;
            }
            else if (array[mid] > target) {
                high = mid-1;
            }
            mid = (low + high)/2;
        }

        return -1;
    }
}
