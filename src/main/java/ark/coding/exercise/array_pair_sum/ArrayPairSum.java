package ark.coding.exercise.array_pair_sum;

import ark.coding.Solution;

import java.util.Arrays;

/**
 * Created by Akshayraj on 9/24/15.
 */
public class ArrayPairSum implements Solution<Boolean>{

    @Override
    public Boolean solution(Object... args) {
        return checkForSum((int[])args[0], (int)args[1]);
    }

    private enum Check{
        LESSER,
        GREATER,
        EQUAL;
    }

    public static void main(String[] args){
        int array[] = {
                1, 4, 45, 6, 10, -8
        };
        int X = 55;
        ArrayPairSum arrayPairSum = new ArrayPairSum();
        boolean isSumPresent = arrayPairSum.checkForSum(array, X);
        System.out.println("isSumPresent: " + isSumPresent);
    }

    private boolean checkForSum(int[] array, int sumToCheck){
        Arrays.sort(array);
        int index_left = 0;
        int index_right = array.length - 1;
        int sum = 0;
        while(index_left < index_right){
            System.out.println("sum: " + sum);
            sum = array[index_left] + array[index_right];
            if(sum > sumToCheck){
                index_right--;
            }
            if(sum < sumToCheck){
                index_left++;
            }
            if(sum == sumToCheck){
                System.out.println("sum: " + sum);
                return true;
            }
        }
        return false;
    }
}
