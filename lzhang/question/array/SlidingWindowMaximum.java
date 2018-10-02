package lzhang.question.array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an integer array and a value to be the size of the sliding window.
 * Find out the max along the way when the window slides.
 * 
nput :
arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}
k = 3
Output :
3 3 4 5 5 5 6
 * 
 * The naive solution would be T O(nk). Can we do better, such as O(n logk )?
 * In other words, find the max within k elements in logk. 
 * 
 * A balanced BST shall do. Balanced BST can find/delete/insert a node all 
 * in O(logk), therefore O(nlogk).
 * 
 * Can we use a MaxHeap? No. A maxHeap can give O(1) to find current max, O(logk) to
 * heapify, but would need O(k) to find the node to be removed or replaced by a new one.
 * 
 * Let's implement an O(n) approach using a Deqqueue.
 *  
 * @author lzhang
 *
 */
public class SlidingWindowMaximum {
    public static void test() {
        SlidingWindowMaximum p = new SlidingWindowMaximum();
        
        int[] arr = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        //int arr[]={12, 1, 78, 90, 57, 89, 56};
        int k = 3;
        
        p.findMaxPerWindow(arr, k);
        
        p.findMinPerWindow(arr, k);
    }

    /**
     * Given an array {1, 2, 3, 1, 4, 5, 2, 3, 6}, and a windows size k = 3, there are
     * 7 window positions (arr.length - k + 1). The max at each window position shall be
     * [3 3 4 5 5 5 6]
     * @param arr
     * @param k
     */
    private void findMaxPerWindow(int[] arr, int k) {
        if(arr == null || k <= 0) return;
        
        int n = arr.length;
        
        /*
         * create a Dequeue to store the indexes of current window of elements
         * in a descending order of their values. Note that a Deque is just a
         * queue and it doesn't alter the ordering the elements are added. The
         * program uses an intelligence to determine what to be kept in it. 
         * 
         * Basically if the sequence is [3, 2, 1], we will keep their indexes in
         * the queue in the same sequence, [0, 1, 2]. 
         * 
         * However, if the sequence is [3, 1, 2], the queue will only have [0, 2].
         * The second one is less than the last and therefore be removed.
         * 
         * If sequence is [2, 1, 3], the queue will only have [2]. The rule is remove
         * the current last one (tail) from queue if the element to be added is greater.
         * This way, the window at 3 positions, when sliding, shall see the same greatest
         * if there is no greater value encountered.
         */
        Deque<Integer> indexQueueInWindow = new LinkedList<Integer>();
        
        /*
         * Adding the first k elements and make sure any elements that is smaller
         * are NOT kept - not useful (?) 
         */
        int i = 0;
        for(i = 0; i < k; i++) {
            // remove any smaller ones from the queue
            while(!indexQueueInWindow.isEmpty() && arr[i] >= arr[indexQueueInWindow.peekLast()]) {
                indexQueueInWindow.removeLast();
            }
            
            indexQueueInWindow.addLast(i);
        }
        
        // Process rest of the elements, i.e., from arr[k] to arr[n-1]
        System.out.print("\nMax seen from each window: ");
        for( ;i < n; ++i) {
            System.out.print(arr[indexQueueInWindow.peek()] + " ");
            
            
            /*
             * Remove any early elements that the window already moved away from.
             * Remember what saved in Deque are the indexes.
             */
            while((!indexQueueInWindow.isEmpty()) && i-k >= indexQueueInWindow.peek()) {
                // i should be >= k + first index in queue to keep the window size at k
                indexQueueInWindow.removeFirst();
            }
            
            // same as the beginning, only 
            while(!indexQueueInWindow.isEmpty() && arr[i] >= arr[indexQueueInWindow.peekLast()]) {
                indexQueueInWindow.removeLast();
            }
            
            indexQueueInWindow.addLast(i);
        }
                
        // Print the maximum element of last window
        System.out.print(arr[indexQueueInWindow.peek()]);
    }
    
    private void findMinPerWindow(int[] arr, int k) {
        if(arr == null || k <= 0) return;
        
        int n = arr.length;
        
        // create a Dequeue to store the indexes of a window of elements in a descending order.
        Deque<Integer> dq = new LinkedList<Integer>();
        
        /*
         * Adding the first k elements and make sure any elements that is smaller
         * are NOT kept - not useful (?) 
         */
        int i = 0;
        for(i = 0; i < k; i++) {
            // remove any larger ones from the queue
            while(!dq.isEmpty() && arr[i] <= arr[dq.peekLast()]) {
                dq.removeLast();
            }
            
            dq.addLast(i);
        }
        
        // Process rest of the elements, i.e., from arr[k] to arr[n-1]
        System.out.print("\nMin seen from each window: ");
        for( ;i < n; ++i) {
            System.out.print(arr[dq.peek()] + " ");
            
            
            // Remove any early elements that the window already moved away from
            while((!dq.isEmpty()) && i-k >= dq.peek()) {
                dq.removeFirst();
            }
            
            // same as the beginning, only 
            while(!dq.isEmpty() && arr[i] <= arr[dq.peekLast()]) {
                dq.removeLast();
            }
            
            dq.addLast(i);
        }
                
        // Print the maximum element of last window
        System.out.print(arr[dq.peek()]);
    }
}
