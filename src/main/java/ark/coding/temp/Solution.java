/**
 * Created by Akshayraj
 */
package ark.coding.temp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        GCD gcd = solution.getGCD();

        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int [] myArray = new int[length];
        for(int i=0; i<length; i++ ) {
            myArray[i] = scanner.nextInt();
        }

        int greatestCD = gcd.generalizedGCD(length, myArray);
        System.out.println(greatestCD);
    }

    /*********************************************************************
     *                  TEST - 1
     *********************************************************************/
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
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
    // METHOD SIGNATURE ENDS



    /*********************************************************************
     *                  TEST - 2
     *********************************************************************/
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int minimumDays(int rows, int columns, List<List<Integer>> grid) {
        int numberOfMinimumDays = 0;

        List<List<Integer>> updatedGrid = new ArrayList<>(grid);
        while (!areAllServersUpdated(rows, columns, updatedGrid)) {
            updatedGrid = updateServers(rows, columns, updatedGrid);
            numberOfMinimumDays++;
        }

        // WRITE YOUR CODE HERE
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
    // METHOD SIGNATURE ENDS


    /*********************************************************************
     *
     *********************************************************************/

    GCD getGCD() {
        return new GCD();
    }

    class GCD
    {
        // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
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

    public int[] stateAfterOneDay(int[] state) {
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
     *
     *********************************************************************/
}