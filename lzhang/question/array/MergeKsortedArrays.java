package lzhang.question.array;

import java.util.Arrays;

import lzhang.util.BaseUtil;

/**
 * Given k sorted arrays, a1, a2, ..., ak, merge them into one sorted array.
 * Blinded join all of them first and then sorted the resultant array is not
 * accepted - O(kn + knLog(kn)).
 * 
 * The optimized approach is to use a MinHeap to sort the k element, one from
 * each array.
 * 
 * @author lzhang
 *
 */
public class MergeKsortedArrays extends BaseUtil {
    public static void test() {
        int arr[][] =  {
                {91, 93, 94},
                {23, 34, 90, 2000},
                {2,  6,  12, 34},
                {1,  9,  20, 1000},
                
                };
        MergeKsortedArrays p = new MergeKsortedArrays();
        int[] ret = p.mergeSort(arr);
        
        print("Merge sort k arrays: " + Arrays.toString(ret));
    }
    
    private int[] mergeSort(int[][] arr) {
        int length = 0;
        for(int i = 0; i < arr.length; i++) {
            length += arr[i].length;
        }
        
        // create initial array of size k to start the MinHeap
        int[] karray = new int[ arr.length ];
        for(int i = 0; i < arr.length; i++) {
            karray[i] = arr[i][0];
        }

        // create final array
        int index = 0;
        int[] ret = new int[length];
        
        Wrapper node = null;
        MinHeap heap = new MinHeap(karray);
        
        while(!heap.isEmpty()) {
            // get the current min value from MinHeap
            node = heap.peek();
            ret[index++] = node.value;
            
            // get the next value from the right array to replace the current root
            node = pickNextElement(arr[node.whichArray], node);
            if(node == null) {
                // this array has been fully consumed
                heap.pop();
            } else {
                heap.replaceRoot(node);
            }
        }
        
        return ret;
    }
    
    private Wrapper pickNextElement(int[] arr, Wrapper node) {
        int nextIndex = node.indexInArray+1;
        
        if(nextIndex >= arr.length) {
            // no more element available from this array
            return null;
        }
        
        return new Wrapper(node.whichArray, nextIndex, arr[nextIndex]);
    }

    static class Wrapper {
        public int value;
        public int whichArray; // (0, k]
        public int indexInArray; // the index in its source array
        
        public Wrapper(int whichArray, int indexInArray, int value) {
            this.whichArray = whichArray;
            this.indexInArray = indexInArray;
            this.value = value;
        }
        
        @Override
        public String toString() {
            return String.format("arrar: %d, index: %d, value: %d", whichArray, indexInArray, value);
        }
    }
    
    /**
     * The MinHeap is specially designed for this question. The required
     * methods are:
     * 
     * 1. constructor. Caller must pass an array that contains the first element from each array.
     * 2. isEmpty();
     * 3. peek();
     * 4. replaceRoot();
     * 5. pop();
     * 6. heapify().
     * 
     * @author lzhang
     *
     */
    static class MinHeap {
        private int count;
        Wrapper[] arr = null;
        
        public MinHeap(int[] firstElement) {
            int k = firstElement.length;
            arr = new Wrapper[k];
            
            // populate the initial state of the heap
            for(int i = 0; i < k; i++) {
                // whichArray, indexInThatArray, the element value
                arr[i] = new Wrapper(i, 0, firstElement[i]);
                
                count++;
                
                // since we always append (leaf) to the end, we must heapifyUp() from bottom! 
                heapifyUp(count-1);
            }
        }
        
        public boolean isEmpty() {
            return count == 0;
        }
        
        // return the root value, the first element
        public Wrapper peek() {
            if(count <= 0) return null;
            else return arr[0];
        }
        
        /**
         * Instead of calling pop() and push(), this method if more effective to
         * one less heapify().
         *  
         * @param w
         */
        public void replaceRoot(Wrapper w) {
            arr[0] = w;
            heapifyDown(0);
        }
        
        public Wrapper pop() {
            if(count <= 0) return null;
            
            Wrapper ret = arr[0];
            
            // move the last node to the root
            arr[0] = arr[count - 1];
            
            // nullify the last leaf
            arr[count - 1] = null;
            count--;
            
            heapifyDown(0);
            
            return ret;
        }
        
        /**
         * Since there is no insert() or add() at all, we can always heapify down from the root.
         */
        private void heapifyDown(int index) {
            if(isEmpty()) {
                return;
            }
            
            Wrapper parent = arr[index];
            if(parent == null) {
                return;
            }
            
            Wrapper l = this.getChild(index, 1);
            Wrapper r = this.getChild(index, 2);
            
            if(l == null && r == null) {
                // reach the leaf already
                return;
            }
            
            int childHeapIndex = -1;
            Wrapper child = null;
            
            if(l != null && r != null) {
                // find which child is smaller and to be swapped
                int minValue = Math.min(l.value, r.value);
                if(minValue > parent.value) {
                    // no need to heapify
                    return;
                } else {
                    if(minValue == l.value) {
                        childHeapIndex = 2 * index + 1;
                        child = l;
                    } else {
                        childHeapIndex = 2 * index + 2;
                        child = r;
                    }
                }
            } else if(l != null && l.value < parent.value) {
                childHeapIndex = 2 * index + 1;
                child = l;
            } else if(r != null && r.value < parent.value) {
                childHeapIndex = 2 * index + 2;
                child = r;
            }
            
            if(child != null) {
                // first swap the 2 nodes
                arr[index] = child;
                arr[childHeapIndex] = parent;
                
                // heapify down further
                heapifyDown(childHeapIndex);
            }
        }
        
        /**
         * The current child at childIndex and its parent may not confirm the min heap property
         * since its just appended!
         * @param childIndex
         */
        private void heapifyUp(int childIndex) {
            if(childIndex <= 0) {
                return;
            }
            
            int parentIndex = (childIndex - 1)/2;
            Wrapper parent = arr[parentIndex];
            if(parent == null) {
                // the node at childIndex was already the root!
                return;
            }
            
            Wrapper child = arr[childIndex];
            if(parent.value < child.value) {
                // heap property was good already
                return;
            }
            
            // otherwise swap and heapifyUp further
            arr[parentIndex] = child;
            arr[childIndex] = parent;
            
            heapifyUp(parentIndex);
        }
        
        /**
         * parentIndex starts from 0;
         * childIndex is either 1 - left or 2 - right.
         * 
         * @param parentIndex
         * @param childIndex
         * @return
         */
        private Wrapper getChild(int parentIndex, int childIndex) {
           int childNodeIndexInArray = 2 * parentIndex + childIndex;
           if(childNodeIndexInArray > count - 1) return null;
           
           return arr[childNodeIndexInArray];
        }
    }
}
