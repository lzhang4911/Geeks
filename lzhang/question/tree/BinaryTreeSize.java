package lzhang.question.tree;

import java.util.LinkedList;
import java.util.Queue;

import lzhang.model.BinaryNode;
import lzhang.util.BaseUtil;

public class BinaryTreeSize extends BaseUtil {
    static class Count {
        private int count;
    }
    
    public static void test(BinaryNode<Integer> root) {
        BinaryNode.levelOrderPrint(root);
        
        BinaryTreeSize p = new BinaryTreeSize();
        
        print("Binary tree hight: " + p.getBtHeight(root));
        
        print("Binary tree width: " + p.getBtWidth(root));
        
        print("Binary tree minDepth: " + p.minDepth(root));
        
        print("Binary tree diameter: " + p.diameter(root));
        
        double d = p.getTreeDecity(root);
        
        print("tree dencity: " + d);
    }

    
    /**
     * The number of node from root to the longest leaf.
     * @param root
     * @return
     */
    private int getBtHeight(BinaryNode<Integer> root) {
        if(root == null) {
            return 0;
        }
        
        // root is counted as 1
        return 1 + Math.max( getBtHeight(root.left), getBtHeight(root.right) );
        
        /*
         * Or simply
         * return (root == null? 0 : 1 + Math.max( getBtHeight(root.left), getBtHeight(root.right) ));
         */
    }
    
    /**
     * This should be the number of node from bottom view.
     * 
     * Just use level order traversal and count the number of nodes for each
     * level. A tricky approach is to use 2 while-loops when processing the
     * queue. At the beginning of the first while-loop, we know the total number
     * of nodes for that level, count them using q.size().
     * 
     * Then in the second while-loop, pop each node off up to the count. Enqueue
     * both left and right, if not null, after having popped a node.
     * 
     * @param root
     * @return
     */
    private int getBtWidth(BinaryNode<Integer> root) {
        if(root == null) {
            return 0;
        }
        
        // use Queue
        Queue<BinaryNode<Integer>> q = new LinkedList<BinaryNode<Integer>>();

        int levelW, maxW = 0;
        
        // enquqe root first
        q.add(root);
        
        while(!q.isEmpty()) {
            levelW = q.size();
            maxW = Math.max(maxW, levelW);
            
            while(levelW-- > 0) {
                // keep popping and processing the nodes from the queue
                BinaryNode<Integer> node = q.poll();
                
                if(node.left != null) {
                    q.add(node.left);
                }
                
                if(node.right != null) {
                    q.add(node.right);
                }
            }
        }
        
        return maxW;
    }
    
    /**
     * The min depth is the number of node along the shorted path.
     * 
     * We can use inorder traversal to compare the height of every path.
     * @param root
     * @return
     */
    private int minDepth(BinaryNode<Integer> root) {
        if(root == null) {
            return 0;
        }
        
        return 1 + Math.min( minDepth(root.left), minDepth(root.right) );
    }
    
    /**
     * A diameter is the max of
     *  - left_height + right_height + 1;
     *  - left diameter;
     *  - right diameter.
     *  
     * @param root
     * @return
     */
    private int diameter(BinaryNode<Integer> root) {
        if(root == null) {
            return 0;
        }
        
        int left_height = this.getBtHeight(root.left);
        int right_height = this.getBtHeight(root.right);
        
        int diameter_via_root = 1 + left_height + right_height;
        int left_diameter = this.diameter(root.left);
        int right_diameter = this.diameter(root.right);
        
        return Math.max(diameter_via_root, Math.max(left_diameter, right_diameter));
    }
    
    private double getTreeDecity(BinaryNode<Integer> root) {
        Count c = new Count();
        int height = getHeightAndCount(root, c);
        
        return (height == 0? 0 : c.count/height);
    }
    
    /**
     * This method tries to get both node count and the tree height in one traversal.
     * 
     * There can be many different approaches:
     * - level order traversal
     * @param root
     * @param c
     * @return
     */
    private int getHeightAndCount(BinaryNode<Integer> root, Count c) {
        if(root == null) return 0;
        
        int l = this.getHeightAndCount(root.left, c);
        int r = this.getHeightAndCount(root.right, c);
        c.count++;
        
        // return the height
        return 1 + Math.max(l, r);
    }
}
