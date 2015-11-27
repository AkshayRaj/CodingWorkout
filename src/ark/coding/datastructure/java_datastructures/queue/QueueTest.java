package ark.coding.datastructure.java_datastructures.queue;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Akshayraj on 8/22/15.
 */
public class QueueTest {

    public static void main(String[] args){
        ConcurrentLinkedQueue<String> mTraceBuffer;
        mTraceBuffer = new ConcurrentLinkedQueue<>();
        for(int i = 10 ; i >= 1 ; i--) {
            mTraceBuffer.add("" + i + "\n");
        }
        Iterator<String> iterator = mTraceBuffer.iterator();
        StringBuilder trace = new StringBuilder();
        while(iterator.hasNext()){
//            System.out.println(trace.append(iterator.next()));
            trace.append(iterator.next());
        }
        System.out.println(trace.toString());
        int a = 1;
        int b = 1;
        String string = (a == b ? "TRUE" : "FALSE");
        System.out.println(string);
    }
}
