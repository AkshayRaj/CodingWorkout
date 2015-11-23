package ark.coding.brainteaser.arrayleader;

import ark.coding.Solution;
import java.util.Arrays;

public class ArrayLeader implements Solution<Integer> {
    private static final int NO_LEADER = -1;

    @Override
    public Integer solution(Object... args) {
        return getLeader((int[]) args[0]);
    }

    private int getLeader(int[] array){
        int length = array.length;
        Arrays.sort(array);
        int candidate = array[length/2];
        int count = 0;
        for(int i = 0 ; i < length ; i++){
            if(array[i] == candidate){
                count ++;
            }
        }
        if(count > length/2){
            return candidate;
        }
        return NO_LEADER;
    }

    public static void main(String[] args){
        int[] array_leaderAtStart = {1,1,1,1,4,5,6};
        int[] array_leaderInMiddle = {1,2,4,4,4,5};
        System.out.println("Leader is: " + new ArrayLeader().solution(array_leaderInMiddle));
    }
}
