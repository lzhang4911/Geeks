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
}
