package lzhang.question.array;

import lzhang.util.BaseUtil;

/**
 * Unlike MaxSumSubarray, this question is to find in a given array the max sum of a sequence
 * where no two elements are next to each other. For example,
 * - Input:  {3, 2, 5, 10, 7}
 * - Output: 15 ( the sum of {3, 5, 7})
 *
 * Can this be done using include-exclude? Yes it works!
 * 
 * @author lzhang
 *
 */
public class MaxSumNonAdjacentSequence extends BaseUtil {
    public static int test() {
        MaxSumNonAdjacentSequence p = new MaxSumNonAdjacentSequence();
        
//        int[] arr = {3, 2, 5, 10, 7}; // 15
        int arr[] = {5, 5, 10, 100, 10, 5}; // 110
        return p.maxSumNonAdjacent(arr, arr.length);
    }

    private int maxSumNonAdjacent(int[] arr, int len) {
        if(len == 0) return 0;
        if(len == 1) return arr[len - 1];
        if(len == 2) return Math.max(arr[len - 1], arr[len - 2]);
        
        return Math.max(
                arr[len - 1] + maxSumNonAdjacent(arr, len - 2), // include the last one 
                maxSumNonAdjacent(arr, len - 1) // exclude the last one
                );
    }
}
