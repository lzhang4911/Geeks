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
        boolean inLeftTree = true;
        if(key < bst.value) {
            inLeftTree = true;
            node = bst;
        } else { // including key == bst.value
            inLeftTree = false;
            node = bst.right;
        }
        
        // start inorder traversal
        addAllLefties(node, s);
        
        if(inLeftTree) {
            /* 
             * The node on the top of stack is smallest. If it's still bigger
             * than the key, that means that is the one.
             */
            int nextGreaterKey = s.peek().value;
            if(nextGreaterKey > key) {
                return nextGreaterKey;
            }
            
            /*
             * Otherwise, the one on the top must be less, then looping
             * through the stack below will see a bigger one eventually.
             */
        } 
        
        while(!s.isEmpty()) {
            node = s.pop();
            
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
     * @param bst
     * @param key
     * @return
     */
    private int order1solution(BinaryNode<Integer> bst, int key) {
        BinaryNode<Integer> ans = null;
        
        while(bst != null) {
            if(bst.value > key) {
                // must be in left branch including the current node as well
                ans = bst;
                bst = bst.left;
            } else if(bst.value == key) {
                return key;
            } else {
                // the searched node may be in right branch
                bst = bst.right;
            }
        }
        
        if(ans != null) {
            return ans.value;
        }
        
        return -1;
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
}
