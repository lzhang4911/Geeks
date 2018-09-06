package lzhang.question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import wzhang.drive.Driver;

/**
 * Given an array of chars, {a, b, c, d, e, f}, find all permutations.
 * 
 * There can be different flavors:
 *  
 *  1. permute whole string.
 *  2. permute subset
 *   
 * @author lzhang
 *
 */
public class StringPermutation {

    public static Collection<String> test() {
        char[] string = new char[] {'A', 'B', 'A'};
        
        // use a list if there is no repeat in string
//        List<String> result = new ArrayList<>();
        
        // otherwise use Set and use String representation instead of char[]
        Set<String> result = new HashSet<String>();
        
        int count = loopAndRecurse(string, 0, string.length, result);
        return result;
    }
    
    /**
     * 
     * @param a
     * @param index: starting index from 0 .. n-1
     * @param n: total elemnt count
     * @param result
     */
    private static int loopAndRecurse(char[] a, int start, int n, Collection<String> result) {
        int permCount = 0;
        
        // base case
        if(a == null) {
            return permCount;
        }
        
        if(start == n-1) {
            // Already reached the end of the string. No more element to be considered. Add it to result set.
            permCount++; // count including dup
            result.add( new String(a) ); // collapse the dup due to use of HashSet 
            Driver.print(a);
        }
        
        for(int i = start; i < n; i++) {
            // 1. choose at current index (start) one at a time from (start..n-1)
            char temp = a[start];
            a[start] = a[i];
            a[i] = temp;
                        
            // 2. recurse
            permCount += loopAndRecurse(a, start+1, n, result);
            
            // 3. unchosen
            a[i] = a[start];
            a[start] = temp;
        }
        
        return permCount;
    }
}
