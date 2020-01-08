/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    // Do not edit the class below except for the
    // populateSuffixTrieFrom and contains methods.
    // Feel free to add new properties and methods
    // to the class.
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    }

    static class SuffixTrie {
        TrieNode root = new TrieNode();
        char endSymbol = '*';

        TrieNode currentNode;

        public SuffixTrie(String str) {
            populateSuffixTrieFrom(str);
        }

        public void populateSuffixTrieFrom(String str) {
            // Write your code here.
            for (int indexSubStringStart = 0; indexSubStringStart < str.length(); indexSubStringStart++) {

                int index = indexSubStringStart;
                currentNode = root;

                while (index < str.length()) {
                    Character character = str.charAt(index);
                    if (currentNode.children.containsKey(character)) {
                        // traverse the node that already exists
                        currentNode = currentNode.children.get(character);
                    }
                    else {
                        // create a new node for the given character
                        currentNode.children.put(character, new TrieNode());
                        currentNode = currentNode.children.get(character);
                    }
                    index++;
                }
                // end of substring; add "*"
                currentNode.children.put(endSymbol, new TrieNode());
            }
        }

        public boolean contains(String str) {
            TrieNode currentNode = root;
            for (int index = 0; index < str.length(); index++) {
                Character nextChar = str.charAt(index);
                if (!currentNode.children.containsKey(nextChar)) {
                    return false;
                }
                currentNode = currentNode.children.get(nextChar);
            }

            return currentNode.children.containsKey(endSymbol);
        }
    }

}
