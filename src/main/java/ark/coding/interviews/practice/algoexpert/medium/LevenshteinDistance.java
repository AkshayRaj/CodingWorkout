/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

/**
 * https://www.algoexpert.io/questions/Levenshtein%20Distance
 */
public class LevenshteinDistance {

    /**
     * Calculate minimum number of operations needed to perform on first string {@param str1} to obtain
     * the second string {@param str2}
     *
     * Operations allowed are -
     * 1. Insertion (insert any character)
     * 2. Deletion (delete any character)
     * 3. Substitution (substitute a character)
     *
     * @param str1 string which is to be converted.
     * @param str2 the string to convert to.
     * @return minimum number of operations required to get str2 from str1
     */
    public static int levenshteinDistance(String str1, String str2) {
        // Calculate minimum number of alterations to be done
        // for substrings of str1, starting from substring of length '0', i.e. empty string,
        // and build up to the original str1
        // results from smaller length substrings can be used for longer length-ed substrings.
        // substrings always start from first char, and grow towards the tail.

        int str1Length = str1.length(); // rows
        int str2Length = str2.length(); // columns

        int[][] solution = new int[str1Length+1][str2Length+1];
        solution[0][0] = 0; // no operation to change an empty string to an empty string
        for (int row = 1; row <= str1Length; row++) {
            // No of alterations required to change substring of str1 to an empty string.
            solution[row][0] = row;
        }
        for (int col = 1; col <= str2Length; col++) {
            // Alterations required to change empty string to substring of str2
            solution[0][col] = col;
        }

        /**
         * Check how many alterations needed to make a susbtring of str1 to substring of str2
         * Row[0] represents empty string
         * Row[1] represents first char
         * Row[2] represents str1#substring of first two chars
         * ...
         * Row[str1.length] represents whole str1
         *
         * Column[0] ~> modify a given string to empty string
         * Column[1] ~> modify a given string to first char of str2
         * Column[2] ~> modify a given string to first two chars of str2
         * ...
         * Column[str2.length] ~> modify a given string to str2
         *
         */
        for (int str1Index = 0; str1Index < str1Length; str1Index++) {
            int row = str1Index+1;
            Character str1Char = str1.charAt(str1Index);
            for (int str2Index = 0; str2Index < str2Length; str2Index++) {
                int column = str2Index+1;
                Character str2Char = str2.charAt(str2Index);
                if (str1Char.equals(str2Char)) {
                    solution[row][column] = solution[str1Index][str2Index];
                }
                else {
                    solution[row][column] = 1
                            + Math.min(solution[str1Index][str2Index],
                                Math.min(solution[str1Index+1][str2Index], solution[str1Index][str2Index+1]));
                }
            }
        }

        return solution[str1Length][str2Length];
    }
}

