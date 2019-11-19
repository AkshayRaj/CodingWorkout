package ark.coding.interviews.quantcast;

public class Solution {

    static int findCountOfBidByFindingFirstLastIndex(int currentBid, int[] previousBids) {
        int indexOfFirstOccurrence = findFirstOccurence(currentBid, previousBids);
        if (indexOfFirstOccurrence == -1) {
            return -1;
        }

        int indexOfLastOccurence = findLastOccurence(currentBid, previousBids);

        return indexOfLastOccurence - indexOfFirstOccurrence
                + 1; // we add 1 to accommodate the inclusion of last index.
    }

    private static int findFirstOccurence(final int numberToLookFor, final int[] sortedArray) {
        int length = sortedArray.length;

        int start = 0;
        int end = length - 1;
        int mid = (start + end)/2;

        int indexOfFirstOccurence = -1;
        while (start <= end) {
            if (sortedArray[mid] < numberToLookFor) {
                // discard left-half of the array
                start = mid + 1;
            }
            else if (sortedArray[mid] > numberToLookFor) {
                // discard the right-half of the array
                end = mid - 1;
            }
            else if (sortedArray[mid] == numberToLookFor) {
                // We found the location; store it
                indexOfFirstOccurence = mid;

                // and continue looking in the left-half of the array
                // to find the "first" occurrence of our number
                end = mid - 1;
            }
            mid = (start + end)/2;
        }

        return indexOfFirstOccurence;
    }

    private static int findLastOccurence(int numberToLookFor, int[] sortedArray) {
        int length = sortedArray.length;

        int start = 0;
        int end = length - 1;
        int mid = (start + end)/2;

        int indexOfLastOccurrence = -1;
        while (start <= end) {
            if (sortedArray[mid] < numberToLookFor) {
                // discard left half of the array
                start = mid + 1;
            }
            else if (sortedArray[mid] > numberToLookFor) {
                // discard right half of the array
                end = mid - 1;
            }
            else if (sortedArray[mid] == numberToLookFor) {
                // store the index
                indexOfLastOccurrence = mid;
                // and then continue looking in the right half of the array
                start = mid + 1;
            }
            mid = (start + end)/2;
        }

        return indexOfLastOccurrence;
    }

    /**
     *
     * @param currentBid The current bid amount
     * @param previousBids array of previous bid amounts;
     *                     array is sorted in ascending order, and contents of the array are not unique.
     *                     Elements of the array are non-negative.
     * @return the number of times currentBid was offered;
     *         -1 if the currentBid was never offered before.
     */
    static int findCountOfBid(int currentBid, int[] previousBids) {
        // Since array is sorted, we can use binary search
        // 1. Check value in the middle of the array
        //    a) if value in the middle is greater than current bid,
        //       then current bid lies between 0 & middle
        //    b) if value in the middle is less than current bid,
        //       then current bid lies between middle & end of array
        // 2. Keep checking until we find the current bid in the array
        //    return -1, if the value does not exist.

        int length = previousBids.length;
        if (length == 0) {
            return -1;
        }

        if (length > 0) {
            return findCountOfBidByFindingFirstLastIndex(currentBid, previousBids);
        }

        int low = 0;
        int midIndex = length/2;
        int high = length - 1;

        int valueInMid = previousBids[midIndex];
        while(valueInMid != currentBid
                && low < high) {
            if (valueInMid > currentBid) {
                // between low & middle
                high = midIndex - 1;
            }
            if (valueInMid < currentBid) {
                // between middle & high
                low = midIndex + 1;
            }
            midIndex = (low + high)/2;
            valueInMid = previousBids[midIndex];
        }

        // after binary search, if the value does not exist in the midIndex,
        // then the currentBid has not been offered before, and return -1.
        if (valueInMid != currentBid) {
            return -1;
        }

        /**
         * ******************************************************************
         * Use Binary search to get "first" & "last" occurrences of currentBid,
         * once midIndex has been identified.
         * *******************************************************************
         */

        // Once we find the index in array where the currentBid is found,
        // we go both left & right to count for duplicate occurences.
        int count = 1;
        int leftIndex = midIndex - 1;
        if (leftIndex >= 0) {
            while (previousBids[leftIndex] == valueInMid && leftIndex >= 0) {
                count++;
                leftIndex--;
                if (leftIndex < 0) {
                    break;
                }
            }
        }

        int rightIndex = midIndex + 1;
        if (rightIndex < length) {
            while (previousBids[rightIndex] == valueInMid && rightIndex < length) {
                count++;
                rightIndex++;
                if (rightIndex >= length) {
                    break;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] test = new int[] {1, 2, 2, 2, 2, 2, 3};

        System.out.println(findCountOfBid(4, test));
    }
}
