/**
 * Splitting Hexadecimal Strings:
 * return the minimum number of splits such that each split is a hexadecimal representation of a perfect square.
 *
 * Example: 896bb1 returns 1 because it is the hexadecimal representation of 9006001, which is a perfect square of 3001.
 * Example: 1a919 returns 3 because 1, a9, and 19 are hexadecimal representations of perfect squares,
 *          and 3 is the minimum number of splits required.
 */
package ark.coding.interviews.apollo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution implements ark.coding.Solution<Integer> {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("Solution is: " + solution.solution("896bb1"));
    }

    @Override
    public Integer solution(Object... args) {
        return getMin((String) args[0]);
    }

    static int getMin(String string) {
        HashMap<Integer, String> splits = new HashMap<>();

        int counter = 1;
        int beginIndex = 0;
        List<String> pieces = Arrays.asList(string.split(""));

        for (int index = 0; index < pieces.size(); index++) {
            String piece = string.substring(beginIndex, index + 1);
            if (isPerfectSquare(piece)) {
                splits.put(counter, piece);
            }
            else {
                if (splits.isEmpty()) {
                    return -1;
                }
                counter++;
                beginIndex = index + 1;
            }
        }

        if (!splits.isEmpty()) {
            return splits.size();
        }
        return -1;
    }

    private static boolean isPerfectSquare(String hexadecimalString) {
        int decimal = Integer.parseInt(hexadecimalString, 16);
        int squareRoot = (int) Math.sqrt(decimal);
        return (Math.pow(squareRoot, 2) == decimal);
    }
}
