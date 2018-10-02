package lzhang.question.tree;

import lzhang.model.BinaryNode;

/**
 * A BST is a binary tree that for any given node, the key of its left child must be
 * smaller than itself and its right child must be greater. This is true recursively
 * for every node.
 * 
 * A BST doesn't have to be balanced, BTW.
 * 

Example 1:

Input:
    10
   / \
  5   20
 / \
3   6
Output: true

Example 2:

    5
   / \
  1   4  <-- violation
     / \
    3   6
Output: false

Example 3:

    5
   / \
  1   7
     / \
    3   9
    ^
    ^
violation
Output: false

 * 
 * @author lzhang
 *
 */
public class ValidateBst {
    public static boolean test() {
        BinaryNode<Integer> root1 = new BinaryNode<Integer>(10);
        root1.right = new BinaryNode<Integer>(20);
        root1.left = new BinaryNode<Integer>(5);
        root1.left.left = new BinaryNode<Integer>(3);
        root1.left.right = new BinaryNode<Integer>(7);
        
        BinaryNode<Integer> root2 = new BinaryNode<Integer>(5);
        root2.left = new BinaryNode<Integer>(1);
        root2.right = new BinaryNode<Integer>(4);
        root2.right.left = new BinaryNode<Integer>(3);
        root2.right.right = new BinaryNode<Integer>(6);
        
        BinaryNode<Integer> root3 = new BinaryNode<Integer>(5);
        root3.left = new BinaryNode<Integer>(1);
        root3.right = new BinaryNode<Integer>(7);
        root3.right.left = new BinaryNode<Integer>(3);
        root3.right.right = new BinaryNode<Integer>(9);
        
        
        ValidateBst p = new ValidateBst();
        boolean result = p.validate(root1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Example 1 is a BST? " + result);
        
        result = p.validate(root2, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Example 2 is a BST? " + result);
        
        result = p.validate(root3, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Example 3 is a BST? " + result);
        
        return result;
    }
    
    
    /**
     * The validation not only needs to check whether (left < parent < right), it
     * also need to consider the key range for each child as well.
     * 
     * @param node
     * @return
     */
    private boolean validate(BinaryNode<Integer> node, int min, int max) {
        if(node == null) {
            // does it make sense?
            return true;
        }
        
        // A BST node must be in its range
        if(node.value < min || node.value > max) {
            return false;
        }
        
        /*
         * + and - 1 reflects that there are no 2 nodes with the same value - at least apart
         * by 1 because they all are integers.
         */
        return validate(node.left, min, node.value - 1) && validate(node.right, node.value + 1, max);
    }
}
