/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String string, List<String> wordDict) {
        Set<String> dictionary = new HashSet<String>(wordDict);

        //return recursive(string, dictionary, 0, string.length());
        return iterativeWithMemoization(string, dictionary);
    }

    public boolean iterativeWithMemoization(String string, Set<String> dictionary) {
        boolean[] dp = new boolean[string.length() + 1];
        // dp[i] represents if a substring(0,i) or its components, exist in the dictionary.

        dp[0] = true; // assume empty string exists in dictionary
        for (int end = 1; end <= string.length(); end++) {
            for (int mid = 0; mid < end; mid++) {
                // `mid` partitions the substring into two parts
                // (0 ~> mid-1) and (mid ~> end-1)
                dp[end] = (dp[mid] && dictionary.contains(string.substring(mid, end)))
                            || dp[end];
            }
        }

        return dp[string.length()];
    }

    private boolean recursive(String string, Set<String> dictionary, int start, int end) {
        if (start > end) {
            return false;
        }
        if (dictionary.contains(string.substring(start, end))) {
            return true;
        }

        boolean wordBreakExists = false;
        for (int mid = start; mid < end; mid++) {
            wordBreakExists = (recursive(string, dictionary, start, mid) && recursive(string, dictionary, mid, end))
                            || wordBreakExists;
        }

        return wordBreakExists;
    }
}
