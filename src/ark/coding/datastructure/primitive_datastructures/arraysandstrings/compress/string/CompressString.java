package ark.coding.datastructure.primitive_datastructures.arraysandstrings.compress.string;

import ark.coding.Solution;

/**
 * Created by Akshayraj on 11/26/15.
 */
public class CompressString implements Solution<String> {

    @Override
    public String solution(Object... args) {
        return getCompressedString((String) args[0]);
    }

    private String getCompressedString(String stringToCompress) {
        StringBuilder compressedString = new StringBuilder();
        for(int i = 0 ; i < stringToCompress.length() ; ) {
            char currentChar = stringToCompress.charAt(i);
            int currentCharCount = getCount(stringToCompress, i);
            compressedString.append(currentChar);
            if(currentCharCount > 1){
                compressedString.append(currentCharCount);
            }
            i = i + currentCharCount;
        }
        return compressedString.toString();
    }

    int getCount(String string, int codePoint){
        int count = 1;
        if(codePoint < string.length()-1) {
            if (string.codePointAt(codePoint) == string.codePointAt(codePoint + 1)) {
                count = count + getCount(string, codePoint + 1);
            }
        }
        return count;
    }

    public static void main(String[] args){
        CompressString compressString = new CompressString();
        System.out.println("compressedString " + compressString.solution("aaaaaba"));

    }
}
