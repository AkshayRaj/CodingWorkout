/**
 * Created by Akshayraj
 */
package ark.coding.algorithm.dynamic_programming.substrings_distinct_palindrome;

import ark.coding.Solution;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static ark.coding.interviews.practice.PracticeInterview.isPalindrome;

public class DistinctPalindromeSubstrings implements Solution<Integer> {

    /**
     * Set to store distinct palindrome substrings.
     */
    Set<String> palindromes = new HashSet<>();

    public static void main(String[] args) {
        DistinctPalindromeSubstrings answer = new DistinctPalindromeSubstrings();

        String testString1 = "abaaa";
        String testString2 = "abcd";
        String testString3 = "kadak";
        String testString4 = "malayalam";
        int noOfPalindromeSubstrings = answer.solution(testString4);

        System.out.println("No of distinct palindrome substrings: " + noOfPalindromeSubstrings);
    }

    @Override
    public Integer solution(Object... args) {
        return noOfDistinctPalindromeStringsUsingIterativeMethod((String) args[0]);
    }

    /**
     * Iterative solution
     * Uses {@link #palindromes} to store the results of palindromic sub-strings.
     */
    private Integer noOfDistinctPalindromeStringsUsingIterativeMethod(String string) {
        for (int start = 0; start < string.length(); start++) {
            for (int end = start; end < string.length(); end++) {
                Optional<String> result = getPalindromeString(string.substring(start, end));
                result.ifPresent(palindromes::add);
            }
        }
        
        return palindromes.size();
    }

    /**
     * Checks if the string is a palindrome or not, and returns a result based on it.
     * @param string the string to check
     * @return An Optional containing the string, if its a palindrome;
     *         Empty otherwise.
     */
    private Optional<String> getPalindromeString(String string) {
        if (isPalindrome(string)){
            return Optional.of(string);
        }
        return Optional.empty();
    }
}
