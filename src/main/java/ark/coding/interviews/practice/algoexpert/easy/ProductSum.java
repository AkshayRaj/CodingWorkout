/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

import java.util.List;

/**
 * An array can contain either integers, or arrays of integers.
 * Thus, an integer can be at depth 1, i.e. in the outer array, or at depth n, i.e. the innermost array.
 * Such an array is represented as a {@link List} of {@link Object}s.
 * At each depth level, the sum of the integers should be multiplied by the depth of the array.
 *
 * We want the sum of all integers in the original list of objects.
 *
 * Eg,
 * [x,y]             ~> x + y
 * [x,[a,b], y]      ~> x + 2(a+b) + y
 * [x,[a,b,[m,n]],y] ~> x + 2(a+b) + 3(m+n) + y
 */
public class ProductSum {

    public static int productSum(List<Object> array) {
        return addToSum(1, array);
    }

    private static int addToSum(int depth, List<Object> array) {
        Integer sumAtCurrentDepth = 0;
        for (Object item: array) {
            if (item instanceof List) {
                List<Object> innerArray = (List<Object>) item;
                int innerArrayDepth = depth + 1;
                sumAtCurrentDepth = sumAtCurrentDepth + addToSum(innerArrayDepth, innerArray);
            }
            else {
                sumAtCurrentDepth = sumAtCurrentDepth + (Integer) item;
            }
        }
        return depth * sumAtCurrentDepth;
    }
}
