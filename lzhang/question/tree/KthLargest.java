package lzhang.question.tree;

import lzhang.model.BinaryNode;
import lzhang.util.BaseUtil;

/**
 * Given a BST and a number k, find the kth largest node.
 * 
 * The best solution is to conduct the reverse inorder traversal:
 * right-root-left and keep counting the nodes that have been visited.
 * 
 * Note that the counting variable has to be either an object passed as an
 * argument or an instance variable. Passing a primitive int doesn't work - don't know why!
 * 
 * @author lzhang
 *
 */
public class KthLargest extends BaseUtil {
    static class Count {
        int count;
    }
    
    private int count = 0;
    public static void test() {
        // sorted array
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        
        // construct BST from the array
        CreateTreeFromArray util = new CreateTreeFromArray();
        BinaryNode<Integer> root = util.buildBalancedBstFromSortedArray(arr, 0, arr.length - 1);
        BinaryNode.levelOrderPrint(root);
        
        KthLargest p = new KthLargest();
        int k = 3;
        Count c = new Count();
        
        // works
        p.findKthLargest(root, k, c);
        
        // of course works as well
        c.count = k;
        p.findKthLargest(root, c);
        
        // Using an instance variable as the count also works
        p.count = k;
        p.findKthLargest(root);
        print("kth largest in BST");
    }
    
    
    private void findKthLargest(BinaryNode<Integer> root, int k, Count c) {
        if(root == null || k < 0) {
            return;
        }
        
        // first right child
        this.findKthLargest(root.right, k, c);
        
        // process
        c.count++;
        if(c.count == k) {
            // found the node
            print("kth largest using Count object: " + root.value);
            return;
        }
        
        // then left
        this.findKthLargest(root.left, k, c);
    }
    
    /**
     * Not really different from the previous one
     * @param root
     * @param c
     */
    private void findKthLargest(BinaryNode<Integer> root, Count c) {
        if(root == null) {
            return;
        }
        
        // first right child
        this.findKthLargest(root.right, c);
        
        // process
        c.count--;
        if(0 == c.count) {
            // found the node
            print("kth largest using Count object: " + root.value);
            return;
        }
        
        // then left
        this.findKthLargest(root.left, c);
    }
    
    /**
     * Using instance variable also works. But using a primitive count as
     * an argument doesn't work!
     * 
     * @param root
     */
    private void findKthLargest(BinaryNode<Integer> root) {
        if(root == null) {
            return;
        }
        
        // first right child
        this.findKthLargest(root.right);
        
        // process
        this.count--;
        if(this.count == 0) {
            // found the node
            print("kth largest using instance int var: " + root.value);
            return;
        }
        
        // then left
        this.findKthLargest(root.left);
    }
}
