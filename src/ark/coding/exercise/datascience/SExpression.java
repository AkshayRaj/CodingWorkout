package ark.coding.exercise.datascience;

import java.util.HashMap;

/**
 * Created by Akshayraj on 4/14/16.
 */
public class SExpression {

    static String SExpression(String nodes) {
        String sExpression = "";
        String[] nodePairs = nodes.split(" ");
        HashMap<String, Integer> parentNodes = new HashMap<>();
        for(int index = 0; index < nodePairs.length; index++){
            String parent = nodePairs[index].split(",")[0];
            int count = parentNodes.get(parent);
            if(count > 2){
                System.out.println("E1: More than 2 parents");
            }
        }
        return sExpression;
    }
}
