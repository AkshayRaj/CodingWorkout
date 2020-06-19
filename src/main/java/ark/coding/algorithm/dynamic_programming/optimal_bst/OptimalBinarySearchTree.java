/**
 * Created by Akshayraj
 */
package ark.coding.algorithm.dynamic_programming.optimal_bst;

import java.util.Arrays;
import java.util.List;

/**
 * The solution is based on the algorithm laid out in the book -
 * Introduction to Algorithms (CLRS) 3rd edition, page 397-403
 */
public class OptimalBinarySearchTree {

    public static void main(String[] args) {
        Double[] p = new Double[]{null, 0.15, 0.10, 0.05, 0.10, 0.20}; // 1 ~> n (n) success probabilities; Value at index 0 is not used.
        Double[] q = new Double[]{0.05, 0.10, 0.05, 0.05, 0.05, 0.10}; // 0 ~> n (n+1) failure probabilities
        String[] keys = new String[]{null, "k1", "k2", "k3", "k4", "k5"}; // 1 ~> n (n) keys; Value at index 0 is not used.

        findOptimalBST(5, Arrays.asList(keys), p, q);
    }

    /**
     * Find the Optimal Binary Search Tree from a given set of keys and probabilities.
     *
     * @param n number of keys to consider for constructing an optimal BST
     * @param keys list of keys. Index of keys ranges from -
     *             1 ~> n; Value at index 0 is not used.
     * @param p Probabilities for finding a key at index i, i.e. success scenarios.
     *          where i:  1 ~> n; Value at index 0 is not used.
     * @param q Probabilities of values which are not keys, i.e. failure scenarios.
     *          Failure probablities are 1 more than number of keys. Therefore, index i of {@code q} ranges from -
     *          i: 0 ~> n;
     */
    public static void findOptimalBST(int n,
                                      List<String> keys,
                                      Double[] p,
                                      Double[] q) {

        Double[][] e = new Double[n+2][n+1]; // i: 1 ~> n+1; j: 0 ~> n (index 0 is not used for i)
        Double[][] w = new Double[n+2][n+1]; // i: 1 ~> n+1; j: 0 ~> n (index 0 is not used for i)
        int[][] root = new int[n+1][n+1];    // i: 1 ~> n  ; j: 1 ~> n (index 0 is not used for i,j)

        for (int i = 1; i <= n+1; i++) {
            e[i][i-1] = q[i-1];
            w[i][i-1] = q[i-1];
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n-l+1; i++) {
                int j = i+l-1;
                e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j - 1] + p[j] + q[j];
                for (int r = i; r <= j; r++) {
                    double t = e[i][r-1] + e[r+1][j] + w[i][j];
                    if (t < e[i][j]) {
                        e[i][j] = t;
                        root[i][j] = r;
                    }
                }
            }
        }
        /*************************************************************************************
         * Print optimal cost, and the actual tree that is optimal
         *************************************************************************************/
        Double optimalCost = e[1][n];
        System.out.println("Optimal Cost is: " + optimalCost);

        constructOptimalBST(e, root);
    }

    private static void constructOptimalBST(Double[][] e, int[][] root) {

    }
}
