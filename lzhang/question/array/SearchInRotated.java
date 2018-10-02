package lzhang.question.array;

import java.util.Arrays;

import lzhang.util.BaseUtil;

/**
 * Given a sorted int array. The array is clock-wise rotated k times.
 * Search for a given target.
 * 
 * Ex: 
 * int[] arr = {7, 8, 9, 1, 2, 3, 4, 5, 6}
 * int target = 4 or 9
 * 
 * Assume all numbers are distinct.
 * 
 * Solution 1:
 * - first find the pivot if it exists, the greatest one: pivot must be greater than its next element;
 * - otherwise, keep searching for it in both halves;
 * - with pivot, check if target is greater than arr[0] and decide whether to binary search in first
 *   portion or not.
 * 
 * Solution 2: (better one)
 * No need to search for pivot. Start normal binary search by looking at the mid point.
 * Only one half is sorted meaning arr[l] < arr[h]. Otherwise go with the other half.
 *  
 * @author lzhang
 *
 */
public class SearchInRotated extends BaseUtil {
    public static void test() {
        SearchInRotated p = new SearchInRotated();
        int arr[] = {4, 5, 6, 7, 8, 9, 1, 2, 3};
        int target = 6;
        
        int ret = p.alteredBinarySearch(arr, 0, arr.length - 1, target);
        print( String.format("1. Find index of %d from %s: %d", target, Arrays.toString(arr), ret) );
        
        ret = p.alteredBinarySearch_2(arr, 0, arr.length - 1, target);
        print( String.format("2. Find index of %d from %s: %d", target, Arrays.toString(arr), ret) );
        
        target = 1;
        ret = p.alteredBinarySearch(arr, 0, arr.length - 1, target);
        print( String.format("Find index of %d from %s: %d", target, Arrays.toString(arr), ret) );
        
        ret = p.alteredBinarySearch_2(arr, 0, arr.length - 1, target);
        print( String.format("2. Find index of %d from %s: %d", target, Arrays.toString(arr), ret) );
        
        target = 30;
        ret = p.alteredBinarySearch(arr, 0, arr.length - 1, target);
        print( String.format("Find index of %d from %s: %d", target, Arrays.toString(arr), ret) );
        
        ret = p.alteredBinarySearch_2(arr, 0, arr.length - 1, target);
        print( String.format("2. Find index of %d from %s: %d", target, Arrays.toString(arr), ret) );
        
        print("done");
    }
    /**
     * Consider that the arr *may* have been rotated. With some intelligence,
     * we can use the binary search to find the index of given key.
     * 
     * Return -1 if the key doesn't exist.
     * 
     * @param arr
     * @param l
     * @param h
     * @param target
     * @return
     */
    private int alteredBinarySearch(int[] arr, int l, int h, int target) {
        if(arr == null || l > h) {
            return -1;
        }
        
        // now starts the real work
        int mid = (l + h)/2;
        if(arr[mid] == target) return mid;
        
        /*
         * About the 2 halves: one of them is still perfectly sorted but not the other half.
         * 
         * We can determine which SHOULD contain the target value judging from the half that
         * is perfectly sorted - containing or not containing! As such, we must first locate
         * the half that is completely sorted.
         */
        if(arr[l] < arr[mid]) {
            /*
             * Then we are certain the first half is perfectly sorted. See if the target falls into this half.
             */
            if(target > arr[l] && target < arr[mid - 1]) {
                return this.alteredBinarySearch(arr, l, mid - 1, target);
            } else {
                return this.alteredBinarySearch(arr, mid + 1, h, target);
            }
        } else {
            /*
             * The second half is sorted.
             */
            if(target > arr[mid + 1] && target < arr[h]) {
                return this.alteredBinarySearch(arr, mid + 1, h, target);
            } else {
                return this.alteredBinarySearch(arr, l, mid - 1, target);
            }
        }
    }
    
    private int alteredBinarySearch_2(int[] arr, int l, int h, int target) {
        if(arr == null || l > h) {
            return -1;
        }
        
        // now starts the real work
        int mid = (l + h)/2;
        if(arr[mid] == target) return mid;
        
        if(target > arr[l] && target < arr[mid - 1]) {
            return this.alteredBinarySearch(arr, l, mid - 1, target);
        } else {
            return this.alteredBinarySearch(arr, mid + 1, h, target);
        }
    }
}
