package lzhang.question.array;

/**
 * Kadane's Algorithm
 * 
 * Given an array containing both negative and positive integers. Find the contiguous sub-array with maximum sum.
 * 
 * {-2, -3, 4, -1, -2, 1, 5, -3} -> 7
 * 
 * 
 * @author lzhang
 *
 */
public class MaxSumSubarray {

    /**
     * O(n) to walk through the array
     * 
     * @param a
     * @return
     */
    public static int findMaxSum(int[] a) {
        if(a == null) return 0;
        
        int curSum = a[0];
        int maxSum = curSum;
        
        for(int i = 1; i < a.length; i++) {
            if(a[i] > curSum + a[i]) {
                curSum = a[i];
            } else {
                curSum = curSum + a[i];
            }
            
            if(curSum > maxSum) {
                maxSum = curSum;
            }
            
            /*
             * Or it can simply be
             * curSum = Math.max(a[i], a[i] + curSum);
             * 
             * maxSum = Math.max(maxSum, curSum);
             * 
             * that's it!
             */
        }
        
        return maxSum;
    }
}
