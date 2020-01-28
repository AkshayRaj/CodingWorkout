/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Question: https://www.algoexpert.io/questions/Topological%20Sort
 *
 * 1. You are given list of jobs that need to be completed.
 * 2. You are also given a list of dependencies, which indicate the dependency of a given job.
 *    A dependency is a pair of jobs, where the first job needs to be finished for the second one to get completed.
 *
 * Given the list of jobs & dependencies, find a valid order in which the jobs have to be completed.
 * Return an empty list if such a valid order does not exist.
 *
 * Sample input:
 * List of jobs: [1,2,3,4]
 * List of deps: [[1,2],[1,3],[3,2],[4,2],[4,3]]
 *
 * Sample output:
 * [1,4,3,2] or [4,1,3,2]
 */
public class TopologicalSort {
    public static List<Integer> topologicalSort(List<Integer> jobIds, List<Integer[]> deps) {
        // 1. Confirm cycle does not exist

        // 2. Select the currentNode with activeDepCount = 0
        //    - after currentNode (with depCount = 0) is traversed,
        //      decrement activeDepCount of nodes which had dependency on currentNode
        // 3. Go back to 2, if all nodes are not traversed
        List<Job> jobs = new ArrayList<>();
        for (Integer jobId : jobIds) {
            Set<Integer> dependencies = new HashSet<>();
            for (Integer[] dependencyPair : deps) {
                if (dependencyPair[1] == jobId) {
                    dependencies.add(dependencyPair[0]);
                }
            }
            jobs.add(new Job(jobId, dependencies));
        }

        List<Integer> validOrder = new ArrayList<>();
        while (!jobs.isEmpty()) {
            // Sort is O(n log(n)); n is the number of jobs
            // To optimize this, we could use MinHeap, which would give us O(log n) time complexity
            // We sort the list of Jobs. The criteria for sort is the number of dependencies of a given job.
            Collections.sort(jobs);
            Job currentJob = jobs.get(0);
            // 1. Check for cycle.
            if (currentJob.getDepCount() != 0) {
                return new ArrayList<>();
            }
            validOrder.add(currentJob.getJobId());
            jobs.remove(0);

            for (Job job : jobs) {
                job.removeDependency(currentJob.getJobId());
            }
        }

        return validOrder;
    }
    // ^^^ Time complexity if O(n * n log(n));
    // (The n log(n)) results from having to sort the list after each pop.
    // If we use MinHeap, the time complexity will drop to just O(n log(n)).
    //
    // Space Complexity:
    // O(job + dependencies), or O(V + E)
    // We allocate additional space for each job, and for each of the dependency.

    /**
     * Helper class to store mapping between the jobId & its dependencies.
     *
     * We also make this class of type {@link Comparable} to make sorting possible for a list of {@link Job}s
     * Check {@link Job#compareTo(Job)} to read about how jobs are weighed during a sort.
     */
    static class Job implements Comparable<Job> {
        private final int jobId;
        private final Set<Integer> incompleteDependencies;

        Job(int jobId, Set<Integer> incompleteDependencies) {
            this.jobId = jobId;
            this.incompleteDependencies = incompleteDependencies;
        }

        int getJobId() {
            return jobId;
        }

        /**
         * Remove dependency for this job.
         *
         * Time Complexity: O(1)
         * Since we use HashSet, the lookup time complexity is O(1)
         *
         * Space Complexity : O(deps)
         * deps is the number of dependencies of this job.
         * @param depJobId id of the job, to remove as a dependency for this job.
         */
        void removeDependency(int depJobId) {
            if (incompleteDependencies.contains(depJobId)) {
                incompleteDependencies.remove(depJobId);
            }
        }

        int getDepCount() {
            return incompleteDependencies.size();
        }

        /**
         * Implement the criteria for comparing this job with another job, for sorting purposes.
         *
         * @param job the other job to compare to with this job, for sorting & other purposes.
         * @return  1, if this job has more dependencies than the other job;
         *         -1, if this job has less dependencies than the other job;
         *          0, if this job has same number of dependencies than the other job.
         */
        @Override
        public int compareTo(Job job) {
            if (getDepCount() < job.getDepCount()) {
                return -1;
            }
            if (getDepCount() > job.getDepCount()) {
                return 1;
            }
            return 0;
        }

        /**
         * {@link #equals(Object)} & {@link #hashCode()} needed to implement to maintain the equality contract
         * of the {@link Job} objects.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Job job = (Job) o;
            return jobId == job.getJobId();
        }

        /**
         * {@link #equals(Object)} & {@link #hashCode()} needed to implement to maintain the equality contract
         * of the {@link Job} objects.
         */
        @Override
        public int hashCode() {
            return Objects.hash(jobId);
        }
    }

}
