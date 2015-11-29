package ark.coding.datastructure.primitive_datastructures.arraysandstrings.check.string.rotation;

import ark.coding.Solution;

/**
 * Created by Akshayraj on 11/28/15.
 */
public class StringRotation implements Solution<Boolean> {
    private static final String STRING1 = "aterbottlwe";
    private static final String STRING2 = "waterbottle";
    private static final boolean NOT_ROTATED = false;

    @Override
    public Boolean solution(Object... args) {
        return isStringRotated((String)args[0], (String) args[1]);
    }

    private Boolean isStringRotated(String string1, String string2) {
        if(string1.length() == string2.length()){
            boolean rotated = (string1+string1).indexOf(string2) != -1;
            return rotated;
        }
        return NOT_ROTATED;
    }

    public static void main(String[] args){
        StringRotation stringRotation = new StringRotation();
        System.out.println("isRotated: " + stringRotation.solution(STRING1,STRING2));
    }
}
