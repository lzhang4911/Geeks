package lzhang.question.array;

/**
 * Given an array that contains both positive and negative integers, find the product of the maximum product 
 * subarray. Expected Time complexity is O(n) and only O(1) extra space can be used.
 * 
 * It also assumes that the given input array always has a positive output.
 * 
Input: arr[] = {6, -3, -10, 0, 2}
Output:   180  // The subarray is {6, -3, -10}

Input: arr[] = {-1, -3, -10, 0, 60}
Output:   60  // The subarray is {60}

Input: arr[] = {-2, -3, 0, -2, -40}
Output:   80  // The subarray is {-2, -40}
*/
public class MaximumProductSubarray {

    public static long test() {
        MaximumProductSubarray p = new MaximumProductSubarray();
        
        //int arr[] = {6, -3, -10, 0, 2};
        int arr[] = {-1, -3, -10, 0, 60};
        //int arr[] = {-2, -3, 0, -2, -40};
        long result = p.maxProduct(arr);
        
        return result;
    }
    
    /**
     * Walk through the array once and keep max product along the way
     * 
     * @param arr
     * @return
     */
    private long maxProduct(int[] arr) {
        if(arr == null) {
            return 0;
        }
        
        // starting index of non-zero
        int start = 0;
        long result = 0;
        
        while(result == 0) {
            result = arr[start++];
        }
        
        long productSoFar = result;
        for(int i = start; i < arr.length; i++) {
            if(arr[i] == 0) {
                productSoFar = 1;
            } else {
                productSoFar *= arr[i];
            }
            
            result = Math.max(productSoFar, result);
        }
        
        return result;
    }
}
