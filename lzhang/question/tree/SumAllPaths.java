package lzhang.question.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import lzhang.model.BinaryNode;
import lzhang.util.BaseUtil;

/**
 * Given a binary tree, first form a number from root to each leaf, and then
 * sum all of them up. Return that sum.
 * 
                                          6
                                      /      \
                                    3          5
                                  /   \          \
                                 2     5          4  
                                      /   \
                                     7     4
  There are 4 leaves, hence 4 root to leaf paths:
   Path                    Number
  6->3->2                   632
  6->3->5->7               6357
  6->3->5->4               6354
  6->5>4                    654   
Answer = 632 + 6357 + 6354 + 654 = 13997 
 * 
 * @author lzhang
 *
 */
public class SumAllPaths extends BaseUtil {
    public static int test() {
        // for this tree first from a complete level order array
        Integer[] arr = {6, 3, 5, 2, 5, null, 4, null, null, 7, 4};
        CreateTreeFromArray util = new CreateTreeFromArray();
        BinaryNode<Integer> root = util.buildBalancedTreeFromLeveledOrderArray(arr);
        
        SumAllPaths p = new SumAllPaths();
        List<Integer> paths = new ArrayList<>();
        p.sumOfPaths(root, paths);
        
        int ret = 0;
        for(int i : paths) {
            ret += i;
        }
        
        return ret;
    }
    
    /**
     * Inorder traversal should get the node from root to leafs. Choosing inorder is
     * because the stack keeps the common nodes for adjacent paths.
     * 
     * @param root
     * @return
     */
    private void sumOfPaths(BinaryNode<Integer> root, List<Integer> paths) {
        if(root == null) {
            return;
        }
        
        Stack<BinaryNode<Integer>> s = new Stack<>();
        Set<BinaryNode<Integer>> visited = new HashSet<BinaryNode<Integer>>();
        
        BinaryNode<Integer> node = root;
        addAllLefties(node, s);
        
        while(!s.isEmpty()) {
            node = s.peek();
            if(visited.contains(node)) {
                s.pop();
                continue;
            }
            
            // Never forget this!
            visited.add(node);
            
            if(isLeaf(node)) {
                paths.add( createPath(s) );
            } else {
                //node = s.pop();
                this.addAllLefties(node.right, s);
            }
        }
    }
    
    private void addAllLefties(BinaryNode<Integer> node, Stack<BinaryNode<Integer>>s) {
        while(node != null) {
            s.push(node);
            
            node = node.left;
        }
    }
    
    private boolean isLeaf(BinaryNode<Integer> node) {
        return (node.left == null && node.right == null);
    }
    
    /**
     * Since what's held in source stack is from leaf to root, we need to move the
     * whole stack to a temporary stack so that a path from root to leaf can be properly
     * formed.
     * 
     * Note we must place each node back to the source stack when we pop them from the
     * temporary stack to continue the rest of inorder traversal. 
     * 
     * The last (the leaf) node is purposely neglected. Otherwise, the while-loop in the
     * main processing logic will run forever to print out the same path.
     * 
     * @param source
     * @return
     */
    private int createPath(Stack<BinaryNode<Integer>> source) {
        Stack<BinaryNode<Integer>> temp = new Stack<BinaryNode<Integer>>();
        
        // first swap everything from source stack to temp
        while(!source.isEmpty()) {
            temp.push( source.pop() );
        }
        
        /*
         * Now nodes in temp are sequenced from root to a leaf. Pop each from temp,
         * add the key to for the path, and then push back to the source complete the 
         * rest of inorder traversal.
         */
        String path = null;
        BinaryNode<Integer> node;
        
        while(!temp.isEmpty()) {
            node = temp.pop();
            if(path == null) path = Integer.toString(node.value);
            else path += node.value;
            
            if(!this.isLeaf(node)) {
                // don't push the leaf back to avoid repeating the path creation forever
                source.push( node );
            }
        }
        
        print("Path: " + path);
        
        // convert this path string to number
        return Integer.parseInt(path);
    }
}
