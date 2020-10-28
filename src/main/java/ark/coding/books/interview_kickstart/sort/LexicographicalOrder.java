package ark.coding.books.interview_kickstart.sort;

import java.util.*;

public class LexicographicalOrder {
    static String[] solve(String[] arr) {
        Map<String, List<String>> map = new HashMap<>();

        for (String string : arr) {
            String[] keyValuePair = string.split(" ");
            String key = keyValuePair[0];
            List<String> values = map.getOrDefault(key, new ArrayList<>());
            values.add(keyValuePair[1]);
            map.put(key, values);
        }

        for (List<String> valuesSet : map.values()) {
            Collections.sort(valuesSet, new Comparator<String>() {
                @Override
                public int compare(String string1, String string2) {
                    return string2.compareTo(string1);
                }
            });
        }

        String[] solution = new String[map.size()];
        int idx = 0;
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            solution[idx] = new StringBuilder()
                    .append(entry.getKey())
                    .append(":")
                    .append(entry.getValue().size())
                    .append(",")
                    .append(entry.getValue().get(0))
                    .toString();
            idx++;
        }

        return solution;
    }
}
