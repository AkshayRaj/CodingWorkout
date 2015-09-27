package ark.coding.exercise.pascals_triangle;

/**
 * Created by Akshayraj on 9/24/15.
 */
public class PascalTriangle {

    public static void main(String[] args){
        int triangle_height = 1;
        PascalTriangle.constructPascalTriangle(triangle_height);
        PascalTriangle.getRow(triangle_height);
    }

    private static void constructPascalTriangle(int rows){
        for(int row_no = 1; row_no <= rows; row_no++) {
            getRow(rows,row_no);
            System.out.println();
        }
    }

    private static void getRow(int rows, int rowToReturn){
        int row_no = rowToReturn;
        int element = 1;
        System.out.format("%" + (rows-row_no+1)*2 + "s","");
        for(int element_no = 1; element_no <= row_no; element_no++) {
            System.out.format("%4d",element);
            element = element * (row_no - element_no) / (element_no);
        }
    }
    /*
       Gets the elements at the specified row in a Pascal's triangle
     */
    private static int[] getRow(int rowToReturn) {
        int row_no = rowToReturn;
        int element = 1;
        int[] row = new int[row_no];
        for(int element_no = 1; element_no <= row_no; element_no++) {
            row[element_no - 1] = element;
            System.out.format("%4d",element);
            element = element * (row_no - element_no) / (element_no);
        }
        return row;
    }
}
