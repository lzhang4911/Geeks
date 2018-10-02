package lzhang.question.tree;

import lzhang.util.BaseUtil;

/**
 * https://www.geeksforgeeks.org/calculate-depth-full-binary-tree-preorder/
 * 
 * A full binary tree whose preorder array is given. find out its depth (height).
 * 
 * Note a full binary tree means each node has either 0 or 2 sub nodes.
 * 
 * The preorder is given as a string consisting of n and l where n denotes an
 * internal node while l a leaf node.
 * 
 * Input: "nlnnlll" is a tree with height of 4.
 *  
 * @author lzhang
 *
 */
public class PreorderFullBinaryTreeDepth extends BaseUtil {
    public static void test() {
        PreorderFullBinaryTreeDepth p = new PreorderFullBinaryTreeDepth();
        
//        String pre = "nlnnlll"; // return 3
        String pre = "nlnll"; // return 2
        int res = p.getHeight(pre.toCharArray(), 0, pre.length());
        
        print( String.format("Preorder %s depth: %d", pre, res) );
    }
    
    /**
     * Don't really understand the logic!
     * Recursive
     * 
     * @param pre
     * @param nodeIndex
     * @param len
     * @return
     */
    private int getHeight(char[] pre, int nodeIndex, int len) {
        if(pre == null) {
            return 0;
        }
        
        if(nodeIndex >= len || pre[nodeIndex] == 'l') {
            return 0;
        }
        
        nodeIndex++;
        int left = getHeight(pre, nodeIndex, len);
        
        nodeIndex++;
        int right = getHeight(pre, nodeIndex, len);
        
        return 1 + Math.max(left,  right);
    }
}
