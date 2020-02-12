/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.coding_patterns_course.subsets;

import java.util.*;

/**
 * https://www.educative.io/courses/grokking-the-coding-interview/B8R83jyN3KY
 */
public class Permutations {

    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> permutations = new LinkedList<>();
        permutations.add(new ArrayList<>());
        for (int currentNumber : nums) {
            // we will take all existing permutations and add the current number to create new permutations
            int setsCreatedSoFar = permutations.size();
            int count = 1;
            while(count <= setsCreatedSoFar) {
                List<Integer> oldPermutation = permutations.poll();
                // create a new permutation by adding the current number at every position
                for (int positionForAddNewNumber = 0;
                     positionForAddNewNumber <= oldPermutation.size(); // 0 ~> end of set.
                     positionForAddNewNumber++) {
                    List<Integer> newPermutation = new ArrayList<Integer>(oldPermutation);

                    // add the new number at different positions in sets created so far.
                    newPermutation.add(positionForAddNewNumber, currentNumber);
                    permutations.add(newPermutation);
                }
                count++;
            }
        }
        return new ArrayList<>(permutations);
    }

    // Recursive Solution
    public static List<List<Integer>> generatePermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generatePermutationsRecursive(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private static void generatePermutationsRecursive(
            int[] nums,
            int index,
            List<Integer> currentPermutation,
            List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(currentPermutation);
        }

        // create a new permutation by adding the current number at every position
        for (int positionForAddingNewNumber = 0;
             positionForAddingNewNumber <= currentPermutation.size();
             positionForAddingNewNumber++) {
            List<Integer> newPermutation = new ArrayList<Integer>(currentPermutation);

            // add the new number at different positions in sets created so far.
            newPermutation.add(positionForAddingNewNumber, nums[index]);

            // repeat for rest of the numbers.
            generatePermutationsRecursive(nums, index + 1, newPermutation, result);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = Permutations.findPermutations(new int[] { 1, 3, 5 });
        System.out.print("Here are all the permutations: " + result);
    }
}

