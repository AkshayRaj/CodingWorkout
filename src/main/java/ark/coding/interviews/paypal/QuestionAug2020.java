package ark.coding.interviews.paypal;

import java.util.*;

public class QuestionAug2020 {

    public static List<Integer> hackerCards(List<Integer> collection, int d) {
        // i: 0 ~> collection.size-1
        // collection[i] != i+1 && not in giftBag, d = d - (i+1), add (i+1) to giftBag
        int budget = d;
        List<Integer> giftBag = new ArrayList<>();

        int previousCard = 0;
        outer: for (int idx = 0; idx < collection.size(); idx++) {
            int currentLeanneCard = collection.get(idx);
            for (int cardNumber = previousCard+1; cardNumber < currentLeanneCard; cardNumber++) {
                int remainingAmount = budget - cardNumber;
                if (remainingAmount >= 0) {
                    giftBag.add(cardNumber);
                    budget = remainingAmount;
                }
                else {
                    break outer;
                }
            }
            previousCard = currentLeanneCard;
        }

        // still money left
        int cardNumber = previousCard + 1;
        while (budget > 0 && budget >= cardNumber && cardNumber <= Math.pow(10, 9)) {
            budget = budget - cardNumber;
            if (budget >= 0) {
                giftBag.add(cardNumber);
                cardNumber++;
            }
        }

        return giftBag;
    }

    /*
     * Complete the 'minCost' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER numProjects
     *  2. INTEGER_ARRAY projectId
     *  3. INTEGER_ARRAY bid
     */

    public static long minCost(int numProjects, List<Integer> projectIds, List<Integer> bids) {
        // check if there are no bid for any project
        Map<Integer, PriorityQueue<Integer>> projectBidMap = new HashMap<>();
        for (int idx = 0; idx < projectIds.size(); idx++) {
            Integer projectId = projectIds.get(idx);
            Integer bidForProject = bids.get(idx);
            PriorityQueue<Integer> bidsForProject = projectBidMap.getOrDefault(
                    projectId, new PriorityQueue<Integer>((a,b) -> a-b));
            bidsForProject.add(bidForProject);
            projectBidMap.put(projectId, bidsForProject);
        }

        // If any of the projects does not contain a single bid.
        for (int projectId = 0; projectId < numProjects; projectId++) {
            if (!projectBidMap.containsKey(projectId)) return -1;
        }

        // get minimum cost for all projects
        long minCost = 0;
        for (Map.Entry<Integer, PriorityQueue<Integer>> projectBidsEntry : projectBidMap.entrySet()) {
            minCost = minCost + projectBidsEntry.getValue().peek();
        }
        return minCost;
    }
}
