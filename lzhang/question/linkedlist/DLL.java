package lzhang.question.linkedlist;

import lzhang.model.Node;

/**
 * Create a Doubly-Linked List. Supports 4 basic features:
 * - push()
 * - pop();
 * - remove();
 * - size()
 * 
 * Note that this class is not thread safe!
 * 
 * @author lzhang
 *
 */
public class DLL<T> {
    // maintain the head and tail
    private Node<T> head, tail;
    
    // maintain the internal node count
    private int count = 0;
    
    public DLL() {
    }
    
    
    /**
     * Add the node to the tail and become the new tail
     * @param n
     */
    public void push(Node<T> n) {
        if(tail == null) {
            // this must be the first node
            tail = n;
            
            head = tail;
        } else {
            tail.append(n);
            
            tail = n;
            tail.next = null;
        }
        
        count++;
    }
    
    /**
     * Add the node in front of existing head and become a the head
     * @param n
     */
//    public void addHead(Node<T> n) {
//        if(head == null) {
//            head = n;
//            
//            tail = head;
//        } else {
//            n.append(head);
//            
//            head = n;
//            head.prev = null;
//        }
//        
//        count++;
//    }
    
    // Pop the current head off and return it
    public Node<T> pop() {
        if(head == null) {
            return head;
        } else {
            Node<T> temp = head;
            
            // point the head to the next node
            head = temp.next;
            
            if(head == null) {
                // no more node left
                tail = null;
            } else {
                // prepare head with null prev
                head.prev = null;
            }
            
            count--;
            
            return temp;
        }
    }
    
    // remove and return the tail
//    public Node<T> pullTail() {
//        if(tail == null) {
//            return tail;
//        } else {
//            Node<T> temp = tail;
//            
//            tail = temp.prev;
//            
//            if(tail != null) {
//                tail.next = null;
//            } else {
//                head = null;
//            }
//            
//            count--;
//            
//            return temp;
//        }
//    }
    
    /**
     * The Node n is one of the nodes in this DDL
     * @param n
     * @return
     */
    public void remove(Node<T> n) {
        if(n.prev == null) {
            // n is the head to be removed
            this.pop();
        } else {
            // re-wire to exclude the current node
            n.prev.append(n.next);
        }
        
        count--;
    }
    
    public int size() {
        return count;
    }
}
