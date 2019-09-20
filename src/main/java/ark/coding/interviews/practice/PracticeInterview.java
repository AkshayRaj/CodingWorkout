package ark.coding.interviews.practice;

import ark.coding.Solution;

public class PracticeInterview implements Solution {

    public static void main(String[] args){


        System.out.println("Solution: " + isPalindrome("KA1dak"));

    }

    @Override
    public Boolean solution(Object... args) {
        return isPalindrome((String) args[0]);
    }

    public static boolean isPalindrome(String word) {
        String lowercase = word.toLowerCase();

        int length = word.length();
        StringBuilder reverseString = new StringBuilder();
        for(int index = length-1; index >= 0; index--){
            reverseString.append(lowercase.charAt(index));
        }
        String reverse = reverseString.toString();
        for(int index = 0; index < length; index++){
            if(lowercase.charAt(index) != reverse.charAt(index)){
                return false;
            }
        }
        return true;
    }
}
