package lzhang.question;

/**
 * Heap sort has 2 parts: turn the array to a max heap first, then rearrange the array by swapping arr[0] with
 * arr[n-1-i].
 * 
 * @author leonzhan
 *
 */
public class HeapSort {
    public static Integer[] test() {
        //int arr[] = {12, 11, 13, 5, 6, 7};
    	Integer arr[] = {7,11, 6, 12, 5, 13};
        
        Integer[]  res = sort(arr);
        return res;
    }

    private static Integer[] sort(Integer[] arr) {
        if(arr == null || arr.length == 1) {
            return arr;
        }
        
        int n = arr.length;
        
        /*
         * build the heap - at the end of the loop the 2 max heap properties
         * will hold: the root is greater than its children which also applies
         * to any sub tree.
         * 
         * Why only loop to half of the array? Because the first half of the array
         * corresponds to the upper half of the tree.
         */
        for(int i = n/2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        /*
         * One by one extract an element from heap:
         * The current root is at index 0. To simulate the removal of the current
         * root (which is also the max), we can swap it with the last element in the
         * array, and then reduce the array length by 1 to "ignore" the largest element
         * at the end.
         * 
         * Because of the swap, the new heap property has been broken, meaning the root
         * may not be the greatest any more. Then we just need to re-heapify the shortened
         * array from index 0 again. Keep doing these...
         */
        for (int i=n-1; i>=0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
 
            // call max heapify at index 0 again on the reduced heap
            heapify(arr, i, 0);
        }
        
        return arr;
    }
    
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    private static void heapify(Integer arr[], int n, int i)
    {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2
 
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
 
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
 
        // If largest is not root arr[i]
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
}
