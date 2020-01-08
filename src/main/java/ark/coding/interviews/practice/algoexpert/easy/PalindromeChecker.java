/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

public class PalindromeChecker {

    // Time : O(n)
    public static boolean isPalindrome(String str) {
        int length = str.length();
        int reverseIndex = length - 1;
        for (int forwardIndex = 0; forwardIndex < length; forwardIndex++) {
            if (str.charAt(forwardIndex) != str.charAt(reverseIndex)) {
                return false;
            }
            reverseIndex--;
        }
        return true;
    }

    // This solution will iterate only upto n/2 times; n is length of string
    // It is still O(n) time complexity.
    public static boolean isPalindromeOptimal(final String string) {
        char[] array = string.toCharArray();

        int low = 0;
        int high = array.length-1;
        while (low <= high) {
            if (array[low] != array[high]) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
}
