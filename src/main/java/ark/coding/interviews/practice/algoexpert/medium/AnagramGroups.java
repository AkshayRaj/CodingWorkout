/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import ark.coding.tools.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AnagramGroups {
    public static List<List<String>> groupAnagrams(List<String> words) {

        HashMap<Integer, List<String>> solution = new HashMap<>();
        for (String word : words) {
            // sort the chars in string, to get consistent hash for anagrams
            char[] charsInWord = word.toCharArray();
            Arrays.sort(charsInWord); // Sort is O (w log(w))
            int hash = new String(charsInWord).hashCode();

            if (!solution.containsKey(hash)) {
                List<String> anagramListForCurrentWord = new ArrayList<>();
                anagramListForCurrentWord.add(word);
                solution.put(hash, anagramListForCurrentWord);
            }
            else {
                List<String> anagramsOfCurrentWord = solution.get(hash);
                anagramsOfCurrentWord.add(word);
            }
        }

        return solution.values().stream()
                .collect(Collectors.toList());
    }
    // Time : O (n * w log(w)); n is length of list of words
    //                        ; w is length of longest word.
    // sorting of a word is O (w log(w)), and we do that for each word in the list (n).
    //----------------------------------------------------------
    // Space :
    // O (n * w) - we store all words in a map for computation

    public static void main(String[] args) {
        char[] chars = "cab *".toCharArray();
        Arrays.sort(chars);
        System.out.println(new String(chars).hashCode());
        System.out.println(" *abc".hashCode());
        Utils.printArray(chars);
    }
}
