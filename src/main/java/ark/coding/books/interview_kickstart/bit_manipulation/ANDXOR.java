package ark.coding.books.interview_kickstart.bit_manipulation;

public class ANDXOR {

    /**
     * https://leetcode.com/problems/single-number-ii/solution/
     *
     * {@link ark.coding.books.interview_kickstart.counting.CountWithArrayOrMap#singleNumber(int[])}
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int seenOnce = 0;
        int seenTwice = 0;

        for (int ptr = 0; ptr < nums.length; ptr++) {
            seenOnce = ~seenTwice & seenOnce^nums[ptr];
            //       1 & number = number;
            //       1 & 0      = 0;
            // ~number & number = 0;
            //=====================
            seenTwice = ~seenOnce & seenTwice^nums[ptr];
            // ~number & number = 0;
            //       1 & number = number;
        }
        return seenOnce;
    }
}
