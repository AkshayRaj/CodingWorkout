/**
 * Created by Akshayraj
 */
package ark.coding.temp;
import java.util.Scanner;

import static ark.coding.tools.Utils.swapElements;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    static void countSwaps(int[] a) {
        int length = a.length;

        int swapCount = 0;
        for (int iterationCount = 0; iterationCount < length; iterationCount++) {
            for (int index = 0; index < length - 1; index++) {
                if (a[index] > a[index + 1]) {
                    swapElements(a, index, index + 1);
                    swapCount++;
                }
            }
        }
        System.out.println(String.format("Array is sorted in %d swaps.", swapCount));
        System.out.println(String.format("First Element: %d", a[0]));
        System.out.println(String.format("Last Element: %d", a[length-1]));
    }

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.close();
    }
}
