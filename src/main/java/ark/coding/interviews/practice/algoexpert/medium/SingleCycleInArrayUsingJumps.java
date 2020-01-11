/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.algoexpert.io/questions/Single%20Cycle%20Check
 */
class SingleCycleInArrayUsingJumps {
    public static boolean hasSingleCycle(int[] array) {
        // Write your code here.
        final int startingIndex = 0;
        Set<Integer> indicesVisited = new HashSet<>();
        int currentIndex = startingIndex;
        while (indicesVisited.size() < array.length) {
            if (!indicesVisited.contains(currentIndex)) {
                indicesVisited.add(currentIndex);
                int jumpVector = array[currentIndex];
                int jumpVectorWithoutCycle = (Math.abs(jumpVector) % array.length) // this strips any cycles within the jump vector
                        * (Integer.signum(jumpVector)); // add back the original sign of the vector.

                // getting the vector without cycle is critical to get the proper jumpSize in forward direction.
                // this will get the right jumpSize in forward direction regardless of the original sign of the vector
                int jumpSize = (array.length + jumpVectorWithoutCycle) % array.length;
                currentIndex = (currentIndex + jumpSize) % array.length;
            }
            else {
                // cycle exists, but all indices were not traversed.
                return false;
            }
        }
        if (currentIndex == startingIndex && indicesVisited.size() > 0) {
            // for the cycle to exist, currentIndex should point back to start index after all indices are visited.
            return true;
        }
        return false;
    }
}

