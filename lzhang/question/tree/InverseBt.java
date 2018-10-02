package lzhang.question.tree;

import lzhang.model.BinaryNode;
import lzhang.util.BaseUtil;

/**
 * Inverse a binary tree
 * 
 * {4, 2, 7, 1, 3, 6, 9};
 * 
 * Result shall be
 * {4, 7, 2, 9, 6, 3, 1}
 * 
 * @author lzhang
 *
 */
public class InverseBt extends BaseUtil {

    public static void test() {
        Integer[] arr = {4, 2, 7, 1, 3, 6, 9};
        
        InverseBt p = new InverseBt();
        BinaryNode<Integer> root = new CreateTreeFromArray().buildBalancedTreeFromLeveledOrderArray(arr);
        
        print("Original BT inorder array:");
        BinaryNode.inorderPrint(root);
        
        p.recursive(root);
        
        print("Reversed BT inorder array:");
        BinaryNode.inorderPrint(root);
    }
    
    private void recursive(BinaryNode<Integer> root) {
        if(root == null) {
            return;
        }
        
        // swap left-right nodes
        BinaryNode<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        // recurse
        recursive(root.left);
        recursive(root.right);
    }
}
