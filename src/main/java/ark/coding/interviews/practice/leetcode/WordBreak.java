/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class WordBreak {
    public boolean wordBreak(String string, List<String> wordDict) {
        Set<String> dictionary = new HashSet<String>(wordDict);

        // return recursive(string, dictionary, 0, string.length());
        return bfs(string, dictionary);
    }

    public boolean bfs(String string, Set<String> dictionary) {
        // 1. Check if substrings (from index `i` ~> string.length()) exist in dictionary
        //    - range of i : 0 ~> string.length()-1
        // 2. Add the index where a substring in step-1 ends.
        //    - Thus we add end indices of substrings (from i ~> end) to the queue
        // 3. At each new level, remove the element from the head of the queue.
        //    - if the popped element is equal to string.length(), we have the word break
        // 4. repeat steps 1-3 until queue is empty. If end has not been reached, then word break does not exist.

        Queue<Integer> substringStartingIndexTracker = new LinkedBlockingQueue<Integer>();
        substringStartingIndexTracker.add(0);
        boolean[] visited = new boolean[string.length()];
        while (!substringStartingIndexTracker.isEmpty()) {
            Integer startingIndex = substringStartingIndexTracker.remove();
            if (startingIndex == string.length()) {
                return true;
            }
            if (visited[startingIndex]) {
                // we have already computed for this index; don't calculate again.
                continue;
            }
            visited[startingIndex] = true;
            for (int end = startingIndex + 1; end <= string.length(); end++) {
                if (dictionary.contains(string.substring(startingIndex, end))) {
                    substringStartingIndexTracker.add(end);
                }
            }
        }

        return false;
    }

    public boolean iterativeWithMemoization(String string, Set<String> dictionary) {
        boolean[] dp = new boolean[string.length() + 1];
        // dp[i] represents if a substring(0,i) or its components, exist in the dictionary.

        dp[0] = true; // assume empty string exists in dictionary
        for (int substringLength = 1; substringLength <= string.length(); substringLength++) {
            int end = substringLength;
            for (int mid = 0; mid < end; mid++) {
                // `mid` partitions the substring into two parts
                // (0 ~> mid-1) and (mid ~> end-1)
                //
                // (0 ~> mid-1) is encoded in `dp[mid]`
                // (mid ~> end-1) is something we calculate for each new mid.
                String substring = string.substring(mid, end);
                dp[substringLength] = (dp[mid] && dictionary.contains(substring))
                            || dp[substringLength];
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
