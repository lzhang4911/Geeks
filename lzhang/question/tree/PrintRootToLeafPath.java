package lzhang.question.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import lzhang.model.BinaryNode;
import lzhang.util.BaseUtil;

/**
 * Give a binary tree, print all root-to-leaf paths
 * 
 * @author lzhang
 *
 */
public class PrintRootToLeafPath extends BaseUtil {
    public static void test() {
        // level order array
        Integer[] arr = {10, 8, 7, null, 3, 5, 4};
        
        CreateTreeFromArray util = new CreateTreeFromArray();
        BinaryNode<Integer> root = util.buildBalancedTreeFromLeveledOrderArray(arr);
        
        PrintRootToLeafPath p = new PrintRootToLeafPath();
        // my code works too - an iteration version
        p.printAllPaths(root);
        
        p.printPaths(root);
    }
    
    /**
     * Inorder traversal
     * @param root
     */
    private void printAllPaths(BinaryNode<Integer> root) {
        if(root == null) return;
        
        Stack<BinaryNode<Integer>> stack = new Stack<BinaryNode<Integer>>();
        Set<BinaryNode<Integer>> visited = new HashSet<BinaryNode<Integer>>();
        
        BinaryNode<Integer> node = root;
        addAllLefties(node, stack);
        
        while(!stack.isEmpty()) {
            node = stack.peek();
            if(visited.contains(node)) {
                stack.pop();
                continue;
            }
            
            visited.add(node);
            if(isLeaf(node)) {
                // print the path
                printPath(stack);
            } else {
                // push its right and all the lefties from right node
                addAllLefties(node.right, stack);
            }
        }
    }
    
    private void addAllLefties(BinaryNode<Integer> node, Stack<BinaryNode<Integer>> stack) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    
    private boolean isLeaf(BinaryNode<Integer> node) {
        return (node.left == null && node.right == null);
    }
    
    /**
     * Transfer everything from the original stack to a temporary one first, and
     * then print the path from the later since all nodes in it are in the right
     * order root -> leaf.
     * 
     * Restore the original stack.
     * @param stack
     */
    private void printPath(Stack<BinaryNode<Integer>> stack) {
        Stack<BinaryNode<Integer>> temp = new Stack<BinaryNode<Integer>>();
        
        // transfer over
        while(!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        
        // print path and restore
        print("");
        BinaryNode<Integer> node;
        while(!temp.isEmpty()) {
            node = temp.pop();
            
            // print it
            System.out.print(node.value + " ");
            
            stack.push(node);
        }
    }
    
    /*
     * Below is the recursion version copied from geeks. It's very concise!
     * No need to track which has been visited, of course, at the cost of using
     * deep calling stacks. 
     */
    void printPaths(BinaryNode<Integer> node) {
        int path[] = new int[1000];
        printPathsRecur(node, path, 0);
    }
  
    /* Recursive helper function -- given a node, and an array
       containing the path from the root node up to but not 
       including this node, print out all the root-leaf paths.
       
       Note that pathLen is very important. It controls the actual path
       content. The reason is that the code doesn't clear what's been saved
       into the path[] from previous root-leaf path.
       
       This is preorder traversal.
     */
    void printPathsRecur(BinaryNode<Integer> node, int path[], int pathLen) {
        if (node == null) return;
  
        /* append this node to the path array */
        path[pathLen] = node.value;
        pathLen++;
  
        /* it's a leaf, so print the path that led to here  */
        if (node.left == null && node.right == null) {
            printArray(path, pathLen);
        } else {
            /* otherwise try both subtrees */
            printPathsRecur(node.left, path, pathLen);
            printPathsRecur(node.right, path, pathLen);
        }
    }
    
    void printArray(int ints[], int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(ints[i] + " ");
        }
        
        System.out.println("");
    }
}
