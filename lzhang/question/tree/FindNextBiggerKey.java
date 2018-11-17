package lzhang.question.tree;

import java.util.Stack;

import lzhang.model.BinaryNode;

/**
 * Given a BST and a key (may or may not exist), find the next bigger node.
 * 
 * @author lzhang
 *
 */
public class FindNextBiggerKey {
    public static int test() {
        int[] arr = {1, 2, 3, 4, 6, 7, 8};
        CreateTreeFromArray util = new CreateTreeFromArray();
        BinaryNode<Integer> bst = util.buildBalancedBstFromSortedArray(arr, 0, arr.length - 1);
        int key = 5;
        
        // good
        FindNextBiggerKey p = new FindNextBiggerKey();
        int ret = p.findNextBiggerKey(bst, key);
        
        // better
        ret = p.order1solution(bst, key);
        
        return ret;
    }
    
    /**
     * One solution is to convert the bst to its inorder array and use binary search to
     * locate the next bigger one.
     * 
     * How can we do it without creating this extra array? The solution below is
     * O(n) in time and O(n) in space using stack.
     * 
     * The best can be O(n) in time and O(1) in space using iteration as well.
     * 
     * @param bst
     * @param key
     * @return
     */
    private int findNextBiggerKey(BinaryNode<Integer> bst, int key) {
        if(bst == null) {
            // no key to find
            return -1;
        }
        
        Stack<BinaryNode<Integer>> s = new Stack<BinaryNode<Integer>>();
        
        int ret = -1;
        BinaryNode<Integer> node;
        if(key < bst.value) {
            node = bst;
        } else { // including key == bst.value
            node = bst.right;
        }
        
        // start inorder traversal
        addAllLefties(node, s);
        
        while(!s.isEmpty()) {
            node = s.pop();
            
            // the very first time the bigger node is the immediate successor!
            if(node.value > key) {
                // this is the one - this logic works for both subtrees
                ret = node.value;
                break;
            }
            
            addAllLefties(node.right, s);
        }
        
        return ret;
    }
    
    /**
     * This solution doesn't use a stack or the DFS traversal. Instead it
     * just do the basic BFS traversal in iteration using a constant one
     * pointer for the answer.
     * 
     * Time: O(logn), space: O(1)
     * 
     * @param bst
     * @param key
     * @return
     */
    private int order1solution(BinaryNode<Integer> bst, int key) {
        BinaryNode<Integer> ans = null;
        
        while(bst != null) {
            if(key < bst.value) {
                // must be in left branch including the current node as well
                ans = bst;
                bst = bst.left;
            } else {
                // the searched node may be in right branch
                bst = bst.right;
            }
        }
        
        if(ans == null) {
            return -1;
        } else {
        	return ans.value;
        }
    }
    
    /**
     * Push this node and all left most node along its inorder path
     * @param node
     * @param s
     */
    private void addAllLefties(BinaryNode<Integer> node, Stack<BinaryNode<Integer>> s) {
        while(node != null) {
            s.push(node);
            
            node = node.left;
        }
    }
    
    /**
     * There is another situation where both root and a node is given, find the inorder
     * successor of the said node.
     * 
     * The solution to such question is divided into 2 situation:
     * (1) if node.right != null, find the left most node from subtree node.right;
     * (2) else, the successor would be the closest ancestor who is a left node of
     *     from its own family tree. This is exactly like order1solution().
     */
}
