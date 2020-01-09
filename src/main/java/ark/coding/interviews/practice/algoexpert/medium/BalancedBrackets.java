/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class BalancedBrackets {

    public static boolean balancedBrackets(String str) {
        Deque<Character> stack = new ArrayDeque<>();

        for (Character character : str.toCharArray()) {
            if (character == '(' || character == '[' || character == '{') {
                stack.push(character);
            }
            if (character == ')' || character == '}' || character == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                Character popped = stack.pop();
                switch (character) {
                    case ')' :
                        if (popped != '(') {
                            return false;
                        }
                        break;
                    case '}' :
                        if (popped != '{') {
                            return false;
                        }
                        break;
                    case ']' :
                        if (popped != '[') {
                            return false;
                        }
                        break;
                }
            }
        }
        return stack.isEmpty()
                ? true   // if stack is empty, there are balanced brackets
                : false; // otherwise brackets are not balanced.
    }
}
