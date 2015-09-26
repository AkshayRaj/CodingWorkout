package ark.coding.exercise.pascals_triangle;

/**
 * Created by Akshayraj on 9/24/15.
 */
public class PascalTriangle {

    public static void main(String[] args){
        PascalTriangle.triangle(5);
    }

    private static int[] triangle(int row_no) {
        int element = 1;
        int[] row = new int[row_no];
        row[0] = element;
        System.out.println("element: " + row[0]);
        for(int i = 1 ; i < row_no ; i++ ){
            element = element * (row_no - i)/i;
            row[i] = element;
            System.out.println("element: " + row[i]);
        }
        return row;
    }
}
