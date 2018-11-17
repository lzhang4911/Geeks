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
	private BinaryNode<Integer> root = null;
	
    public static void test() {
        CreateTreeFromArray util = new CreateTreeFromArray();
        int[] arr = {1, 3, 5, 7, 9, 11, 13};
        int start = 0;
        int end = arr.length - 1;
        
        BstOperation p = new BstOperation();
        p.root = util.buildBalancedBstFromSortedArray(arr, start, end);
        BinaryNode.levelOrderPrint(p.root);
        
//        print("after deleting leaf node 5:");
//        BinaryNode<Integer>  res = p.deleteNode(root, 5);
//        BinaryNode.levelOrderPrint(res);
        
//        print("after deleting mid node 3:");
//        BinaryNode<Integer> res = p.deleteNode(root, 3);
//        BinaryNode.levelOrderPrint(res);
        
        print("after deleting root node 7:");
        BinaryNode<Integer> res = p.deleteNode(p.root, 7);
        BinaryNode.levelOrderPrint(res);
    }

    /**
     * AFter deleting a node, the tree must still be BST.
     * 
     * There are 3 different situations:
     * (https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/)
     * 
     * 1. node with 0 child (leaf node): simply drop it;
     * 2. node with one child: replace the node with that child;
     * 3. node with both children: find its inorder successor, replace its value with this successor, and then delete that successor.
     * @param node
     * @param key
     * @return
     */
    private BinaryNode<Integer> deleteNode(BinaryNode<Integer> node, int key) {
        if(node == null) return node;
        
        if(key < node.value) {
            node.left = this.deleteNode(node.left, key);
        } else if(key > node.value) {
            node.right = this.deleteNode(node.right, key);
        } else {
            /*
             * The node to be deleted is found. There are 3 situations:
             * - the node is a leaf;
             * - the node has only one child;
             * - the node has 2 children
             */
            
            if (node.left == null) {
            	// this is either a leaf node or one with right child only
                return node.right;
            } else if (node.right == null) {
            	// node with left child only
                return node.left;
            } else {
                /*
                 * The node has both children: 
                 * - replace this node with its inorder successor;
                 * - delete the successor node
                 */
                node.value = getInorderSuccessor(root, node).value;
                
                // delete the successor node (from its right child)
                node.right = deleteNode(node.right, node.value);
            }
        }
        
        return node;
    }
    
    /**
     * A successor node is the node that is right after the current node
     * in the inorder traversal array if such an array is available.
     * 
     * If searching from the BST, the successor is the left most node from
     * its right subtree, the smallest node in its right sub-tree.
     * 
     * FIXME (2018/11/02): This is incomplete! This doesn't work for a node that doesn't 
     * have a right branch. For such node, its immediate successor can only be found from
     * its ancestors that is the first left descendent of its upper family tree.
     * 
     * @param node
     * @return
     */
    private BinaryNode<Integer> getInorderSuccessor(BinaryNode<Integer>  root, BinaryNode<Integer> node) {
    	BinaryNode<Integer> successor = null;
    	
    	if(node.right != null) {
    		// successor is the left most node in its right sub-tree
    		successor = node.right;
    		while(successor.left != null) {
	        	successor = successor.left;
	        }
    	} else {
    		// find the last left-most ancestor
    		while(root != null) {
    			if(node.value < root.value) {
    				successor = root;
    				root = root.left;
    			} else {
    				root = root.right;
    			}
    		}
    	}
        
        return successor;
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
