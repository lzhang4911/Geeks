package lzhang.question.array;

import java.util.Arrays;

import lzhang.util.BaseUtil;

/**
 * Heap sort algorithm has 2 steps:
 * 1. build a proper max heap out of the original array (by re-arrange the array);
 * 2. sort the array in-place by swapping out the first element (the root) with the last
 *    one in the array. Keep repeating this on the reduced heap till everything is sorted.
 *  
 * @author lzhang
 *
 */
public class MaxHeapSort extends BaseUtil {
    public static void test() {
        MaxHeapSort p = new MaxHeapSort();
        int[] arr = new int[] {12, 11, 13, 5, 6, 7};
        p.sort(arr);
        
        return;
    }
    
	private void sort(int arr[]) {
        int n = arr.length;
        
        print("Before MaxHeep: " + Arrays.toString(arr));
 
        /*
         * Step 1: building max heap
         * 
         * Build max heap (rearrange array) from given
         * [12, 11, 13, 5, 6, 7]
         * to
         * [13, 11, 12, 5, 6, 7]
         */
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }
        print("After MaxHeep: " + Arrays.toString(arr));
        
 
        /*
         * Step 2: sorting
         * 
         * Build the sorted array in place:
         * One by one extract an element from top of the heap (the root)
         */
        for (int i=n-1; i>=0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
 
            // heapify down from new root all the way down on the reduced heap - i is decreasing
            heapify(arr, 0, i);
        }
        print("After Heep sort: " + Arrays.toString(arr));
    }

	/**
	 * To heapify a subtree rooted with node i which is an 
	 * index in arr[]. n is size of heap.
	 * 
	 * This is basically heapify down from index i. To heapify down,
	 * we need to compare the key at index i with its 2 children at
	 * index l and r. Swap value at i with the bigger child that is
	 * greater than the key at i. And then recursively do down to the
	 * child that was just swapped.
	 * 
	 * @param arr
	 * @param n
	 * @param i
	 */
	private void heapify(int arr[], int i, int n) {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2
 
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }
 
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }
 
        // If largest is not root, swap the root with the largest
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(arr, largest, n);
        }
    }
}
