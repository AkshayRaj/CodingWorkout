/**
 * Created by Akshayraj
 */
package ark.coding.books.interview_kickstart.recursion;

import java.util.ArrayList;
import java.util.List;

public class StringSubsets {

    static String[] generate_all_subsets(String s) {
        List<String> subsets = new ArrayList<>();

        generateSubsets(s, 0, "", subsets);

        return subsets.toArray(new String[subsets.size()]);
    }

    private static void generateSubsets(
            final String orgString,
            int idx,
            String partialSolution,
            final List<String> subsets) {
        if (idx == orgString.length()) {
            subsets.add(partialSolution);
            return;
        }

        String include = partialSolution + orgString.charAt(idx);
        String exclude = partialSolution;
        idx++;
        generateSubsets(orgString, idx, include, subsets);
        generateSubsets(orgString, idx, exclude, subsets);
    }
}
