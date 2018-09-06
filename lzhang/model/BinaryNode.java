package lzhang.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryNode<T> {
	public T value;
	public BinaryNode<T> left;
	public BinaryNode<T> right;
	
	// use this as doubly linked list as well
	public BinaryNode<T> next, prev;
	
	// other value such as horizontal distance
	public int otherValue;
	
	public BinaryNode(T value) {
		this.value = value;
	}
	
	/**
	 * Show the inorder nodes of the entire subtree
	 */
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder("(" + value + ") ");
	    Stack<BinaryNode<T>> s = new Stack<>();
        
        // DFS: first push the root and all possible left nodes to stack
        BinaryNode<T> node = this;
        pushThisAndAllLefties(node, s);
        
        while(!s.isEmpty()) {
            node = s.pop();

            // process
            sb.append(node.value + " ");
            
            // does it have any right?
            if(node.right != null) {
                node = node.right;
                
                // push this right node and all its lefties just like what we did for root at the beginning
                pushThisAndAllLefties(node, s);
            }
        }
        
		return sb.toString();
	}
	
	private static <T> void pushThisAndAllLefties(BinaryNode<T> node, Stack<BinaryNode<T>> s) {
	    while(node != null) {
            s.push(node);
            node = node.left;
        }
	}
	
	public static <T> void inorderPrint(BinaryNode<T> root) {
        if(root == null) return;
        
        System.out.println("Inorder array: ");
        Stack<BinaryNode<T>> s = new Stack<>();
        
        // DFS: first push the root and all possible left nodes to stack
        BinaryNode<T> node = root;
        pushThisAndAllLefties(node, s);
        
        while(!s.isEmpty()) {
            node = s.pop();

            // process
            System.out.print(node.value + " ");
            
            // does it have any right?
            if(node.right != null) {
                node = node.right;
                
                // push this right node and all its lefties just like what we did for root at the beginning
                pushThisAndAllLefties(node, s);
            }
        }
        
        System.out.println("");
	}
	
	/**
	 * Use recursion looks much simpler!
	 * 
	 * @param root
	 */
	public static <T> void inorderPrintRecur(BinaryNode<T> root) {
        if(root == null) return;
        
        // left first
        inorderPrintRecur(root.left);
        
        System.out.print(root.value + " ");
        
        inorderPrintRecur(root.right);
	}
	
	public static <T> void levelOrderPrint(BinaryNode<T> root) {
	    if(root == null) return;
	    
	    Queue<BinaryNode<T>> q = new LinkedList<>();
	    
	    q.add(root);
	    
	    // Important: add the line terminator
	    q.add(null);
	    
	    // go for the full processing
	    System.out.println("== Begin printing the tree");
	    while(!q.isEmpty()) {
	        // processing this node first
	        BinaryNode<T> n = q.poll();
	        if(n == null) {
	            // is there any other node to process?
	            if(q.peek() == null) {
	                // we are done with the tree
	                break;
	            }
	            
	            System.out.println("");
	            q.add(null);
	            continue;
	        } else {
	            System.out.print(n.value + " ");
	        }
	        
	        // queue its children if any
	        if(n.left != null) {
	            q.add(n.left);
	        }
	        
	        if(n.right != null) {
                q.add(n.right);
            }
	    }
	    
	    System.out.println("\n== Done printing the tree");
	}
	
	public static <T> void levelOrderPrint_simpler(BinaryNode<T> root) {
        if(root == null) return;
        
        Queue<BinaryNode<T>> q = new LinkedList<>();
        
        q.add(root);
        
        // go for the full processing
        System.out.println("== Begin printing the tree");
        BinaryNode<T> n;
        while(!q.isEmpty()) {
            int level_size = q.size();
            
            while(level_size >= 0) {
                n = q.poll();
                System.out.print(n.value + " ");
                
                if(n.left != null) {
                    q.add(n.left);
                }
                
                if(n.right != null) {
                    q.add(n.right);
                }
                
                level_size--;
            }
        }
        
        System.out.println("\n== Done printing the tree");
    }
	
	public static <T> void preorderPrintRecur(BinaryNode<T> root) {
        if(root == null) return;
        
        // first myself
        System.out.print(root.value + " ");
        
        // then left
        preorderPrintRecur(root.left);
        
        // right at last
        preorderPrintRecur(root.right);
    }
}
