/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.easy;

/**
 *
 */
public class CeasarCipherEncryptor {

    private final static char[] alphabet = new char[] {
            'a','b','c','d','e',
            'f','g','h','i','j',
            'k','l','m','n','o',
            'p','q','r','s','t',
            'u','v','w','x','y',
            'z'
    };

    public static String caesarCypherEncryptor(String str, int key) {
        StringBuilder decryptedString = new StringBuilder();
        int normalizedKey = key%26;
        for (int charIndex = 0; charIndex < str.length(); charIndex++) {
            decryptedString.append(getDecipheredChar(str.charAt(charIndex), normalizedKey));
        }
        return decryptedString.toString();
    }

    private static char getDecipheredChar(char originalChar, int key) {
        int originalCharPositionInAlphabet = 0;
        while (originalChar != alphabet[originalCharPositionInAlphabet]
                && originalCharPositionInAlphabet < 26) {
            originalCharPositionInAlphabet++;
        }
        if (originalCharPositionInAlphabet >= 26) {
            throw new RuntimeException("Character [" + originalChar + "] not in alphabet.");
        }
        int newCharPositionInAlphabet = (originalCharPositionInAlphabet + key)%26;

        return alphabet[newCharPositionInAlphabet];
    }

    public static void main(String[] args) {
        System.out.println(caesarCypherEncryptor("ayr", 2)); // answer should be "cat"
    }

}
