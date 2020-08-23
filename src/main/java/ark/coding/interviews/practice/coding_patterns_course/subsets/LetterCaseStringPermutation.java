/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.coding_patterns_course.subsets;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * https://www.educative.io/courses/grokking-the-coding-interview/xVlKmyX542P
 */
public class LetterCaseStringPermutation {

    public static List<String> findLetterCaseStringPermutations(String str) {
        Queue<String> permutations = new LinkedBlockingQueue<>();
        if (str == null)
            return new ArrayList<>();

        permutations.offer(str);
        // process every character of the string one by one
        for (int charIndex = 0; charIndex < str.length(); charIndex++) {

            // only process characters, skip digits
            if (Character.isLetter(str.charAt(charIndex))) {

                // we will take all existing permutations and change the letter case appropriately
                int noOfPermutationsSoFar = permutations.size();
                for (int count = 0; count < noOfPermutationsSoFar; count++) {
                    char[] charPermutation = permutations.poll().toCharArray();

                    charPermutation[charIndex] = Character.toLowerCase(charPermutation[charIndex]);
                    permutations.add(String.valueOf(charPermutation));

                    charPermutation[charIndex] = Character.toUpperCase(charPermutation[charIndex]);
                    permutations.add(String.valueOf(charPermutation));
                }
            }
        }
        return new ArrayList<>(permutations);
    }

    /**
     ### Time complexity
     * - We can have 2^N permutations at the most.
     * - While processing each permutation we convert it into a character array.
     * - Therefore, the overall time complexity of the algorithm will be O(N * 2^N)
     *
     ### Space complexity
     * All the additional space used by our algorithm is for the output list.
     * We can have a total of O(2^N) permutations, the space complexity of our algorithm is O(N*2^N).
     */

    public static void main(String[] args) {
        List<String> result = LetterCaseStringPermutation.findLetterCaseStringPermutations("ad52");
        System.out.println(" String permutations are: " + result);

        result = LetterCaseStringPermutation.findLetterCaseStringPermutations("ab7c");
        System.out.println(" String permutations are: " + result);
    }
}

