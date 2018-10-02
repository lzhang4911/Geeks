package lzhang.question.linkedlist;

import lzhang.model.Node;
import lzhang.util.BaseUtil;

/**
 * https://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
 * Method 3
 * 
 * Given a singly linked list from a1 to a9, due to some mistake,
 * a9 points to a3 and therefore forms a circular list. How to fixed
 * (setting a9.next = null)?
 * 
 * @author lzhang
 *
 */
public class CircularLinkBreak extends BaseUtil {

    public static void test() {
        Node<Integer> head = new Node<Integer>(1);
        Node<Integer> a3 = head.append(2).append(3).append(13).append(23);
//        Node<Integer> a9 = a3.append(4).append(5).append(6).append(7).append(8).append(9);
//        Node<Integer> a9 = a3.append(4);
        Node<Integer> a9 = a3.append(4).append(5).append(6).append(7).append(8).append(9).append(10).append(11);
        a9.next = a3;
        
        CircularLinkBreak p = new CircularLinkBreak();
        p.detectAndFix(head);
    }
    
    private void detectAndFix(Node<Integer> head) {
        if(head == null) {
            return;
        }
        
        // set up 2 pointers: slow and fast
        Node<Integer> slow = head;
        Node<Integer> fast = head;
        
        while(fast != null) {
            // move slow 1 step a time
            slow = slow.next;
            
            // move fast 2 steps a time
            if(fast.next != null) {
                fast = fast.next.next;
            }
            
            if(slow == fast) {
                print("Cycle detected! Fixing the circular reference...");
                
                fixCircularReference(head, slow);
                
                print(head);
                return;
            }
        }
        
        print("No circle detected");
    }
    
    /**
     * Advance the 2 pointer at the same pace - 1 at a step. They will meet at
     * the starting point of the cycle. The one right before that node will be
     * the tail and should be fixed.
     * 
     * @param headNode
     * @param meetNode
     */
    private void fixCircularReference(Node<Integer> headNode, Node<Integer> meetNode) {
       while(true) {
           if(headNode.next == meetNode.next) {
               // found the tail
               meetNode.next = null;
               break;
           } else {
               headNode = headNode.next;
               meetNode = meetNode.next;
           }
       }
    }
}
