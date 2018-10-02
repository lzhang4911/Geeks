package lzhang.question.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Typical questions are:
 * 1. find any pair, from a given array, whose sum equals a target value (0 or other);
 *    1, 45, -2, 23, -45 => true 
 * 
 * 2. find 3 numbers, from a given array, whose sum is at a given number. 
 *    1, 45, 34 -46, 1 => true
 *    
 * Just return the first result instead of boolean.
 * 
 * Q1: find a pair with sum at given target;
 * Q2: find triplet with sum at given target;
 * Q3: find triplet with max product;
 * 
 * @author lzhang
 *
 */
public class GivenArrayAndTarget {

    public static List<Integer> test() {
        int[] arr = {1, 45, -2, 23, -45, 0, 1, 23};
        int target = 0;
        
        long maxProduct = findTripletMaxProduct(arr);
        
        List<Integer> copiedFromGeaks = findTriplets(arr, target);
        
//        return two(arr, target, -1);
        return three(arr, target);
    }
    
    /**
     * Time: O(n), space: O(n)
     * 
     * @param arr
     * @param target
     * @param indexToSkip not really required for a pair of two, but needed
     *        if using the same method for a triplet. Set this to -1 for pair.
     * @return
     */
    private static List<Integer> two(int[] arr, int target, int indexToSkip) {
        List<Integer> result = new ArrayList<Integer>();
        
        if(arr == null || arr.length < 2) {
            return result;
        }
        
        // use a HashSet, O(n) extra memory, to achieve O(n) time complexity
        Set<Integer> set = new HashSet<>();
        
        // just one linear walk
        int a;
        for(int i=indexToSkip + 1; i<arr.length; i++) {
            a = arr[i];
            
            // was the counterpart already placed in the set?
            if( set.contains(target - a) ) {
                // find a pair
                result.add(a);
                result.add(target - a);
                System.out.println("Found the pair: " + result);
        
                // break at first finding or find all?
//                break;
            }

            set.add(a);
        }
        
        return result;
    }
    
    /**
     * Reuse the previous method two()
     * 
     * Time: O(n^2), space: O(n)
     * 
     * @param arr
     * @param target
     * @return
     */
    private static List<Integer>  three(int[] arr, int target) {
        List<Integer> result = new ArrayList<Integer>();
        
        if(arr == null || arr.length < 3) {
            return result;
        }
        
        List<Integer> temp;
        for(int i=0; i<arr.length - 2; i++) {
            temp = two(arr, target - arr[i], i);
            if(!temp.isEmpty()) {
                // found the pair for this arr[i]
                result.add(arr[i]);
                result.addAll(temp);
                
                System.out.println("Found the triplet: " + result);
//                break;
            }
        }
        
        return result;
    }
    
    /**
     * Merge the 2 methods above to create one solid method
     * Time: O(n^2), space: O(n)
     * 
     * Find all triplets
     * 
     * @param arr
     * @param target
     * @return
     */
    private static List<Integer> findTriplets(int arr[], int target) {
        List<Integer> result = new ArrayList<Integer>();
        if(arr == null) {
            return result;
        }
        
        int n = arr.length;
     
        for (int i = 0; i < n-1; i++) {
            // Find all pairs with sum equals to
            // "-arr[i]"
            Set<Integer> s = new HashSet<>();
            
            for (int j=i+1; j<n; j++) {
                int x = target -(arr[i] + arr[j]);
                
                if (s.contains(x)) {
                    System.out.println( String.format("%d %d %d\n", x, arr[i], arr[j]) );
                    
                    result.add(x);
                    result.add(arr[i]);
                    result.add(arr[j]);
                } else {
                    s.add(arr[j]);
                }
            }
        }
     
        if(result.isEmpty()) {
            System.out.println("No Triplet Found");
        }
        
        return result;
    }
    
    /**
     * One approach would be to find 2 smallest and 3 biggest and the max from
     * (s1*s2, b1*b2*b3) in case there are negative values in the
     * array.
     * 
     * The final max would be 
     * 
     * max(s1*s2*b1, b1*b2*b3)
     * 
     * where b1 > b2 > b3.
     * 
     * This could be easily done by sorting the array first, which requires O(nlogn)
     * in time complexity.
     * 
     * However, sorting may be disallowed. Then can we scan the array for such 5
     * numbers?
     * 
     * @param arr
     * @return
     */
    private static long findTripletMaxProduct(int[] arr) {
        if(arr == null || arr.length < 3) {
            return 0;
        }

        // 1 smallest with s1 the smallest 
        int s1 = Integer.MAX_VALUE, s2 = Integer.MAX_VALUE;
        
        // 3 greatest with g1 the smallest
        int g1 = Integer.MIN_VALUE, g2 = Integer.MIN_VALUE, g3 = Integer.MIN_VALUE;
        
        int temp;
        for(int a : arr) {
            // find the smallest
            if(a < s2) s2 = a;
            if(s2 < s1) {
                // swap
                temp = s1;
                s1 = s2;
                s2 = temp;
            }
            
            // find the greatest
            if(a > g3) g3 = a;
            if(g3 > g2) {
                temp = g2;
                g2 = g3;
                g3 = temp;
            }
            if(g2 > g1) {
                temp = g1;
                g1 = g2;
                g2 = temp;
            }
        }
        
        // the possible 3 max
        long m1 = g1 * g2 * g3;
        long m2 = s1 * s2 * g3;
        
        m1 = Math.max(m1, m2);
        
        return m1;
    }
}
