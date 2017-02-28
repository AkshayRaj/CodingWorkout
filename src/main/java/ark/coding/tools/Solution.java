package ark.coding.tools;

/**
 * Created by Akshayraj on 3/11/16.
 */
public class Solution {

    public static void main(String[] args){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ZERO")
                .append(";").append("ONE")
                .append(";").append("TWO")
                .append(";").append("THREE")
                .append(";").append("FOUR")
                .append(";").append("FIVE")//attend time
                .append(";").append("SIX");//add exit time
        String string = stringBuilder.toString();

        for(int index = 0; index < string.split(";").length; index++){
            System.out.println(string.split(";")[index]);

        }
    }
}
