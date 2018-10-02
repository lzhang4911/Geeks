package lzhang.question;

import java.util.HashMap;
import java.util.Map;

import lzhang.model.Node;
import lzhang.question.linkedlist.DLL;

/**
 * As a cache utility, the only 2 methods are get() and put().
 *
 * With DDL, every operation is in O(n)
 * 
 * @author lzhang
 *
 */
public class LRU2 {
    /*
     * Always append to the end and remove from the head which is the least recently used one
     */
    private DLL<Integer> recentlyUsedQueue;
    
    // for fast lookup
    private Map<Integer, Node<Integer>> map;
    
    // cache size limit
    private int size;
    
    public LRU2(int size) {
        this.size = size;
        
        this.recentlyUsedQueue = new DLL<Integer>();
        this.map = new HashMap<Integer, Node<Integer>>();
    }
    
    /**
     * O(1) lookup
     * @param key
     * @return
     */
    public int get(int key) {
        Node<Integer> n = map.get(key);
        
        if(n != null) {
            // move this node to tail of the linked list
            recentlyUsedQueue.remove(n);
            
            recentlyUsedQueue.push(n);
            
            return n.data;
        }
        
        return -1;
    }
    
    public void put(Integer key, int v) {
        if(map.size() >= size) {
            // purge the oldest one
            Node<Integer> n = recentlyUsedQueue.pop();
            
            map.remove(n.helperValue);
        }
        
        Node<Integer> n = map.get(key);
        if(n != null) {
            // update the cached value
            n.data = v;
            
            recentlyUsedQueue.remove(n);
        } else {
            n = new Node<Integer>(v);
            n.helperValue = key; // remember the map key
            map.put(key, n);
        }
        
        recentlyUsedQueue.push(n);
    }
    
    public static void test() {
        LRU2 p = new LRU2(2);
        
        int key = 1;
        int value = p.get(key);
        print( String.format("Expect not finding value key %d: %d", key, value) );
        

        for(int i = 1; i < 15; i++) {
            key = 100 + i;
            p.put(key, 100 + i);
        }
        
        for(int i = 1; i < 15; i++) {
            key = 100 + i;
            value = p.get(key);
            print( String.format("Cached value at key %s: %d", key, value) );
        }
        
        print("Cache size: " + p.map.size());
    }
    
    static void print(String s) {
        System.out.println(s);
    }
}
