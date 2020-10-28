package ark.coding.books.interview_kickstart;

import ark.coding.books.interview_kickstart.recursion.NQueens;

import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {
        for (String[] row : NQueens.find_all_arrangements(11)) {
            System.out.println(Arrays.toString(row));
        }
    }
}
