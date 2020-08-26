package ark.coding.books.interview_kickstart.bit_manipulation;

public class XOR {

    /**
     * https://leetcode.com/problems/single-number/
     *
     * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     *
     * Note:
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     *
     * Approach 4: Bit Manipulation
     * Concept
     *
     * If we take XOR of zero and some bit, it will return that bit
     * a ⊕ 0 = a ⊕ 0 = a
     * If we take XOR of two same bits, it will return 0
     * a ⊕ a = a ⊕ a = 0
     * a ⊕ b = b ⊕ a
     * a⊕b⊕a = (a⊕a)⊕b = 0⊕b = b
     *
     * An approach with Cyclic Sort was tried, but that clearly does not works
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int singleNumber = nums[0];
        for (int index = 1; index < nums.length; index++) {
            singleNumber = singleNumber ^ nums[index];
        }
        return singleNumber;
    }
}
