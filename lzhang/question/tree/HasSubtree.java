package lzhang.question.tree;

import lzhang.model.BinaryNode;

/**
 * Given 2 binary trees A and B, verify if A contains B
 * 
 * A: {20, 18, 30, 10, 16, 25, 40, 5, 15, 4, 6}
 * 
 * B: {10, 5, 15, 4, 6}
 * 
 * @author lzhang
 *
 */
public class HasSubtree {
    public static void test() {
        int[] As = {20, 18, 30, 10, 19, 25, 40, 5, 15, 4, 6};
        int[] Bs = {10, 5, 15, 4, 6};
        
        HasSubtree p = new HasSubtree();
        
        // build BST from leveled-order array first
        CreateTreeFromArray builder = new CreateTreeFromArray();
        BinaryNode<Integer> A = builder.buildBstFromLevelOrderArray(As);
        BinaryNode<Integer> B = builder.buildBstFromLevelOrderArray(Bs);
        
        System.out.println("A contains B? " + p.hasSubtree(A, B));
    }
    
    
    /**
     * Use preorder on A?
     *  
     * @param A
     * @param B
     * @return
     */
    private boolean hasSubtree(BinaryNode<Integer> A, BinaryNode<Integer> B) {
        boolean result = false;
        
        if(A != null && B != null) {
            result = isEqual(A, B);
            if(!result) {
                result = this.hasSubtree(A.left, B);
            }
            
            if(!result) {
                result = this.hasSubtree(A.right, B);
            }
        }
        
        return result;
    }
    
    private boolean isEqual(BinaryNode<Integer> A, BinaryNode<Integer> B) {
        if(A == null && B == null) {
            return true;
        }
        
        if(A == null || B == null) {
            return false;
        }
        
        if(A.value != B.value) {
            return false;
        }
        
        return isEqual(A.left, B.left) && isEqual(A.right, B.right);
    }
}
