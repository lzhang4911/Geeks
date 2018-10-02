package lzhang.question;

import java.util.HashMap;
import java.util.Map;

import lzhang.model.Node;

public class LRU {
    /*
     * Always insert into the front (to become the new head).
     * And always purge from the tail. 
     */
    private Node<Integer> head, tail;
    private Map<String, Node<Integer>> map = new HashMap<String, Node<Integer>>();
    
    // cache size limit
    private int size;
    
    public LRU(int size) {
        this.size = size;
    }
    
    /**
     * O(1) lookup
     * @param key
     * @return
     */
    public int get(String key) {
        Node<Integer> n = map.get(key);
        
        if(n != null) {
            // update linked list
            deleteNode(n);
            addHead(n);
            
            return n.data;
        }
        
        return -1;
    }
    
    public void put(String key, int v) {
        if(map.size() >= size) {
            // purge the tail
            purge();
        }
        
        Node<Integer> n = map.get(key);
        
        if(n != null) {
            // update the cached value
            n.data = v;
            
            // update linked list
            deleteNode(n);
        } else {
            n = new Node<Integer>(v);
            map.put(key, n);
        }
        
        addHead(n);
    }
    
    private void deleteNode(Node<Integer> n) {
        if(n.prev != null) {
            // 1. Node n is NOT head. By-pass this node.
            n.prev.append(n.next);
            
            // in case n was the tail
            if(n.next == null) {
                tail = n.prev;
            }
        } else {
            // 2. the to-be-deleted node is the head
            head = n.next;
            
            if(head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        }
    }
    
    /**
     * Always prepend the new Node as new head 
     * @param n
     */
    private void addHead(Node<Integer> n) {
        // prepend
        n.append(head);
        n.prev = null;
        
        if(head != null) {
            head.prev = n;
        }
        
        // let head point to the new head
        head = n;
        
        if(tail == null) {
            tail = head;
        }
    }
    
    private void purge() {
        if(tail == null) {
            // do nothing
            return;
        }
        
        // delete the object from map first - use data as key as well
        map.remove(tail.data);
        
        this.deleteNode(tail);
    }
}
