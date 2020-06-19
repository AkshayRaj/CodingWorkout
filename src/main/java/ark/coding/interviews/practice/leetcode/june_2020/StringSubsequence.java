/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.leetcode.june_2020;

public class StringSubsequence {
    /**
     * 0 <= s.length <= 100
     * 0 <= t.length <= 10^4
     * Both strings consists only of lowercase characters.
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int tIndex = 0;
        int sIndex = 0;

        while (sIndex < s.length() && tIndex < t.length()) {
            Character sChar = s.charAt(sIndex);
            Character tChar = t.charAt(tIndex);

            while (!tChar.equals(sChar)) {
                tIndex++;
                if (tIndex >= t.length()) {
                    return false;
                }
                tChar = t.charAt(tIndex);
            }

            sIndex++;
            tIndex++;
        }
        if (sIndex == s.length()) {
            return true;
        }
        return false;
    }
}
