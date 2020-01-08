/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

public class LongestPalindromicSubstring {
    public static String longestPalindromicSubstring(String str) {
        int length = str.length();

        if (str.length() <= 1) {
            return str;
        }

        String solution = str.substring(0,1); // first character. A single character is a palindrome.
        for (int index = 1; index < str.length(); index++) {
            /**
             * At each index, we should check if middle of the palindrome is a single letter, or two letters.
             * That means, we have to iterate 2 times to find longest palindromes.
             * Time Complexity : O(2 * n) ~> O(n)
             */
            String newPalindrome = "";

            // 1. Check for even length palindromes; middle two chars are same
            int palindromeStart = index - 1;
            int palindromeEnd = index;
            while (palindromeStart >= 0 && palindromeEnd <= length - 1) {
                if (str.charAt(palindromeStart) == str.charAt(palindromeEnd)) {
                    newPalindrome = str.substring(palindromeStart, palindromeEnd+1);
                    palindromeStart--;
                    palindromeEnd++;
                }
                else {
                    break;
                }
            }
            if (solution.length() < newPalindrome.length()) {
                solution = newPalindrome;
            }

            // 2. Check for odd length palindromes; adjacent chars of middle-one are same
            if ((index + 1) <= (length - 1)) {
                palindromeStart = index - 1;
                palindromeEnd = index + 1;
                while (palindromeStart >= 0 && palindromeEnd <= length - 1) {
                    if (str.charAt(palindromeStart) == str.charAt(palindromeEnd)) {
                        newPalindrome = str.substring(palindromeStart, palindromeEnd+1);
                        palindromeStart--;
                        palindromeEnd++;
                    }
                    else {
                        break;
                    }
                }
                if (solution.length() < newPalindrome.length()) {
                    solution = newPalindrome;
                }
            }

        }
        return solution;
    }
    // Time  : O(n^2) ; n is length of string
    // Space : O(1) - we don't use additional memory that varies with input.
    //              - just few additional pointers are used, that means constant space overhead.

    public static void main(String[] args) {

    }
}
