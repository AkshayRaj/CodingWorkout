package ark.coding.exercise.number_in_intervals;

import java.util.ArrayList;

/**
 * Created by Akshayraj on 2/19/16.
 */
public class Intervals {
    ArrayList<Interval> intervalList = new ArrayList<Interval>();
    public Intervals() {
        // Preprocessing ; intervalList stored in sorted order
        Interval interval1 = new Interval(10, 20);
        Interval interval2 = new Interval(35, 36);
        Interval interval4 = new Interval(40,45);
        Interval interval3 = new Interval(50, 70);
        intervalList.add(interval1);
        intervalList.add(interval2);
        intervalList.add(interval4);
        intervalList.add(interval3);
    }

    public static void main(String[] args){
        Intervals intervals = new Intervals();
        long numberInInterval = 45;//use this to test assertTrue
        long numberNotInInterval = 38;//use this to test assertFalse
        System.out.println("is " + numberInInterval + " in interval: " + intervals.isNumberInInterval(numberInInterval));
        System.out.print("is " + numberNotInInterval + " in interval: " + intervals.isNumberInInterval(numberNotInInterval));
    }

    public boolean isNumberInInterval(long number){
        //sorted intervalList, i.e. previousInterval.upper <= nextInterval.lower
        int i = intervalList.size()/2;
        int j = i;
        do{
            if(number < intervalList.get(j).mLowerBound){
                j = (int) (j - Math.ceil(j/2.0));
            } else if(number > intervalList.get(j).mUpperBound){
                j = (int) (j + Math.ceil(j/2.0));
            } else if(number >= intervalList.get(j).mLowerBound || number <= intervalList.get(j).mUpperBound){
                return true;
            }
            i++;
        }while(i < intervalList.size());
        return false;
    }

    private class Interval {
        public long mLowerBound;
        public long mUpperBound;

        public Interval(long lowerBound, long upperBound) {
            mLowerBound = lowerBound;
            mUpperBound = upperBound;
        }
    }
}
