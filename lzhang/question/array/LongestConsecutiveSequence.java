package lzhang.question.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 
Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

 *
 * This question is a little bit confusing. The result is not really a sequence. Rather,
 * it creates a new consecutive sequence out of the original input.
 * 
 * @author lzhang
 *
 */
public class LongestConsecutiveSequence {
    public static int test() {
        int[] arr = {100, 4, 200, 1, 3, 2};
        
        LongestConsecutiveSequence p = new LongestConsecutiveSequence();
        int res = p.findLongestConsecutiveSequence(arr);
        return res;
    }
    
    /**
     * Time complexity O(n) - walking through the array twice but also use O(n) extra
     * space. A consecutive sequence is a set of numbers that are consecutive in value
     * but can be in any order in the original array.
     * 
     * So, a naive solution would be to sort the array first and the linear walk through
     * to count the longest consecutive sequence. O(nlogn + n) = O(nlogn). Bad!
     * 
     * @param arr
     * @return
     */
    private int findLongestConsecutiveSequence(int[] arr) {
        if(arr == null) {
            return 0;
        }
        
        // first place the the array into a hash set for O(1) lookup later
        Set<Integer> set = new HashSet<>();
        for(int i : arr) {
            set.add(i);
        }
        
        int maxLength = 0;
        
        /**
         * Scan through the array, check for each element and see if it starts a sequence.
         * The intelligence here is to locate the smallest number to start the sequence. For
         * example, if the input is [4, 5, 3, 2], then don't start the sequence when we see
         * 4 or 5 or 3 because another number that is 1 less is available. The sequence look
         * will start till we see number 2, because there is no number 1 available.
         */
        for(int i = 0; i < arr.length; i++) {
            int count = 0;
            
            /**
             * This is a good trick: by looking for a value that is 1 less allows to
             * locate the smallest value that starts a consecutive sequence and
             * count the numbers in that sequence.
             */
            if(!set.contains(arr[i] - 1)) {
                // this might be the smallest one that starts a sequence
                while(set.contains(arr[i] + count)) {
                    count++;
                }
                
                maxLength = Math.max(maxLength, count);
            }
        }
        
        return maxLength;
    }
}
