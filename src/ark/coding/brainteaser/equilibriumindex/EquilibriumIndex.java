package ark.coding.brainteaser.equilibriumindex;

import ark.coding.Solution;

public class EquilibriumIndex implements Solution {

    private static final int NOT_FOUND = -1;

    @Override
    public Integer solution(Object... args) {
        return getEquilibriumIndex((int[])args[0]);
    }

    public static void main(String[] args){
        int[] A = {
                -1,
                3,
                -4,
                5,
                1,
                -6,
                2,
                1
        };
        int N = 8;
        System.out.println("EquilibriumIndex is: " + new EquilibriumIndex().solution(A, N));
    }

    private int getEquilibriumIndex(int[] A){
        int arraySize = A.length;
        int previousSum = 0;
        //iterate array and store sum of all elements in array in nextSum
        int nextSum = 0;
        for(int i = 1 ; i < arraySize ; i ++){
            nextSum = nextSum + A[i];
        }
        System.out.println("nextSum: " + nextSum);
        //iterate array to compare previousIndices and nextIndices
        for (int i = 1; i <= arraySize - 2; i++){
            previousSum = previousSum + A[i-1] ;
            nextSum = nextSum - A[i];
            System.out.println("nextSum: " + nextSum + " previousSum: " + previousSum);
            if(previousSum == nextSum){
                return i;
            }
        }
        return NOT_FOUND;
    }
}
