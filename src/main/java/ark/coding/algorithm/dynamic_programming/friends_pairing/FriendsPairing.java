/**
 * Created by Akshayraj
 */
package ark.coding.algorithm.dynamic_programming.friends_pairing;

import ark.coding.Solution;

public class FriendsPairing implements Solution<Integer> {
    @Override
    public Integer solution(Object... args) {
        //return findDistinctFriendPairingsRecursive((Integer) args[0]);
        return findDistinctFriendPairingsUsingDynamicProgramming((Integer) args[0]);
    }

    /**
     * Iterative; using Dynamic Programming
     */
    private Integer findDistinctFriendPairingsUsingDynamicProgramming(int numberOfPeople) {
        // index indicates number of people;
        // array[index] contains number of pairings for (@index) number of people
        int[] noOfPairings = new int[numberOfPeople + 1]; // noOfPairings[numberOfPeople] will contain the solution

        noOfPairings[0] = 0; // base case
        noOfPairings[1] = 1; // base case
        noOfPairings[2] = 2; // base case

        for (int index = 3; index <= numberOfPeople; index++) {
            noOfPairings[index] = noOfPairings[index-1]
                    + (index - 1) * noOfPairings[index-2];
        }

        return noOfPairings[numberOfPeople];
    }

    /**
     * Recursive solution
     */
    private Integer findDistinctFriendPairingsRecursive(int numberOfPeople) {
        if (numberOfPeople == 0) {
            return 0;
        }
        if (numberOfPeople == 1) {
            return 1;
        }
        if (numberOfPeople == 2) {
            return 2;
        }

        return
                // nth person has two choices - either he chooses to be single, or pairs up with n-1th person.
                // nth person is single; recurse for n-1th person
                findDistinctFriendPairingsRecursive(numberOfPeople - 1)
                // nth person pairs with n-1th person;
                // There are n-1 different persons n can pair up with; and then we recurse for the rest of n-2th persons.
                + (numberOfPeople - 1) * (findDistinctFriendPairingsRecursive(numberOfPeople - 2));
    }

    public static void main(String[] args) {
        FriendsPairing friendsPairing = new FriendsPairing();

        int test1 = 4;

        int distinctFriendPairings = friendsPairing.solution(test1);
        System.out.println("No of distinct friend pairings: " + distinctFriendPairings);
    }
}
