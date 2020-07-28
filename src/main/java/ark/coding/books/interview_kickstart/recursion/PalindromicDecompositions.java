/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.recursion;

import ark.coding.tools.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PalindromicDecompositions {

    /**
     * <b>Permutation Problem</b>
     *
     * @param s
     * @return
     */
    static String[] generate_palindromic_decompositions(String s) {
        // 1. parse string from left ~> end
        // 2. Consider all "in-order" permutations of original string
        //  - if substring is palindrome, suffix "|" after it
        //  - when you reach end of string, add to set

        List<String> palindromicDecompositions = new ArrayList<>();
        generate(s, 0, "", palindromicDecompositions);


        return palindromicDecompositions.toArray(new String[palindromicDecompositions.size()]);
    }

    private static void generate(
            String orgString,
            int idx,
            String partialString,
            List<String> palindromicDecompositions) {
        if (idx == orgString.length()) {
            String[] subStrings = partialString.split("\\|");
            for (String subString : subStrings) {
                if (!Utils.isPalindrome(subString)) {
                    return;
                }
            }
            palindromicDecompositions.add(partialString);
            return;
        }

        String withoutDelimiter = partialString + orgString.charAt(idx);
        String withDelimiter = withoutDelimiter + "|";
        idx++;
        generate(orgString, idx, withoutDelimiter, palindromicDecompositions);
        if (idx < orgString.length())
        generate(orgString, idx, withDelimiter, palindromicDecompositions);
    }


    //########################################################################################################
                                    //TRASH
//########################################################################################################
    private static void decompose(String orgString,
                                  int low, int high, boolean parsingDone,
                                  Set<String> palindromicDecompositions) {
        if (high == orgString.length()) {
            String substring = orgString.substring(low, high);
            if (Utils.isPalindrome(substring)) {
                addPalindromicDecomposition(orgString, low, high, palindromicDecompositions);
            }
            return;
        }
        if (parsingDone) {
            String substring = orgString.substring(low, high);
            if (Utils.isPalindrome(substring)) {
                addPalindromicDecomposition(orgString, low, high, palindromicDecompositions);
            }
            return;
        }

        // include
        decompose(orgString, low, high+1, true, palindromicDecompositions);
        decompose(orgString, low, high+1, false, palindromicDecompositions);

        // exclude
        low++;
        decompose(orgString, low, low+1, true, palindromicDecompositions);
        decompose(orgString, low, low+1, false, palindromicDecompositions);
    }

    private static void addPalindromicDecomposition(String orgString, int low, int high, Set<String> palindromicDecompositions) {
        String substring = orgString.substring(low, high);

        StringBuilder sb = new StringBuilder();
        for (int left = 0; left < low; left++) {
            sb.append(orgString.charAt(left)).append("|");
        }
        sb.append(substring);
        for (int right = high; right < orgString.length(); right++) {
            sb.append("|").append(orgString.charAt(right));
        }
        palindromicDecompositions.add(sb.toString());
    }

}
