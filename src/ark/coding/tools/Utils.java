/**
 * Created by Akshayraj
 */
package ark.coding.tools;

public class Utils {

    public static void printArray(int[] A){
        for (int index = 0; index  < A.length; index ++){
            System.out.print( A[index] + " ");
        }
    }

    public static void reverseArray(int[] A){
        for(int i = 0; i < A.length / 2; i++)
        {
            int temp = A[i];
            A[i] = A[A.length - i - 1];
            A[A.length - i - 1] = temp;
        }
        return;
    }
}
