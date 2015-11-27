package ark.coding.datastructure.java_datastructures.listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akshayraj on 8/31/15.
 */
public class ListenerTest {

    static ListenerTest mListenerTest;
    static List<Listener> mListeners = new ArrayList<Listener>();

    public static void main(String[] args){
        mListenerTest = new ListenerTest();
        mListenerTest.addListeners();

        mListenerTest.executeTest();
    }

    private void executeTest() {
        System.out.println("Trip will be NULL");
        mListenerTest.notifyListeners();
        //System.out.println("Trip will not be NULL");
    }

    private void addListeners() {
        Listener listener1 = new Listener() {
            @Override
            public void onValueChanged(String string) {
                System.out.println("Listener1: " + string);
            }
        };
        Listener listener2 = new Listener() {
            @Override
            public void onValueChanged(String string) {
                System.out.println("Listener2: " + string);
            }
        };
        Listener listener3 = new Listener() {
            @Override
            public void onValueChanged(String string) {
                System.out.println("Listener3: " + string);
            }
        };
        Listener listener4 = new Listener() {
            @Override
            public void onValueChanged(String string) {
                doNotReturn();
                System.out.println("Listener4: " + string);
            }
        };

        mListeners.add(listener1);
        mListeners.add(listener2);
        mListeners.add(listener3);
        //mListeners.add(listener4);
    }

    private void doNotReturn() {
        System.out.println("Listener4 started...but will not return !");
        while(true){
            //System.out.println("Not returning");
        }
    }

    private void notifyListeners(){
        for(Listener listener : mListeners){
            listener.onValueChanged("executes and returns !");
        }
    }
}
