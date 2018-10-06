package lzhang.question.array;

import java.util.Arrays;

import lzhang.util.BaseUtil;

/**
 * https://www.geeksforgeeks.org/roll-characters-string/
 * 

Input : s = "bca"
        roll[] = {1, 2, 3}         
Output : eeb

Explanation :
arr[0] = 1 means roll first character of string -> cca
arr[1] = 2 means roll first two characters of string -> dda
arr[2] = 3 means roll first three characters of string -> eeb
So final ans is "eeb"
 
Input : s = "zcza"
        roll[] = {1, 1, 3, 4}
Output : debb

 * @author lzhang
 *
 */
public class RollCharacters extends BaseUtil {

	public static void test() {
		char[] arr = "bca".toCharArray();
		int[] roll = {1, 2, 3};
		
		// populate a count array to indicate how many times each char hsall be rolled
		Integer[] rollCount = new Integer[arr.length];
		Arrays.fill(rollCount, 0);
		
		for(int i = 0; i < roll.length; i++) {
			for(int j = 0; j < roll[i]; j++) {
				rollCount[j]++;
			}
		}
		print(rollCount);
		
		
		// roll each char
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (char)('a' + (arr[i] + rollCount[i]%26 - 'a') % 26);
		}
		print(arr);
	}
}
