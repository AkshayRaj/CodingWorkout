/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.recursion;

import ark.coding.tools.Utils;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Stack;

public class TowerOfHanoi {

    private static Stack<Integer> A;
    private static Stack<Integer> B = new Stack<>();
    private static Stack<Integer> C = new Stack<>();

    public static void main(String[] args) {
        A = new Stack<>();
        for (Integer number : new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1})
            A.push(number);

        List<Stack<Integer>> poles = ImmutableList.of(A, B, C);
        moveDiscs(10, poles);
    }

    public static void moveDiscs(int n, List<Stack<Integer>> poles) {
        Stack<Integer> src = poles.get(0);
        Stack<Integer> des = poles.get(1);
        Stack<Integer> aux = poles.get(2);

        moveDiscs(n, src, des, aux);
    }

    private static void moveDiscs(int n, Stack<Integer> src, Stack<Integer> des, Stack<Integer> aux) {
        if (n == 1) {
            des.push(src.pop());

            System.out.print("\n=================================");
            System.out.print("\nSRC: ");Utils.printList(A);
            System.out.print("\nDES: ");Utils.printList(B);
            System.out.print("\nAUX: ");Utils.printList(C);
            return;
        }

        moveDiscs(n-1, src, aux, des);
        moveDiscs(1, src, des, aux);
        moveDiscs(n-1, aux, des, src);
    }
}
