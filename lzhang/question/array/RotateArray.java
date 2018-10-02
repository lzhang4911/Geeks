package lzhang.question.array;

import lzhang.util.BaseUtil;

/*
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
Note:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?
*/
public class RotateArray extends BaseUtil {

    public static Integer[] test() {
        Integer[] arr = {1,2,3,4,5,6,7,8,9};
        int k = 3;
        
        RotateArray p = new RotateArray();
        //p.rotate(arr, k);
        p.reverse(arr, k);
        
//        p.juggling(arr, k);
        
        return arr;
    }
    
    /**
     * There can be a few solutions. One would be using a temp var to hold the last element,
     * and then shift the rest to right by one index. Repeat this k times.
     * 
     * Time complexity: O(n∗k), space O(1). NOT accepted!
     * 
     * Introducing a second buffer makes everything easy, but costs O(n) extra space.
     * 
     * @param arr
     * @param k
     * @return
     */
    private void rotate(Integer[] arr, int k) {
        if(arr == null || k < 1) {
            return;
        }
        
        int temp, len = arr.length;
        
        k = k % arr.length;
        
        while(k > 0) {
            temp = arr[len - 1];
            
            for(int i = len - 1; i > 0; i--) {
                arr[i] = arr[i - 1];
            }
            
            arr[0] = temp;
            k--;
        }
    }
    
    /**
     * Divide the array into segments and move one at a time among these
     * segments
     */
    private void juggling(Integer[] arr, int k) {
        int n = arr.length;
        
        int i, j, m, temp;
        int gcd = gcd(k, n);
        for (i = 0; i < gcd; i++) 
        {
            /* move i-th values of blocks */
            temp = arr[i];
            j = i;
            while (true) 
            {
                m = j + k;
                if (m >= n) 
                    m = m - n;
                if (m == i) 
                    break;
                arr[j] = arr[m];
                j = m;
            }
            arr[j] = temp;
        }
    }
    
    /*Fuction to get gcd of a and b*/
    int gcd(int a, int b) 
    {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
    
    /**
     * Brilliant idea!
     * 
     * Time: O(n)
     * Space: constant
     * 
     * @param arr
     * @param k
     */
    private void reverse(Integer[] arr, int k) {
        if(arr == null || k < 1) {
            return;
        }
        
        k = k % arr.length;

        // first reverse the entire arr - [7, 6, 5, 4, 3, 2, 1]
        reverseArray(arr, 0, arr.length - 1);
        
        // reverse first k - [5, 6, 7, 4, 3, 2, 1]
        reverseArray(arr, 0, k - 1);
        
        // then the rest
        reverseArray(arr, k, arr.length - 1);
        
    }

    /**
     * Swapping without extra space!
     * 
     * @param arr
     * @param s
     * @param e
     */
    private void reverseArray(Integer[] arr, int s, int e) {
        while(s < e) {
            arr[s] = arr[s] + arr[e];
            arr[e] = arr[s] - arr[e];
            arr[s] = arr[s] - arr[e];
            
            s++;
            e--;
        }
    }
}
