package lzhang.question.tree;

import lzhang.model.BinaryNode;

/**
 * A Symmetric binary tree is a tree that horizontally symmetric about its
 * center line. For example,
 * 
    1
   / \
  2   2
 / \ / \
3  4 4  3

Note that it's not necessarily that every has the same children.

 * @author lzhang
 *
 */
public class SymmetrcTree {
    public static boolean test() {
        // the leveled-order array for the tree
        Integer[] arr = {1,2,2,3,4,4,3};
        
        // convert it into a binary tree
        BinaryNode<Integer> root = new CreateTreeFromArray().buildBalancedTreeFromLeveledOrderArray(arr);
        
        SymmetrcTree p = new SymmetrcTree();
        return p.isMirrored(root.left, root.right);
    }
    
    private boolean isMirrored(BinaryNode<Integer> left, BinaryNode<Integer> right) {
        if(left == null && right == null) {
            return true;
        } else if(left == null || right == null) {
            return false;
        }
        
        // break fast
        if(left.value != right.value) {
            return false;
        }
        
        return isMirrored(left.left, right.right) && isMirrored(left.right, right.left);
    }
}
