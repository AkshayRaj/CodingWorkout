/**
 * Created by Akshayraj
 */
package ark.coding.books.epi;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Chapter 4. Primitive Types
 */
public class PrimitiveTypes {
    private static final Map<Long, Long> REVERSE_BITS = ImmutableMap.<Long, Long>builder()
            .put(0L, 0L)
            .put(1L, 8L)
            .put(2L, 4L)
            .put(3L, 12L)
            .put(4L, 2L)
            .put(5L, 10L)
            .put(6L, 6L)
            .put(7L, 14L)
            .put(8L, 1L)
            .put(9L, 9L)
            .put(10L, 5L)
            .put(11L, 13L)
            .put(12L, 3L)
            .put(13L, 11L)
            .put(14L, 7L)
            .put(15L, 15L)
            .build();
    private static final long MASK_4_BIT = 15L;
    private static final int WORD_SIZE = 4;
    private static final long MASK_64_BIT = allOnes();


    public static void main(String[] args) {
        long number = Integer.MAX_VALUE;
        long equalWeightedClosestNumber = getEqualWeightClosestNumber(number);

        System.out.println(add(3,7));
        //printAllBits(allOnes());
        System.out.println(increment(number));
        System.out.println(number);
        System.out.println(decrement(number));
        //printBits(number);
        //printBits(equalWeightedClosestNumber);
        //System.out.println("Distance: " + Math.abs(number - equalWeightedClosestNumber));
        //System.out.println("Distance left shift: " + Math.abs(number - (number << 1)));
    }

    public static short countBits(int x) {
        short numBits = 0;
        while (x != 0) {
            numBits += (x & 1);
            x >>>= 1;
        }
        return numBits;
    }

    public static short parityBruteForce(long x) {
        short result = 0;
        while (x != 0) {
            result ^= (x&1);
            x >>>= 1;
        }
        return result;
    }

    public static short parityLogn(long x) {
        x ^= x >>> 32;
        x ^= x >>> 16;
        x ^= x >>> 8;
        x ^= x >>> 4;
        x ^= x >>> 2;
        x ^= x >>> 1;
        return (short) (x & 1);
    }

    /**
     * Propagate the rightmost `1` (set) bit
     * For eg,(01010000) ~> (01011111)
     * (00110100) ~> (00110111)
     * In above examples, the rightmost-setbit is propagated till the end
     * @param number
     */
    public static long rightPropagateRightmostSetBit(int number) {
        if (number == 0) {
            return number;
        }
        return number | (number - 1);
    }

    /**
     * Compute x mod a power of two.
     * Eg, 77 mod 64 = 13
     *
     * @param number the number to be divided
     * @param power the power of two
     * @return the remainder
     */
    public static long modPowerOfTwo(long number, byte power) {
        if (number == 0) {
            throw new UnsupportedOperationException("Division by zero is not supported.");
        }
        if (power > 64) {
            throw new IllegalArgumentException("long cannot be greater than 64-bit");
        }
        long moduloBitFilter = Long.MAX_VALUE >>> (63 - power);
        return (number & moduloBitFilter);
    }

    /**
     * Check if a number is a power of '2'
     *
     * @param number
     * @return
     */
    public static boolean isPowerOfTwo(long number) {
        if (number <= 0) {
            return false;
        }
        return (number & (number - 1)) == 0;
    }

    /**
     * Swaps bits at indices i,j of a given number.
     *
     * @param number
     * @param i
     * @param j
     * @return
     */
    public static long swapBits(long number, byte i, byte j) {
        if (i > 63 || 0 > i
         || j > 63 || 0 > j) {
            throw new IllegalArgumentException(String.format("Either of i-[%d], j-[%d] is out of bounds[0-63]", i, j));
        }

        if (((number >>> i) & 1) != ((number >>> j) & 1)) {
            long bitMask = (1L << i) | (1L << j);
            number ^= bitMask;
        }
        return number;
    }

    /**
     *
     *
     * @param number
     * @return
     */
    public static long reverseBits(long number) {
        // break into 16 4-bit words
        // we have a word map to get O(1) time reversals of 4-bit words
        return    (REVERSE_BITS.get((number >>> (WORD_SIZE * 15)) & MASK_4_BIT) << WORD_SIZE * 0)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 14)) & MASK_4_BIT) << WORD_SIZE * 1)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 13)) & MASK_4_BIT) << WORD_SIZE * 2)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 12)) & MASK_4_BIT) << WORD_SIZE * 3)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 11)) & MASK_4_BIT) << WORD_SIZE * 4)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 10)) & MASK_4_BIT) << WORD_SIZE * 5)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 9)) & MASK_4_BIT) << WORD_SIZE * 6)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 8)) & MASK_4_BIT) << WORD_SIZE * 7)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 7)) & MASK_4_BIT) << WORD_SIZE * 8)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 6)) & MASK_4_BIT) << WORD_SIZE * 9)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 5)) & MASK_4_BIT) << WORD_SIZE * 10)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 4)) & MASK_4_BIT) << WORD_SIZE * 11)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 3)) & MASK_4_BIT) << WORD_SIZE * 12)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 2)) & MASK_4_BIT) << WORD_SIZE * 13)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 1)) & MASK_4_BIT) << WORD_SIZE * 14)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 0)) & MASK_4_BIT) << WORD_SIZE * 15);
    }

    /**
     * Weight of a non-negative integer is the number of bits that are set to 1 in its binary representation.
     *
     * @param number
     * @return
     */
    public static byte getWeightOfNumber(long number) {
          int count = 63;
          byte weight = 0;
          while (count >= 0) {
              if ((number >>> count & 1L) == 1L) {
                  weight++;
              }
              count--;
          }
          return weight;
    }

    public static long getEqualWeightClosestNumber(long number) {
        if (Long.MAX_VALUE == number || 0 >= number) {
            throw new IllegalArgumentException(
                    String.format(
                    "Number [%d] is either Long.MAX_VALUE or less than equal to zero",
                    number));
        }
        // 1. get LSB
        // 2. keep going right until a opposite bit of LSB is found
        // 3. swap when you find it
        long lsb = number & 1L;

        byte count = 1;
        while (((number >>> count) & 1L) == lsb) {
            count++;
        }
        return swapBits(number, count, (byte) (count-1));
    }

    public static long multiply(long x, long y) {
        long mul = 0;

        return mul;
    }

    /**
     * Binary addition using sum & carry method
     * We do not use arithmetic operators like +,-,*,%,/
     * We use AND, XOR SHIFT operations
     *
     * https://www.electronics-tutorials.ws/combination/comb_7.html
     *
     * @param a
     * @param b
     * @return
     */
    public static long add(long a, long b) {
        long carry = (a & b) << 1;
        long sum = (a ^ b);

        while (carry != 0) {
            long newCarry = (sum & carry) << 1;
            sum = sum ^ carry;
            carry = newCarry;
        }

        return sum;
    }

    public static long increment(long number) {
        //return add(number, 1);
        int noOfShits = 0;

        while (((number >>> noOfShits) & 1L) != 0
                && noOfShits <= 63) {
            noOfShits++;
        }

        return ((number ^ (MASK_64_BIT >>> (63 - noOfShits))));
    }

    public static long decrement(long number) {
        int noOfShits = 0;

        while (((number >>> noOfShits) & 1L) != 1
                && noOfShits <= 63) {
            noOfShits++;
        }

        return (number ^ (MASK_64_BIT >>> (63 - noOfShits)));
    }

    private static long allOnes() {
        int count = 0;
        long allOnes = 1L;
        while (count <= 63) {
            allOnes |= 1L << count;
            count++;
        }
        return allOnes;
    }
}
