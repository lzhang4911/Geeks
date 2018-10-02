package lzhang.question.matrix;

import java.util.ArrayList;
import java.util.List;

/*
Input:
    1    2   3   4
    5    6   7   8
    9   10  11  12
    13  14  15  16
Output: 
1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10 
*/
public class PrintMatrixInSpiral {
    /**
     * Give a n x m matrix,
     * row i in (0, n)
     * col j in (0, m)
     * 
     *  1. For top most row 0, travel from j = 0 to j < m to the last column.
     *  2. Then right most column [m-1], walk from i = 1 to i < n the
     *  bottom row.
     *  3. Them bottom most row [n-1], j = m-1-1; j>=0.
     *  4. walking up, i = n-1-1; i>=1
     *  ...
     *  
     *  The key here are
     *  1. to figure out both start and end index when walking along either row or column;
     *  2. the termination condition to stop the walk (when all cells are visited).
     */
    public static void test() {
        // initialize a 4 x 4 matrix
        int[][] matrix = {
                {1,    2,   3,   4},
                {5,    6,   7,   8},
                {9,   10,  11,  12},
                {13,  14,  15,  16}
        };
        
        PrintMatrixInSpiral p = new PrintMatrixInSpiral();
        p.method1(matrix);
    }
    
    private void method1(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int rowsVisited = 0, colsVisited = 0;
        
        while(rowsVisited < rows || colsVisited < cols) {
            // from left to right
            rowsVisited += rowWalk(matrix, rows, cols, rowsVisited, colsVisited, true);
            
            // downwards
            colsVisited += colWalk(matrix, rows, cols, rowsVisited, colsVisited, true);
            
            // from right to left
            rowsVisited += rowWalk(matrix, rows, cols, rowsVisited, colsVisited, false);
            
            // upwards
            colsVisited += colWalk(matrix, rows, cols, rowsVisited, colsVisited, false);
        }
    }
    
    /**
     * If rightBound is set, we are walking along the upper row indexed at 
     * rowsVisited/2. Otherwise it's the row near the bottom at index
     * (rows - 1 - rowsVisited/2).
     * 
     * It's a bit tricky to figure out start.end column indexes based on given
     * colsVisited. Note that we start right most column (walk down).
     * 
     * If the row has been visited already return 0; otherwise 1.
     * 
     * @param rightBound
     * @param rowsVisited
     * @param colsVisited
     * @return
     */
    private int rowWalk(int[][] matrix, int rows, int cols, int rowsVisited, int colsVisited, boolean rightBound) {
        if(rowsVisited >= rows) {
            return 0;
        }
        
        int rowIndex = rowsVisited/2;
        int startIndex = colsVisited/2;
        int endIndex = cols - 1 - colsVisited/2;
        
        if(!rightBound) {
            rowIndex = rows - 1 - rowsVisited/2;
            
            // unlike row-wise walk, column walk is unsymmetric
            startIndex = cols - 1 - (1 + colsVisited)/2;
            endIndex = colsVisited/2;
        }
        
        if(rightBound) {
            for(int j = startIndex; j <= endIndex; j++) {
                System.out.print(matrix[rowIndex][j] + " ");
            }
        } else {
            for(int j = startIndex; j >= endIndex; j--) {
                System.out.print(matrix[rowIndex][j] + " ");
            }
        }
        System.out.print(", ");
        
        return 1;
    }
    
    private int colWalk(int[][] matrix, int rows, int cols, int rowsVisited, int colsVisited, boolean downwards) {
        if(colsVisited >= cols) {
            return 0;
        }
        
        int colIndex = rows - 1 - colsVisited/2;
        int startIndex = rowsVisited/2 + 1;
        int endIndex = rows - 1 - rowsVisited/2;
        
        if(!downwards) {
            colIndex = colsVisited/2;
            
            startIndex = rows - 1 - rowsVisited/2;
            endIndex = rowsVisited/2;
        }
        
        if(downwards) {
            for(int i = startIndex; i <= endIndex; i++) {
                System.out.print(matrix[i][colIndex] + " ");
            }
        } else {
            for(int i = startIndex; i >= endIndex; i--) {
                System.out.print(matrix[i][colIndex] + " ");
            }
        }
        System.out.print(", ");
        
        return 1;
    }
    
    
    /**
     * Copied from Leetcode - a much cleaner code!
     * @param matrix
     * @return
     */
    private List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<Integer>();
        if (matrix.length == 0) return ans;
        
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        
        while (r1 <= r2 && c1 <= c2) {
            // top row
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            
            // right most column
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);
            
            if (r1 < r2 && c1 < c2) {
                // bottom row
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]);
                
                // left most column
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);
            }
            
            r1++;
            r2--;
            c1++;
            c2--;
        }
        
        return ans;
    }
    
    /**
     * https://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/
     * 
     * Similar to the previous but seemed more logical!
     */
    private void spiralPrint(int rows, int cols, int a[][]) {
        int i, r = 0, c = 0;
        while (r < rows && c < cols) {
            // Print the first row from the remaining rows
            for (i = c; i < cols; ++i) {
                System.out.print(a[r][i]+" ");
            }
            r++;
  
            // Print the last column from the remaining columns 
            for (i = r; i < rows; ++i) {
                System.out.print(a[i][cols-1]+" ");
            }
            cols--;
  
            // Print the last row from the remaining rows */
            if ( r < rows) {
                for (i = cols-1; i >= c; --i) {
                    System.out.print(a[rows-1][i]+" ");
                }
                rows--;
            }
  
            // Print the first column from the remaining columns */
            if (c < cols) {
                for (i = rows-1; i >= r; --i) {
                    System.out.print(a[i][c]+" ");
                }
                c++;    
            }        
        }
    }
}
