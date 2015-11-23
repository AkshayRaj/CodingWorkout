package ark.coding.brainteaser.frogcrossingalgorithm;

import ark.coding.Solution;

/**
 * Created by Akshayraj on 11/14/15.
 */
public class FrogLeap implements Solution<Integer>{
    private static final int CANNOT_REACH_POSITION_X = -1;
    private static int[] leafPositions = {
            1,
            4,
            5,
            6,
            2
    };
    private static int destination = 7;
    private static int jumpCapacity = 3;
    @Override
    public Integer solution(Object... args) {
        return getEarliestTime((int[]) args[0],(int) args[1],(int) args[2]);
    }

    private Integer getEarliestTime(int[] A, int X, int D) {
        int size = A.length;
        int leafPresent[] = new int[X+1];
        leafPresent[0] = 1;
        int positionLeafFell;
        for(int sec = 0; sec < size; sec++){
            positionLeafFell = A[sec];
            leafPresent[positionLeafFell] = 1;
            System.out.println("positionLeafFell: " + positionLeafFell);
            //check if frog can travel from position '0' to position 'X'
            int currentPosition = 0;
            int j = 0;
            do{
                System.out.println("=======");
                if(leafPresent[j] == 1) {
                    currentPosition = j;
                    int k = j+1;
                    do{
                        if(k <= X) {
                            System.out.println(" k: " + k);
                            if (leafPresent[k] == 1) {
                                currentPosition = k;
                                j = k;//frog hops if leaf present within D from currentPosition
                                System.out.println("j: " + j + " k: " + k);
                            }
                            k++;
                        }
                    }while(k <= j + D && k < X);
                    if(k == X){
                        return sec;
                    }
                }else{
                    break;
                }
                j++;
            }while(j <= X);
        }
        return CANNOT_REACH_POSITION_X;
    }

    public static void main(String[] args){
        int timeFrogJumps = new FrogLeap().solution(leafPositions, destination, jumpCapacity);
        System.out.println("timeFrogJumps: " + timeFrogJumps);
    }


}
