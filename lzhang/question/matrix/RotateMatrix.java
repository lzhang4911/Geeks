package lzhang.question.matrix;

import java.util.Arrays;

import lzhang.util.BaseUtil;

/**
 * Given a matrix NxN, rotate it 90 degrees clock wise.
 * 
 * @author lzhang
 *
 */
public class RotateMatrix extends BaseUtil {
    public static void test() {
        RotateMatrix p = new RotateMatrix();
        
        // matrix
        int[][] arr = 
            {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10,11,12},
                {13,14,15,16}
            };
        
        p.rotate(arr);
    }

    /**
     * For clock-wise rotation, we can in each inner loop swap 3 elements clock-wise.
     * Namely, save first one on top row arr[i][j] to temp, and then
     * - copy the last one arr[j][N-i-1] from left column to the first element arr[i][j];
     * - copy the last one from bottom row to first element of that row;
     * - copy the top one on the right column to bottom element;
     * - assign the temp value to the top element in right column.
     * @param arr
     */
    private void rotate(int[][] arr) {
        printMatrix(arr, false);
        
        int N = arr.length;
        
        int temp;
        for(int i = 0; i < N/2; i++) {
            for(int j = i; j < N - i - 1; j++) {
                temp = arr[i][j];
                
                /*
                 * Counter-clock rotation
                 */
                
                // top element in left column
                arr[i][j] = arr[j][N-i-1];
                
                // left element in bottom row
                arr[j][N-i-1] = arr[N-i-1][N-j-1];
                
                // bottom element in right column
                arr[N-i-1][N-j-1] = arr[N-j-1][i];
                
                // last column in top row
                arr[N-j-1][i] = temp;
                
                /*
                 * Let's do counter-clock rotation
                 */
//                arr[i][j] = arr[j][N-i-1];
//                arr[j][N-i-1] = arr[N-i-1][N-j-1];
//                arr[N-i-1][N-j-1] = arr[N-j-1][i];
//                arr[N-j-1][i] = temp;
            }
        }
        
        printMatrix(arr, true);
    }
    
    private void printMatrix(int[][] arr, boolean rotated) {
        print((rotated? "After":"Before") + " rotation:");
        
        for(int i = 0; i < arr.length; i++) {
            print(Arrays.toString(arr[i]));
        }
    }
}
