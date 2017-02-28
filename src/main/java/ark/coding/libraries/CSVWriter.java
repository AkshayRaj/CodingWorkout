package ark.coding.libraries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akshayraj on 11/5/15.
 */
public class CSVWriter {

    public static void main(String[] args){
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // CSV format
        String csv = list.toString().replace("[", "").replace("]", "")
                .replace(", ", ",");
        // CSV format surrounded by single quote
        // Useful for SQL IN QUERY
        String csvWithQuote = list.toString().replace("[", "'").replace("]", "'")
                .replace(", ", "','");
        System.out.println(csv);
    }
}
