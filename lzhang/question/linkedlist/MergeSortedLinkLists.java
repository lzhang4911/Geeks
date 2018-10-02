package lzhang.question.linkedlist;

import lzhang.model.Node;

/**
 * Merge 2 sorted linked lists
 * 
 * 1 3 5 7
 * 2 4 6 8 9
 * 
 * 1 2 3 4 5 6 7 8 9
 * 
 * @author lzhang
 *
 */
public class MergeSortedLinkLists {
    public static Node<Integer> test() {
        // create 2 sorted linked lists
        Node<Integer> n1 = new Node<Integer>(1);
        n1.append(3).append(5).append(7);
        
        Node<Integer> n2 = new Node<Integer>(2);
        n2.append(4).append(6).append(8).append(9);
        
        return merge(n1, n2);
    }

    public static Node<Integer> merge(Node<Integer> n1, Node<Integer> n2) {
        //return iterate(n1, n2);
        
        // working!
//        Node<Integer> merged = iterateB(n1, n2);
//        merged.print();
        
        return recurse(n1, n2);
    }
    
    /**
     * In-place merge
     * 
     * @param n1
     * @param n2
     * @return
     */
    public static Node<Integer> iterate(Node<Integer> n1, Node<Integer> n2) {
        if(n1 == null) return n2;
        if(n2 == null) return n1;
        
        // choose the smaller one as the merged head
        Node<Integer> head = n1;
        if(n1.data > n2.data) {
            head = n2;
        }
        
        /*
         * Re-wire the nodes between 2 lists to form the resultant list in place
         */
        while(true) {
            if(n1.data < n2.data) {
                while(n1 != null) {
                    if(n1.next == null) {
                        // this is the end of n1. bridge to n2
                        n1.next = n2;
                        n1 = null;
                        break;
                    } else if(n1.next.data > n2.data) {
                        // need to re-wire
                        Node<Integer> p = n1;
                        
                        // remember p.next and point n1.next to n2
                        if(p != null) {
                            n1 = p.next;
                            p.next = n2;
                        }
                        
                        break;
                    }
                    
                    n1 = n1.next;
                }
            } else {
                while(n2 != null) {
                    if(n2.next == null) {
                        // this is the end of n2. bridge to n1
                        n2.next = n1;
                        n2 = null;
                        break;
                    } else if(n2.next.data > n1.data) {
                        Node<Integer> p = n2;
                        
                        if(p != null) {
                            n2 = p.next;
                            p.next = n1;
                        }
                        
                        break;
                    }
                    
                    n2 = n2.next;
                }
            }
            
            if(n1 == null || n2 == null) {
                break;
            }
        }
        
        return head;
    }
    
    /**
     * A better in-place iterative approach is to process just one node in each
     * loop step, very much like the recursive idea.
     *  
     * @param n1
     * @param n2
     * @return
     */
    public static Node<Integer> iterateB(Node<Integer> n1, Node<Integer> n2) {
        if(n1 == null) return n2;
        if(n2 == null) return n1;
        
        System.out.println("Merging 2 sorted LinkedList in iterative way");
        
        // choose the smaller one as the merged head
        Node<Integer> dummy = new Node<>(-111);
        
        if(n1.data > n2.data) {
            dummy.append(n2);
            n2 = n2.next;
        } else {
            dummy.append(n1);
            n1 = n1.next;
        }
        
        // let's save the head pointer
        Node<Integer> head = dummy.next;
        dummy = head;
        
        /*
         * Re-wire the nodes between 2 lists to form the resultant list in place
         */
        while(n1 != null && n2 != null) {
            if(n1.data > n2.data) {
                dummy.append(n2);
                n2 = n2.next;
            } else {
                dummy.append(n1);
                n1 = n1.next;
            }
            
            dummy = dummy.next;
        }
        
        /*
         * When the while-loop finishes, it's possible that one node is NOT null
         * if the 2 original linked lists are not equally i nlength.
         */
        if(n1 != null) dummy.append(n1);
        if(n2 != null) dummy.append(n2);
        
        return head;
    }
    
    public static Node<Integer> recurse(Node<Integer> n1, Node<Integer> n2) {
        if(n1 == null) return n2;
        if(n2 == null) return n1;
        
        if(n1.data < n2.data) {
            n1.next = recurse(n1.next, n2);
            return n1;
        } else {
            n2.next = recurse(n1, n2.next);
            return n2;
        }
    }
}
