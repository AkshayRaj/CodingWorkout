/**
 * Created by Akshayraj
 */
package ark.coding.interviews.walmrt;

public class ValidParentheses {

    private static final Character OPEN = '(';
    private static final Character CLOSED = ')';

    /**
     *  This function calculates the minimum number of operations it would take, to make a
     *  string containing only parentheses - '(', ')'  "valid".
     *
     *  A valid parentheses string has matching set of open & closed parentheses.
     *
     *  In this context, an operation can be either of the two things -
     *  1. Adding an open parentheses to the string
     *  2. Adding a closed parentheses to the string
     *  The parentheses could be placed anywhere within the string, such as to make it valid.
     *
     * @param parenthesesString the string to validate
     * @return the minimum number of operations it would take to make the string valid again.
     *
     * ### Big-O complexity
     *  - Time  : O(n) -> n is the length of the string
     *  - Space : O(1) -> constant space allocation
     */
    static int minOperations(String parenthesesString) {
        int stringLength = parenthesesString.length();

        // ### APPROACH :
        // To find min number of operations required to "validate" a parentheses string,
        // we have to find the following two numbers -
        // 1. number of unmatched open parentheses
        // 2. number of unmatched closed parentheses

        /*
         *  1. Find Unmatched Open Parentheses
         *      -> Iterate from "end of the string" ~> "start of the string"
         *      -> For each count of open parentheses, we increment the count of  `openParentheses`
         *      -> For each count of closed parentheses, we decrement the count of  `openParentheses`
         *      -> Anytime the count of `openParentheses` goes above 0,
         *          : we increment the count of `unmatchedOpenParentheses
         *          : reset `openParentheses` to 0
         */
        int openParentheses = 0;
        int unmatchedOpenParentheses = 0;
        for (int charIndex = stringLength - 1; charIndex >= 0; charIndex--) {
            Character parentheses = parenthesesString.charAt(charIndex);

            if (parentheses.equals(OPEN)) {
                openParentheses++;
                if (openParentheses > 0) {
                    unmatchedOpenParentheses++;
                    openParentheses = 0;
                }
            }
            else if (parentheses.equals(CLOSED)) {
                openParentheses--;
            }
        }

        /*
         *  2. Find Unmatched Closing Parentheses
         *      -> Iterate from "start of the string" ~> "end of the string"
         *      -> For each count of closed parentheses, we increment the count of  `closedParentheses`
         *      -> For each count of open parentheses, we decrement the count of  `closedParentheses`
         *      -> Anytime the count of `closedParentheses` goes above 0,
         *          : we increment the count of `unmatchedClosedParentheses
         *          : reset `closedParentheses` to 0
         */
        int closedParentheses = 0;
        int unmatchedClosedParentheses = 0;
        for (int charIndex = 0; charIndex < stringLength; charIndex++) {
            Character parentheses = parenthesesString.charAt(charIndex);

            if (parentheses.equals(CLOSED)) {
                closedParentheses++;
                if (closedParentheses > 0) {
                    unmatchedClosedParentheses++;
                    closedParentheses = 0;
                }
            }
            else if (parentheses.equals(OPEN)) {
                closedParentheses--;
            }
        }

        return unmatchedOpenParentheses + unmatchedClosedParentheses;
    }

    public static void main(String[] args) {
        UnitTest.assertEquals(2, minOperations( ")("));
        UnitTest.assertEquals(0, minOperations( "()"));
        UnitTest.assertEquals(4, minOperations("))(("));
        UnitTest.assertEquals(0, minOperations("()()"));
        UnitTest.assertEquals(0, minOperations("(())"));
        UnitTest.assertEquals(4, minOperations(")())(("));
        UnitTest.assertEquals(5, minOperations(")))(("));
    }

    static class UnitTest  {

        public static void assertEquals(int expected, int actual) {
            if (expected != actual) {
                throw new RuntimeException(String.format("Expected value [%d] is not equal to actual value[%d].", expected, actual));
            }
        }
    }
}
