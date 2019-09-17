/**
 * Created by Akshayraj
 */
package ark.coding.algorithm.dynamic_programming.substrings_distinct_palindrome;

import ark.coding.Solution;

import java.util.HashSet;
import java.util.Set;

import static ark.coding.interviews.practice.PracticeInterview.isPalindrome;

public class DistinctPalindromeSubstrings implements Solution<Integer> {

    Set<String> palindromes = new HashSet<>();
    public static void main(String[] args) {
        DistinctPalindromeSubstrings answer = new DistinctPalindromeSubstrings();

        String testString = "abaaa";
        String testString2 = "abcd";
        int noOfPalindromeSubstrings = answer.solution(testString2);

        System.out.println("No of distinct palindrome substrings: " + noOfPalindromeSubstrings);
    }

    @Override
    public Integer solution(Object... args) {
        return noOfDistinctPalindromeStrings((String) args[0]);
    }

    private Integer noOfDistinctPalindromeStrings(String string) {
        for (int start = 0; start < string.length(); start++) {
            for (int end = string.length() - 1; end >= 0; end--) {
                String result = getPalindromeString(string.substring(start, end));
                if (result != null) {
                    if (result != "") {
                        palindromes.add(result);
                    }
                }
            }
        }



        return palindromes.size();
    }

    private String getPalindromeString(String string) {
        if (string.length() <= 1) {
            return string;
        }

        if (isPalindrome(string)){
            return string;
        }
        return null;
    }
}
