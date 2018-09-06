package lzhang.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a histogram (or bar graph), find the area of the largest rectangle.
 * 
 * Given height = [2,1,5,6,2,3] and each bar is 1 unit wide.
 * Answer: 10
 * 
 * @author lzhang
 *
 */
public class HistogramRectangle {

    public static int test() {
//        int height[] = {2, 1, 5, 6, 2, 3};
//        int height[] = {2,1,2,1,2,1};
        int height[] = {2, 1, 5, 6, 1, 4, 5, 6};
        
        HistogramRectangle p = new HistogramRectangle();
        
        // traditional one
      int result = p.iterative(height, height.length);
      return result;
        
        // this also works
//        int[] max = new int[1];
//        p.recurse(height, 0, height.length-1, max);
//        return max[0];
        

        // this is NOT working
//        int result = p.useList(height, height.length);
//        return result;
        
    }
    
    /**
     * Typical solution with Stack
     * O(n)
     * 
     * @param hist
     * @param n
     * @return
     */
    private int iterative(int hist[], int n) {
        // Create an empty stack. The stack holds indexes of hist[] array
        // The bars stored in stack are always in increasing order of their
        // heights.
        Stack<Integer> s = new Stack<>();
         
        int max_area = 0; // Initialize max area
        int tp;  // To store top of stack
        int area_with_top; // To store area with top bar as the smallest bar
      
        // Run through all bars of given histogram
        int i = 0;
        while (i < n) {
            if (s.empty() || hist[s.peek()] <= hist[i]) {
                // If this bar is higher than the bar on top stack, push it to stack
                
                s.push(i++);
            } else {
                // If this bar is lower than top of stack, then calculate area of rectangle 
                // with stack top as the smallest (or minimum height) bar. 'i' is 
                // 'right index' for the top and element before top in stack is 'left index'
                
                tp = s.pop();  // pop the top
      
                /*
                 * Calculate the area with hist[tp] that was just popped from the stack as the
                 * tallest bar so far.
                 */
                //area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);
                area_with_top = hist[tp] * (s.empty() ? i : i - tp);
      
                // update max area, if needed
                if (max_area < area_with_top) {
                    max_area = area_with_top;
                }
            }
        }
      
        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (s.empty() == false) {
//            tp = s.peek();
            tp = s.pop();
//            area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);
            area_with_top = hist[tp] * (s.empty() ? i : i - tp);
      
            if (max_area < area_with_top) {
                max_area = area_with_top;
            }
        }
      
        return max_area;
 
    }
    
    
    /**
     * This works! It's aka binary search: always find the shortest one to
     * divide the histogram into 2 parts. First calculate the area at the min height
     * considering all bars. And then repeat the same process for the 2
     * parts.
     * 
     * Divide-and-conquer and recursion
     * 
     * O(nlogn)
     * 
     * @param heights
     * @param start
     * @param end
     * @param max
     */
    private void recurse(int[] heights, int start, int end, int[] max) {
        if(start>end || start<0 || end>=heights.length) return;
        if(start == end) {
            max[0] = Math.max(max[0], heights[start]);
            return;
        }

        // find the index min whose height is the least
        int min = start;
        for(int i=start; i<=end; i++) {
            if(heights[i]<heights[min]) min = i;
        }
        
        /*
         * Calculate the max area for all bars at this least height. This may
         * or may not the max.
         */
        max[0] = Math.max(max[0], heights[min]*(end-start+1));
        
        // recursion to calculate for the 2 portions: before and after min 
        recurse(heights, start, min-1, max);
        recurse(heights, min+1, end, max);
    }
    

    /**
     * WARNING: this fails for {2,1,2,1,2,1}!
     * 
     * This method uses a List to hold the index for the bars. The tricky part is
     * to swap the 2 end of the input at the beginning. Don't understand why yet.
     * 
     * But the idea is almost identical to use a stack which is more intuitive.
     * 
     * With stack, you can pop the recently added indexes as long as their corresponding
     * heights is greater than the current bar (at index i).
     * 
     *  With List, we remove the ones from the end - exactly the same as popping from
     *  a stack. 
     * 
     * @param height
     * @param n
     * @return
     */
    private int useList(int[] height, int n) {
        if(height == null || n <= 0) {
            return 0;
        }
        
        // swap the 2 ends
        swapEnds(height);
        
        // another list to hold bar indexes
        List<Integer> indexes = new ArrayList<Integer>(n);
        
        
        int maxArea = 0;
        for(int i = 0; i < n; i++) {
            while(indexes.size() > 0) {
                // get the height at the index that sits at the end of List indexes
                int curH = height[ indexes.get(indexes.size() - 1) ];
                if(curH < height[i]) {
                    /*
                     * If the bar at position i is taller than the last one in indexes,
                     * let it be appended to the end of indexes.
                     */
                    break;
                }
                
                /*
                 * For the bar whose height is less than the last one in indexes, let's
                 * remove the last index until we hit the one whose height is >= current
                 * height. At the same time, calculate the area and update the max.
                 */
                int index = indexes.remove(indexes.size() - 1);
                int h = height[index];
                
                // determine the width of the rectangle
                int sidx = indexes.size() > 0 ? indexes.get(indexes.size() - 1) : -1;
                
                // calculate the area of this rectangle
                if(h * (i-sidx-1) > maxArea) {
                    maxArea = h * (i-sidx-1);
                }
            }
            
            // must NOT add before the while-loop!
            indexes.add(i);
        }
        
        // swap back
        swapEnds(height);
        
        return maxArea;
    }
    
    private void swapEnds(int[] height) {
        int end = height.length - 1;
        int temp = height[0];
        
        height[0] = height[end];
        height[end] = temp;
    }
}
