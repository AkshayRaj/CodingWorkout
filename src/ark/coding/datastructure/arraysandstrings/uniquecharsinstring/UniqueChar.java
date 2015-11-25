package ark.coding.datastructure.arraysandstrings.uniquecharsinstring;

import ark.coding.Solution;

/**
 * Created by Akshayraj on 11/24/15.
 */
public class UniqueChar implements Solution<Boolean>{
    private static final boolean NO_UNIQUE_CHAR = false;
    private static final boolean ALL_UNIQUE_CHARS = true;
    private static String mString = "unique";
    @Override
    public Boolean solution(Object... args) {
        return doesStringHasUniqueChar((String) args[0]);
    }

    /*
     * Strings are represented in ASCII and Unicode character set
     * -ASCII has 128 characters
     * -Unicode has more than 120,000 characters in its set(2^21)
     * Assume we are dealing with ASCII character set, so character set with 8-bits ~ 256
     */
    private boolean doesStringHasUniqueChar(String string){
        int stringLength = string.length();
        boolean[] charBitMap = new boolean[128];
        if(stringLength > 128){
            return NO_UNIQUE_CHAR;
        }
        for(int i = 0 ; i < stringLength; i++){
            if(charBitMap[string.charAt(i)] == true){
                return NO_UNIQUE_CHAR;
            }
            charBitMap[string.charAt(i)] = true;
        }
        return ALL_UNIQUE_CHARS;
    }
//  This function has time complexity of O(n)

    public static void main(String[] args){
        UniqueChar uniqueChar = new UniqueChar();
        System.out.println("isUnique: " + uniqueChar.solution(mString));
        mString = "Johny";
        System.out.println("isUnique: " + uniqueChar.solution(mString));
    }


    /***************************************************************************************************
     * This function has time complexity of O(n^2), which is not an optimal solution
     */
    private boolean doesStringHasUniqueChar(String string, int dummy){
        int stringLength = string.length();
        for(int i = 0 ; i < stringLength - 1; i++){
            char currentChar = string.charAt(i);
            for(int j = i+1 ; j < stringLength; j ++){
                char nextChar = string.charAt(j);
                if(currentChar == nextChar){
                    return NO_UNIQUE_CHAR;
                }
            }
        }
        return ALL_UNIQUE_CHARS;
    }

}
