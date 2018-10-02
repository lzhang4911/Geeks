package lzhang.question.linkedlist;

import lzhang.model.Node;

/**
 * Given a singly linked list and an integer k, reverse the list in groups in
 * size k.
 * 
 * @author lzhang
 *
 */
public class ReverseInGroup {
    public static void test() {
        ReverseInGroup p = new ReverseInGroup();
        
        // create a list
        Node<Integer> curNode = null, head = null;
        for(int i = 0; i<10; i++) {
            if(curNode == null) {
                curNode = new Node<Integer>(i);
                
                head = curNode;
            } else {
                curNode.append( i );
                
                curNode = curNode.next;
            }
        }
        head.print();
        
        int k = 4;
        p.recursive(head, k).print();
    }
    
    /**
     * Always keep the "next" in the original list, reverse the segment each
     * time. As long as the "next" is not null, recur the call again to continue
     * from that point on.
     * 
     * @param head
     * @param k
     * @return
     */
    private Node<Integer> recursive(Node<Integer> head, int k) {
        Node<Integer> current = head;
        Node<Integer> prev = null;
        Node<Integer> next = null;
         
        int count = 0;
  
        /* Reverse first k nodes of linked list */
        while (count < k && current != null) {
            // remember the next node in original list
            next = current.next;
            
            // re-wire the current node pointing the prev to achieve reversing
            current.next = prev;
            
            // move forward for next run
            prev = current;
            current = next;
            count++;
        }
  
        /* next is now a pointer to (k+1)th node 
           Recursively call for the list starting from current.
           And make rest of the list as next of first node */
        if (next != null) {
           head.next = recursive(next, k);
        }
  
        // prev is now head of input list
        return prev;
     }
}
