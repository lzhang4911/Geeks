package lzhang.question.tree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

import lzhang.model.BinaryNode;
import lzhang.util.BaseUtil;

/**
 * https://www.geeksforgeeks.org/find-pair-root-leaf-path-sum-equals-roots-data/
 * 
 * Given a binary tree, print out the path (from root to leaf) from which the
 * sum of 2 node is the same as root.
 * 
 * Assume the nodes are distinct.
 * 
 * @author lzhang
 *
 */
public class SumOf2InPathMatchesRoot extends BaseUtil {

    public static void test() {
        /*
         * level order array
         * The path [11, 5, 3, 6] has (5 + 6) == 11
         */
        Integer[] arr= {11, 5, 6, 3, 4, 7, 8, null, 6};
        
        CreateTreeFromArray util = new CreateTreeFromArray();
        BinaryNode<Integer>  root = util.buildBalancedTreeFromLeveledOrderArray(arr);
        
        SumOf2InPathMatchesRoot p = new SumOf2InPathMatchesRoot();
        p.findPath(root);
    }
    
    /**
     * Let's try DFS with both regular stack and a set. The set is used to
     * calculate the sum.
     * 
     * @param root
     */
    private void findPath(BinaryNode<Integer>  root) {
        if(root == null) return;
        
        Stack<BinaryNode<Integer>> s = new Stack<>();
        Set<BinaryNode<Integer>> visited = new HashSet<>();
        
        // push all lefties into stack first
        BinaryNode<Integer> node = root;
        pushAllLefties(node, s, visited);
        
        while(!s.isEmpty()) {
            node = s.peek();
            
            if(isLeaf(node)) {
                if(find2Sum(s, root.value)) {
                    printPath(s);
                    break;
                } else {
                    s.pop();
                }
            } else {
                if(!visited.contains(node.right)) {
                    this.pushAllLefties(node.right, s, visited);
                } else {
                    s.pop();
                }
            }
        }
    }

    /**
     * Push both the node itself and all possible left-most nodes into the stack. 
     * @param node
     * @param s
     */
    private void pushAllLefties(BinaryNode<Integer> node, Stack<BinaryNode<Integer>> s, Set<BinaryNode<Integer>> visited) {
        while(node != null) {
            s.push(node);
            visited.add(node);
            
            node = node.left;
        }
    }
    
    private boolean isLeaf(BinaryNode<Integer> node) {
        return (node.left == null && node.right == null);
    }
    
    private boolean find2Sum(Stack<BinaryNode<Integer>> s, int sum) {
        boolean found = false;
        BinaryNode<Integer> node;
        
        Stack<BinaryNode<Integer>> temp = new Stack<BinaryNode<Integer>>();
        Set<Integer> set = new LinkedHashSet<>();
        
        while(!s.isEmpty()) {
            node = s.pop();
            temp.push(node);
            
            if(set.contains(sum - node.value)) {
                // found the 2 nodes
                print( String.format("Found the 2 nodes, %d and %d with sum equals to %d", node.value, (sum-node.value), sum));
                found = true;
                break;
            } else {
                set.add(node.value);
            }
        }
        
        // transfer anything in the temp back to the original stack
        while(!temp.isEmpty()) {
            s.push( temp.pop() );
        }
        
        set.clear();
        
        return found;
    }
    
    private void printPath(Stack<BinaryNode<Integer>> s) {
        Stack<BinaryNode<Integer>> temp = new Stack<BinaryNode<Integer>>();
        
        while(!s.isEmpty()) {
            temp.push( s.pop() );
        }
        
        // now nodes in temp is order from root to leaf
        while(!temp.isEmpty()) {
            System.out.print(temp.pop().value + " ");
        }
    }
}
