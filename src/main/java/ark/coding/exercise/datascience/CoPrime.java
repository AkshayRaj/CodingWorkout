/**
 * Created by Akshayraj on 4/14/16.
 */
package ark.coding.exercise.datascience;

public class CoPrime {

    static int[] coprimeCount(int[] A) {
        int[] B = new int[A.length];
        for(int index = 0; index < A.length; index++){
            int count = 0;
            for(int i = 1; i < A[index]; i++){
                if(isCoPrime(i,A[index])){
                    count++;
                }
            }
            B[index] = count;
        }
        return B;
    }

    public static int findCoPrimeEuclidAlgorithm(int value1, int value2)
    {
        while (value1 != 0 && value2 != 0)
        {
            if (value1 > value2)
                value1 %= value2;
            else
                value2 %= value1;
        }
        return Math.max(value1, value2);
    }

    public static boolean isCoPrime(int value1, int value2)
    {
        return findCoPrimeEuclidAlgorithm(value1, value2) == 1;
    }
}
