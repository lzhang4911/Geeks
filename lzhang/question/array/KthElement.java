package lzhang.question.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import lzhang.model.BinaryNode;
import lzhang.util.BaseUtil;

/**
 * Given an unsorted integer array and an integer K, find the Kth
 * smallest (or largest) element from the array.
 * 
 * Worst and simplest would be to sort the array and then take Kth element,
 * which is O(nlogn). Can you do any better?
 * 
 * Another solution is to build a complete min heap out of the array, which
 * will be logN. And then pop k times from the root to retrieve the k smallest
 * elements, which is klogN. Together,the time complexity is
 * O(n + logn + klogn) = O(n + klogn).
 * 
 * This also needs O(n) additional space for the heap.
 * 
 * Use max heap if kth LARGEST element is needed.
 * 
 * We can do better by using a smaller heap, just k nodes indeed. The trick here
 * is to use MAX heap if kth smallest element is the one to go after.
 * 
 * Initialize the MaxHeap with first k elements from array -> O(k + logk).
 * Iterate through the array from k to n-1. If arr[i] > top do nothing; else
 * replace the root with current one and heapify down, which is logk. Overall,
 * O(n + nlogk).
 * 
 * Additional space: O(k).
 * 
 * We can use PriorityQueue as the MaxHeap by providing descending sorting order.
 *  
 * @author lzhang
 *
 */
public class KthElement extends BaseUtil {
    public static void test() {
        KthElement p = new KthElement();
        int[] arr = {7, 10, 4, 3, 20, 15};
        int k = 3;
        
        p.findKthSmallestWithPriorityQueue(arr, k);
        p.findKthSmallestWithMaxHeap(arr, k);
    }
    
    /**
     * This works with PriorityQueue:
     * Create a max heap to store k smallest elements. We can initially add first k elements
     * from array to the max heap, and then iterate through the rest of the array. If an element
     * is smaller than the root the heap, add it to the bottom and pop the biggest one off.
     * 
     * @param a
     * @param k
     */
    private void findKthSmallestWithPriorityQueue(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                // change to descending to make the q a max heap
                return -a.compareTo(b);
            }
        });
        
        print("Input array: " + Arrays.toString(arr));
        
        // add the first k elements to the queue
        for(int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        
        // now iterate through the rest of the array
        for(int i = k; i < arr.length; i++) {
            /*
             * If arr[i] is bigger than the current root, discard it because
             * we just want to trap the smaller ones.
             */
            if(arr[i] > maxHeap.peek()) {
                continue;
            }
            
            /*
             * For any element that is smaller than the root, first 
             * pop the root and then add the new and smaller one the tail.
             * This can even be optimized if using my home made
             * heap - replace the root instead of add and pop.
             */
            maxHeap.poll();
            maxHeap.add(arr[i]);
        }
        
        // now we have k smallest element kept in the heap.
        System.out.println("k smallest elements (PriorityQueue): ");
        while(!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        System.out.println("");
    }
    
    private void findKthSmallestWithMaxHeap(int[] arr, int k) {
        MaxHeap maxHeap = new MaxHeap(k);
        
        print("Input array: " + Arrays.toString(arr));
        
        // add the first k elements to the queue
        for(int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        
        // now iterate through the rest of the array
        for(int i = k; i < arr.length; i++) {
            /*
             * If arr[i] is bigger than the current root, discard it because
             * we just want to trap the smaller ones.
             */
            if(arr[i] > maxHeap.peek()) {
                continue;
            }
            
            // add it first and then pop
            maxHeap.add(arr[i]);
        }
        
        // now we have k smallest element kept in the heap.
        System.out.println("k smallest elements (MaxHeap): ");
        while(!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        System.out.println("");
    }
    
    /**
     * This MaxHeap is used to keep a list of smallest keys.
     * 
     * This is a special max heap which provides the following features for
     * this question:
     * 
     * (1) add a leaf - used to initially build the heap;
     * (2) replace the root with a smaller key to achieve both pop() and add()
     *     and only heapify once. 
     * @author lzhang
     *
     */
    static class MaxHeap {
        private int capacity = 1;
        private int count = 0;
        
        // an array to hold the level order key sequence
        Object[] levelOrder;
        
//        private BinaryNode<Integer> root;
        
        public MaxHeap(int c) {
            // give one extra
            this.capacity = c + 1;
            levelOrder = new Object[capacity];
        }
        
        public void add(int key) {
            if(count > capacity) {
                throw new RuntimeException("Heap is full");
            }
            
            // add this as the last leaf node by appending to the array
            BinaryNode<Integer> node = new BinaryNode<Integer>(key);
            levelOrder[count] = node;
            count++;
            
            ////////////////////////////////////////////////////
            //// heap structure manipulation - not really needed
//            if(root == null) {
//                root = node;
//            } else {
//                // append as the last leaf
//                addLeaf(root, node);
//            }
            /// end of heap structure manipulation
            ////////////////////////////////////////////////////

            // after the leaf is added, we need to heapifyUp to ensure the heap property holds.
            heapifyUp(count - 1);
            
            // we purposely allocate one extra capacity
            while(count >= capacity) {
                this.poll();
            }
        }
        
        public int peek() {
            if(count == 0) {
                return -1;
            } else {
                BinaryNode<Integer> rootNode = this.getNodeAtIndex(0);
                return rootNode.value;
            }
        }
        
        /**
         * - Remember the current root;
         * - Remove the last leaf and make it the new root;
         * - HeapifyDown(root);
         * - return the key of the old root;
         * @return
         */
        public int poll() {
            // hold on to the current root
            BinaryNode<Integer> ret = this.getNodeAtIndex( 0 );
            
            // remove the last lead from the heap
            int index = count - 1;
            BinaryNode<Integer> lastLeaf = this.getNodeAtIndex( index );
            
            // find its parent and de-link it from the leaf
            BinaryNode<Integer> parent = this.getParentByChildIndex( index );
            
            // delete the last node from array
            levelOrder[index] = null;
            count--;

            // make lastLeaf the new root;
            BinaryNode<Integer> rootNode = lastLeaf;
            rootNode.left = ret.left;
            rootNode.right = ret.right;
            levelOrder[0] = rootNode;
            
//            root = rootNode;
            
            
            heapifyDown(0);
            
            // ensure its parent doesn't point to it any more
            if(parent != null) {
                if(parent.left == lastLeaf) {
                    parent.left = null;
                } else if(parent.right == lastLeaf) {
                    parent.right = null;
                }
            }
            
            return ret.value;
        }
        
        public boolean isEmpty() {
            return count == 0;
        }
        
        /**
         * Use level order traversal to find the first null child.
         * 
         * We can simply first find the place to add and then heapify
         * to maintain the MaxHeap property.
         * 
         * @param root
         * @param node
         */
        private void addLeaf(BinaryNode<Integer> root, BinaryNode<Integer> node) {
            // find its parent based on its index (count - 1)
            BinaryNode<Integer> parent = getParentByChildIndex(count - 1);
            if(parent.left == null) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
        
        /**
         * This is required because the root key is changed and its key may not
         * be the greatest.
         */
        private void heapifyDown(int index) {
            BinaryNode<Integer> parent = getNodeAtIndex(index);
            if(parent == null) {
                return;
            }
            
            // 2 children
            BinaryNode<Integer> childL = getLeftChildForParent(index);
            BinaryNode<Integer> childR = getRightChildForParent(index);
            
            // which child to swap
            BinaryNode<Integer> child = null;
            if(childL != null && childR != null) {
                int biggerChildKey = Math.max(childL.value, childR.value);
                if(parent.value > biggerChildKey) {
                    // no need to heapify
                    return;
                }
                
                child = childL;
                if(biggerChildKey == childR.value) {
                    child = childR;
                }
            } else {
                if(childL != null && parent.value < childL.value) {
                    child = childL;
                } else if(childR != null && parent.value < childR.value) {
                    child = childR;
                }
            }
            
            // do we need to heapify?
            if(child == null) {
                // no need to
                return;
            }
            
            swap(parent, child);
            
            // go down to children
            heapifyDown(2*index + 1);
            heapifyDown(2*index + 2);
        }
        
        /**
         * This is needed because we just blindly added a leaf node which may be greater
         * than some or even all of his parent nodes.
         */
        private void heapifyUp(int childIndex) {
            BinaryNode<Integer> parent = getParentByChildIndex(childIndex);
            if(parent == null) {
                return;
            }
            
            // children
            BinaryNode<Integer> childL = parent.left;
            BinaryNode<Integer> childR = parent.right;
            BinaryNode<Integer> child = null;
            
            if(childL != null && childL.value > parent.value) {
                child = childL;
            }
            
            if(childR != null && childR.value > parent.value) {
                child = childL;
            }
            
            if(child == null) {
                return;
            }
            
            swap(parent, child);
            
            // find its parent to heapify further
            this.heapifyUp((childIndex - 1)/2);
        }
        
        private void swap(BinaryNode<Integer> parent, BinaryNode<Integer> child) {
            int temp = parent.value;
            parent.value = child.value;
            child.value = temp;
        }
        
        private BinaryNode<Integer> getNodeAtIndex(int index) {
            if(index < 0 || index > capacity - 1) {
                return null;
            }
            
            return  (BinaryNode<Integer>)levelOrder[index];
        }
        
        private BinaryNode<Integer> getParentByChildIndex(int childIndex) {
            if(childIndex == 0) {
                return null;
            }
            
            return  getNodeAtIndex( (childIndex - 1)/2 );
        }
        
        private BinaryNode<Integer> getLeftChildForParent(int index) {
            return  getNodeAtIndex( 2*index + 1 );
        }
        
        private BinaryNode<Integer> getRightChildForParent(int index) {
            return  getNodeAtIndex( 2*index + 2 );
        }
    }
}
