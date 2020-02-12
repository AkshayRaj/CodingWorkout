/**
 * Created by Akshayraj
 */
package ark.coding.interviews.amzn;

/**
 *  Find leastMissingNumber(int[] array)
 *     Time : O(n)
 *     Space: O(1)
 */
public class LeastMissingNumber {

    int findLeastMissingNumber(int[] array) {
        int minPositiveNumber = Integer.MAX_VALUE;
        int maxPositiveNumber = -1;

        int runningSum = 0;
        for (int number : array) {
            runningSum += number;

            if (number > maxPositiveNumber) {
                maxPositiveNumber = number;
            }
            if (number < minPositiveNumber) {
                minPositiveNumber = number;
            }
        }

        int expectedSum = 0;
        for (int number = minPositiveNumber; number <= maxPositiveNumber; number++) {
            expectedSum += number;
        }

        int diffInExpectation = expectedSum - runningSum;
        if (diffInExpectation > 0) {
            return diffInExpectation;
        }
        else {
            if (minPositiveNumber == 1) {
                return maxPositiveNumber + 1;
            }
            else {
                return minPositiveNumber-1;
            }
        }
    }
}
