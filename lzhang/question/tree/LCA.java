package lzhang.question.tree;

import java.util.Stack;

import lzhang.model.AnyBinaryTree;
import lzhang.model.BinaryNode;

/**
 * Find the Lowest Common Ancestor.
 * https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
 * 
 * @author lzhang
 *
 */
public class LCA {
    public static BinaryNode<Integer> test() {
        // first build a binary tree
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7};
        
        BinaryNode<Integer> root = AnyBinaryTree.buildBinaryTreeFromLeveledOrderArray(arr, 0);
        
        BinaryNode<Integer> ret = findLCA(root, 3, 5);
        return ret;
    }
    
    private static BinaryNode<Integer> findLCA(BinaryNode<Integer> root, Integer n1, Integer n2) {
        if(root == null) {
            return root;
        }
        
        if(root.value == n1 || root.value == n2) {
            /*
             * Note that this works only if both nodes are told in the tree. If one 
             * of the two nodes is not in the tree, we should search for the other
             * node to make sure it's part of the tree. Otherwise, they have NO common
             * ancestor.
             */
//            int otherNodeValue = n1;
//            if(root.value == n1) {
//                otherNodeValue = n2;
//            }
//            
//            return (contains(root, otherNodeValue)? root : null);
            
            return root;
        }
        
        // generic case
        BinaryNode<Integer> left = findLCA(root.left, n1, n2);
        BinaryNode<Integer> right = findLCA(root.right, n1, n2);
        
        if(left != null && right != null) {
            // if each is found in different branch, then only root can be the LCA 
            return root;
        } else {
            return (left != null? left : right);
        }
    }
    
    /**
     * Since we just want to confirm if the tree contains the said node, the efficient
     * approach would be using either preorder or level order traversal. We will stop
     * traversing as soon as we find the said node.
     * 
     * Use iterative and preorder approach to conserve memory from using deep stack.
     * 
     * @param root
     * @param nodeValue
     * @return
     */
    private static boolean contains(BinaryNode<Integer> root, int nodeValue) {
        if(root == null) return false;
        
        Stack<BinaryNode<Integer>> s = new Stack<>();
        
        // first push the root into stack
        BinaryNode<Integer> temp = root;
        s.push(temp);
        
        while(!s.isEmpty()) {
            temp = s.pop();
            if(temp.value == nodeValue) return true;
            
            // left and right
            if(temp.left != null) {
                s.push(temp.left);
            }
            
            if(temp.right != null) {
                s.push(temp.right);
            }
        }
        
        return false;
    }
}
