package lzhang.question;


/**
 * Each result must have the same sequence as in the original array.
 * For example, if input array is {1, 2, 3, 4} and r is 2, then output 
 * should be {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4} and {3, 4}.
 * 
 * There are 2 ways to solve: fix elements at indexes and recur or chosen-and-unchosen.
 * 
 * https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
 * @author lzhang
 *
 */
public class Combination {
    public static void test() {
        int combinations = 3;
        
        int[] arr = {1, 2, 3, 4, 5};
        
        // data to hold one combination
        int data[] = new int[combinations];
        
        // int[]
        System.out.println("Fix one at a time:");
        recursive(arr, 0, arr.length, data, 0, combinations);
        
        // char[]
        String text = "ABCDEFghi";
        char[] data1 = new char[combinations];
        System.out.println("Fix one at a time:");
        recursive_1(text.toCharArray(), 0, text.length(), data1, 0, combinations);
        
        // chosen-and-unchosen
        System.out.println("Chosen-and-unchosen:");
        recursive_2(text.toCharArray(), 0, text.length(), data1, 0, combinations);
    }
    
    /**
     * Method 1: Fix element at index one at a time using 2 pointers source index srcIndex
     * and result index resIndex
     * 
     * @param arr original array
     * @param srcIndex the current index in source array arr
     * @param length length of arr
     * @param data temporary buffer to hold one combination
     * @param resIndex the current index in the result array data
     * @param combinations elements in each combination
     */
    private static void recursive(int[] arr, int srcIndex, int length, int[] data, int resIndex, int combinations) {
        if(length < combinations || arr == null || data == null) {
            return;
        }
        
        // check if we have got a result at the beginning of every recursion
        if(resIndex == combinations) {
            // we found a combination
            for (int j=0; j<combinations; j++) {
                System.out.print(data[j]+" ");
            }
            System.out.println("");
            
            return;
        }
        
        // real work begins
        
        /*
         * Let data[index] to be every possible element from arr 
         */
        for(int i = srcIndex; i < length; i++) {
            data[resIndex] = arr[i];
            
            recursive(arr, i + 1, length, data, resIndex + 1, combinations);
        }
    }
    
    /**
     * Same approach as the previous one for char array instead of integer 
     * @param arr
     * @param arrIndex
     * @param length
     * @param data
     * @param dataIndex
     * @param combinations
     */
    private static void recursive_1(char[] arr, int arrIndex, int length, char[] data, int dataIndex, int combinations) {
        if(length < combinations || arr == null || data == null) {
            return;
        }
        
        if(dataIndex == combinations) {
            // we found a combination
            for (int j=0; j<combinations; j++) {
                System.out.print(data[j]+" ");
            }
            System.out.println("");
            
            return;
        }
        
        // real work begins
        
        /*
         * Let data[index] to be every possible element from arr 
         */
        for(int i = arrIndex; i < length; i++) {
            data[dataIndex] = arr[i];
            
            recursive_1(arr, i + 1, length, data, dataIndex + 1, combinations);
        }
    }
    
    /**
     * Method 2: For each dataIndex, we try to include the current char and exclude the
     * current char.
     * 
     * @param arr
     * @param arrIndex
     * @param length
     * @param data
     * @param dataIndex
     * @param combinations
     */
    private static void recursive_2(char[] arr, int arrIndex, int length, char[] data, int dataIndex, int combinations) {
        if(arr == null || data == null) {
            throw new IllegalArgumentException("Both source array arr and result array data must not be null");
        }
        
        if(arrIndex >= length) {
            // done with every element in the source array
            return;
        }
        
        if(dataIndex == combinations) {
            // we found a combination
            for (int j=0; j<combinations; j++) {
                System.out.print(data[j]+" ");
            }
            System.out.println("");
            
            return;
        }
        
        // include the current arr[arrIndex] at data[dataIndex] and advance both pointers
        data[dataIndex] = arr[arrIndex];
        recursive_2(arr, arrIndex + 1, length, data, dataIndex + 1, combinations);
        
        /*
         * Exclude the current arr[arrIndex] from data[dataIndex] by keeping dataIndex
         * unchanged and advance pointer arrIndex only
         */
        recursive_2(arr, arrIndex + 1, length, data, dataIndex, combinations);
    }
}
