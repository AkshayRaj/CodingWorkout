/**
 * Created by Akshayraj
 */
package ark.coding.interviews.flatiron;

public class Solution implements ark.coding.Solution<Integer> {

    public static void main(String[] args) {
        ark.coding.interviews.apollo.Solution solution = new ark.coding.interviews.apollo.Solution();
        System.out.println("Solution is: " + solution.solution(""));
    }

    @Override
    public Integer solution(Object... args) {
        return function((String) args[0]);
    }

    public Integer function(String arg){
        return 0;
    }
}
