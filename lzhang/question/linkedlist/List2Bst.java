package lzhang.question.linkedlist;

import lzhang.model.BinaryNode;

/**
 * Requirements:
 * 
 * Convert a doubly linked sorted list DLL, in-place, to a Binary Search Tree.
 * 
 * @author lzhang
 *
 */
public class List2Bst {
    private BinaryNode<Integer> head;
    
    public static void test() {
        // let's use BinaryNode to construct the DLL
        BinaryNode<Integer> head = null, node = null;
        
        int i = 1;
        int n = 10;
        while(i < n) {
            if(head == null) {
                head = new BinaryNode<Integer>(i);
                node = head;
            } else {
                node.next = new BinaryNode<Integer>(i);
                node.next.prev = node;
                
                node = node.next;
            }
            
            i++;
        }
        
        List2Bst p = new List2Bst();
        p.head = head;
        BinaryNode<Integer> root = p.convert2Bst(n);
        BinaryNode.inorderPrint(root);
    }
    
    /**
     * There are 2 solutions.
     * 
     * Solution 1: This is conventional. Locate the mid node as the root. The
     *             the mid in the left half as the left node, and mid of right
     *             half as right node. This goes recursively.
     *             
     *             This is an O(nlogn) in time complexity.
     *             
     * Solution 2: Build leaf first and from bottom up. O(n).
     *             FIXME: doesn't work
     *             https://www.geeksforgeeks.org/?p=17629
     * @param head
     * @param n
     */
    private BinaryNode<Integer> convert2Bst(int n) {
        if(head == null || n <= 0) {
            return null;
        }
        
        /* Recursively construct the left subtree */
        BinaryNode<Integer> left = convert2Bst(n / 2);
 
        /* head_ref now refers to middle node,
           make middle node as root of BST*/
        BinaryNode<Integer> root = head;
 
        // Set pointer to left subtree
        root.left = left;
 
        /* Change head pointer of Linked List for parent
           recursive calls */
        head = head.next;
 
        /* Recursively construct the right subtree and link it
           with root. The number of nodes in right subtree  is
           total nodes - nodes in left subtree - 1 (for root) */
        root.right = convert2Bst(n - n / 2 - 1);
 
        return root;
    }
}
