package lzhang.question.array;

import java.util.Arrays;

/**
 * Given a sequence, find the length of the longest increasing subsequence from a given sequence .
 * 
 * Given array { 10, 22, 9, 33, 21, 50, 41, 60, 80 }
 * LIS is {10, 22, 33, 50, 60, 80}.
 * 
 * @author lzhang
 *
 */
public class LongestIncreaseSubsequence {
    public static int test() {
        int[] a = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
        
        int result = recursive(a, a.length);
        result = dp(a, a.length);
        return result;
    }
    
    /**
     * This also works but harder to understand
     * 
     * @param a
     * @param n
     * @return
     */
    private static int recursive (int[] a, int n) {
        if(a == null || n <= 0) {
            return 0;
        } else if(n == 1) {
            return 1;
        }
        
        int max_ending_here = 1;
        
        for(int i = 1; i < n; i++) {
            int result = recursive(a, i);
            
            if(a[i-1] < a[n-1] // why? 
                    && result + 1 > max_ending_here) {
                max_ending_here = result + 1;
            }
        }
        
        return max_ending_here;
    }
    
    /**
     * This is the same as LongestAscendingSequence.dp()
     * 
     * @param a
     * @param n
     * @return
     */
    private static int dp(int[] a, int n) {
        if(a == null || n == 0) {
            return 0;
        }
        
        /*
         * Create an array of the same size to memorize the longest sub 
         * sequence length at each index. 
         */
        int[] mem = new int[n];
        
        /*
         * Initialize the array to 1 since every element is by default the
         * longest subsequence of length 1.
         */
        Arrays.fill(mem, 1);
        
        /*
         * Use 2 loops:
         * The outer loop i walks through the array from 1 (0 index was set to 1 already).
         * The inner loop j compares every element from (0 to i-1). 
         * 
         * If a[i] > a[j], then there is one more element (at i) that can be included to 
         * an increasing sequence.
         * 
         */
        int maxLength = 1;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(a[i] > a[j]) {
                    mem[i] = Math.max(mem[i], mem[j] + 1);
                }
            }
            
            if(mem[i] > maxLength) {
                maxLength = mem[i];
            }
        }
        
        return maxLength;
    }
}
