/**
 * Created by Akshayraj
 */
package ark.coding.datastructure.primitive_datastructures.arraysandstrings.new_year_chaos;

import java.util.Scanner;

public class Solution {

    /**
     * For the full problem description, check
     * https://www.hackerrank.com/challenges/new-year-chaos/problem?isFullScreen=true
     *
     * Solving Approach:-
     * My first approach to this problem, was to go from start to end (0 to length-1),
     * and keep track of persons (in memory) who were both "briber" as well as "bribee";
     * It's straightforward to know how many bribes were given by people who were ahead in the queue,
     * but it was not straightforward to understand if a person has not given <i>any</i> bribe,
     * if he is in the original position; either because -
     * 1. he did not accept any bribes
     * 2. He accepted a bribe, and gave one, to come back to his original position.
     * That approach led me down to a rabbithole, and ultimately I was not able to write a solution which passed all 12 test cases.
     *
     * SECOND APPROACH:-
     * I came across this solution on the web, namely - https://www.youtube.com/watch?v=YWYF6bOhPW8
     * This approach is position-centric, as opposed to the person-centric approach in the previous attempt.
     * - We start from the end of the queue, and check if the position in the queue has the expected person,
     * or if the expected person has bribed towards the head by 1 or 2 places.
     * - If the expected peron is not found in the current position, we restore the original position of the person,
     * and slide our "window" to the left.
     * - This way we preserve the ordering of the "bribees" and count the number of bribes that have taken place,
     *   until the current position under consideration.
     *
     * Glossary:
     * Briber: Person who has given a bribe to move up in the queue.
     * Bribee: Person who has accepted a bribe, moved down in the queue.
     *
     * @param queue current state of the queue.
     * @return minimum number of bribes to get the queue to its given state.
     */
    public static void minimumBribes(int[] queue) {
        int lastElementIndex = queue.length - 1;

        int noOfTotalMinimumBribes = 0;
        for (int index = lastElementIndex; index >= 0; index--) {
            int actualPerson = queue[index];
            int expectedPerson = index + 1;// queue goes from 1 -> n; OR n -> 1 (for our loop)

            // Check if the position has the expected person;
            // - If not, then check if the expected person has bribed once or twice;
            // - Then correct the position of the expected person,
            //   & move either one or both persons (respectively) to the left.
            int bribesGivenByExpectedPerson = 0;
            if (expectedPerson != actualPerson) {
                if (queue[index-1] == expectedPerson) {
                    // this means expectedPerson = queue[index-1]
                    swap(queue, index, index-1);
                    bribesGivenByExpectedPerson = 1;
                } else if (queue[index-2] == expectedPerson) {
                    // this means expectedPerson = queue[index-2]
                    // Note:- Swapping first index-2 with index-1, and then index-1 with index
                    //        maintains the ordering between the elements on the right of the expectedPerson.
                    swap(queue, index - 2, index - 1);
                    swap(queue, index - 1, index);
                    bribesGivenByExpectedPerson = 2;
                } else {
                    // Expected person has bribed more than 2 times.
                    System.out.println("Too chaotic");
                    return;
                }
            }
            noOfTotalMinimumBribes = noOfTotalMinimumBribes + bribesGivenByExpectedPerson;
        }
        System.out.println(noOfTotalMinimumBribes);
        /**
         * Some after thoughts to this approach:
         * One of the benefits of going from end of the array towards the start is, we can "look-ahead" two places,
         * and make a unambiguous decision regarding the person who is expected to be at a given position.
         * Since, a person can <i>only</i> move two places up (remember: he can give only two bribes), the "look-ahead"
         * is easier to perform.
         *
         * One the other hand, there is no limit to the number of bribes a person can accept.
         * If we iterate from 0 -> n, then the "look-ahead" is not definitive;
         * a person could be at the last place in the list, as he can take as many bribes.
         *
         * Iteration from end -> start :
         * As we iterate from the end of the array towards the start, we "swap" the persons, which have bribed their way up.
         * By swapping, we reduce the "window" size of the array, thus reducing our problem size.
         * One important thing we observe is the order of the "bribees", while performing the swap -
         * - If the expected person has bribed two places up, we keep the order between the i-1 & i-1 persons in the current
         *   state of the queue. This gives us fidelity that other bribes performed are not "undone" accidentally.
         *
         * ==========
         * Actively changing the state of the {@code queue} by swapping, reduces the need to hold additional data/calculations in the memory.
         * By "undoing" the bribes, we actively try to restore the original state of the queue, one swap at a time.
         * So, our solution reduces to keep track of "swaps" which undo the bribes.
         *
         */
    }

//    private static int undoBribes(final int[] queue, final int person, final int currentPositionInQueue) {
//        int expectedPositionInQueue = person - 1;
//        int newPositionInQueue = currentPositionInQueue;
//
//        int noOfBribes = expectedPositionInQueue - currentPositionInQueue;
//        for (int count = 1; count <= noOfBribes; count++) {
//            swap(queue, newPositionInQueue, newPositionInQueue + 1);
//            newPositionInQueue++;
//        }
//
//        return noOfBribes;
//    }

    /**
     * Swapping is very inherent to this solution.
     * One can try to "formulize" ways to count the number of bribes.
     * However, swap has occurred, which changed the state of the queue;
     * therefore a swap in the opposite direction should happen to restore the original state of the queue, i.e sorted array of integers.
     */
    public static void swap(int[] array, int element1Index, int element2Index) {
        int tmp = array[element1Index];
        array[element1Index] = array[element2Index];
        array[element2Index] = tmp;
    }

    // Complete the minimumBribes function below.
    public static void minimumBribesWithBubbleSort(int[] queue) {
        int swap = 0;
        int bribes;
        int pos = 0;
        for (int index = queue.length - 1; index >= 0; index--) {
            int j = 0;

            bribes = queue[pos] - (pos + 1);
            if (bribes > 2) {
                System.out.println("Too chaotic");
                return;
            }

            // exclude "1" & "2"
            if (queue[index] - 2 > 0) {
                j = queue[index] - 2;
            }

            while (j <= index) {
                if (queue[j] > queue[index]){
                    swap++;
                }
                j++;
            }
            pos++;
        }
        System.out.println(swap);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribesWithBubbleSort(q);
        }

        scanner.close();
    }
}
