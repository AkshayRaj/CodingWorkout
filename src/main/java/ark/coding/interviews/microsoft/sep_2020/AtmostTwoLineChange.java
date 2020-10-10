package ark.coding.interviews.microsoft.sep_2020;

public class AtmostTwoLineChange {

    public static void main(String[] args) {
        int highestPowerOfTwoSmallerThanMaxNodes = Integer.highestOneBit(6); // this gives the leftmost set bit, which is the highest power of two less than or equal to max nodes
        int solution = highestPowerOfTwoSmallerThanMaxNodes;
        while (highestPowerOfTwoSmallerThanMaxNodes > 0) {
            System.out.println(solution);
            solution += (highestPowerOfTwoSmallerThanMaxNodes >>> 1);
            highestPowerOfTwoSmallerThanMaxNodes = highestPowerOfTwoSmallerThanMaxNodes >>> 1;
        }
        System.out.println(solution);
    }

    /**
     * The method should return the index in array, of the longest sequence of 1s.
     * If multiple longest sequences exist (same length), find the first one.
     *
     * Problem: Given code, change atmost 2 lines to make it correct
     * (i.e. remove bug in original code, with minimal changes)
     *
     * @param A Given array containing 1s and 0s.
     * @return starting index of longest sequence of 1s.
     */
    int solution(int[] A) {
        int n = A.length;
        int i = n - 1;
        int result = -1;
        int k = 0, maximal = 0;
        while (i > 0) {
            if (A[i] == 1) {
                k = k + 1;
                if (k >= maximal) {
                    maximal = k;
                    result = i;
                }
            }
            else
                k = 0;
            i = i - 1;
        }
        // i=0
        if (A[i] == 1 && k + 1 >= maximal)
            result = 0;
        return result;
    }

//    ORIGINAL CODE
//    int solution(int[] A) {
//        int n = A.length;
//        int i = n - 1;
//        int result = -1;
//        int k = 0, maximal = 0;
//        while (i > 0) {
//            if (A[i] == 1) {
//                k = k + 1;
//                if (k >= maximal) {
//                    maximal = k;
//                    result = i;
//                }
//            }
//            else
//                k = 0;
//            i = i - 1;
//        }
//        // i=0
//        if (A[i] == 1 && k + 1 > maximal)
//            result = 0;
//        return result;
//    }
}
