package ark.coding.books.interview_kickstart.graphs;

import ark.coding.tools.Utils;

import java.util.*;

public class StringTransitionSequence {

    public static void main(String[] args) {
        Utils.printArray(string_transformation(
                new String[]{"poon", "plee", "same", "poie", "plie", "poin", "plea"},
                "toon",
                "plea"
        ));
    }

    static String[] string_transformation(String[] words, String start, String stop) {
        // dictionary to check the words
        Set<String> dictionary = new HashSet<>();
        for (String word : words) {
            dictionary.add(word);
        }
        dictionary.add(stop);

        // Edge case - if start == stop, see if there is an interim node
        if (start.equals(stop)) {
            List<String> sequence = new ArrayList<>();
            sequence.add(start);
            List<IntermediaryString> neighbours = findNeighbours(new IntermediaryString(start, sequence), dictionary);

            if (neighbours.size() > 0) {
                neighbours.get(0).sequence.add(start);
                return neighbours.get(0).sequence.toArray(new String[0]);
            }
            else {
                return new String[]{"-1"};
            }
        }

        Queue<IntermediaryString> frontier = new LinkedList<>();
        List<String> initSequence = new ArrayList<>(); initSequence.add(start);
        frontier.add(new IntermediaryString(start, initSequence));
        while (!frontier.isEmpty()) {
            IntermediaryString head = frontier.poll();

            // return sequence when we encounter stop
            if (head.string.equals(stop)) {
                return head.sequence.toArray(new String[]{});
            }

            // change one char at a time, and add them in queue for further consideration
            frontier.addAll(findNeighbours(head, dictionary));
        }

        return new String[]{"-1"};

    }

    private static List<IntermediaryString> findNeighbours(IntermediaryString node, Set<String> dictionary) {
        LinkedList<IntermediaryString> neighbours = new LinkedList<>();

        for (String word : dictionary) {
            int diffCount = 0;
            for (int idx = 0; idx < word.length(); idx++) {
                if (node.string.charAt(idx) != word.charAt(idx)) diffCount++;
            }
            if (diffCount == 1) {
                List<String> sequence = new ArrayList<>();
                sequence.addAll(node.sequence);
                sequence.add(word);
                neighbours.add(new IntermediaryString(word, sequence));
            }
        }

        for (IntermediaryString neighbour : neighbours) dictionary.remove(neighbour.string);

        return neighbours;
    }

    static class IntermediaryString {
        final String string;
        final List<String> sequence;

        IntermediaryString(String string, List<String> sequence) {
            this.string = string;
            this.sequence = sequence;
        }
    }
}
