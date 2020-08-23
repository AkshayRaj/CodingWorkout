/**
 * Created by Akshayraj
 */
package ark.coding.interviews.amzn;

import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();

        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int [] myArray = new int[length];
        for(int i=0; i<length; i++ ) {
            myArray[i] = scanner.nextInt();
        }
    }

    /*********************************************************************
     *                  TEST - 1
     * Autotype suggestions -
     * After a user has typed two characters in a search box, show him list of suggestions from a given repository of keywords.
     * Show atmost 3 suggestions.
     *********************************************************************/
    /**
     *
     * @param numreviews the number of keywords in the repository
     * @param repository A given repository of keywords, to be returned as suggestions.
     * @param customerQuery the full string of customer query;
     *                      Start showing suggestions after the first two characters of the search string
     * @return List of list of suggestions.
     *         The multiple lists in the outer list will contain the suggestions, start from the first 2 characters of
     *         the query, up to the full customer query string
     */
    List<List<String>> threeKeywordSuggestions(int numreviews,
                                               List<String> repository,
                                               String customerQuery) {
        int lengthOfQuery = customerQuery.length();
        List<List<String>> suggestions = new ArrayList<List<String>>();
        if (lengthOfQuery < 2 || numreviews == 0) {
            return suggestions;
        }

        List<String> sortedRepository = new ArrayList<>(repository);
        sortedRepository.sort(Comparator.naturalOrder());
        for (int subStringIndex = 2; subStringIndex <= lengthOfQuery; subStringIndex++) {
            List<String> subStringSuggestions = getSuggestionsForGivenSubString(
                    customerQuery.substring(0, subStringIndex),
                    sortedRepository);
            suggestions.add(subStringSuggestions);
        }

        return suggestions;
    }

    private List<String> getSuggestionsForGivenSubString(String substring, List<String> sortedRepository) {
        ArrayList<String> subStringSuggestions = new ArrayList<>();
        int count = 1;
        for (String keyword : sortedRepository) {
            if (count > 3) {
                return subStringSuggestions;
            }
            if (keyword.toLowerCase().startsWith(substring.toLowerCase())) {
                subStringSuggestions.add(keyword);
                count++;
            }
        }

        return subStringSuggestions;
    }

    /*********************************************************************
     *                  TEST - 2
     * A cluster of servers is in a 2D grid of {@code rows} * {@code columns}
     * If a server is updated with latest patch/version, it is marked as 1; 0 otherwise
     * An updated server can update its neighbours (left, right, up, down (not diagonal)) in 1 day
     * QUESTION:
     * Given the current state of patched servers,
     * find minimum number of days it will take to update all servers in a grid.
     *********************************************************************/
    int minimumDays(int rows, int columns, List<List<Integer>> grid) {
        int numberOfMinimumDays = 0;

        List<List<Integer>> updatedGrid = new ArrayList<>(grid);
        while (!areAllServersUpdated(rows, columns, updatedGrid)) {
            updatedGrid = updateServers(rows, columns, updatedGrid);
            numberOfMinimumDays++;
        }

        return numberOfMinimumDays;
    }

    private List<List<Integer>> updateServers(int rows, int columns, List<List<Integer>> grid) {
        int[][] updatedGrid = new int[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; columns++) {
                int status = grid.get(row).get(column);
                // first & last row
                if (row == 0) {
                    if (column == 0) {
                        if (status == 1) {
                            updatedGrid[row][column] = 1;
                            if (columns > 1) {
                                updatedGrid[row][column + 1] = 1;
                            }
                            if (rows > 1) {
                                updatedGrid[row + 1][column] = 1;
                            }
                        }
                    }
                    else if (column == columns - 1) {
                        if (status == 1) {
                            updatedGrid[row][column] = 1;
                            updatedGrid[row][column - 1] = 1;
                            if (rows > 1) {
                                updatedGrid[row + 1][column] = 1;
                            }
                        }
                    }
                    else if (0 < column && column < columns - 2) {
                        updatedGrid[row][column] = 1;
                        if (rows > 1) {
                            updatedGrid[row + 1][column] = 1;
                        }
                        updatedGrid[row][column+1] = 1;
                        updatedGrid[row][column-1] = 1;
                    }
                }
                else if (row == rows - 1) {
                    if (column == 0) {
                        updatedGrid[row][column] = 1;
                        updatedGrid[row-1][column] = 1;
                        if (columns > 1) {
                            updatedGrid[row][column+1] = 1;
                        }
                    }
                    else if (column == column - 1) {
                        updatedGrid[row][column] = 1;
                        updatedGrid[row - 1][column] = 1;
                        updatedGrid[row][column - 1] = 1;
                    }
                    else if (0 < column && column < columns - 2) {
                        updatedGrid[row][column] = 1;
                        updatedGrid[row][column-1] = 1;
                        updatedGrid[row][column+1] = 1;
                        updatedGrid[row-1][column] = 1;
                    }
                }
                // all intermediate rows
                else if (0 < row && row < rows - 2) {
                    if (column == 0) {
                        updatedGrid[row][column] = 1;
                        updatedGrid[row-1][column] = 1;
                        updatedGrid[row+1][column] = 1;
                        if (columns > 1) {
                            updatedGrid[row][column+1] = 1;
                        }
                    }
                    else if (column == column - 1) {
                        updatedGrid[row][column] = 1;
                        updatedGrid[row+1][column] = 1;
                        updatedGrid[row-1][column] = 1;
                        updatedGrid[row][column-1] = 1;
                    }
                    else if (0 < column && column < columns - 2) {
                        updatedGrid[row][column] = 1;
                        updatedGrid[row][column-1] = 1;
                        updatedGrid[row][column+1] = 1;
                        updatedGrid[row-1][column] = 1;
                        updatedGrid[row+1][column] = 1;
                    }
                }
            }
        }

        List<List<Integer>> updatedGRidASList = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            List<Integer> rowList = new ArrayList<Integer>();
            for (int column = 0; column < columns; columns++) {
                rowList.add(updatedGrid[row][column]);
            }
            updatedGRidASList.add(row, rowList);
        }
        return updatedGRidASList;
    }

    private boolean areAllServersUpdated(int rows, int columns, List<List<Integer>> grid) {
        int totalServersUpdated = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; columns++) {
                totalServersUpdated = totalServersUpdated + grid.get(row).get(column);
            }
        }

        return rows * columns == totalServersUpdated;
    }

    /*********************************************************************
     * TEST ENDS
     *********************************************************************/


    /*********************************************************************
     * DEMO QUESTIONS -
     * 1. Find GCD of numbers given in an array
     * 2. Find state of cells after 'n' days
     *********************************************************************/

    class GCD
    {
        /**
         * 1. Find GCD
         * @param num number of elements in the array
         * @param arr array of number
         * @return
         */
        public int generalizedGCD(int num, int[] arr) {
            Arrays.sort(arr);
            int tmpGCD = arr[0];
            for (int index = 0; index < arr.length; index++) {
                int remainder = arr[index] % tmpGCD;
                if(remainder != 0) {
                    tmpGCD = remainder;
                    index = -1;
                }
            }
            return tmpGCD;
        }
        // METHOD SIGNATURE ENDS
    }

    /**
     * 2. Find state of cells after 'n' days
     * - There are 8 cells in a straight line. They are either active(1) or inactive(0)
     * - If cells on the either side are active/inactive, then a given cell goes inactive the next day;
     *   otherwise it goes active.
     * - For cells at the edges, assume the "other neighbour" as inactive
     *
     * @param states states of the cells today
     * @param days the number of days after which we want to find the state of the cell
     * @return the state of the cells after a given number of days, given the current state of the cells.
     */
    public List<Integer> cellCompete(int[] states, int days) {
        int[] givenState = Arrays.copyOf(states, states.length);
        for (int day = 1; day <= days; day++) {
            givenState = stateAfterOneDay(givenState);
        }
        ArrayList<Integer> finalState = new ArrayList<>(8);
        for (int index = 0; index < 8; index++) {
            finalState.add(givenState[index]);
        }

        return finalState;
    }

    private int[] stateAfterOneDay(int[] state) {
        int[] newState = new int[8];
        for (int index = 0; index < 8; index++) {
            //edge cells
            if (index == 0) {
                if (state[index + 1] == 0) {
                    newState[index] = 0;
                }
                else {
                    newState[index] = 1;
                }
            }
            if (index == 7) {
                if (state[index - 1] == 0) {
                    newState[index] = 0;
                }
                else {
                    newState[index] = 1;
                }
            }

            // index from 1 to 6 (cells that have neighbours on both sides)
            if (0 < index && index < 7) {
                if (state[index - 1] == state[index + 1]) {
                    newState[index] = 0;
                } else {
                    newState[index] = 1;
                }
            }
        }
        return newState;
    }
    /*********************************************************************
     * DEMO END
     *********************************************************************/
}
