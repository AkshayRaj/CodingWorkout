/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.coding_patterns_course.two_heaps;

import java.util.PriorityQueue;

/**
 * https://www.educative.io/courses/grokking-the-coding-interview/B6x69OLX4jY
 * Problem Statement #
 * Given a set of investment projects with their respective profits, we need to find the most profitable projects.
 * We are given an initial capital and are allowed to invest only in a fixed number of projects.
 * Our goal is to choose projects that give us the maximum profit.
 *
 * We can start an investment project only when we have the required capital.
 * Once a project is selected, we can assume that its profit has become our capital.
 */
public class MaximizeCapital {
    public static int findMaximumCapital(int[] capital, int[] profits, int noOfProjectsToWork, int initialCapital) {
        int totalProjects = profits.length;
        PriorityQueue<Integer> investmentsMinHeap = new PriorityQueue<>(totalProjects, (i1, i2) -> capital[i1] - capital[i2]);
        PriorityQueue<Integer> profitsMaxHeap = new PriorityQueue<>(totalProjects, (i1, i2) -> profits[i2] - profits[i1]);

        // insert all project capitals to a min-heap
        for (int capitalArrayIndex = 0; capitalArrayIndex < totalProjects; capitalArrayIndex++) {
            investmentsMinHeap.offer(capitalArrayIndex);
        }

        // let's try to find a total of 'numberOfProjects' best projects
        int solution = initialCapital; // availableCapital
        int projectCount = 1;
        while (projectCount <= noOfProjectsToWork) {
            // find all projects that can be selected within the available capital and insert them in a max-heap
            while (!investmentsMinHeap.isEmpty() && capital[investmentsMinHeap.peek()] <= solution) {
                profitsMaxHeap.add(investmentsMinHeap.poll());
            }

            // terminate if we are not able to find any project that can be completed within the available capital
            if (profitsMaxHeap.isEmpty()) {
                break;
            }

            // select the project with the maximum profit
            solution += profits[profitsMaxHeap.poll()];
            projectCount++;
        }

        return solution;
    }

    public static void main(String[] args) {
        int result = MaximizeCapital.findMaximumCapital(new int[] { 0, 1, 2 }, new int[] { 1, 2, 3 }, 2, 1);
        System.out.println("Maximum capital: " + result);
        result = MaximizeCapital.findMaximumCapital(new int[] { 0, 1, 2, 3 }, new int[] { 1, 2, 3, 5 }, 3, 0);
        System.out.println("Maximum capital: " + result);
    }
}
