package ark.coding.datastructure.primitive_datastructures.arraysandstrings;

/**
 * Created by Akshayraj on 11/28/15.
 */
public class MatrixUtil {

    public static void printMatrix(int[][] matrixToPrint){
        int rows = matrixToPrint.length;
        int columns = matrixToPrint[0].length;
        System.out.println("\nMATRIX: ");
        for(int row = 0; row < rows; row++){
            System.out.print("\n");
            for(int column = 0 ; column < columns; column++){
                System.out.print(" " + matrixToPrint[row][column]);
            }
        }
        System.out.print("\n");
    }
}
