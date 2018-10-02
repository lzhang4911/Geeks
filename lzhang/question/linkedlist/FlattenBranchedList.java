package lzhang.question.linkedlist;

import lzhang.model.Node;
import lzhang.util.BaseUtil;

/**
 * (1) -> (2) -> (-1) -> (100)
 *                 |
 *                (3) -> (4) -> (5) -> (-2) -> (200)
 *                                      |
 *                                     (6) -> (7) -> (8)
 *                                     
 * Flatten this branched linked list into single-level LL:
 * (1) - (2) - (3) - (4) - (5) - (6) - (7) - (8) - (200) - (100)
 * 
 * @author lzhang
 *
 */

public class FlattenBranchedList extends BaseUtil {

    public static Node<Integer> test() {
        Node<Integer> head = new Node<Integer>(1);
        
        Node<Integer>  p = head.append(2).append(-1);
        p.append(100);
        p.branch = new Node<Integer>(3);
        p.branch.prev = p; // doubly linked
        
        p = p.branch.append(4).append(5).append(-2);
        p.append(200);
        p.branch = new Node<Integer>(6);
        p.branch.prev = p;
        
        p.branch.append(7).append(8);
        
        flatten(head);
        
        print(head);
        
        return head;
    }
    
    /**
     * Node<Integer> n starts a linked list with multiple level of branches. Flatten
     * this list in place.
     * 
     * @param n
     * @return last node
     */
    private static Node<Integer> flatten(Node<Integer> n) {
        if(n == null || (n.next == null && n.branch == null)) {
            return n;
        }
        
        if(n.branch != null) {
            // save the next node of n first
            Node<Integer> temp = n.next;
            
            if(n.prev == null) {
                // overwrite current pointer node
                n.data = n.branch.data;
                n.append(n.branch.next);
            } else {
                // bypass current node
                n.prev.append(n.branch);
            }
            
            // flatten this branch in case there are further branches
            Node<Integer> p = flatten(n.branch);
            
            // append the original tail
            return p.append(temp);
        } else {
            return flatten(n.next);
        }
    }
}
