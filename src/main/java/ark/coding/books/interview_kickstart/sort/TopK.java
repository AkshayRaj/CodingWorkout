/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.sort;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class TopK {

    static int[] topKPriorityQueue(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (i2, i1) -> i1 - i2);

        for (int number : arr) {
            maxHeap.add(number);
        }

        Set<Integer> set = new HashSet<>();
        while (!maxHeap.isEmpty() && set.size() < k) {
            set.add(maxHeap.poll());
        }

        int[] result = new int[set.size()];
        int index = 0;
        for (Integer number : set) {
            result[index] = number;
            index++;
        }

        return result;
    }
}
