package ark.coding.datastructure.abstractclass;

/**
 * Created by Akshayraj on 9/1/15.
 */
public class AbstractTest {

    public static void main(String args[]){
        AbstractClass first = new AbstractClass("first") {
            @Override
            public void printString() {

            }
        };
        AbstractClass second = new AbstractClass("second") {
            @Override
            public void printString() {

            }
        };

        first.printString();
        second.printString();
    }
}
