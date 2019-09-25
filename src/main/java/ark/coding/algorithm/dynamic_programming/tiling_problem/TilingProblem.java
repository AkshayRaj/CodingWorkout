/**
 * Created by Akshayraj
 */
package ark.coding.algorithm.dynamic_programming.tiling_problem;

import ark.coding.Solution;

public class TilingProblem implements Solution<Long> {
    @Override
    public Long solution(Object... args) {
        return calculateNoOfWaysToTileTheBoard((Integer) args[0]);
    }

    /**
     * If the first tile is laid vertically, & so the rest of the tiles remaining are (n-1),
     * if the first file is laid horizontally, then the second tile also has to be laid horizontally,
     * so the rest of the tiles are (n-2).
     * The problem breaks down into arranging the rest of the (n-1) or (n-2) tiles.
     * Thus, we can break the problem of arranging the tiles into:
     * f(n) = f(n-1) + f(n-2)
     *
     * @param n the length of the board.
     * @return permutations possible for arranging tiles of '2x1' into an '2xn' board;
     */
    private Long calculateNoOfWaysToTileTheBoard(int n) {
        long[] waysToTile = new long[n+1];
        switch (n) {
            case 0:
                return 0L;
            case 1:
                return 1L;
            case 2:
                return 2L;
            default:
                waysToTile[0] = 0;
                waysToTile[1] = 1;
                waysToTile[2] = 2;
                for (int boardLength = 3; boardLength <= n; boardLength++) {
                    waysToTile[boardLength] = waysToTile[boardLength - 1] + waysToTile[boardLength - 2];
                }
                return waysToTile[n];
        }
    }

    public static void main(String[] args) {
        TilingProblem tilingProblem = new TilingProblem();

        int n = 4; // 5
        //int n = 5;// 8
        //int n = 42;// 433494437
        //int n = 58; // 956722026041

        Long waysToTile = tilingProblem.solution(n);
        System.out.println("Ways to tile the board: " + waysToTile);
    }
}
