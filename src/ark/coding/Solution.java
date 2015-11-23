package ark.coding;

/**
 * interface Solution contains
 * - Object solution(Object...args) method
 * - all coding questions in this repo implement this interface
 * - to get the solution of any coding problem, do this - CodingQuestion.solution(params)
 * - Eg.
 *      Class Sum implements Solution{
 *          @Override
 *          Integer solution(Integer a, Integer b){
 *              return getSum(a, b);
 *          }
 *
 *          int getSum(int a, int b){
 *              int sum = a + b;
 *              return sum;
 *          }
 *      }
 *
 *      ...
 *      public static void main(String[] args){
 *          System.out.println("Solution of Sum is: " + new Sum().solution(a,b));
 *      }
 */
public interface Solution<T> {
    T solution(Object...args);
}
