package lzhang.question.tree;

import lzhang.model.BinaryNode;

/**
 * Give a binary tree, calculate the the average of each possible sub tree
 * and find the max average.
 * 
 * Note a leaf node is considered a sub tree as well.
 * 
 * The said average include the average at root level as well.
 * 
 * @author lzhang
 *
 */
public class MaxSubtreeAverage {
    static class NodeSumCount {
        int sum;
        int count;
        
        public int getAvg() {
            if(count == 0) return 0;
            
            return (int)(sum/count); 
        }
    }
    
    private static int maxAverage = 0;
    
    public static int test() {
        BinaryNode root = new BinaryNode(0);
        root.left = new BinaryNode(21);
        root.left.left = new BinaryNode(3);
        root.left.right = new BinaryNode(5);
        
        root.right = new BinaryNode(4);
        root.right.right = new BinaryNode(7);
        
        getNodeSumCount(root);
        return maxAverage;
    }

    /**
     * To calculate the average for each sub, we need to know the sum of each sub
     * tree and total count, NodeSumCount.
     * 
     * A postorder approach might make more sense.
     * 
     * @param n
     * @return
     */
    private static NodeSumCount getNodeSumCount(BinaryNode<Integer> n) {
        NodeSumCount current = new NodeSumCount();
        
        if(n == null) {
            return current;
        }
        
        // left and right first
        NodeSumCount leftNodeSumCount  = getNodeSumCount(n.left);
        NodeSumCount rightNodeSumCount  = getNodeSumCount(n.right);
        
        // visit
        current.sum = n.value + leftNodeSumCount.sum + rightNodeSumCount.sum;
        current.count = 1 + leftNodeSumCount.count + rightNodeSumCount.count;
        
        // Has maxAverage changed?
        maxAverage = Math.max(maxAverage, leftNodeSumCount.getAvg());
        maxAverage = Math.max(maxAverage, rightNodeSumCount.getAvg());
        
        return current;
    }
}
