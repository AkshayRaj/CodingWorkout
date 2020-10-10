package ark.coding.books.interview_kickstart.graphs;

import ark.coding.tools.Utils;

import java.util.*;

public class StringDistance {

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
        dictionary.add(start);
        dictionary.add(stop);

        // Identify which chars need to be changed.
        Set<Integer> charsToChangeFromStart = new HashSet<>();
        for (int idx = 0; idx < start.length(); idx++) {
            if (start.charAt(idx) != stop.charAt(idx)) charsToChangeFromStart.add(idx);
        }

        Queue<IntermediaryString> frontier = new LinkedList<>();
        List<String> initSequence = new ArrayList<>(); initSequence.add(start);
        frontier.add(new IntermediaryString(start, charsToChangeFromStart, initSequence));
        while (!frontier.isEmpty()) {
            IntermediaryString head = frontier.poll();

            // return sequence when we encounter stop
            if (head.string.equals(stop)) {
                return head.sequence.toArray(new String[]{});
            }

            // change one char at a time, and add them in queue for further consideration
            for (Integer charIdx : head.charsToBeChanged) {
                char[] next = head.string.toCharArray();
                next[charIdx] = stop.charAt(charIdx);
                String nextString = new String(next);

                // if intermediary string exists in dictionary, then add it.
                if (dictionary.contains(nextString)) {
                    Set<Integer> charsToBeChanged = new HashSet<>(head.charsToBeChanged);
                    charsToBeChanged.remove(charIdx);
                    List<String> sequence = new ArrayList<>();
                    sequence.addAll(head.sequence);
                    sequence.add(nextString);
                    frontier.add(new IntermediaryString(nextString, charsToBeChanged, sequence));
                }
            }
        }

        return new String[]{"-1"};

    }

    static class IntermediaryString {
        final String string;
        final Set<Integer> charsToBeChanged;
        final List<String> sequence;

        IntermediaryString(String string, Set<Integer> charsToBeChanged, List<String> sequence) {
            this.string = string;
            this.charsToBeChanged = charsToBeChanged;
            this.sequence = sequence;
        }
    }
}
