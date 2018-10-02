package lzhang.question.array;

import java.util.Stack;

/**
 * Find the nearest smaller one on the left hand side for each element
 * in an array. Ex,
 * 
 * Input:  arr[] = {1, 6, 4, 10, 2, 5}
 * Output:         {_, 1, 1,  4, 1, 2}
 * 
 * Input: arr[] = {1, 3, 0, 2, 5}
 * Output:        {_, 1, _, 0, 2}
 * 
 * Must be O(n)
 * @author lzhang
 *
 */
public class NearestSmallerLeft {
    public static void test() {
        int[] arr = {1, 6, 4, 10, 2, 5};
        
        smaller(arr);
    }
    
    private static void smaller(int[] arr) {
        if(arr == null) {
            return;
        }
        
        int n = arr.length;
        Stack<Integer> track = new Stack<>();
        
        // the first element never have anything on left
        System.out.print("_, ");
        track.push(arr[0]);
        
        for(int i = 1; i < n; i++) {
            /*
             * Pop anything out that is greater than or equal to the current element
             */
            while(!track.isEmpty() && track.peek() >= arr[i]) {
                track.pop();
            }
            
            if(track.isEmpty()) {
                // no one is smaller than arr[i] so far
                System.out.print("_, ");
            } else {
                System.out.print(track.peek() + ", ");
            }
            
            track.push(arr[i]);
        }
        
        System.out.println("");
    }
}
