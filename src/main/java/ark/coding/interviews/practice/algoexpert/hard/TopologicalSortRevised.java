/**
 * Created by Akshayraj
 */
package ark.coding.interviews.practice.algoexpert.hard;

import java.util.*;

public class TopologicalSortRevised {
    // Data Structure represents mapping of job to its dependencies

    /**
     * We are given an edge list, but we need adjacency list of incoming edge for nodes.
     * That is why, we have to create {@link JobGraph} from the given <b>input format</b>.
     *
     * @param jobIds list of all jobIds
     * @param deps dependencies of a given jobId, represented by a two-length-ed Integer array.
     * @return A list populated with valid order, if one exists;
     *          An empty list otherwise
     */
    public static List<Integer> topologicalSort(List<Integer> jobIds, List<Integer[]> deps) {

        // Graph creation happens in - O(V + E) time
        JobGraph jobGraph = new JobGraph(jobIds, deps);

        List<Integer> validOrder = new ArrayList<>();
        jobLoop: // runs in O(V) time.
        for (Job job : jobGraph.jobs) {
            boolean cycleExists = findDepsDFS(job, jobGraph, validOrder);
            if (cycleExists) {
                return new ArrayList<Integer>();
            }
        }


        return validOrder;
    }

    /**
     * Find dependencies of the job under consideration, before deploying it.
     * 1. A job is <b>scheduled</b> to be deployed, before actually deploying it.
     * 2. Before deploying the job, we have to deploy its dependencies.
     * 3. Until all the dependencies of the job are deployed, we cannot deploy the current job.
     *    - As such, if the current job is also a dependency of another job in the dependency chain,
     *      then we have a cycle.
     *    - We detect the cycle by checking if the current job is already <b>scheduled</b> for deployment.
     *
     * @param job the job to be <b>scheduled</b> for <b>deployment</b>.
     * @param jobGraph a global graph of jobs & dependencies
     * @param validOrder the valid order in which jobs should be deployed.
     * @return true, if a cycle has been detected in the dependency chain
     *          false, otherwise.
     */
    private static boolean findDepsDFS(Job job, JobGraph jobGraph, List<Integer> validOrder) {
        // current job has been deployed. No need to go further.
        if (job.deployed) {
            return false;
        }
        // job has not been deployed, but scheduled;
        // And we are visiting it again - as part of the current dependency chain : A cycle exists.
        if (job.scheduled) {
            return true;
        }

        job.scheduled = true;
        // runs in O(E) time; does not repeat if job/dep has already been visited.
        dependencyLoop:
        for (Integer jobId : job.dependencies) {
            Job dependency = jobGraph.idJobMap.get(jobId);
            boolean cycleExists = findDepsDFS(dependency, jobGraph, validOrder);
            if (cycleExists) {
                return true;
            }
        }
        // Either all dependencies of the current job have been DEPLOYED
        // Or, the job has no dependencies. Add the job to the valid order.
        validOrder.add(job.jobId);

        job.scheduled = false;
        job.deployed = true;

        return false;
    }

    /**
     * Data Structure that represents all the jobs under consideration mapped to their ids,
     * for fast lookup (O(1))
     */
    static class JobGraph {
        final List<Job> jobs;
        final Map<Integer, Job> idJobMap; // because we only know jobIds (input is in jobIds),
                                    // we need a (fast) way to get a job (O(1) lookup using map)

        /**
         * Creates the job graph in O(V * E) time.
         *
         * @param jobIds list of job ids
         * @param deps dependency of jobs, represented as 2-length-ed Integer array
         * @param dummy to differentiate this constructor from the other.
         */
        JobGraph(List<Integer> jobIds, List<Integer[]> deps, boolean dummy) {
            // Construct the Graph of jobs by using jobIds & their dependencies
            List<Job> jobs = new ArrayList<>();
            jobLoop:
            for (int jobId : jobIds) {
                Set<Integer> dependenciesOfJob = new HashSet<>();

                JobDepsLoop:
                for (Integer[] dependencyPair : deps) {
                    if (jobId == dependencyPair[1]) {
                        dependenciesOfJob.add(dependencyPair[0]);
                    }
                }
                jobs.add(new Job(jobId, dependenciesOfJob));
            }
            // ^^^ these nested for loops result in O(V * E) time complexity.
            // To optimize it, we break & fix it.
            this.jobs = jobs;

            HashMap<Integer, Job> idJobMap = new HashMap<>();
            jobIdMapLoop:
            for (Job job : jobs) {
                idJobMap.put(job.jobId, job);
            }
            this.idJobMap = idJobMap;
        }

        /**
         * Optimal creation of graph in O(V + E) time
         * @param jobIds list of job ids
         * @param deps dependency of jobs, represented as 2-length-ed Integer array
         **/
        JobGraph(List<Integer> jobIds, List<Integer[]> deps) {
            // Construct the Graph of jobs by using jobIds & their dependencies
            List<Job> jobs = new ArrayList<>();
            jobLoop:
            for (int jobId : jobIds) {
                Set<Integer> dependenciesOfJob = new HashSet<>();
                jobs.add(new Job(jobId, dependenciesOfJob));
            }
            this.jobs = jobs;

            HashMap<Integer, Job> idJobMap = new HashMap<>();
            jobIdMapLoop:
            for (Job job : this.jobs) {
                idJobMap.put(job.jobId, job);
            }
            this.idJobMap = idJobMap;

            // we break this loop out of the `jobLoop`
            JobDepsLoop:
            for (Integer[] dependencyPair : deps) {
                int job = dependencyPair[1];
                int dependencyOfJob = dependencyPair[0];
                idJobMap.get(job).dependencies.add(dependencyOfJob);
            }
        }
    }

    /**
     * A {@link Job} has -
     * 1. id
     * 2. list of dependencies. Deps are represented by their respective jobIds
     * 3. Whether it is scheduled to be deployed.
     * 4. Whether it has been actually deployed.
     */
    static class Job {
        final int jobId;
        final Set<Integer> dependencies;
        boolean scheduled = false;
        boolean deployed = false;

        Job(int jobId, Set<Integer> dependencies) {
            this.jobId = jobId;
            this.dependencies = dependencies;
        }
    }
}
