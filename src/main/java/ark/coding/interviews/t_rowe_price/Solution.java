/**
 * Created by Akshayraj
 */
package ark.coding.interviews.t_rowe_price;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int n = Integer.parseInt(input);
        String[] words = new String[n];
        for (int count = 1; count <= n; count++) {
            words[count-1] = br.readLine();
        }

        System.out.println(wordChainPrefixLength(words));

    }

    public static int wordChainPrefixLength(String[] words) {
        int length = 1;

        int wordNo = 0;
        while (wordNo < words.length - 1
                && words[wordNo].charAt(words[wordNo].length()-1) == words[wordNo+1].charAt(0)) {
            length++;
            wordNo++;
        }

        return length;
    }


    private static void checkSmoothness(int[] numberArray) {
        boolean smooth = true;
        for (int i = 0; i < numberArray.length-1; i++) {
            if (Math.abs(numberArray[i] - numberArray[i+1]) > 1) {
                smooth = false;
                break;
            }
        }

        if (smooth) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }


    public static void reverseWords(String[] lines) {
        for (int lineNo = 0; lineNo < lines.length; lineNo++) {
            String[] line = lines[lineNo].split(" ");
            StringBuilder reversedWordsLine = new StringBuilder();
            for (int wordNo = 0; wordNo < line.length; wordNo++) {
                String word = line[wordNo];
                reversedWordsLine.append(reverseString(word));

                // do not append space after last word
                if (wordNo < line.length-1) {
                    reversedWordsLine.append(" ");
                }
            }
            lines[lineNo] = reversedWordsLine.toString();
        }
    }

    private static String reverseString(String string) {
        char[] reverseString = new char[string.length()];

        // to reverse the string, copy characters in reverse order.
        int index = string.length()-1;
        for (char character : string.toCharArray()) {
            reverseString[index] = character;
            index--;
        }
        return new String(reverseString);
    }
}
