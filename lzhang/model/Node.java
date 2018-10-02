package lzhang.model;

public class Node<T> {

    public T data;
    public Node<T> next;
    public Node<T> prev;
    
    /*
     * This value is NOT required by any classic Node structure. Application
     * can use this auxiliary value for a context specific purpose. For example,
     * application could use this one horizontal distance.
     */
    public int helperValue;
    
    /* 
     * If branch is set, this node serves only as a pointer to branch to
     * other node (or linked list), such as random pointer or multi-level
     * linked list.
     */
    public Node<T> branch;
    
    public Node(T v) {
        this.data = v;
    }
    
    public Node<T> append(Node<T> n) {
        this.next = n;
        
        if(n != null) {
            n.prev = this;
        }
        
        return n;
    }
    
    public Node<T> append(T data) {
        return append( new Node<T>(data) );
    }
    
    public void setBranch(Node<T> b) {
        this.branch = b;
    }
    
    @Override
    public boolean equals(Object o) {
    	if(o == null || !(o instanceof Node)) return false;
    	
    	Node<?> that = (Node<?>)o;
    	
    	return this.data.equals(that.data);
    }
    
    @Override
    public String toString() {
        return data.toString();
    }
    
    public void print() {
        Node<T> temp = this;
        
        System.out.print("Node<T>: ");
        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println("\n");
    }
    
    /**
     * Check if the linked list "head" contains a node referenced by pointer p
     * 
     * @param head
     * @param p
     * @return
     */
    public static <T> boolean contains(Node<T> head, Node<T> p) {
    	if(head == null || p == null) return false;
    	
    	while(head != null) {
    		if(head == p) return true;
    		
    		head = head.next;
    	}
    	
    	return false;
    }
    
    public static <T> int size(Node<T> head) {
    	int size = 0;
    	
    	while(head != null) {
    		size++;
    		
    		head = head.next;
    	}
    	
    	return size;
    }
}
