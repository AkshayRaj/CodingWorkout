/**
 * Created by Akshayraj
 */
package ark.coding.books.epi;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static ark.coding.tools.Utils.printBits;

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
    private static final long BIT_MASK = 15L;
    private static int WORD_SIZE = 4;

    public static void main(String[] args) {
        long number = 47;
        long equalWeightedClosestNumber = getEqualWeightClosestNumber(number);

        printBits(number);
        printBits(equalWeightedClosestNumber);
        System.out.println("Distance: " + Math.abs(number - equalWeightedClosestNumber));
        System.out.println("Distance left shift: " + Math.abs(number - (number << 1)));
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
        return    (REVERSE_BITS.get((number >>> (WORD_SIZE * 15)) & BIT_MASK) << WORD_SIZE * 0)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 14)) & BIT_MASK) << WORD_SIZE * 1)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 13)) & BIT_MASK) << WORD_SIZE * 2)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 12)) & BIT_MASK) << WORD_SIZE * 3)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 11)) & BIT_MASK) << WORD_SIZE * 4)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 10)) & BIT_MASK) << WORD_SIZE * 5)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 9)) & BIT_MASK) << WORD_SIZE * 6)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 8)) & BIT_MASK) << WORD_SIZE * 7)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 7)) & BIT_MASK) << WORD_SIZE * 8)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 6)) & BIT_MASK) << WORD_SIZE * 9)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 5)) & BIT_MASK) << WORD_SIZE * 10)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 4)) & BIT_MASK) << WORD_SIZE * 11)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 3)) & BIT_MASK) << WORD_SIZE * 12)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 2)) & BIT_MASK) << WORD_SIZE * 13)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 1)) & BIT_MASK) << WORD_SIZE * 14)
                | (REVERSE_BITS.get((number >>> (WORD_SIZE * 0)) & BIT_MASK) << WORD_SIZE * 15);
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

}
