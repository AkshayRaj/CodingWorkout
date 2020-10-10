package ark.coding.interviews.paypal;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Asked two questions -
 *
 * 1. Code review of function to find prime numbers
 *    Function was already written
 * 2. Implement {@link #generate(Map)} function
 */
public class InPerson {

    public static void main(String[] args) {
        Map<String, Map<String, Long>> map = new HashMap<>();
        map.put("One", ImmutableMap.of("NestedOne", 1L));
        map.put("Two", ImmutableMap.of("NestedTwo", 2L));
        map.put("Three", ImmutableMap.of("NestedThree", 3L));

        System.out.println(generate(map));

        Map<String, List<String>> map2 = new HashMap<>();
        map2.put("Fruits", ImmutableList.of("apples", "oranges", "bananas"));
        map2.put("Cars", ImmutableList.of("Honda", "Ford", "Tesla"));
        System.out.println(generate(map2));
    }

    /**
     * Suppose we have a Map<String, ?>, and ? can be a String, Long, List<?>, and Map<String, ?>.
     * Write a method to convert the map to a JSON. String and Long are converted to JSON values, and
     * List<?> is converted to JSON arrays, and Map<String, ?> is converted to JSON objects.
     *
     * Initially, start with just strings and then expand to other types.

     * The JSON Specification may be accessed from: https://www.json.org
     */
    public static String generate(Map<String, ?> map) {
        StringBuilder jsonStringBuilder = new StringBuilder();

        helper(jsonStringBuilder, map);// helper("{", map)

        return jsonStringBuilder.toString();
    }

    private static void helper(StringBuilder jsonBuilder, Object value) {
        if (value instanceof String) {
            jsonBuilder.append("\"").append(value).append("\"");
        } else if (value instanceof Long) {
            jsonBuilder.append(value);
        } else if (value instanceof List) {
            List list = (List) value;
            jsonBuilder.append("[");
            for (int index = 0; index < list.size(); index++) {
                helper(jsonBuilder, list.get(index));

                // for the last object do not add comma
                if (index != list.size() - 1) {
                    jsonBuilder.append(",");
                }
            }
            jsonBuilder.append("]");
        } else if (value instanceof Map) {
            jsonBuilder.append("{");
            Map<String, Object> map = ((Map<String, Object>) value);
            int counter = 1;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                jsonBuilder.append(entry.getKey()).append(":");
                helper(jsonBuilder, entry.getValue());

                // for the last object do not add comma
                if (counter < map.size()) {
                    jsonBuilder.append(",");
                }
                counter++;
            }
            jsonBuilder.append("}");
        }
    }
}
