package lzhang.question.tree;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import lzhang.model.BinaryNode;

public class PrintSideView {
    static int  max_level = 0;

    public static void test(BinaryNode<Integer> node) {
        PrintSideView p = new PrintSideView();
        
        p.printLeftView(node);
        
        System.out.print("\nLeft view 2: ");
        max_level = 0;
        p.printLeftView2(node, 1);
        
        System.out.print("\nright view: ");
        max_level = 0;
        p.printRightView(node, 1);
        
        p.printTopView(node);
        
        System.out.print("\nBottom view: ");
        p.printBottomView(node);
    }
    
    
    /**
     * The left view contains all nodes that are first nodes in their levels. A simple  
     * solution is to do level order traversal and print the first node in every level.
     * 
     * Let's first try a modified leveled traversal.
     *  
     * @param node
     */
    private void printLeftView(BinaryNode<Integer> node) {
        if(node == null) {
            return;
        }
        
        System.out.print("Left view:   ");
        Queue<BinaryNode<Integer>> q = new LinkedList<>();
        
        // first add line terminator
        q.offer(null);
        q.offer(node);
        
        boolean isFirstNode = false;
        while(!q.isEmpty()) {
            BinaryNode<Integer> tmp = q.poll();
            if(tmp == null) {
                if(q.isEmpty()) {
                    // don't put it back any more since we have done with all nodes
                    break;
                } else {
                    q.offer(null);
                    
                    // mark the next node as first one to start this level
                    isFirstNode = true;
                }
            } else {
                if(isFirstNode) {
                    System.out.print(tmp.value + " ");
                    isFirstNode = false;
                }
                
                if(tmp.left != null) {
                    q.offer(tmp.left);
                }
                
                if(tmp.right != null) {
                    q.offer(tmp.right);
                }
            }
        }
    }
    
    /**
     * recursive function to print left view using level value
     *  
     * @param node
     * @param level
     */
    private void printLeftView2(BinaryNode<Integer> node, int level) {
        // Base Case
        if (node==null) return;
 
        // If this is the first node of its level
        if (max_level < level) {
            System.out.print(node.value + " ");
            max_level = level;
        }
 
        // Recur for left and right subtrees
        printLeftView2(node.left, level+1);
        printLeftView2(node.right, level+1);
    }
    
    /**
     * Same as left view, but always tale right node before left instead.
     * 
     * @param node
     * @param level
     */
    private void printRightView(BinaryNode<Integer> node, int level) {
        // Base Case
        if (node==null) return;
 
        // If this is the first node of its level
        if (max_level < level) {
            System.out.print(node.value + " ");
            max_level = level;
        }
 
        // Recur for left and right subtrees
        printLeftView2(node.right, level+1);
        printLeftView2(node.left, level+1);
    }
    
    /**
     * 
     * - enqueue left most nodes in inorder, plus
     * - loop through right most nodes in postorder;
     * 
     * FIXME: I don't think this would work. We need to do the vertical traversal
     * using horizontal distance, which requires each node to carry its own HD. A
     * trick to print the top node in each vertical line is to use a HashSet to
     * remember the each HD. Print the node only if the HD of the node is not found
     * in the set yet. Lever order traversal is fine. 
     * 
     * @param node
     * @param hd
     */
    private void printTopView(BinaryNode<Integer> root) {
        Stack<BinaryNode<Integer>> s = new Stack<>();
        
        System.out.println("\nTop view: ");
        
        // push all left most nodes to the stack
        BinaryNode<Integer> node = root;
        while(node != null) {
            s.push(node);
            
            node = node.left;
        }
        
        // print from stack
        while(!s.isEmpty()) {
            System.out.print( s.pop().value + " " );
        }
        
        // print out the right most
        node = root.right;
        while(node != null) {
            System.out.print( node.value + " " );
            
            node = node.right;
        }
    }
    
    /**
     * - assign HD 0 to root;
     * - recurse for children with proper HD;
     * - keep each node into a TreeMap as (HD, LinkedList of nodes)
     * 
     * @param root
     */
    private void printBottomView(BinaryNode<Integer> root) {
        Map<Integer, BinaryNode<Integer>> hdNodeTrack = new TreeMap<>();
        
        // assign 0 for HD to root
        root.otherValue = 0;
        this.printBottomViewHelper(root, hdNodeTrack);
        
        // print the bottom nodes
        for(int i : hdNodeTrack.keySet()) {
            System.out.print(hdNodeTrack.get(i).value + " ");
        }
    }
    
    private void printBottomViewHelper(BinaryNode<Integer> root, Map<Integer, BinaryNode<Integer>> hdNodeTrack) {
        if(root == null) {
            return;
        }
        
        // add this node to the map - the last node with the same HD stays!
        hdNodeTrack.put(root.otherValue, root);
        
        if(root.left != null) {
            root.left.otherValue = root.otherValue - 1;
            printBottomViewHelper(root.left, hdNodeTrack);
        }
        
        if(root.right != null) {
            root.right.otherValue = root.otherValue + 1;
            printBottomViewHelper(root.right, hdNodeTrack);
        }
    }
}
