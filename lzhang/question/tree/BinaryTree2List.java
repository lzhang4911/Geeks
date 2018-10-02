package lzhang.question.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import lzhang.model.BinaryNode;
import lzhang.util.BaseUtil;

public class BinaryTree2List extends BaseUtil {
    
    public static List<Integer> test(BinaryNode<Integer> root) {
        return inorderList(root);
    }
    
    public static LinkedList<BinaryNode<Integer>> test2(BinaryNode<Integer> root) {
        BinaryNode.levelOrderPrint_simpler(root);
                
        
//        BinaryNode<Integer> head = inorderLinkedList(root);
//        return head;
        
        LinkedList<BinaryNode<Integer>> list = inorderLinkedList2(root);
        return list;
    }

    /**
     * Convert a BT into an inorder list (of values):
     * Left most leaf as the head and right most the tail by traversing
     * the binary tree using in-order.
     * 
     * @param n
     * @return
     */
    static List<Integer> inorderList(BinaryNode<Integer> n) {
        List<Integer> result = new ArrayList<>();
        
        if(n == null) {
            return result;
        }
        
        // left first
        result.addAll( inorderList(n.left) );
        
        result.add(n.value);
        
        result.addAll( inorderList(n.right) );
        
        return result;
    }
    
    static List<Integer> preorderList(BinaryNode<Integer> n) {
        List<Integer> result = new ArrayList<>();
        
        if(n == null) {
            return result;
        }
        
        // visit current node first
        result.add(n.value);
        
        // then left
        result.addAll( inorderList(n.left) );
        
        // and last right
        result.addAll( inorderList(n.right) );
        
        return result;
    }
    
    static List<Integer> postorderList(BinaryNode<Integer> n) {
        List<Integer> result = new ArrayList<>();
        
        if(n == null) {
            return result;
        }
        
        // first left
        result.addAll( inorderList(n.left) );
        
        // then right
        result.addAll( inorderList(n.right) );
        
        // last current node
        result.add(n.value);
        
        return result;
    }
    
    /**
     * 
     * Convert a binary tree into an in-order singly LinkedList.
     * In-place conversion.
     * 
     * Note that you cold use BinaryNode with its right as the point next.
     * 
     * Convert tree into List<Node> is easy. But how can we do in-place
     * conversion without using a List, O(n) space?
     *  
     * @param n
     * @return
     */
    static List<BinaryNode<Integer>> inorderNodeList(BinaryNode<Integer> n) {
        List<BinaryNode<Integer>> result = new ArrayList<>();
        
        if(n == null) {
            return result;
        }
        
        // left first
        result.addAll( inorderNodeList(n.left) );
        
        result.add(n);
        
        result.addAll( inorderNodeList(n.right) );
        
        return result;
    }
    
    /**
     * Not very intuitive!
     * 
     * First push to the stack the root and all left most nodes from the root.
     * The start entering the while-loop to examine the stack. For every one that
     * is popped from the stack, we will first check if its right node is available.
     * If it is, push to the stack that node and all of the left most ones from it.
     * 
     * Basically, we only push the right node one at a time for the one that we just
     * popped from the stack.
     * 
     * @param n
     * @return
     */
    static BinaryNode<Integer> inorderLinkedList(BinaryNode<Integer> node) {
        if(node == null) {
            return node;
        }
        
        Stack<BinaryNode<Integer>> stack = new Stack<>();
        
        // DFS: first push the root and all possible left nodes to stack
        BinaryNode<Integer> temp = node;
        while(temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
        
        // introduce a dummy node to hold the list
        BinaryNode<Integer> dummy = new BinaryNode<Integer>(-1000);
        BinaryNode<Integer> p = dummy;
        
        while(!stack.isEmpty()) {
            temp = stack.pop();
            
            p.right = temp;
            p = p.right;
            
            if (temp.right != null) {
                temp = temp.right;
                 
                // the next node to be visited is the leftmost
                while (temp != null) {
                    // push this (right) node and all possible lefties just like the beginning!
                    stack.push(temp);
                    temp = temp.left;
                }
            }
        }
        
        return dummy.right;
    }
    
    /**
     * Using recursion is much simpler:
     * 1. instantiate the result to return;
     * 2. call the recursion for left node first to add its return to the result;
     * 3. process the current node by adding it to the result;
     * 4. call the recursion for the right node and add its return to the result;
     * 5. return the result.
     * 
     * Similar processing for preorder and postorder as well.
     * 
     * @param root
     * @return
     */
    static LinkedList<BinaryNode<Integer>> inorderLinkedList2(BinaryNode<Integer> root) {
        LinkedList<BinaryNode<Integer>> list = new LinkedList<>();
        
        if(root == null) {
            return null;
        }
        
        // left first
        LinkedList<BinaryNode<Integer>> temp = inorderLinkedList2(root.left);
        if(temp != null) {
            list.addAll( temp );
        }

        // middle
        list.add(root);
        
        // at last on right node
        temp = inorderLinkedList2(root.right);
        if(temp != null) {
            list.addAll( temp );
        }
        
        return list;
    }
}
