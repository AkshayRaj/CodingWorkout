/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * ===============================================================
 * | Distance     d1       d2    d3           d4                 |
 * ---------------------------------------------------------------
 * | SOURCE.....gas1......gas2..gas3.........gas4....DESTINATION |
 * ---------------------------------------------------------------
 * | Refuel      f1        f2    f3           f4                 |
 * ===============================================================
 */
public class MinReFuelingStops {

    /**
     * 1000
     * 246
     *      * @param args
     */
    public static void main(String[] args) {
        int[][] stations = new int[][] {
                {3,306}, {97,198}, {99,26}, {110,478}, {163,174}, {187,233}, {241,181}, {256,405}, {264,181}, {279,168}, {304,226}, {336,487}, {487,451}, {493,27}, {552,466}, {562,299}, {566,73}, {712,165}, {768,337}, {830,468}, {863,391}, {913,172}, {928,243}, {934,386}, {990,296}
        };
        int solution = minRefuelStops(1000, 246, stations);
        System.out.println(solution);
    }
    
    /**
     *
     * @param target
     * @param startFuel
     * @param stations
     * @return
     */
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        List<GasStation> gasStations = new ArrayList<>();
        for (int index = 0; index < stations.length; index++) {
            gasStations.add(new GasStation(stations[index][0], stations[index][1]));
        }

        return function(target, startFuel, gasStations);
    }

    static int function(int totalDistance, int startFuel, List<GasStation> gasStations) {
        int distanceTravelledSoFar = 0;
        int gasStationNo = 0;
        int noOfStopsMadeTillNow = 0;

        return memoizedWithIterative(totalDistance, startFuel, gasStations);
//        return recursive(
//                totalDistance,
//                distanceTravelledSoFar,
//                startFuel,
//                gasStationNo,
//                gasStations,
//                noOfStopsMadeTillNow);
    }

    static int memoizedWithIterative(int totalDistance, int fuelRemaining, List<GasStation> gasStations) {
        int[] maxDistancePossible = new int[gasStations.size() + 1];
        maxDistancePossible[0] = fuelRemaining;

        for (int stationNo = 0; stationNo < gasStations.size(); stationNo++) {
            GasStation currentStation = gasStations.get(stationNo);

            /**
             * Calculate the farthest you can reach with `i`+1 number of stops.
             * In the next iteration, i has incremented by 1, and you can use the previously computed result
             * to calculate the next `i`+1.
             */
            for (int noOfStops = stationNo; noOfStops >= 0; noOfStops--) {
                if (maxDistancePossible[noOfStops] >= currentStation.distanceFromStartingPosition) {
                    maxDistancePossible[noOfStops + 1] = Math.max(
                            maxDistancePossible[noOfStops + 1],
                            maxDistancePossible[noOfStops] + currentStation.refuelingCapacity
                    );
                }
            }
        }

        /**
         * 1. maxDistancePossible[i] contains the farthest you can reach using `i` number of stops.
         * 2. <b>Start from i=0</b>
         *    - If maxDistancePossible[i] >= target, then i is the min number of stops to reach target.
         */
        for (int noOfStops = 0; noOfStops <= gasStations.size(); noOfStops++) {
            if (maxDistancePossible[noOfStops] >= totalDistance) {
                return noOfStops;
            }
        }

        return -1;
    }

    static int recursive(int totalDistance,
                      int distanceTraveled,
                      int fuelRemaining,
                      int stopNo,
                      List<GasStation> gasStations,
                     int noOfStopsMadeTillNow) {
        if (distanceTraveled >= totalDistance) {
            return noOfStopsMadeTillNow;
        }

        // We crossed all stops.
        // Check if we can reach destination.
        if (stopNo == gasStations.size()) {
            int distanceLeft = totalDistance - distanceTraveled;
            // cannot reach destination if remaining fuel is less than distance left.
            if (fuelRemaining < distanceLeft) {
                return -1;
            }
            // we are able to reach.
            // return number of stops we are able to make.
            else {
                return noOfStopsMadeTillNow;
            }
        }

        GasStation currentStop = gasStations.get(stopNo);
        int distanceFromPreviousStop = currentStop.distanceFromStartingPosition - distanceTraveled;

        // abort further processing if we cannot travel until current gas station
        if (fuelRemaining < distanceFromPreviousStop) {
            return -1;
        }

        int fuelLeftAfterSkippingCurrentStop = fuelRemaining - distanceFromPreviousStop;
        int fuelAmountAfterRefuel = fuelLeftAfterSkippingCurrentStop + currentStop.refuelingCapacity;
        int nextStop = stopNo+1;

        int noOfHaltsAfterSkip = recursive(
                        totalDistance,
                        currentStop.distanceFromStartingPosition,
                        fuelLeftAfterSkippingCurrentStop,
                        nextStop,
                        gasStations,
                        noOfStopsMadeTillNow);
        int noOfHaltsAfterStopping = recursive(
                        totalDistance,
                        currentStop.distanceFromStartingPosition,
                        fuelAmountAfterRefuel,
                        nextStop,
                        gasStations,
                        noOfStopsMadeTillNow+1);

        if (noOfHaltsAfterSkip == -1 || noOfHaltsAfterStopping == -1) {
            return Math.max(noOfHaltsAfterSkip, noOfHaltsAfterStopping);
        }

        return Math.min(noOfHaltsAfterSkip, noOfHaltsAfterStopping);
    }

    static class GasStation {
        String name;
        final int refuelingCapacity;
        final int distanceFromStartingPosition;// to next stop. Eg, A~> 6; E ~> 0

        GasStation(int distanceFromStartingPosition, int refuelingCapacity) {
            this.distanceFromStartingPosition = distanceFromStartingPosition;
            this.refuelingCapacity = refuelingCapacity;
        }
    }

}
