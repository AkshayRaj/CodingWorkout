/**
 * Created by Akshayraj
 */
package ark.coding.interviews.statementcampus;

import java.util.regex.Pattern;

public class BackendPosition {

    public static void main(String[] args){
        String st = "l       &      l";
        Pattern pattern = Pattern.compile("\\s");
        String g = st.replaceAll("\\s*&\\s*"," & ");
        System.out.println(g);
    }
}
