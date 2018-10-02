package lzhang.question.linkedlist;

import lzhang.model.Node;

/**
 * Create a SLL, and then try to delete head, tail, or any other node.
 * If both head and the node to be deleted are both provided, you can
 * always delete that node regardless its position.
 * 
 * However, if head is not given, you cannot delete the tail which requires
 * knowing its previous node to do so.
 * 
 * Note that once a node is deleted from the list, an external reference to
 * the next node may not point to that node any more. 
 * 
 * For example, n1 -> n2. If n1 is "deleted" from the list, what actually happens 
 * is that we update n1's value with n2's and set n1.next to n2.next. In other words,
 * deleting n1 doesn't delete that pointer from the list, instead, pointer to n2
 * is actually removed!
 * 
 * @author lzhang
 *
 */
public class DeleteNode {

    public static void test() {
        Node<Integer> head = new Node<>(0);
        Node<Integer> p0 = head;
        
        Node<Integer> p1 = new Node<>(10);
        Node<Integer> p2 = new Node<>(20);
        
        // create a list as 0 -> 10 -> 20 -> 30
        head.append(p1).append(p2).append(30);
        head.print();
        assert Node.size(head) == 4;
        
        
        DeleteNode p = new DeleteNode();
        
        // deleting p1 actually delete p2 from the list
        p.delete(p1);
        head.print();
        assert Node.size(head) == 3;
        
        // expecting p1 still exists but not p2 any more
        assert Node.contains(head, p1);
        assert !Node.contains(head, p2);
        
        // deleting p2 by reference shall have no effect 
        head = p.delete(head, p2);
        head.print();
        assert Node.size(head) == 3;
        
        // but we still can delete it by value
        p.delete(head, 20);
        head.print();
        assert Node.size(head) == 2;
        
        // pointer p0 is not affected
        head = p.delete(head, p0);
        head.print();
        assert Node.size(head) == 1;
        
        // deleting the only node (30) shall fail
        head = p.delete(head, 30);
        assert head == null;
    }
    
    /**
     * Given the head of a Linked List with unique node values, delete the Node at specified value.
     * 
     * Return true of if the node is; otherwise false.
     *   
     * @param head
     * @param nodeValue
     */
    private Node<Integer> delete(Node<Integer> head, int nodeValue) {
        Node<Integer> p = head;
        
        while(p != null) {
            if(p.data == nodeValue) {
                // found the node
                return this.delete(head, p);
            }
            
            p = p.next;
        }
        
        return null;
    }

    private Node<Integer> delete(Node<Integer> head, Node<Integer> p) {
        if(head == null || p == null) {
            // nothing to do
            return head;
        }
        
        System.out.println("Deleting node w/ head: " + p.data);
        boolean deleteHead = p.equals(head);
        
        if(p.next == null) {
        	if(deleteHead) {
        		/*
        		 * The list only has one node that is to be deleted. We cannot really
        		 * delete this node, but return null as the new head.
        		 */
        		System.out.println("Cannot delete the only node: " + p.data);
        		return null;
        	}
        	
            /*
             * This is the tail node. Though this is a singly linked list, since
             * the head is provided we can try to find its previous node. If found,
             * we easily get rid of this node p from the list.
             */
            Node<Integer> temp = head;
            while(temp != null) {
                if(p.equals(temp.next)) {
                    // temp is the second from last. simply set its next to null.
                    temp.next = null;
                    System.out.print("\tRemoved the tail node\n");
                    
                    // head doesn't changed
                    return head;
                } else {
                    temp = temp.next;
                }
            }
            
            // this should never happen!
            System.out.println("\tCannot remove this node. There is no other node before this one - " + p.data);
            return null;
        } else {
            /*
             * No matter whether this is the head or any mid node, we don't need to find its previous node
             * to do the bridge. Instead, we can simply move the next node to overwrite the current one.
             */
        	System.out.print("\tRemoved node " + p.data + "\n");
        	
            p.data = p.next.data;
            p.next = p.next.next;
            
            return (deleteHead? p : head);
        }
    }
    
    /**
     * If head is NOT given, there is no way to delete the tail node! Note that
     * external references to nodes in the linked list should NOT be used to manipulate
     * the list any more.
     * @param p
     */
    private void delete(Node<Integer> p) {
    	if(p == null) return;
    	System.out.println("Deleting node w/o head " + p.data);
    	
    	if(p.next == null) {
    		/*
    		 * The node to be deleted is the tail and there is no way to find out
    		 * its previous node. 
    		 */
    		System.out.print("\tCannot remove the tail node!\n");
    	} else {
    		System.out.print("\tRemoved node " + p.data + "\n");

    		p.data = p.next.data;
            p.next = p.next.next;
    	}
    }
}
