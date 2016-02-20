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
        Interval interval3 = new Interval(50, 70);
        intervalList.add(interval1);
        intervalList.add(interval2);
        intervalList.add(interval3);
    }

    public static void main(String[] args){
        Intervals intervals = new Intervals();
        System.out.println("is 60 in interval: " + intervals.isNumberInInterval(60));
        System.out.print("is 38 in interval: " + intervals.isNumberInInterval(38));
    }

    public boolean isNumberInInterval(long number){
        //sorted intervalList, i.e. previousInterval.upper <= nextInterval.lower
        int i = intervalList.size()/2;
        int j = i;
        do{
            if(number < intervalList.get(j).mLowerBound){
                j--;
            } else if(number > intervalList.get(j).mUpperBound){
                j++;
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
