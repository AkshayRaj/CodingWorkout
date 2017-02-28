package ark.coding.brainteaser.frogcrossingalgorithm;

import ark.coding.Solution;

public class FrogLeap implements Solution<Integer>{
    private static final int CANNOT_REACH_POSITION_X = -1;
    private static int[] mLeafPositions = {
           2,
           4
    };
    private static int mDestination = 7;
    private static int mJumpCapacity = 2;

    @Override
    public Integer solution(Object... args) {
        return getEarliestTime((int[]) args[0],(int) args[1],(int) args[2]);
    }

    private Integer getEarliestTime(int[] A, int X, int D) {
        int lastLeafTime = A.length - 1;//time last leaf fell is length of array - 1, as array-index is 0-based.
        int leafPositions[] = new int[X+1];
        int positionLeafFell = 0;
        leafPositions[0] = 1;//at position 0, we assume "leaf is present"\
        for(int time = 0; time <= lastLeafTime; time++){
            positionLeafFell = A[time];
            leafPositions[positionLeafFell] = 1;
            System.out.println("positionLeafFell: " + positionLeafFell);
            //check if frog can travel from position '0' to position 'X', after a new leaf has fallen
            // We require TWO "POINTERS" :
            // -one tracks currentPosition
            // -the other checks jumps possible from currentPosition
            int currentPosition = 0;
            int possibleJumpPosition = currentPosition+1;
            while(possibleJumpPosition <= currentPosition + D && possibleJumpPosition <= X){
                if (leafPositions[possibleJumpPosition] == 1) {
                    //frog hops, if possible, from currentPosition
                    currentPosition = possibleJumpPosition;
                }
                possibleJumpPosition++;
            }
            possibleJumpPosition--;//when possibleJumpPosition = X, while loops again, so we correct the value
            //check if frog is able to jump to 'X'
            if(possibleJumpPosition >= X){
                return time;
            }
        }
        return CANNOT_REACH_POSITION_X;
    }

    public static void main(String[] args){
        int timeFrogJumps = new FrogLeap().solution(mLeafPositions, mDestination, mJumpCapacity);
        System.out.println("EarliestTimeFrogJumps: " + timeFrogJumps);
        System.out.println("Division: " + (float)143/150);
    }
}
