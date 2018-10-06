package lzhang.question.array;

import java.util.TreeSet;

import lzhang.util.BaseUtil;

/**
 * https://www.geeksforgeeks.org/subarray-whose-sum-is-closest-to-k/
 * 
 * Given an integer array that is unsorted and a number k, find a subarray
 * whose sum is the closest to k.
 * 
 * Input: a[] = { -5, 12, -3, 4, -15, 6, 1 }, K = 2
 * Output: 1
 * The subarray {-3, 4} or {1} has sum = 1 which is the closest to K.
 * 
 * Input: a[] = { 2, 2, -1, 5, -3, -2 }, K = 7
 * Output: 6
 * Here the output can be 6 or 8
 * The subarray {2, 2, -1, 5} gives sum as 8 which has abs(8-7) = 1 which is same as 
 * that of the subarray {2, -1, 5} which has abs(6-7) = 1.
 * 
 * The naive approach is to calculate all possible subarrays in double loops and
 * find the smallest abs(delta). This would be O(n^2).
 * 
 * Can we solve this in O(n)?
 * The approach is to calculate the aggregated sum at each i and sort them using
 * data structure like TreeSet. 
 * 
 * @author lzhang
 *
 */
public class CloseSumSubarray extends BaseUtil {
    public static void test() {
        CloseSumSubarray p = new CloseSumSubarray();
        int arr[] = { 2, 2, -1, 5, -3, -2 };
        int K = 7;
        int result = p.getLargestSumCloseToK(arr, K);
        
        print("getLargestSumCloseToK: " + result);
    }

    /**
     * https://www.programcreek.com/2016/08/maximum-sum-of-subarray-close-to-k/
     * 
     * Overall time complexity: O(nlogn)
     * Additional Space: O(n)
     * 
     * @param arr
     * @param k
     * @return
     */
    private int getLargestSumCloseToK(int[] arr, int k){
        TreeSet<Integer> set = new TreeSet<Integer>();
        
        // why?
        set.add(0);
        
        // the closest sum to K
        int result=Integer.MIN_VALUE;
        
        int sum=0;
        
        // O(n) for the for-loop
        for(int i=0; i<arr.length; i++){
            sum=sum+arr[i];
     
            /*
             * Because we are searching for the LARGEST sum is the closest to the given
             * value K, such sum should be either equals to K or the the smallest one that
             * is GREATER than K if all of the pre-sums are sorted in ascending order.
             * 
             * Use floor() if the smaller but closest to K is sought for.
             *  
             * O(logn) to find the floor/ceiling in the sorted set.
             * 
             * Therefore, the overall complexity is O(nLogn)
             */
            Integer ceiling = set.ceiling(Math.abs(sum-k));
            if(ceiling!=null){
                result = Math.max(result, sum-ceiling);        
            }
     
            set.add(sum);
        }
     
        return result;
    }
}
