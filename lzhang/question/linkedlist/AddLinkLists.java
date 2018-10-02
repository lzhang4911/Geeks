package lzhang.question.linkedlist;

import lzhang.model.Node;
import lzhang.util.BaseUtil;

public class AddLinkLists extends BaseUtil {
    public static void test() {
        AddLinkLists p = new AddLinkLists();
        
        Node<Integer> list1 = new Node<Integer>(9);
        list1.append(8).append(8).append(9);
        print(list1);
        
        Node<Integer> list2 = new Node<Integer>(1);
        list2.append(8).append(8).append(9);
        print(list2);
        
        Node<Integer> res = p.sum1(list1, list2);
        print(res);
        
        res = p.sum2(list1, list2);
        print(res);
    }
    
    /**
     * Move carries to the right. For example,
     * 
     * Input:
     * 1->3->7->4-6
     * 4->9->2->1
     * 
     * Output:
     * 5->2->0->6->6
     * 
     * @param list1
     * @param list2
     * @return
     */
    private Node<Integer> sum1(Node<Integer> list1, Node<Integer> list2) {
        Node<Integer> p1 = list1;
        Node<Integer> p2 = list2;
        
        Node<Integer> dummy = new Node<Integer>(-100);
        Node<Integer> p = dummy;
        
        int sub = 0, carry = 0;
        while(p1 != null || p2 != null) {
            sub = carry;
            
            if(p1 != null) {
                sub += p1.data;
                p1 = p1.next;
            }
            
            if(p2 != null) {
                sub += p2.data;
                p2 = p2.next;
            }
            
            carry = sub/10;
            sub %= 10;
            
            p.append(sub);
            
            p = p.next;
        }
        
        if(carry != 0) {
            p.append(carry);
        }
        
        return dummy.next;
    }
    
    /**
     * Make it like regular addition aligning from the right hand side:
     * 
     * Input:
     *    9->3->7->4->6
     *       9->9->2->1
     * 
     * Output:
     * 1->0->3->6->6->7
     * 
     * This can be done by reversing the lists first, and then use the previous
     * method to do the job.
     * 
     * @param list1
     * @param list2
     * @return
     */
    private Node<Integer> sum2(Node<Integer> list1, Node<Integer> list2) {
        Reverse util = new Reverse();
        
        Node<Integer> p1 = util.iterative(list1);
        Node<Integer> p2 = util.iterative(list2);
        print(p1);
        print(p2);
        
        Node<Integer> dummy = new Node<Integer>(-100);
        Node<Integer> p = dummy;
        
        int sub = 0, carry = 0;
        while(p1 != null || p2 != null) {
            sub = carry;
            
            if(p1 != null) {
                sub += p1.data;
                p1 = p1.next;
            }
            
            if(p2 != null) {
                sub += p2.data;
                p2 = p2.next;
            }
            
            carry = sub/10;
            sub %= 10;
            
            p.append(sub);
            
            p = p.next;
        }
        
        if(carry != 0) {
            p.append(carry);
        }
        
        return util.iterative(dummy.next);
    }
}
