package ark.coding.books.interview_kickstart.graphs;

import java.util.*;

/**
 * https://uplevel.interviewkickstart.com/resource/rc-codingproblem-1705-9596-54-336
 */
public class CourseSchedule {
    static List<Integer> course_schedule(int n, List<List<Integer>> pairs) {
        List<Integer> solution = new ArrayList<>();

        Set<Integer>[] prerequisites = createDependencyList(n, pairs);

        boolean[] included = new boolean[n];
        while (solution.size() < n) {
            int node = 0;
            boolean deadlock = true;
            for (Set<Integer> dependencies : prerequisites) {
                if (dependencies.size() == 0 && !included[node]) {
                    solution.add(node);
                    included[node] = true;
                    deadlock = false;
                    removeNodeAsDependency(prerequisites, node);
                }
                node++;
            }
            if (deadlock) return Collections.singletonList(-1);
        }

        return solution;
    }

    private static Set<Integer>[] createDependencyList(int n, List<List<Integer>> pairs) {
        Set<Integer>[] dependencyList = new Set[n];
        for (int idx = 0; idx < dependencyList.length; idx++) {
            dependencyList[idx] = new HashSet<>();
        }

        for (List<Integer> pair : pairs) {
            Integer node = pair.get(0);
            Integer dep = pair.get(1);
            dependencyList[node].add(dep);
        }
        return dependencyList;
    }

    private static void removeNodeAsDependency(Set<Integer>[] prerequisites, int node) {
        for (Set<Integer> dependencies : prerequisites) {
            dependencies.remove(node);
        }
    }
}
