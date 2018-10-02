package lzhang.question.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import lzhang.model.BinaryNode;
import lzhang.util.BaseUtil;

/**
 * https://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/
 * 
 * Find the largest BST in a given binary tree.
 * 
 * To avoid computing on subtrees multiple times, we should try traverse from bottom up.
 * INorder would be ideal since it achieves both bottom first and left/right together.
 * 
 * Since we are walking bottom up, the BST node range shall be determined from the bottom
 * as well. If the parent violates the BST range, that simply means they don't form a BST!
 * 
 * @author lzhang
 *
 */
public class LargestBst extends BaseUtil {
    static class NodeInfo {
        public int min, max, subtreeSize;
        
        public NodeInfo(int min, int max, int subtreeSize) {
            this.min = min;
            this.max = max;
            this.subtreeSize = subtreeSize;
        }
    }
    
    private int max_tree_size = 1;
    private BinaryNode<Integer> maxTree;
    
    public static int test() {
        // the biggest sub BST would be {60, 55, 70, 45, null, 65, 80}
        Integer[] arr = {50, 10, 60, 5, 20, 55, 70, null, null, null, null, 45, null, 65, 80};
        CreateTreeFromArray util = new CreateTreeFromArray();
        
        BinaryNode<Integer> root = util.buildBalancedTreeFromLeveledOrderArray(arr);
        
        LargestBst p = new LargestBst();
        p.inorder(root);
        
        print("Found max sub BST:");
        BinaryNode.levelOrderPrint(p.maxTree);
        
        return p.max_tree_size;
    }
    
    /**
     * Inorder traversal allows me to process subtrees from the bottom all the way up.
     * This way, no subtrees shall be re-processed. When calculating BstInfo for each node 
     * (really the subtree starting at that node) we keep track the max BST subtree size.
     * Note that we could track the actual node as well with maxTree.
     * 
     * @param root
     */
    private void inorder(BinaryNode<Integer> root) {
        Stack<BinaryNode<Integer>> s = new Stack<>();
        
        /*
         * For each node, the value is its BST size if the subtree is a BST.
         * This is also served as visited lookup.
         */
        Map<BinaryNode<Integer>, NodeInfo> subtreeSizeMap = new HashMap<BinaryNode<Integer>, NodeInfo>();
        BinaryNode<Integer> node = root;
        
        addLefties(node, s, subtreeSizeMap);
        
        while(!s.isEmpty()) {
            node = s.peek();
            if(subtreeSizeMap.get(node) != null) {
                // pop it off if it's processed already
                s.pop();
            } else if(isLeaf(node)) {
                processBstInfo(node, subtreeSizeMap);
            } else {
                if(isRightChildNotProcessed(node.right, subtreeSizeMap)) {
                    // if there is right node and any of lefties of that right node
                    addLefties(node.right, s, subtreeSizeMap);
                } else {
                    // all children are already processed,processing this parent now
                    processBstInfo(node, subtreeSizeMap);
                }
            }
        }
    }
    
    private void addLefties(BinaryNode<Integer> node, Stack<BinaryNode<Integer>> s, Map<BinaryNode<Integer>, NodeInfo> subtreeSizeMap) {
        while(node != null) {
            if(subtreeSizeMap.get(node) != null) {
                // visited already
                continue;
            }
            
            s.push(node);
            node = node.left;
        }
    }
    
    private boolean isLeaf(BinaryNode<Integer> node) {
        return node.left == null && node.right == null;
    }
    
    /**
     * Check to see if this right child is not null and it'ss not processed yet
     * 
     * @param rightNode
     * @param subtreeSizeMap
     * @return
     */
    private boolean isRightChildNotProcessed(BinaryNode<Integer> rightNode, Map<BinaryNode<Integer>, NodeInfo> subtreeSizeMap) {
        if(rightNode == null) {
            return false;
        }
        
        // see if this node has been proceeded or not
        return (subtreeSizeMap.get(rightNode) == null);
    }
    
    /**
     * We calculate the NodeInfo only if
     * (1) it's a leaf node; or
     * (2) both its left and right children are processed already.
     * 
     * @param node
     * @param subtreeSizeMap
     */
    private void processBstInfo(BinaryNode<Integer> node, Map<BinaryNode<Integer>, NodeInfo> subtreeSizeMap) {
        if(isLeaf(node)) {
            // a leaf node is always a BST of size 1
            subtreeSizeMap.put(node, new NodeInfo(node.value, node.value, 1));
        } else {
            // expect that both children were already processed
            NodeInfo li = null, ri = null;
            if(node.left != null) {
                li = subtreeSizeMap.get(node.left);
            }
            
            if(node.right != null) {
                ri = subtreeSizeMap.get(node.right);
            }
            
            NodeInfo ni = null;
            
            if(validBst(node, li, ri)) {
                // create BstInfo for this node
                int size = 1;
                if(li != null) size += li.subtreeSize;
                if(ri != null) size += ri.subtreeSize;
                
                //this.max_tree_size = Math.max(size, max_tree_size);
                if(size > max_tree_size) {
                    max_tree_size = size;
                    maxTree = node;
                }
                
                ni = new NodeInfo((li == null? node.value : li.min), (ri == null? node.value : ri.max), size);
            } else {
                // not a BST so 0 BST size (boundaries are not important)
                ni = new NodeInfo(node.value, node.value, 0);
            }
            
            subtreeSizeMap.put(node, ni);
        }
    }
    
    private boolean validBst(BinaryNode<Integer> node, NodeInfo li, NodeInfo ri) {
        BinaryNode<Integer> l = node.left;
        BinaryNode<Integer> r = node.right;
        
        /*
         * Left node and its 2 bounds must be smaller than root key
         */
        if(l != null) {
            if(l.value > node.value || li.max > node.value) {
                return false;
            }
        }
        
        if(r != null) {
            if(r.value < node.value || ri.min < node.value) {
                return false;
            }
        }
        
        return true;
    }
}
