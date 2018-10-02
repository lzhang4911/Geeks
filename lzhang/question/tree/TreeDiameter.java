package lzhang.question.tree;

import lzhang.model.BinaryNode;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter 
 * of a binary tree is the length of the longest path between any two nodes in a tree. This path 
 * may or may not pass through the root.
 * 
 * @author lzhang
 *
 */
public class TreeDiameter {

    int maxDiameter = 0;
    
    public static int test() {
        
        // create the same tree as the first one in question Merge
        BinaryNode<Integer> root = new BinaryNode<Integer>(30);
        root.left = new BinaryNode<Integer>(20);
        root.left.left = new BinaryNode<Integer>(10);
        
        root.right = new BinaryNode<Integer>(50);
        
        // the diameter so far is 4 passing the root 
        
        // modify the tree so that the diameter doesn't pass the root
        root.left.right = new BinaryNode<Integer>(25);
        root.left.right.left = new BinaryNode<Integer>(22);
        root.left.right.left.right = new BinaryNode<Integer>(23);
        
        root.left.left.left = new BinaryNode<Integer>(5);
        root.left.left.right = new BinaryNode<Integer>(15);
        root.left.left.right.right = new BinaryNode<Integer>(17);
        
        // the diameter is entirely i the left tree and it's 7
        
        
        TreeDiameter p = new TreeDiameter();
        
        // calculate the max diameter during the height sub tree calculation
        p.getBtHeight(root);
        return p.maxDiameter;
    }
    

    /**
     * This method does 2 things:
     * - calculate the height of a given tree;
     * - while calculating the tree height, we can easily get the aka diameter
     *   easily because we already have both heights of left and right sub trees.
     *   Of course, the max diameter must be an instance member.
     *   
     * FIXME: This is not correct. This approach works only if the diameter is the
     * path that passes through the root.
     * 
     * The correct approach would be find the max of( 1+l+r, left_diameter, r_diameter).
     * Refer to BinaryTreeSize.diameter().
     *    
     * @param root
     * @return
     */
    private int getBtHeight(BinaryNode<Integer> root) {
        if(root == null) {
            return 0;
        }
        
        int l = getBtHeight(root.left);
        int r = getBtHeight(root.right);
        
        maxDiameter = Math.max(maxDiameter, (l + 1 + r));
        
        int height = 1 + Math.max( l, r );
        
        
        return height;
    }
}
