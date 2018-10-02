package lzhang.question.tree;

import java.util.Arrays;

import lzhang.model.BinaryNode;
import lzhang.util.BaseUtil;

/**
 * https://www.geeksforgeeks.org/modify-binary-tree-get-preorder-traversal-using-right-pointers/
 * 
 * Given a Binary Tree, modify the tree using the "right" pointer only into an inorder
 * linked list.
 * 
 * Basically, after the transformation in place, the following code will print the
 * list as if being print from its inorder traversal.
 * 
 * @author lzhang
 *
 */
public class ModifyBtIntoPreorderLinkedList extends BaseUtil {
    public static void test() {
        CreateTreeFromArray util = new CreateTreeFromArray();
        
        // give a preorder array
        Integer[] level = {10, 8, 2, 3, 5};
        print("Tree from level order array: " + Arrays.toString(level));
        
        BinaryNode<Integer> root = util.buildBalancedTreeFromLeveledOrderArray(level);
        print("Preorder array");
        BinaryNode.preorderPrintRecur(root);
        
        ModifyBtIntoPreorderLinkedList p = new ModifyBtIntoPreorderLinkedList();
        p.modify(root);
        
        print("\nresultant linked list: ");
        while(root != null) {
            System.out.print(root.value + " ");
            root = root.right;
        }
        print("\ndone");
    }

    private void modify(BinaryNode<Integer> root) {
        if(root == null) {
            return;
        }
        
        BinaryNode<Integer>  left = root.left;
        BinaryNode<Integer>  right = root.right;
        
        if(root.left != null) {
            root.left = null;
            
            // to be preorder, convert current left branch into next list
            root.right = left;
            modify(root.right);
        }
        
        // walk to the end node
        BinaryNode<Integer> n = root;
        while(n.right != null) {
            n = n.right;
        }
        
        n.right = right;
    }
}
