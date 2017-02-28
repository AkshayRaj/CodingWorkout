package ark.coding.exercise.pascals_triangle;

import java.util.Scanner;

/**
 * Created by Akshayraj on 9/24/15.
 */
public class PascalTriangle {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the row number upto which Pascal's triangle has to be printed: ");
        int triangle_height = scanner.nextInt();
        constructUsingPreviousRow(triangle_height); // uses two int[]
        constructUsingRecursion(triangle_height); // recursion fn
        constructUsingRowPattern(triangle_height); // identifies a simple pattern seen in rows
        constructUsingBinomialCoeff(triangle_height); // binomial coeff of (x+y)^n
        //PascalTriangle.getRow(triangle_height);
    }

    public static void constructUsingPreviousRow(int n) {
        int[] previousRow;
        int[] currentRow = {1};
        printArray(currentRow);
        previousRow = currentRow;
        for (int i = 2; i <= n; i++) {
            currentRow = new int[i];
            currentRow[0] = 1;
            currentRow[i - 1] = 1;
            for (int j = 0; j <= i - 3; j++) {
                currentRow[j + 1] = previousRow[j] + previousRow[j + 1];
            }
            printArray(currentRow);
            previousRow = currentRow;
        }
    }

    public static void constructUsingRecursion(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(getElementUsingRecursion(i, j) + " ");
            }
            System.out.println();
        }
    }

    public static int getElementUsingRecursion(int i, int j) {
        if (j == 0) {
            return 1;
        } else if (j == i) {
            return 1;
        } else {
            return getElementUsingRecursion(i - 1, j - 1) + getElementUsingRecursion(i - 1, j);
        }

    }

    private static void constructUsingRowPattern(int rows){
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

    public static void constructUsingBinomialCoeff(int row) {
        for (int n = 0; n < row; n++) {
            for (int k = 0; k <= n; k++) {
                System.out.print(getBinomialElement(n, k) + " ");
            }
            System.out.println();
        }
    }

    public static int getBinomialElement(int n, int k) {
        int numerator = fact(n);
        int denominator = fact(k) * fact(n - k);
        int result = (int) (numerator / denominator);
        return result;
    }

    public static int fact(int num) {
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result = result * i;
        }
        return result;
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
