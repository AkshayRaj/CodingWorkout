/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.sort;

import java.util.PriorityQueue;

public class SortPractice {

    static int[] mergeArrays(int[][] arrays) {
        int k = arrays.length;
        int n = arrays[0].length;

        int[] res = new int[k * n];

        /**
         * 1. Make a minHeap of size `k`
         * 2. Initialize it by adding first element of all `k` arrays
         * 3. Get the min from the heap & insert it into `res`
         * 4. Identify which array the `min` belonged to. Insert the next element from that array into the minHeap
         * 5. GOTO step-3 & repeat until elements from all arrays have been inserted into the heap & then heap is empty
         */
        PriorityQueue<Element> heap = getPriorityQueue(arrays);

        for (int[] array : arrays) {
            heap.add(new Element(array, 0));
        }

        int index = 0;
        while (index < res.length) {
            Element element = heap.remove();
            res[index] = element.array[element.arrIdx];

            element.arrIdx++;
            if (element.arrIdx < n) {
                heap.add(element);
            }
            index++;
        }

        return res;
    }

    static PriorityQueue<Element> getPriorityQueue(int[][] arrays) {
        // determine if arrays are ascending or descending
        // Arrays can have duplicate elements. So check all arrays to establish the correct order.
        // If all arrays have the same element, either min or max heap will work. Otherwise select the right one.
        int k = arrays.length;
        int n = arrays[0].length; // All arrays have same length

        boolean ascending = true;
        for (int[] array : arrays) {
            if (array[0] > array[n-1]) {
                ascending = false;
                break;
            }
        }

        if (ascending) {
            return new PriorityQueue<Element>
                    (k, (e1, e2) -> e1.array[e1.arrIdx] - e2.array[e2.arrIdx]);
        }
        return new PriorityQueue<Element>
                (k, (e2, e1) -> e1.array[e1.arrIdx] - e2.array[e2.arrIdx]);
    }

    static class Element {
        final int[] array;
        int arrIdx;

        public Element(int[] array, int arrIdx) {
            this.array = array;
            this.arrIdx = arrIdx;
        }
    }
}
