package lzhang.question.linkedlist;

import lzhang.model.Node;

/**
 * Given a linked list [A -> B -> C -> D -> E], after pair wise swapping, the
 * result becomes      [B -> A -> D -> C -> E]
 * 
 * @author lzhang
 *
 */
public class PairSwap {

    public static Node<Integer> test() {
        // create a linked list
        Node<Integer> root = new Node<>(1);
        root.append(2).append(3).append(4).append(5).append(6).append(7);
        
        PairSwap p = new PairSwap();
        Node<Integer> result = p.recursive2(root);
        
        return result;
    }
    
    /**
     * Swap the current and the next in value first. Call recursion for the rest.
     * @param in
     * @return
     */
    private Node<Integer> recursive(Node<Integer> in) {
        if(in == null || in.next == null) {
            return in;
        }
        
        int temp = in.next.data;
        
        in.next.data = in.data;
        in.data = temp;
        
        in.next.append(recursive(in.next.next));
        
        return in;
    }
    
    /**
     * Let's try to swap the pointer directly
     * @param in
     * @return
     */
    private Node<Integer> recursive2(Node<Integer> in) {
        if(in == null || in.next == null) {
            return in;
        }
        
        Node<Integer> temp = in;
        Node<Integer> rest = in.next.next;
        
        in = in.next;
        in.append(temp);
        
        in.next.append(recursive(rest));
        
        return in;
    }
}
