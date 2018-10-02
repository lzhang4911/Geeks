package lzhang.question.tree;

import lzhang.model.BinaryNode;
import lzhang.util.BaseUtil;

/**
 * There are many flavors of find kth node in a pre/post/in/level order traversal
 * without creating the corresponding traversal array.
 * 
 * For example, given a binary tree and a number k, find the kth node in the
 * postorder traversal.
 * 
 * Remember that the counter used in each of the methods must be either an object
 * or an instance 
 * 
 * @author lzhang
 *
 */
public class KthInBinaryTree extends BaseUtil {
    static class Count {
        int count;
        
        public Count(int c) {
            count = c;
        }
    }
    
    public static void test() {
        /*
         * Build a BT from a given level order array {1, 2, 3, 4, 5, 6, 7}
         * Its preorder would be: {1, 2, 4, 5, 3, 6, 7}
         * Its postorder          {4, 5, 2, 6, 7, 3, 1}
         * Its inorder            {4, 2, 5, 1, 6, 3, 7} 
         */
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        
        CreateTreeFromArray util = new CreateTreeFromArray();
        BinaryNode<Integer> root = util.buildBalancedTreeFromLeveledOrderArray(arr);
        
        KthInBinaryTree p = new KthInBinaryTree();
        Count c = new Count(k);
        
        p.findKthFromInorder(root, c);
        
        c.count = k;
        p.findKthFromPostorder(root, c);
        
        c.count = k;
        p.findKthFromPreorder(root, c);
    }
    
    /**
     * Do the count down while performing inorder traversal
     * @param root
     * @param c
     */
    private void findKthFromInorder(BinaryNode<Integer> root, Count c) {
        if(root == null) return;
        
        // left first
        findKthFromInorder(root.left, c);
        
        // decrement count
        c.count--;
        
        // processing
        if(c.count == 0) {
            // found the kth node already
            print( String.format("kth node in inorder array: %d", root.value));
            return;
        }
        
        // at last right
        findKthFromInorder(root.right, c);
    }
    
    private void findKthFromPostorder(BinaryNode<Integer> root, Count c) {
        if(root == null) return;
        
        // left first
        findKthFromPostorder(root.left, c);
        
        // then right
        findKthFromPostorder(root.right, c);
        
        // decrement count
        c.count--;
        
        // processing
        if(c.count == 0) {
            // found the kth node already
            print( String.format("kth node in postorder array: %d", root.value));
            return;
        }
    }
    
    private void findKthFromPreorder(BinaryNode<Integer> root, Count c) {
        if(root == null) return;
        
        // decrement count
        c.count--;
        
        // processing
        if(c.count == 0) {
            // found the kth node already
            print( String.format("kth node in preorder array: %d", root.value));
            return;
        }

        // left first
        findKthFromPreorder(root.left, c);
        
        // then right
        findKthFromPreorder(root.right, c);
    }
}
