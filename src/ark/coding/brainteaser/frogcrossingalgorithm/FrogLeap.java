package ark.coding.brainteaser.frogcrossingalgorithm;

import ark.coding.Solution;

/**
 * Created by Akshayraj on 11/14/15.
 */
public class FrogLeap implements Solution<Integer>{
    private static final int CANNOT_REACH_POSITION_X = -1;
    private static int[] mLeafPositions = {
            4,
            1,
            5,
            6,
            4
    };
    private static int mDestination = 4;
    private static int mJumpCapacity = 4;
    @Override
    public Integer solution(Object... args) {
        return getEarliestTime((int[]) args[0],(int) args[1],(int) args[2]);
    }

    private Integer getEarliestTime(int[] A, int X, int D) {
        int lastLeafTime = A.length - 1;//time last leaf fell is length of array - 1, as array-index is 0-based.
        int leafBitMap[] = new int[X+1];
        int positionLeafFell = 0;
        leafBitMap[0] = 1;//at position 0, we assume "leaf is present"
        for(int time = 0; time <= lastLeafTime; time++){
            positionLeafFell = A[time];
            leafBitMap[positionLeafFell] = 1;
            System.out.println("positionLeafFell: " + positionLeafFell);
            //check if frog can travel from position '0' to position 'X'
            int currentPosition = 0;
            do{
                if(leafBitMap[currentPosition] == 1) {
                    int possibleJumpPosition = currentPosition+1;
                    do{
                        if(possibleJumpPosition <= X) {
                            if (leafBitMap[possibleJumpPosition] == 1) {
                                currentPosition = possibleJumpPosition;//frog hops if leaf present within D from currentPosition
                            }
                            possibleJumpPosition++;
                        }
                    }while(possibleJumpPosition <= currentPosition + D &&
                            possibleJumpPosition < X);//this ensures exit from while loop, when possibleJumpPosition == X
                    if(possibleJumpPosition == X){
                        return time;
                    }
                }else{
                    break;
                }
                currentPosition++;
            }while(currentPosition <= X);
        }
        return CANNOT_REACH_POSITION_X;
    }

    public static void main(String[] args){
        int timeFrogJumps = new FrogLeap().solution(mLeafPositions, mDestination, mJumpCapacity);
        System.out.println("timeFrogJumps: " + timeFrogJumps);
    }


}
