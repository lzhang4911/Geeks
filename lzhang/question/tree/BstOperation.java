package lzhang.question.tree;

import lzhang.model.BinaryNode;
import lzhang.util.BaseUtil;

/**
 * Operations on a BST includes Add, Update, Delete, and Search.
 * 
 * @author lzhang
 *
 */
public class BstOperation extends BaseUtil {
    public static void test() {
        CreateTreeFromArray util = new CreateTreeFromArray();
        int[] arr = {1, 3, 5, 7, 9, 11, 13};
        int start = 0;
        int end = arr.length - 1;
        
        BinaryNode<Integer> root = util.buildBalancedBstFromSortedArray(arr, start, end);
        BinaryNode.levelOrderPrint(root);
        
        BstOperation p = new BstOperation();
        
//        print("after deleting leaf node 5:");
//        BinaryNode<Integer>  res = p.deleteNode(root, 5);
//        BinaryNode.levelOrderPrint(res);
        
//        print("after deleting mid node 3:");
//        BinaryNode<Integer> res = p.deleteNode(root, 3);
//        BinaryNode.levelOrderPrint(res);
        
        print("after deleting root node 7:");
        BinaryNode<Integer> res = p.deleteNode(root, 7);
        BinaryNode.levelOrderPrint(res);
    }

    private BinaryNode<Integer> deleteNode(BinaryNode<Integer> root, int key) {
        if(root == null) return root;
        
        if(key < root.value) {
            root.left = this.deleteNode(root.left, key);
        } else if(key > root.value) {
            root.right = this.deleteNode(root.right, key);
        } else {
            /*
             * The node to be deleted is found. There are 3 situations:
             * - the node is a leaf;
             * - the node has only one child;
             * - the node has 2 children
             */
            
            // node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                /*
                 * The node has both children: 
                 * - replace this node with its inorder successor;
                 * - delete the successor node
                 */
                root.value = getInorderSuccessor(root).value;
                root.right = deleteNode(root.right, root.value);
            }
        }
        
        return root;
    }
    
    /**
     * A successor node is the node that is right after the current node
     * in the inorder traversal array if such an array is available.
     * 
     * If searching from the BST, the successor is the left most node from
     * its right subtree.
     * 
     * @param node
     * @return
     */
    private BinaryNode<Integer> getInorderSuccessor(BinaryNode<Integer> node) {
        node = node.right;
        while(node.left != null) {
            node = node.left;
        }
        
        // this must be the left most node
        return node;
    }
    
    /**
     * The right most node from its left subtree is its predecessor
     * @param node
     * @return
     */
    private BinaryNode<Integer> getInorderPredecessor(BinaryNode<Integer> node) {
        node = node.left;
        while(node.right != null) {
            node = node.right;
        }
        
        // this must be the left most node
        return node;
    }
}
