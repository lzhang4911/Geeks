package lzhang.question.linkedlist;

import lzhang.model.Node;
import lzhang.util.BaseUtil;

/**
 * Reverse a Singly linked list
 * @author lzhang
 *
 */
public class Reverse extends BaseUtil {
    /**
     * Since this is singly linked, we can only walk through the list from head
     * to tail. For each node we visit, we can swap with its next if the next is not null.
     * Either recursion or iterative should work.
     * 
     * @param head
     * @return
     */
    public static Node<Integer> test(Node<Integer> head) {
        Reverse p = new Reverse();
        p.printLinkedList(head);
        
        Node<Integer> result = null;
        
        // iterative() works
//        result = p.iterative(head);
        
        // working
//        result = p.recursive(head, null);
        
        // working
        result = p.tailRecursive(head, null);
        
        return result;
    }
    
    
    /**
     * This is a working implementation
     * @param root
     * @return
     */
    public Node<Integer> iterative(Node<Integer> root) {
        if(root == null) {
            return root;
        }
        
        // current node
        Node<Integer> current = root;
        
        // the next node that the "current" is pointing to
        Node<Integer> next = null, prev = null;
        
        while(current != null) {
            // remember the node that the current second points to
            next = current.next;
            
            // re-point the current second to the previous node, the reversal
            current.next = prev;
            
            prev = current;
            
            // advance both current and next for the next iteration 
            current = next;
        }
        
        return prev;
    }
    

    /**
     * Both "current" and "next" are terms referring the original list.
     * The pointer "next" still contains the rest of the original list elements.
     * 
     * Caller starts by calling recursive(null, head).
     * 
     * @param current
     * @param next
     * @return
     */
    private Node<Integer> recursive(Node<Integer> cur, Node<Integer> prev) {
        if(cur == null) {
            return prev;
        }
        
        // remember the next.next before swapping (current-next) 
        Node<Integer> next = cur.next;
        
        // reverse the link
        cur.next = prev;

        // prepare for the next recursion
        prev = cur;
        cur = next;
        
        if(prev != null) {
            // not required for a real singly LL
            prev.prev = null;
        }
        
        return recursive(cur, prev);
    }
    
    /**
     * This is a very simple implementation!
     * (Same as previous one!)
     *  
     * Caller starts calling tailRecursive(root, null);
     * then remember root.next as temp, set root.next = null (prev);
     * then recur tailRecursive(temp, root)...
     * 
     * @param current
     * @param prev
     * @return
     */
    private Node<Integer> tailRecursive(Node<Integer> current, Node<Integer> prev) {
        if(current == null) {
            return prev;
        }
        
        // save as next current
        Node<Integer> next = current.next;
        
        // reverse the link
        current.next = prev;
        
        return tailRecursive(next, current);
    }
    
    /**
     * How to print out a linked list in reverse order without
     * reverse the list? Easy!
     * 
     * @param node
     */
    private void printLinkedList(Node<Integer> node) {
        if(node == null) return;
        
        // print in current order
        print("LinkedLink forward printing: " + node.data);
        
        printLinkedList(node.next);
        
        // to print the list in reverse order
        print("LinkedLink reverse printing: " + node.data);
    }
}
