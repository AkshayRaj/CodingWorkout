/**
 * Created by Akshayraj
 */
package ark.coding.interviews.pill_club;

import java.util.Arrays;

import static ark.coding.tools.Utils.swapElements;

public class PartitionZeroes {

    /**
     * Given an array of integers, return that array with all of the zeroes moved
     * to the end. Example:
     * [1, 3, 0, 2, 0, 4, 0] -> [1, 3, 2, 4, 0, 0, 0]
     *
     */
        public static void main(String[] args) {
            int[] array = new int[]{1, 3, 0, 2, 0, 4, 0,0,0,7,8,0,9};
            partition(array);

            System.out.println(Arrays.toString(array));
        }

//   private static void partition(int[] array) {
//       for (int index = 0; index < array.length; index++) {
//           if (array[index] == 0) {
//               // bubble down the zero
//               for (int i2 = index; i2 < array.length-1; i2++) {
//                   swap(array, i2, i2+1);
//               }
//           }
//       }
//   }

    // 1. Two pointers, start from low, and heads towards the end of array
    // 2. p0 tracks first zero
    // 3. p1 will track first non-zero beginning from p1
    // 4. when p2 finds non-zero, swap(p1, p2)
    private static void partition(int[] array) {
        // check for zero
        // check for non-zero
        // swap
        // p0 & p1 should continue from their last positions
        // 2*n ~> O(n)

        int p0 = 0; // Tracks first zero from any given state; 0 ~> n (1 iteration)
        int p1 = -1; // Tracks first non-zero after p0; 0 ~> n (1 iteration)
        while(p0 < array.length && p1 < array.length) {
            checkForZero: while(p0 < array.length && array[p0] != 0) p0++;
            // Only for the first zero, we'll need p0. After that p1    's previous state is enough.
            if (p1 == -1) {
                p1 = p0 +1;
            }
            checkForNonZero: while(p1 < array.length && array[p1] == 0) p1++;

            if (p0 < array.length && p1 < array.length && p0 < p1) {
                swapElements(array, p0, p1);
            }
        }
    }

//   private static void partition(int[] array) {
//       // 1. Two pointers, which start at opposite ends, they move towards each other
//      //  2. left tracks zeros, right tracks non-zeros
//     //   3. swap
//      //  4. GOTO step-2 until pointers cross each other
//         int high = array.length-1;
//         int low = 0;

//         int leftPtr = 0;
//         int rightPtr = high;
//         while (leftPtr <= rightPtr) {
//             checkForZero: while (leftPtr <= high && array[leftPtr] != 0) leftPtr++;
//             checkForNonZero: while (rightPtr >= low && array[rightPtr] == 0) rightPtr--;

//             if (leftPtr < rightPtr) {
//               swap(array, leftPtr, rightPtr);
//             }
//         }
//   }
}
