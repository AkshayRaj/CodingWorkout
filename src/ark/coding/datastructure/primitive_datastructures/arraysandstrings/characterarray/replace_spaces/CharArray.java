package ark.coding.datastructure.primitive_datastructures.arraysandstrings.characterarray.replace_spaces;

import ark.coding.Solution;

/**
 * Created by Akshayraj on 11/26/15.
 */
public class CharArray implements Solution<char[]>{
    private static final char[] CHAR_ARRAY = {
            'M','r',
            ' ',
            'J','o','h','n',
            ' ',
            'S','m','i','t','h',
            ' ',' ',' ',' '
    };
    private static final int ARRAY_LENGTH = 13;

    @Override
    public char[] solution(Object... args) {
        return replaceSpaces((char[]) args[0], (Integer) args[1]);
    }

    private char[] replaceSpaces(char[] charArray, int stringLength) {
        char[] chars = new char[charArray.length];
        int j = 0;
        for(int i = 0; i < stringLength; i ++){
            if(charArray[i] != ' '){
                chars[j] = charArray[i];
                j++;
            }else{
               chars[j] = '%';
                j++;
               chars[j] = '2';
                j++;
               chars[j] = '0';
                j++;
            }
        }
        return chars;
    }

    public static void main(String[] args){
        CharArray charArray = new CharArray();
        char[] output = charArray.solution(CHAR_ARRAY, ARRAY_LENGTH);

        System.out.println("input: ");
        for(int index = 0; index < CHAR_ARRAY.length; index++){
            System.out.print(CHAR_ARRAY[index]);
        }

        System.out.println("\noutput: ");
        for(int index = 0; index < output.length; index++){
            System.out.print(output[index]);
        }
    }
}
