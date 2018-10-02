package lzhang.question.array;

import java.util.HashMap;
import java.util.Map;

import lzhang.util.BaseUtil;

/**
 * Given an unsorted integer array and an integer K, find a subarray whose sum
 * equals K. There may not be such a subarray.
 * 
 * As usual, a double-loop or O(n^2) can solve this problem. But can we do better?
 * 
 * https://www.geeksforgeeks.org/find-subarray-with-given-sum/
 * https://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/
 * 
 * Considering that negative numbers may appear in the array, the best way is to calculate
 * sum(i) from element 0 to i. The sum(b) - sum(a) would be the sum from a to b. Then this
 * question is changed to find 2 sums whose difference is K.
 * 
 * @author lzhang
 *
 */
public class EqualSumSubarray extends BaseUtil {
	public static boolean test() {
		int arr[] = {10, 2, -2, -20, 10};
		int K = -10;
		
		// save each sum(i) into a hash map
		Map<Integer, Integer> sumIndexMap = new HashMap<>();
		
		int curSum = 0;
		for(int i = 0; i < arr.length; i++) {
			curSum += arr[i];
			
			if(curSum == K) {
				print("Found subarray from 0 to " +  i);
				return true;
			}
			
			Integer startIndex = sumIndexMap.get(curSum - K);
			if(startIndex != null) {
				// found the subarray from startIndex to i
				print( String.format("Found subarray from index %d to %d", startIndex, i));
				return true;
			}
			
			sumIndexMap.put(curSum, i);
		}
		
		return false;
	}

}
