/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import java.util.*;
import java.util.stream.Collectors;

public class UniqueIntegerPermutations {
    public static List<List<Integer>> getPermutations(final List<Integer> array) {
        // Write your code here.
        List<List<Integer>> solution = new ArrayList<>();

        if (array.size() > 0) {
            Integer[] tmpForConstructingPermutationsInPlace = new Integer[array.size()];
            List<Integer> remainingNumbersToAddInPermutation = new ArrayList<>(array);
            constructPermutationOfRemainingNumbers(
                    solution,
                    tmpForConstructingPermutationsInPlace,
                    remainingNumbersToAddInPermutation,
                    0);
        }

        return solution;
    }

    /**
     * Recursive Fn:
     * 1. Iterate remaining numbers in the permutationIndex spot
     *    For each number in remaining numbers list -
     *    a) Add it to the current permutationIndex spot
     *    b) Remove the number from remaining numbers list
     *    c) Increment the permutationIndex spot to the next index, i.e. +1
     *
     *    //------------- Recurse on smaller problem set --------//
     *    d) Call function with the new values (or the updated object)
     *    //----- After recursion from smaller sized problem returns --------//
     *
     *    e) After recursion returns, add the number removed in (b) back to remaining numbers list
     *    f) permutationIndex by default returns to the same value it was when the function was invoked.
     *
     * @param solution Add each permutation calculated to the solution
     * @param tmpForConstructingPermutation kinda like a worksheet for calculating different permutations.
     * @param remainingNumbersToAddInPermutation numbers that have not been included in the permutation so far.
     * @param nextNumberIndex next spot we look at for adding numbers from the remainingNumber list.
     */
    private static void constructPermutationOfRemainingNumbers(List<List<Integer>> solution,
                                                               Integer[] tmpForConstructingPermutation,
                                                               List<Integer> remainingNumbersToAddInPermutation,
                                                               int nextNumberIndex) {
        if (remainingNumbersToAddInPermutation.isEmpty()) {
            List<Integer> oneOfThePermutationsPossible = Arrays.asList(tmpForConstructingPermutation)
                    .stream()
                    .collect(Collectors.toList());
            solution.add(oneOfThePermutationsPossible);
            return;
        }

        for (int index = 0; index < remainingNumbersToAddInPermutation.size(); index++) {
            int number = remainingNumbersToAddInPermutation.remove(index);

            tmpForConstructingPermutation[nextNumberIndex] = number;
            constructPermutationOfRemainingNumbers(
                    solution,
                    tmpForConstructingPermutation,
                    remainingNumbersToAddInPermutation,
                    nextNumberIndex + 1);
            remainingNumbersToAddInPermutation.add(index, number);
        }
    }

    public static List<List<Integer>> getPermutationsV2(final List<Integer> array) {
        // Write your code here.
        List<List<Integer>> solution = new ArrayList<>();

        if (array.size() > 0) {
            Integer[] tmpForConstructingPermutationsInPlace = new Integer[array.size()];
            Set<Integer> numbersAlreadyIncludedInPermutation = new HashSet<Integer>();
            constructPermutationOfRemainingNumbersV2(
                    solution,
                    tmpForConstructingPermutationsInPlace,
                    array,
                    numbersAlreadyIncludedInPermutation,
                    0);
        }

        return solution;
    }

    /**
     * V2 reduces need for creating new lists every time we want to pop off numbers from consideration.
     * We use a set to keep track of numbers that have already been included in the permutation.
     *
     * @param solution Populate this with the different permutations that are possible, for the given list of unique numbers.
     * @param originalArrayOfUniqueNumbers given list of unique numbers, for which we have to find permutations.
     * @param tmpForConstructingPermutation a placeholder to construct different permutation of numbers.
     *                                      This is helpful to progressively build permutations, one index at a time.
     * @param numbersAlreadyInPermutation set of numbers that have been included in the permutation so far.
     * @param nextNumberIndex the next index or slot to consider for putting rest of the numbers.
     */
    private static void constructPermutationOfRemainingNumbersV2(
            final List<List<Integer>> solution,
            Integer[] tmpForConstructingPermutation,
            final List<Integer> originalArrayOfUniqueNumbers,
            Set<Integer> numbersAlreadyInPermutation,
            int nextNumberIndex) {
        if (numbersAlreadyInPermutation.size() == originalArrayOfUniqueNumbers.size()) {
            List<Integer> oneOfThePermutationsPossible = Arrays.asList(tmpForConstructingPermutation)
                    .stream()
                    .collect(Collectors.toList());
            solution.add(oneOfThePermutationsPossible);
            return;
        }

        for (Integer nextNumber : originalArrayOfUniqueNumbers) {
            if (!numbersAlreadyInPermutation.contains(nextNumber)) {
                numbersAlreadyInPermutation.add(nextNumber);

                tmpForConstructingPermutation[nextNumberIndex] = nextNumber;
                constructPermutationOfRemainingNumbersV2(
                        solution,
                        tmpForConstructingPermutation,
                        originalArrayOfUniqueNumbers,
                        numbersAlreadyInPermutation,
                        nextNumberIndex + 1);
                numbersAlreadyInPermutation.remove(nextNumber);
            }
        }
    }
    // Time  : n!
    // Space : n!

    public static void main(String[] args) {
        getPermutations(Arrays.asList(new Integer[]{1,2,3}));
        getPermutationsV2(Arrays.asList(new Integer[]{1,2,3}));
        //
        //getPermutations(new ArrayList<Integer>());
        //System.out.println(new ArrayList<>(10).size());
    }
}
