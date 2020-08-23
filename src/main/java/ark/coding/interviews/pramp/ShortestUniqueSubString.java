/**
 * Created by Akshayraj
 */
package ark.coding.interviews.pramp;

import java.util.*;

public class ShortestUniqueSubString {

    /**
     * Question:- https://www.pramp.com/challenge/wqNo9joKG6IJm67B6z34
     * Find shortest sub-string of a given string, which contains all characters in a given array
     *
     * Approach:-
     * 1. Iterate over each character of the given string, from 0 -> n-1 (n is length of string)
     *    Find a valid sub-string, as we move from 0 ~> n
     *  <b>Note:</b> The key is to dynamically update the smallest sub-string identified so far, as we iterate
     *               over the characters of the string from 0 -> n
     * 2. To keep track of the "smallest sub-string so far, at the currentIndex",
     *    we need to keep track of where a sub-string might begin & end.
     *  - To achieve this, we keep track of the index of each character in the original string, as we move from 0 ~> n
     *    We use {@link HashMap<Character, Integer>}, which is a mapping of a character to its index
     *  - When we see a valid sub-string for the "first time", all chars are present in the HashMap.
     *    We sort the value-set in the map, and the sub-string exists from the smallest index in the value set
     *    to the current index.
     *  - Whenever we get a second occurrence of a character, we update its index. The reasons are two-fold -
     *    a) If the Map is not "full", i.e. all chars have not been identified until the current index
     *       Just replace the index, as the previous index will only lead to a longer sub-string.
     *    b) If the Map is already "full"
     *       In this case, a valid sub-string is already identified, & is tracked by a temp data-holder.
     *       We need to find new sub-strings, & so we replace the index in the map for that character.
     *
     * <b>Glossary:- </b>
     *  Valid sub-string : This is a sub-string that has all the characters in the given array.
     *
     * @param array of unique characters. A constraint to find smallest sub-string of the given string
     * @param string the string, who sub-string is to be identified, that contains all characters in the given array
     * @return the smallest sub-string of the given string, that contains all characters in the given array.
     *         An empty string, if such a sub-string does not exists.
     */
    static String getShortestUniqueSubstring(char[] array, String string) {
        int noOfUniqueChars = array.length;
        int lengthOfString = string.length();
        if (lengthOfString < noOfUniqueChars) {
            return "";
        }

        Set<Character> uniqueCharSet = new HashSet<>();
        for (Character character : array) {
            uniqueCharSet.add(character);
        }

        Map<Character, Integer> charIndexMap = new HashMap<>();
        List<SubString> validSubStrings = new ArrayList<>();

        for (int index = 0; index < lengthOfString; index++) {
            Character currentCharacter = string.charAt(index);

            boolean isCharInSet = uniqueCharSet.contains(currentCharacter);
            if (isCharInSet) {
                // charIndexMap is not full
                // add new char and check if we have a valid substring
                // or replace index of an existing char
                if (charIndexMap.size() < noOfUniqueChars) {
                    if (charIndexMap.containsKey(currentCharacter)) {
                        charIndexMap.replace(currentCharacter, index);
                    } else {
                        charIndexMap.put(currentCharacter, index);

                        // check if all chars in the given array, are in the map
                        // after adding the newest char
                        if (charIndexMap.size() == noOfUniqueChars) {
                            Integer[] charIndexes = charIndexMap.values().toArray(new Integer[noOfUniqueChars]);
                            // The sort is O(m log(m)), where m is the noOfUniqueChars in array
                            // We could use a MinHeap instead, which will give us O(log(m)) time complexity.
                            Arrays.sort(charIndexes);
                            validSubStrings.add(new SubString(string, charIndexes[0], index + 1));
                        }
                    }
                }
                // charIndexMap is already full;
                // By this point, a valid subString already exists; we check if the new one is shorter
                else if (charIndexMap.size() == noOfUniqueChars) {
                    charIndexMap.replace(currentCharacter, index);
                    Integer[] charIndexes = charIndexMap.values().toArray(new Integer[noOfUniqueChars]);
                    Arrays.sort(charIndexes);

                    SubString newSubString = new SubString(string, charIndexes[0], index + 1);
                    SubString previousSubString = validSubStrings.get(0);
                    if (newSubString.length == previousSubString.length) {
                        validSubStrings.add(newSubString);
                    }
                    else if (newSubString.length < previousSubString.length) {
                        validSubStrings.clear();
                        validSubStrings.add(newSubString);
                    }
                }
            }
        }

        // validSubStrings will contain list of the smallest sub-strings.
        return validSubStrings.size() > 0
                ? validSubStrings.get(0).getSubstring()
                : "";
    }
    // Time Complexity:
    // Worst Case: O(n * m log(m)); n -> length of the given string
    //                              m -> number of unique characters in the given array
    // We could reduce the time complexity, by using a MinHeap to keep track of the smallest index in the charIndexMap
    // The smallest index is required to identify the start point of the sub-string.
    //
    // Thus, we could potentially get a Worst Case : O(n log(m))
    // For n >> m, time complexity will be driven by n, thus O(n)
    //
    // Space Complexity:
    // Worst Case : O(n) additional space will be required by this solution.

    static class SubString implements Comparable<SubString> {
        final int startIndex; // inclusive
        final int endIndex; // exclusive
        final int length;
        final String originalString;

        SubString(String originalString, int startIndex, int endIndex) {
            this.originalString = originalString;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            length = endIndex - startIndex; // since endIndex is exclusive, we don't have to add 1.
        }

        String getSubstring() {
            return originalString.substring(startIndex, endIndex);
        }

        @Override
        public int compareTo(SubString subString) {
            if (this.length < subString.length) {
                return -1;
            }
            else if (this.length > subString.length) {
                return 1;
            }
            return 0; //lengths are equal
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SubString subString = (SubString) o;
            return startIndex == subString.startIndex &&
                    endIndex == subString.endIndex &&
                    length == subString.length;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startIndex, endIndex, length);
        }
    }
}
