/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.recursion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TargetString {

    public static void main(String[] args) {
        //System.out.println(evaluatePermutation(""));
        System.out.println(Arrays.toString(generate_all_expressions("123412", 36)));
    }

    static String[] generate_all_expressions(String string, long target) {
        // Each character can be succeeded by join, * or +

        Set<String> permutations = new HashSet<>();

        helper(string, 1, String.valueOf(string.charAt(0)), target, permutations);

        String[] solution = new String[permutations.size()];
        permutations.toArray(solution);
        return solution;
    }

    static void helper(String originalString,
                       int stringIdx,
                       String permutation,
                       long target,
                       Set<String> permutations) {
        if (stringIdx == originalString.length()) {
            // Evaluate Partial String
            if (target == evaluatePermutation(permutation)) {
                permutations.add(permutation);
            }
            return;
        }

        helper(originalString,
                stringIdx+1,
                permutation + originalString.charAt(stringIdx), // join
                target,
                permutations);
        helper(originalString,
                stringIdx+1,
                permutation + "*" + originalString.charAt(stringIdx), // join
                target,
                permutations);
        helper(originalString,
                stringIdx+1,
                permutation + "+" + originalString.charAt(stringIdx), // join
                target,
                permutations);
    }

    private static long evaluatePermutation(String permutation) {
        String[] addendums = permutation.split("\\+");

        long sum = 0;
        for (int idx = 0; idx < addendums.length; idx++) {
            String[] multipliers = addendums[idx].split("\\*");

            long product = 1;
            for (int mIdx = 0; mIdx < multipliers.length; mIdx++) {
                 product *= Long.parseLong(multipliers[mIdx]);
            }
            addendums[idx] = String.valueOf(product);

            sum += Long.parseLong(addendums[idx]);
        }

        return sum;
    }
}
