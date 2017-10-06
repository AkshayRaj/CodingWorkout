package ark.coding.interviews.ifit;

public class Solution {

    public static void main(String[] args){
        int[] a = {3,1,2,3};
        System.out.println("Criteria: " + tripleThreat(a));
    }

    /*
     * Complete the function below.
     */
    static int tripleThreat(int[] a) {
        int size = a.length;

        if(size < 3){
            return 0;
        }

        for(int index = 0; index < size - 3; index++) {
            int one = a[index];
            int two = a[index+1] + 1 ;
            int three = a[index+2] + 2;
            if (one == two) {
                if (one == three) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
