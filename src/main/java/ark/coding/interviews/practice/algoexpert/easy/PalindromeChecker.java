/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

public class PalindromeChecker {

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
}
