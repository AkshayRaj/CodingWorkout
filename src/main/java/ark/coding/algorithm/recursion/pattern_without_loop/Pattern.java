package ark.coding.algorithm.recursion.pattern_without_loop;

/**
 * Created by Akshayraj on 10/1/15.
 */
public class Pattern {
    private static int mInitialValue = 0;
    private boolean mZeroCrossedOnce = false;

    public static void main(String[] args){
        mInitialValue = 21;
        Pattern pattern = new Pattern();
        pattern.printPattern(mInitialValue);
    }

    public int printPattern(int value) {
        System.out.println("\t" + value);
        if(mInitialValue <= 0){
            return mInitialValue;
        }
        if(value == mInitialValue && mZeroCrossedOnce){
            return value;
        }
        if(value <= 0 ){
            setZeroCrossedOnce(true);
            return printPattern(value + 5);
        }
        if(!isZeroCrossedOnce()){
            return printPattern(value - 5);
        }else{
            return printPattern(value + 5);
        }
    }

    public boolean isZeroCrossedOnce() {
        return mZeroCrossedOnce;
    }

    public void setZeroCrossedOnce(boolean zeroCrossedOnce){
        mZeroCrossedOnce = zeroCrossedOnce;
    }
}
