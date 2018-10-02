package lzhang.question.matrix;

import java.util.Stack;

import lzhang.util.BaseUtil;

/**
 * Given a 2D matrix with value 0 and 1, find the maximum area with value
 * 1 only.
 * 
Input :   0 1 1 0
          1 1 1 1
          1 1 1 1
          1 1 0 0

Output :  1 1 1 1
          1 1 1 1
 * 
 * @author lzhang
 *
 */
public class MaxSubArea extends BaseUtil {

    public static int test() {
        int[][] mat = {
                {0, 1, 1, 0},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0}
        };
        
        MaxSubArea p = new MaxSubArea();
        int max = p.findMaxArea(mat);
        
        return max;
    }
    
    /**
     * While scanning rows from 0 to rows, aggregate all previous rows to make a new
     * temporary row, and then use maximum histogram technique to find the so far largest
     * rectangle. Find the max along the way to the last row.
     *  
     * @param a
     */
    private int findMaxArea(int[][] a) {
        int maxArea = 0;
        
        if(a == null) {
            return maxArea;
        }
        
        int rows = a.length;
        int cols = a[0].length;
        
        for(int i = 0; i < rows; i++) {
            if(i > 0) {
                /*
                 * starting from second row, let's aggregate all of the previous
                 * rows. The drawback is the matrix gets modified as well.
                 */
                for(int j = 0; j < cols; j++) {
                    // because of the accumulation, every previous was already aggregated
                    if(a[i][j] == 1) {
                        // only update those that are 1's!
                        a[i][j] += a[i - 1][j];
                    }
                }
            }
            
            // calculate the max rectangle considering the current row a[i] as the histogram
            maxArea = Math.max(maxArea, calculateMaxHistogram(a[i]));
        }
        
        return maxArea;
    }
    
    /**
     * How to calculate the max rectangle under a bar graph assuming each
     * bar is 1 unit wide.
     * 
     * Let's use the typical stack approach which is O(n) both in time and space.
     *  
     * @param hist
     * @return
     */
    public int calculateMaxHistogram(int[] hist) {
        if(hist == null) {
            return 0;
        }
        
        int n = hist.length;
        
        // use a stack to remember the bar index
        Stack<Integer> s = new Stack<Integer>();
        int topIndex;
        
        int maxArea = 0;
        int curHeight, curWidth;
        int curArea = 0;
        
        // walk through the bars
        int i = 0;
        for(i = 0; i < n; i++) {
            if(s.isEmpty() || hist[i] >= hist[s.peek()]) {
                /*
                 * push the current index to the slack if current bar is the same
                 * or taller than the one on top of the stack.
                 */
                s.push(i);
            } else {
                /*
                 * Don't push its index. Rather, calculate the area of the bar that is at
                 * the top of the stack.
                 */
                while(!s.isEmpty() && hist[i] < hist[s.peek()]) {
                    topIndex = s.pop();
                    
                    curWidth = i - (s.isEmpty()? -1 : s.peek()) - 1;
                    curHeight = hist[topIndex];
                    
                    curArea = curWidth * curHeight;
                    maxArea = Math.max(maxArea, curArea);
                }
                
                // now push the current bar
                s.push(i);
            }
        }
        
        /*
         * There might be a few more bars that were pushed into the stack but haven't
         * calculated yet.
         */
        curHeight = Integer.MAX_VALUE;
        while(!s.isEmpty()) {
            topIndex = s.pop();
            
            curWidth = i - (s.isEmpty()? -1 : s.peek()) - 1;
            curHeight = hist[topIndex];
            
            curArea = curWidth * curHeight;
            maxArea = Math.max(maxArea, curArea);
        }
        
        print("maxArea: " + maxArea);
        return maxArea;
    }
}
