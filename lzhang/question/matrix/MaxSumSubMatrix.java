package lzhang.question.matrix;

import lzhang.util.BaseUtil;

/**
 * Given a 2D matrix with both positive and negative integers, find a sub
 * matrix whose sum is the max.
 * 
 * 
Input :   0 1 1 0
          1 1 1 1
          1 1 1 1
          1 1 0 0

Output :  1 1 1 1
          1 1 1 1
           
 * @author lzhang
 *
 */
public class MaxSumSubMatrix extends BaseUtil {
    public static int test() {
        MaxSumSubMatrix p = new MaxSumSubMatrix();
        
        int[][] mat = {
                { 1,  2, -1, -4, -20},
                {-8, -3,  4,  2,   1},
                { 3,  8, 10,  1,   3},
                {-4, -1,  1,  7,  -6}
        };
        
        int maxSubSum = p.findMaxSubSum(mat);
        
        return maxSubSum;
    }
    
    /**
     * Naive solution is to use 4 loops to find all possible sub matrix. O(n^4).
     * 
     * DP can get it down in O(n^2).
     * 
     * @param mat
     * @return
     */
    private int findMaxSubSum(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        
        int[] currentResult;
        int maxSum = Integer.MIN_VALUE;
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        
        for (int leftCol = 0; leftCol < cols; leftCol++) {
            int[] tmp = new int[rows];
     
            for (int rightCol = leftCol; rightCol < cols; rightCol++) {
                for (int i = 0; i < rows; i++) {
                    tmp[i] += mat[i][rightCol];
                }
                
                currentResult = kadane(tmp);
                
                if (currentResult[0] > maxSum) {
                    maxSum = currentResult[0];
                    left = leftCol;
                    top = currentResult[1];
                    right = rightCol;
                    bottom = currentResult[2];
                }
            }
        }
        
        System.out.println("MaxSum: " + maxSum + 
                        ", range: [(" + left + ", " + top + 
                          ")(" + right + ", " + bottom + ")]");
              
        return 0;
    }
    
    /**
     * To find maxSum in 1d array.
     * Note this is really a vertical array, but treated as a regular
     * array for the sake of calculation.
     * 
     * return {maxSum, left, right}
     */
    public int[] kadane(int[] a) {
        //result[0] == maxSum, result[1] == start, result[2] == end;
        int[] result = new int[]{Integer.MIN_VALUE, 0, -1};
        int currentSum = 0;
        int localStart = 0;
     
        for (int i = 0; i < a.length; i++) {
            currentSum += a[i];
            if (currentSum < 0) {
                currentSum = 0;
                localStart = i + 1;
            } else if (currentSum > result[0]) {
                result[0] = currentSum;
                result[1] = localStart;
                result[2] = i;
            }
        }
         
        // If all numbers in a are negative, then find one max element
        if (result[2] == -1) {
            result[0] = 0;
            
            // indeed this idea doesn't work
            for (int i = 0; i < a.length; i++) {
                if (a[i] > result[0]) {
                    // because this can never happen since every a[i] < 0!
                    result[0] = a[i];
                    result[1] = i;
                    result[2] = i;
                }
            }
        }
         
        return result;
      }
}
