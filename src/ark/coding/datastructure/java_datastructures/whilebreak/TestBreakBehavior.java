package ark.coding.datastructure.java_datastructures.whilebreak;

/**
 * Created by Akshayraj on 12/5/15.
 */
public class TestBreakBehavior {

    public static void main(String[] args){
        String[] storeNames = new String[10];
        storeNames[0] = "Cold Stone";

        final int s = 0;

        int i = 0;
        while(true) {
            if (true || false) {
                if (i > 10) {
                    System.out.println("i: " + i);
                    break;
                }
                System.out.println("i: " + i);
                i++;
            }
        }
        System.out.println("hh:mm " + secondsToString(4320));
    }

    private static String secondsToString(int pTime) {
        return String.format("%02d:%02d", pTime / 3600, (pTime%3600) / 60);
    }
}
