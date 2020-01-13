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

    private static void constructPermutationOfRemainingNumbers(List<List<Integer>> solution,
                                                               Integer[] tmpForConstructingPermutation,
                                                               List<Integer> numbersToAddInPermutation,
                                                               int nextNumberIndex) {
        if (numbersToAddInPermutation.isEmpty()) {
            List<Integer> oneOfThePermutationsPossible = Arrays.asList(tmpForConstructingPermutation)
                    .stream()
                    .collect(Collectors.toList());
            solution.add(oneOfThePermutationsPossible);
            return;
        }

        for (int index = 0; index < numbersToAddInPermutation.size(); index++) {
            List<Integer> remainingNumbersToAddInPermutation = new ArrayList<Integer>(numbersToAddInPermutation);
            int number = remainingNumbersToAddInPermutation.remove(index);

            tmpForConstructingPermutation[nextNumberIndex] = number;
            constructPermutationOfRemainingNumbers(
                    solution,
                    tmpForConstructingPermutation,
                    remainingNumbersToAddInPermutation,
                    nextNumberIndex + 1);
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
                    array,
                    tmpForConstructingPermutationsInPlace,
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
            final List<Integer> originalArrayOfUniqueNumbers,
            Integer[] tmpForConstructingPermutation,
            Set<Integer> numbersAlreadyInPermutation,
            int nextNumberIndex) {
        if (numbersAlreadyInPermutation.size() == originalArrayOfUniqueNumbers.size()) {
            List<Integer> oneOfThePermutationsPossible = Arrays.asList(tmpForConstructingPermutation)
                    .stream()
                    .collect(Collectors.toList());
            solution.add(oneOfThePermutationsPossible);
            return;
        }

        for (int index = 0; index < originalArrayOfUniqueNumbers.size(); index++) {
            int nextNumber = originalArrayOfUniqueNumbers.get(index);
            if (!numbersAlreadyInPermutation.contains(nextNumber)) {
                numbersAlreadyInPermutation.add(nextNumber);

                tmpForConstructingPermutation[nextNumberIndex] = nextNumber;
                constructPermutationOfRemainingNumbersV2(
                        solution,
                        originalArrayOfUniqueNumbers,
                        tmpForConstructingPermutation,
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
