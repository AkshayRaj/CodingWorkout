/**
 * Created by Akshayraj
 */
package ark.coding.algorithm.ConsecutiveNumbersInArray;

import java.util.Arrays;

public class ConsecutiveNumbers {

    public static void main(String[] args){

        int[] a ={-2,-3,-1,3,2,1};
        int i = tripleThreat(a);
        System.out.println(34%5);
    }

    static int createPackage(int small, int big, int goal) {
        int smallbagsToUse = -1;

        smallbagsToUse = goal/5;

        return smallbagsToUse;
    }


    static int tripleThreat(int[] a) {
        Arrays.sort(a);
        for (int i = 0; i < a.length - 2; i++) {
            if ((a[i] + 1 == a[i + 1]) && (a[i+1] + 1 == a[i+2])) {
                return 1;
            }
        }
        return 0;
    }
}
