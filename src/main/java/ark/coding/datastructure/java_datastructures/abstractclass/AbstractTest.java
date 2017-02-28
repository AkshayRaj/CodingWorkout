package ark.coding.datastructure.java_datastructures.abstractclass;

/**
 * Created by Akshayraj on 9/1/15.
 */
public class AbstractTest {
    int x;
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
        Integer i = 3;
        print(i);
        int x = 0;

        first.printString();
        second.printString();
    }

    public static void print(Integer i){
        System.out.println(i);
        i = 5;
        System.out.println(i);
    }
}
