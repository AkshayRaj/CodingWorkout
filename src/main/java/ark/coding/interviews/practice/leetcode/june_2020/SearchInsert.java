/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.leetcode.june_2020;

public class SearchInsert {

    /**
     * Given a sorted array and a target value, return the index if the target is found.
     * If not, return the index where it would be if it were inserted in order.
     *
     * Input: [1,3,5,6], 5
     * Output: 2
     *
     * Input: [1,3,5,6], 2
     * Output: 1
     *
     * Input: [1,3,5,6], 7
     * Output: 4
     *
     * Input: [1,3,5,6], 0
     * Output: 0
     *
     * You may assume no duplicates in the array.
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int mid = (left + right)/2;

        while (left < right) {
            if (target == nums[mid]) {
                return mid;
            }
            else if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            }
            mid = (left + right)/2;
        }
        if (target < nums[left]) {
            return left;
        }
        else if (target > nums[right]) {
            return right + 1;
        }
        return mid;
    }
}
