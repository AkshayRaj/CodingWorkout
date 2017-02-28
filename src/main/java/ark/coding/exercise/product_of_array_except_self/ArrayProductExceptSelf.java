package ark.coding.exercise.product_of_array_except_self;

public class ArrayProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        //arrayProductExceptSelf will be :- output[i] = arrayProduct / nums[i];
        int size = nums.length;
        int[] output = new int[size];
        int arrayProduct = 1;
        int no_of_zeros = 0;
        int index_zero = 0;
        for (int i = 0 ;  i < size ; i++ ){
            if(nums[i] == 0){
                no_of_zeros ++;
                index_zero = i;
            }
        }
        /* Edge cases -
           No zeros in array
           One zero in array
           More than one zero array, where output[i] = 0, (0 < i < nums.length)
         */
        switch (no_of_zeros){
            case 0:
                for (int i = 0 ;  i < size ; i++ ){
                arrayProduct = nums[i] * arrayProduct;  
            }
                for (int i = 0 ;  i < size ; i++ ){
                output[i] = arrayProduct / nums[i];
            }
            break;
            case 1:
                for (int i = 0 ;  i < size ; i++ ){
                    if(i != index_zero) {
                        arrayProduct = nums[i] * arrayProduct;
                    }
                }
                for (int i = 0 ;  i < size ; i++ ){
                    if( i != index_zero ){
                        output[i] = 0;
                    }else{
                        output[i] = arrayProduct;
                    }
                }
            break;
            // product except self will be 0 if more then one zeros in array
            default:
                 for (int i = 0 ;  i < size ; i++ ){
                    output[i] = 0;  
            }
        }
        return output;
    }

    public static void main(String[] args){
        int testarray[] = {
                1, 2, 3, 4, 5
        };
        ArrayProductExceptSelf arrayProductExceptSelf = new ArrayProductExceptSelf();
        arrayProductExceptSelf.productExceptSelf(testarray);
    }
}
