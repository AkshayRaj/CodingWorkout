package ark.coding.datastructure.java_datastructures.threadbehavior;

/**
 * Created by Akshayraj on 2/4/16.
 */
public class ThreadBehavior {
    public static int counter = 0;
    private static long ONE_SEC = 1000l;

    public static void increaseCount(){
        counter++;
    }

    public static void main(String[] args){
        One t1 = new One();
        Two t2 = new Two();

        t1.start();
        t2.start();
    }


    private static class One extends Thread{
        private static final String TAG = One.class.getSimpleName();
        @Override
        public void run() {
            while(true) {
                increaseCount();
                System.out.println(TAG + ": " + counter);
            }
        }
    }

    private static class Two extends Thread{
        private static final String TAG = Two.class.getSimpleName();
        @Override
        public void run() {
            while (true){
                System.out.println(TAG + ": " + counter);
//                System.out.println(TAG + ": ");
            }
        }
    }
}
