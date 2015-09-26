package ark.coding.datastructure.abstractclass;

/**
 * Created by Akshayraj on 9/1/15.
 */
public abstract class AbstractClass {
    private String mString;

    public AbstractClass(String string){
        mString = string;
    }

    public void printString(){
        System.out.println(mString);
    };
}
