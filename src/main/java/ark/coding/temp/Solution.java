/**
 * Created by Akshayraj
 */
package ark.coding.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {

    public static void main(String[] args) {
        System.out.println(isIsomorphic("abab", "kiki"));
    }

    static Exception print(int i) {
        if (i > 0 ) {
            return new Exception();
        }
        else {
            throw new RuntimeException();
        }
    }

    public static long solution(long n) {
        // Type your solution here

        long noOfJumpsAtN = 0;

        List<Long> noOfJumps = new ArrayList<>();
        noOfJumps.add(0L);
        noOfJumps.add(1L);
        noOfJumps.add(2L);
        noOfJumps.add(3L);

        for (int index = 4; index <= n; index++) {
            noOfJumpsAtN = noOfJumps.get(index - 3) + noOfJumps.get(index - 2) + noOfJumps.get(1);

            noOfJumps.add(noOfJumpsAtN);
        }

        return noOfJumpsAtN;
    };

    public static boolean isIsomorphic(String s, String t) {
        // if X and Y have different lengths, they cannot be Isomorphic
        if (s.length() != t.length()) {
            return false;
        }

        // use map to store mapping from characters of String X to String Y
        HashMap<Character, Character> oneTwoCharMapping = new HashMap<>();
        HashMap<Character, Character> twoOneCharMapping = new HashMap<>();

        // use set to store pool of already mapped characters
        //Set<Character> set = new HashSet<>();

        for (int index = 0; index < s.length(); index++) {
            char character1 = s.charAt(index);
            char character2 = t.charAt(index);

            // we check iso-morphism both ways -
            // from string-1 to string-2;
            if (oneTwoCharMapping.containsKey(character1)) {
                if (oneTwoCharMapping.get(character1) != character2)
                    return false;
            }

            // as well as from string-2 to string-1.
            if (twoOneCharMapping.containsKey(character2)) {
                if (twoOneCharMapping.get(character2) != character1)
                    return false;
            }

            oneTwoCharMapping.put(character1, character2);
            twoOneCharMapping.put(character2, character1);
        }

        return true;
    }
}