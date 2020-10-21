package ark.coding.books.interview_kickstart.sort;

import java.util.List;

public class MergeSort {
    public static List<Integer> merge_sort(List<Integer> arr) {
        divideAndConquer(arr, 0, arr.size()-1);
        return arr;
    }

    private static void divideAndConquer(List<Integer> arr, int start, int end) {
        if (start == end) return;

        int mid = (start+end)/2;

        // Divide the current level
        divideAndConquer(arr, start, mid);
        divideAndConquer(arr, mid+1, end);

        // Conquer the current level
        // We are basically dealing with two sorted arrays -
        // 1. From start ~> mid
        // 2. From mid+1 ~> end
        // The task is to merge two sorted arrays !
        int[] merge = new int[end-start+1];
        int idxLeft = start;
        int idxRight = mid+1;
        int idxMerge = 0;
        while (idxLeft <= mid && idxRight <= end) {
            if (arr.get(idxLeft) < arr.get(idxRight)) {
                merge[idxMerge] = arr.get(idxLeft);
                idxLeft++;
            }
            else {
                merge[idxMerge] = arr.get(idxRight);
                idxRight++;
            }
            idxMerge++;
        }

        copyLeftOutstandingIfAny: while (idxLeft <= mid) {
            merge[idxMerge] = arr.get(idxLeft);
            idxLeft++;
            idxMerge++;
        }
        copyRightOutstandingIfAny: while (idxRight <= end) {
            merge[idxMerge] = arr.get(idxRight);
            idxRight++;
            idxMerge++;
        }
        int idxArr = start;
        for (int number : merge) {
            arr.set(idxArr, number);
            idxArr++;
        }
    }
}
