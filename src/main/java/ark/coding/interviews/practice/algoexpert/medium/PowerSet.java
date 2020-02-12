/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://www.algoexpert.io/questions/Powerset
 * The approach implemented in both of the {@link #buildPowerSet(List, Deque, List)}
 * and {@link #buildPowerSetV2(List, List, Deque, int)} methods follows the Depth-FirstlApproach.
 *
 * We can also find the powerset using a Breadth-First-Approach -
 * Check {@link ark.coding.interviews.practice.coding_patterns_course.subsets.Subsets} for BFS implementation
 */
public class PowerSet {
    public static List<List<Integer>> powerset(List<Integer> array) {
        List<List<Integer>> solution = new ArrayList<>();
        solution.add(new ArrayList<>());

        // used for constructing a single subset at a time of the original array of integers.
        Deque<Integer> tmpSubset = new ArrayDeque<>();

        // v1 maintains a list of remaining numbers to consider for building a subset
        // this leads to creating additional list objects at each level of the function stack.
        List<Integer> remainingNumbersToConsiderForSubset = new ArrayList<>();
        buildPowerSet(solution, tmpSubset, remainingNumbersToConsiderForSubset);

        // v2 keeps track of an index in the original array,
        // which is used to determine remaining numbers to consider for subsequent subsets.
        int startingIndexOfRemainingNumbers = 0;
        buildPowerSetV2(solution, array, tmpSubset, startingIndexOfRemainingNumbers);

        return solution;
    }
    // We build 2^n subsets for each list of integers, of size 'n'
    // i.e. for len : 0 ~> n, 2^len subsets get built.
    // Therefore, time & space converge towards (n * 2^n) complexity
    // Time  : O(n * 2^n)
    // Space : O(n * 2^n)

    /**
     * Build powerset of the integers in {@code remainingNumbers} list.
     *
     * @param solution List of subsets that will contain the final power-set
     * @param remainingNumbers list of numbers to consider for building the powerset.
     * @param tmpSubset a temporary array/list for calculating different subsets of the original array
     */
    private static void buildPowerSet(
            List<List<Integer>> solution,
            Deque<Integer> remainingNumbers,
            List<Integer> tmpSubset) {
        while (!remainingNumbers.isEmpty()) {
            int number = remainingNumbers.pop();
            tmpSubset.add(number);
            solution.add(new ArrayList<>(tmpSubset));

            buildPowerSet(
                    solution,
                    new ArrayDeque<>(remainingNumbers),
                    tmpSubset);
            tmpSubset.remove(tmpSubset.size()-1);
        }
    }

    /**
     *
     * Build power-set of integers from the {@code originalArray}
     * Only consider the integers from {@code startingIndex} to  {@code originalArray.size()-1},
     * i.e. from {@code startingIndex} to the last element.
     *
     * @param solution List of subsets that will contain the final power-set
     * @param originalArray original array of integers
     * @param tmpSubset a temporary array/list for calculating different subsets of the original array
     * @param startingIndexToDetermineRemainingNumbers
     *        indicates the range of the integers to consider from {@code originalArray}
     *        (startingIndex ~> end of original array)
     */
    private static void buildPowerSetV2(
            final List<List<Integer>> solution,
            final List<Integer> originalArray,
            Deque<Integer> tmpSubset,
            int startingIndexToDetermineRemainingNumbers) {
        while (startingIndexToDetermineRemainingNumbers < originalArray.size()) {
            int number = originalArray.get(startingIndexToDetermineRemainingNumbers);
            tmpSubset.push(number);
            solution.add(new ArrayList<>(tmpSubset));

            buildPowerSetV2(
                    solution,
                    originalArray,
                    tmpSubset,
                    startingIndexToDetermineRemainingNumbers + 1);
            tmpSubset.pop();
            startingIndexToDetermineRemainingNumbers++;
        }
    }
    // Time  : 2^n (there are 2^n subsets)
}
