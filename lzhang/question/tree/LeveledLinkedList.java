package lzhang.question.tree;

import java.util.LinkedList;
import java.util.Queue;

import lzhang.model.BinaryNode;

/**
 * Given a binary tree, connect all node at the same level as a singly linked list.
 * 
 * @author lzhang
 *
 */
public class LeveledLinkedList {
    public static void test() {
        // create 2 binary trees, one perfect and one not
        CreateTreeFromArray util = new CreateTreeFromArray();
        
        Integer[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        BinaryNode<Integer> root1 = util.buildBalancedTreeFromLeveledOrderArray(arr1);
        
        Integer[] arr2 = {1, 2, 3, null, 5, 6, null};
        BinaryNode<Integer> root2 = util.buildBalancedTreeFromLeveledOrderArray(arr2);
        
        LeveledLinkedList p = new LeveledLinkedList();
//        p.iterative(root1);
//        p.iterative(root2);
        
        // FIXME: doesn't work yet
        root1.otherValue = 0;
        p.recursive(root1, null);
        
        // works!
        p.connect(root2.left, root2.right);
        
        // works!
//        p.connect(root1);
//        p.connect(root2);
        
        System.out.println("LeveledLinkedList Done");
    }
    
    /**
     * Though this is O(n) in time complexity, it may not be accepted since it uses O(n) extra space, the queue.
     * @param node
     */
    private void iterative(BinaryNode<Integer> node) {
        if(node == null) {
            return;
        }
        
        // create a queue for level order node processing
        Queue<BinaryNode<Integer> > q = new LinkedList<BinaryNode<Integer> >();
        
        // add the root node first
        q.add(node);
        
        while(!q.isEmpty()) {
            /*
             * The queue at this moment hold all node from the same level.
             * Let's first get its element count and then just process that many.
             */
            int count = q.size();
            BinaryNode<Integer> prev = null;
            while(count-- > 0) {
                BinaryNode<Integer> t = q.poll();
                
                // queue its children
                if(t.left != null) {
                    q.add(t.left);
                }
                if(t.right != null) {
                    q.add(t.right);
                }
                
                // start wiring
                if(prev != null) {
                    prev.next = t;
                }
                
                prev = t;
            }
        }
    }
    
    /**
     * Copied from Internet. It's beautiful and works!
     * 
     * @param root
     */
    public void connect(BinaryNode<Integer>  root) {
        if(root == null) return;
        
        BinaryNode<Integer>  leftN = root.left;
        BinaryNode<Integer>  rightN = root.right;
        while(leftN != null){
            leftN.next = rightN;
            
            // why?
            leftN = leftN.right;
            
            if(rightN != null) {
                rightN = rightN.left;
            }
        }
        
        connect(root.left);
        connect(root.right);
    }
    
    /**
     * The iterative approach requires a queue, an O(n) extra space, to complete
     * the wiring. Can we do better than that?
     * 
     * Let use Node.otherValue to be the position index at each level. if the index
     * is i, the its children will be 2*n and 2*n + 1.
     * 
     * @param node
     * @param prevChild
     */
    private void recursive(BinaryNode<Integer> node, BinaryNode<Integer> prevChild) {
        if(node == null) {
            return;
        }
        
        /*
         * rely on node index starting from 0 at every level and denoted
         * by attribute otherValue.
         */
        if(node.left != null) {
            node.left.otherValue = 2 * node.otherValue;
            
            if(node.left.otherValue == 0) {
                prevChild = node.left;
            } else {
                prevChild.next = node.left;
                prevChild = prevChild.next;
            }
            
            recursive(node.left, prevChild);
            
            
        }
        
        if(node.right != null) {
            node.right.otherValue = 2 * node.otherValue + 1;
            
            if(prevChild == null) {
                prevChild = node.right;
            } else {
                prevChild.next = node.right;
                prevChild = prevChild.right;
            }
            
            recursive(node.right, prevChild);
        }
    }
    
    /**
     * From coworker Nilesh. It's great!
     * 
     * @param left
     * @param right
     */
    private void connect( BinaryNode<Integer> left, BinaryNode<Integer> right ) {
        if ( left == null || right == null ) {
            return;
        }
        
        left.next = right;
        
        // call sequence here is not important
        connect ( left.left, left.right );
        connect ( left.right, right.left );
        connect ( right.left, right.right );
    }
}
