/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ark.coding.tools.Utils.swapElements;

/**
 * https://www.algoexpert.io/questions/Min%20Heap%20Construction
 */
// Do not edit the class below except for the buildHeap,
// siftDown, siftUp, peek, remove, and insert methods.
// Feel free to add new properties and methods to the class.
class MinHeap {
    List<Integer> heap = new ArrayList<Integer>();

    public MinHeap(List<Integer> array) {
        heap = buildHeap(array);
    }

    // parent = (i-1)/2
    // left =  2i + 1
    // right = 2i + 2
    public List<Integer> buildHeap(List<Integer> array) {
        // Write your code here.
        for (Integer number : array) {
            insert(number);
        }
        return heap;
    }

    public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
        // Write your code here.
        int index = currentIdx;
        int leftChild =  (2 * index) + 1;
        int rightChild = (2 * index) + 2;
        while (index <= endIdx && leftChild <= endIdx && rightChild <= endIdx) {
            if (heap.get(index) > heap.get(leftChild) || heap.get(index) > heap.get(rightChild)) {
                if (heap.get(leftChild) < heap.get(rightChild)) {
                    swapElements(heap, index, leftChild);
                    index = leftChild;
                } else {
                    swapElements(heap, index, rightChild);
                    index = rightChild;
                }
                leftChild = (2 * index) + 1;
                rightChild = (2 * index) + 2;
            }
            else {
                break;
            }
        }
    }

    public void siftUp(int currentIdx, List<Integer> heap) {
        // Write your code here.
        int index = currentIdx;
        int parentIndex = (index-1)/2;
        while (parentIndex >= 0) {
            if (heap.get(index) < heap.get(parentIndex)) {
                swapElements(heap, index, parentIndex);
                index = parentIndex;
                parentIndex = (index - 1) / 2;
            }
            else {
                break;
            }
        }
    }

    public int peek() {
        // Write your code here.
        return heap.get(0);
    }

    public int remove() {
        // Write your code here.
        int root = heap.get(0);
        int lastElement = heap.get(heap.size()-1);
        heap.set(0, lastElement); // I used List#add function, and spend hours fixing the "bug". List#set "replaces" the value at the given index.
        heap.remove(heap.size()-1);
        siftDown(0, heap.size()-1, heap);
        return root;
    }

    public void insert(int value) {
        // Write your code here.
        heap.add(value);
        siftUp(heap.size()-1, heap);
    }

    public static void main(String[] args) {
        MinHeap test3 = new MinHeap(new ArrayList<Integer>(
                Arrays.asList(48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41)
        ));
        printHeap(test3.heap, "Original Heap");

        test3.insert(76);
        printHeap(test3.heap, "Insert 76");

        int removedElement = test3.remove();
        printHeap(test3.heap, "Remove " + removedElement);

        removedElement = test3.remove();
        printHeap(test3.heap, "Remove " + removedElement);

        test3.insert(87);
        printHeap(test3.heap, "Insert 87");

        for (int index = test3.heap.size()-1; index >= 0; index--) {
            int parentIdx = (index - 1)/2;
            if (parentIdx < 0) {
                break;
            }
            if (!(test3.heap.get(index) >= test3.heap.get(parentIdx))) {
                throw new RuntimeException(String.format("Element {%d} at heap[%d] is not greater than parent {%d} at heap[%d].",
                        test3.heap.get(index), index, test3.heap.get(parentIdx), parentIdx));
            }
        }
    }

    private static void printHeap(List<Integer> heap) {
        printHeap(heap, "");
    }

    private static void printHeap(List<Integer> heap, String header) {
        System.out.println("\n\n=============================================================================================================");
        System.out.println(String.format("                                        %s                                 ", header));
        System.out.println("=============================================================================================================");
        int height = 0;
        int maxNodes = 1;
        while (maxNodes < heap.size()) {
            maxNodes = maxNodes * 2;
            height++;
        }
        int spaceSize = (int) Math.pow(2, height);
        int idx = 0;
        int level = 1;
        while (idx < heap.size()) {
            System.out.print("\n" + insertSpace((spaceSize/2)+1));
            while (idx < Math.pow(2, level) - 1 && idx < heap.size()) {
                System.out.print(heap.get(idx) + insertSpace(spaceSize-1));
                idx++;
            }
            level++;
            spaceSize = Math.floorDiv(spaceSize, 2);
        }
    }

    private static String insertSpace(int i) {
        StringBuilder tabs = new StringBuilder();
        int counter = 0;
        while (counter <= i) {
            tabs.append("\t");
            counter++;
        }
        return tabs.toString();
    }
}