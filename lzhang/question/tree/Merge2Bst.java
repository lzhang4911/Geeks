package lzhang.question.tree;

import java.util.Stack;

import lzhang.model.BinaryNode;

/**
 * https://www.geeksforgeeks.org/merge-two-bsts-with-limited-extra-space/
 * Given two Binary Search Trees(BST), print the elements of both BSTs in sorted form. 
 * The expected time complexity is O(m+n) where m is the number of nodes in first tree 
 * and n is the number of nodes in second tree. Maximum allowed auxiliary space is 
 * O(height of the first tree + height of the second tree).
 * 
Examples:

First BST 
       3
    /     \
   1       5
Second BST
    4
  /   \
2       6
Output: 1 2 3 4 5 6


First BST 
          8
         / \
        2   10
       /
      1
Second BST 
          5
         / 
        3  
       /
      0
Output: 0 1 2 3 5 8 10 
 * 
 * @author lzhang
 *
 */
public class Merge2Bst {
    public static void test() {
        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6};
        
        // expected merge result: {1, 2, 3, 4, 5, 6}
        CreateTreeFromArray util = new CreateTreeFromArray();
        BinaryNode<Integer> nodeA = util.buildBalancedBstFromSortedArray(a, 0, a.length - 1);
        BinaryNode<Integer> nodeB = util.buildBalancedBstFromSortedArray(b, 0, b.length - 1);
        
        BinaryNode.inorderPrint(nodeA);
        BinaryNode.inorderPrint(nodeB);
        
        Merge2Bst p = new Merge2Bst();
        p.mergePrint(nodeA, nodeB);
    }
    
    /**
     * This method just prints out the merged results. 
     * 
     * The result tree could be constructed from the resultant inorder array,
     * which requires O(n+m) additional space. Can we do better?
     * 
     * 
     * O(logn) in auxiliary space implies that an inorder traversal using a stack.
     * 
     * Starting from root, we can push all the left nodes along the inorder
     * path from both tree into their separated stacks, and then do a merge
     * sort from the 2 stack + right node traversal.
     * 
     * @param nodeA
     * @param nodeB
     */
    private void mergePrint(BinaryNode<Integer> nodeA, BinaryNode<Integer> nodeB) {
        if(nodeA == null) {
            inorderPrint(nodeB);
            return;
        }
        
        if(nodeB == null) {
            inorderPrint(nodeA);
            return;
        }
        
        // now none is null
        BinaryNode<Integer> pa = nodeA;
        BinaryNode<Integer> pb = nodeB;
        
        Stack<BinaryNode<Integer>> sa = new Stack<BinaryNode<Integer>>();
        Stack<BinaryNode<Integer>> sb = new Stack<BinaryNode<Integer>>();
        
        addAllLefties(pa, sa);
        addAllLefties(pb, sb);
        
        while(!sa.isEmpty() || !sb.isEmpty()) {
            if(sa.isEmpty()) {
                // process sb only
                while(!sb.isEmpty()) {
                    pb = sb.pop();
                    System.out.print(pb.value + " ");
                    
                    addAllLefties(pb.right, sb);
                }
            } else if(sb.isEmpty()) {
                // process sa only
                while(!sa.isEmpty()) {
                    pa = sa.pop();
                    System.out.print(pa.value + " ");
                    
                    addAllLefties(pa.right, sa);
                }
            } else {
                // pop one from each stack
                pa = sa.pop();
                pb = sb.pop();
                
                if(pa.value < pb.value) {
                    System.out.print(pa.value + " ");
                    sb.push(pb);
                    
                    addAllLefties(pa.right, sa);
                } else {
                    System.out.print(pb.value + " ");
                    sa.push(pa);
                    
                    addAllLefties(pb.right, sb);
                }
            }
        }
    }
    
    private void inorderPrint(BinaryNode<Integer> node) {
        BinaryNode.inorderPrint(node);
    }
    
    /**
     * First push the node object and then any left node along its inorder path.
     * 
     * @param node
     * @param s
     */
    private void addAllLefties(BinaryNode<Integer> node, Stack<BinaryNode<Integer>> s) {
        while(node != null) {
            s.push(node);
            
            node = node.left;
        }
    }
}
