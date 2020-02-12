/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.coding_patterns_course.two_heaps;

import java.util.PriorityQueue;

/**
 * https://www.educative.io/courses/grokking-the-coding-interview/JP8VKGOEpXl
 *
 * Given list of intervals, find the next interval for each interval in the list.
 * The "Next Interval" is defined as -
 * Min (interval[n].start, interval[n-1].start ... interval[i+1].start) >= interval[i].end
 * i.e. The interval with the smallest start time,
 * from the set of intervals whose start times are greater than end time of current interval,
 * For eg,
 * Next interval of [3,4] in the following list is [5,6],
 * as the start time of "5" is the smallest among the start times greater than 4 (end time)
 * [[1,2], [0,3], [6,7], [3,4], [5,6]]
 *
 * Note: the given list of intervals is not sorted according to any criteria.
 */
public class NextInterval {
    public static int[] findNextInterval(Interval[] intervals) {
        int noOfIntervals = intervals.length;

        // startTimesMaxHeap & endTimesMaxHeap will contain indices (and not the actual start/end times)
        // The indices in these heaps are prioritized by the values of start & end times respectively.
        PriorityQueue<Integer> startTimesMaxHeap = new PriorityQueue<Integer>
                (noOfIntervals, (i1, i2) -> intervals[i2].start - intervals[i1].start);
        PriorityQueue<Integer> endTimesMaxHeap = new PriorityQueue<Integer>
                (noOfIntervals, (i1, i2) -> intervals[i2].end - intervals[i1].end);

        // solution cells contains indices of next intervals.
        // solution index points to the original index in `intervals` array
        int[] solution = new int[noOfIntervals];
        for (int intervalIndex = 0; intervalIndex < noOfIntervals; intervalIndex++) {
            startTimesMaxHeap.offer(intervalIndex);
            endTimesMaxHeap.offer(intervalIndex);
        }

        // go through all the intervals to find each interval's next interval
        // the iteration starts with the interval with highest endTime
        int intervalCount = 1;
        while (intervalCount <= noOfIntervals) {
            int indexOfIntervalWithHighestEndTime = endTimesMaxHeap.poll(); // let's find the next interval of the interval which has the highest 'end'
            solution[indexOfIntervalWithHighestEndTime] = -1; // defaults to -1
            if (intervals[startTimesMaxHeap.peek()].start >= intervals[indexOfIntervalWithHighestEndTime].end) {
                int indexOfIntervalWithHighestStartTime = startTimesMaxHeap.poll();
                // find the interval that has the smallest 'startTime'
                while (!startTimesMaxHeap.isEmpty() && intervals[startTimesMaxHeap.peek()].start >= intervals[indexOfIntervalWithHighestEndTime].end) {
                    indexOfIntervalWithHighestStartTime = startTimesMaxHeap.poll();
                }
                solution[indexOfIntervalWithHighestEndTime] = indexOfIntervalWithHighestStartTime;

                // put the interval back as it could be the next interval of other intervals
                startTimesMaxHeap.add(indexOfIntervalWithHighestStartTime);
            }
            intervalCount++;
        }
        return solution;
    }

    static class Interval {
        int start = 0;
        int end = 0;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Interval[] intervals = new Interval[] { new Interval(2, 3), new Interval(3, 4), new Interval(5, 6) };
        int[] result = NextInterval.findNextInterval(intervals);
        System.out.print("Next interval indices are: ");
        for (int index : result)
            System.out.print(index + " ");
        System.out.println();

        intervals = new Interval[] { new Interval(3, 4), new Interval(1, 5), new Interval(4, 6) };
        result = NextInterval.findNextInterval(intervals);
        System.out.print("Next interval indices are: ");
        for (int index : result)
            System.out.print(index + " ");
    }
}
