package lzhang.question.array;

import java.util.Arrays;

/**
 * Given an unsorted integer array
 * [2, 3, 9, 5, 6, 4, 7]
 * 
 * The longest growing (increasing) sequence (LIS) is
 * [2 3 5 6 7] the max length is 5
 * 
 * Note that a sequence doesn't have to be sequential in value nor contiguous
 * as a sub array! As such, below is one of the valid increasing sub sequences:
 * 2, 3, 9. But the longest one is [2 3 5 6 7].
 * 
 * DP is easier to solve this problem.
 * 
 * @author lzhang
 *
 */
public class LongestAscendingSequence {
    public static int test() {
        int[] a = new int[] {2, 3, 9, 5, 6, 4, 7};
        //int[] a = new int[] {5, 6, 7, 8, 9, 3, 4, 1};
        
        int res = dp(a, a.length);
        return res;
    }
    
    private static int dp(int[] a, int n) {
        if(n == 0 || a == null) return 0;
        
        // mem initialized as
        int[] mem = new int[n];
        Arrays.fill(mem, 1); // each element is 1 such sequence though may not be the longest
        
        int max = 0;
        
        /*
         * In 2-loops: compare each element at j from (0, i] with the one at i. Update
         * mem[i] = max( mem[i], mem[j] + 1 ) if arr[i] > arr[j]
         */
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                /*
                 * mem[i] indicates the length of ascending sequence from a[0] to a[i-1]
                 * depending on
                 * (1) how many elements that are smaller than a[i], and
                 * (2) if mem[j] + 1 > mem[i].
                 * 
                 * Replace mem[i] for each j if both conditions are true.
                 */
                if(a[i] > a[j]) {
                    mem[i] = Math.max(mem[i], 1 + mem[j]);
                    max = Math.max(max, mem[i]);
                }
            }
        }
        
        return max;
    }
}
