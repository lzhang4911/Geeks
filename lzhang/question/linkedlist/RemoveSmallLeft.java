package lzhang.question.linkedlist;

import lzhang.model.Node;

public class RemoveSmallLeft {

    public static Node<Integer> test() {
        Node<Integer> root = new Node<Integer>(12);
        root.append(15).append(10).append(11).append(5).append(6).append(2).append(3);
        
        removeSmallerLefty(root);
        return root;
    }
    
    /**
     * Delete any node whose next is greater.
     * 
     * Original: 12->15->10->11->5->6->2->3->NULL
     * Output: 15->11->6->3->NULL
     * 
     * @param n
     */
    private static void removeSmallerLefty(Node<Integer> n) {
        if(n == null || n.next == null) {
            return;
        }
        
        if(n.data < n.next.data) {
            // delete the current one that is smaller than its next
            n.data = n.next.data;
            n.next = n.next.next;
        } else {
            // no need to delete anything, move to the next one
            n = n.next;
        }
        
        removeSmallerLefty(n);
    }
}
