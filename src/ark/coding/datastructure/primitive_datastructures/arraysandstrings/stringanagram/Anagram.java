package ark.coding.datastructure.primitive_datastructures.arraysandstrings.stringanagram;

import ark.coding.Solution;

/**
 * Created by Akshayraj on 11/26/15.
 */
public class Anagram implements Solution<Boolean> {
    private static final String STRING_ONE = "merer";
    private static final String STRING_TWO = "meera";
    @Override
    public Boolean solution(Object... args) {
        return isAnagram((String)args[0], (String) args[1]);
    }

    private boolean isAnagram(String stringOne, String stringTwo){
        int stringOneLength = stringOne.length();
        int stringTwoLength = stringTwo.length();
        int unicodeSumStringOne = 0;
        int unicodeSumStringTwo = 0;
        if(stringOneLength == stringTwoLength) {
            for (int i = 0; i < stringOneLength; i++) {
                unicodeSumStringOne = unicodeSumStringOne + stringOne.codePointAt(i);
            }
            for (int j = 0; j < stringTwoLength; j++) {
                unicodeSumStringTwo = unicodeSumStringTwo + stringTwo.codePointAt(j);
            }
        }else{
            return false;
        }
        if(unicodeSumStringOne == unicodeSumStringTwo){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        Anagram anagram = new Anagram();
        System.out.println("isAnagram: " + anagram.solution(STRING_ONE, STRING_TWO));

    }
}
