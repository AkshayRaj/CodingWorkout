/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.sort;

import java.util.Arrays;

import static ark.coding.tools.Utils.printArray;
import static ark.coding.tools.Utils.swapElements;

public class TimedTest {

    public static void main(String[] args) {
        char[] balls = new char[]{'G','B','G','G','R','B','R','G'};
        System.out.println(Arrays.toString(balls));
        dutch_flag_sort(balls);
        System.out.print(Arrays.toString(balls));
    }

    /*
     * Complete the merger_first_into_second function below.
     */
    static void merger_first_into_second(int[] arr1, int[] arr2) {
        int p1 = arr1.length-1;
        int p2 = arr1.length-1;
        int p3 = arr2.length-1;
        while (p1 >= 0 && p2 >= 0) {
            if (arr1[p1] > arr2[p2]) {
                arr2[p3] = arr1[p1];
                p1--;
            }
            else {
                arr2[p3] = arr2[p2];
                p2--;
            }
            p3--;
        }

        if (p1 >= 0) {
            while (p3 >= 0) {
                arr2[p3] = arr1[p1];
                p1--;
                p3--;
            }
        }

    }

    public static void dutch_flag_sort(char[] balls) {
        // ptr to track R,G,B
        // R<G<B false, then swap G<->R, B<->G
        int rIdx = -1;
        int gIdx = 0; // the main index iterates from 0~>n-1
        int bIdx = balls.length;
        while (gIdx <= bIdx) {
            if (balls[gIdx] == 'R') {
                rIdx++;
                swapElements(balls, gIdx, rIdx);
            }
            while (gIdx < bIdx && balls[gIdx] == 'B') {
                bIdx--;
                swapElements(balls, gIdx, bIdx);
                if (balls[gIdx] == 'R') {
                    rIdx++;
                    swapElements(balls, gIdx, rIdx);
                }
            }
            printArray(balls);System.out.println();
            gIdx++;
        }
    }
}
