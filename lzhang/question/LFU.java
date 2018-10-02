package lzhang.question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import lzhang.model.Node;
import lzhang.question.linkedlist.DLL;

/**
 * Least-frequently used:
 * 
 * This requires to maintain a count for every every cached object.
 * When the cache is full, the one that has least count will get evicted.
 * 
 * Note that it's possible to have multiple objects with the same reference
 * count, which means we need a TreeMap of
 * 
 * (referenceCount, Node<CacheObject>)
 * 
 * in addition to the regular look-up table
 * (objectId, cacheObject)
 * 
 * @author lzhang
 *
 */
public class LFU {
    static class CacheObject {
        public int refCount;
        public int id;
        public String message;
        
        public CacheObject(int id, String message) {
            this.id = id;
            this.message = message;
        }
    }
    
    
    // cache capacity
    private int capacity = 10;
    
    // fast look-up map - (id, Node)
    Map<Integer, Node<CacheObject>> lookupMap;
    
    // reference count management map - (refCount, head)
    Map<Integer, DLL<CacheObject>> frequencyMap;
    
    
    public LFU(int capacity) {
        this.capacity = Math.max(capacity, this.capacity);
        lookupMap = new HashMap<Integer, Node<CacheObject>>(capacity);
        frequencyMap = new TreeMap<Integer, DLL<CacheObject>>();
    }
    
    /**
     * Use double-lock
     * @return
     */
//    private static LFU inst;
//    public static LFU getInstance() {
//        if(inst == null) {
//            synchronized(LFU.class) {
//                if(inst == null) {
//                    inst = new LFU();
//                }
//            }
//        }
//        
//        return inst;
//    }
    
    /**
     * API 1: get
     */
    public CacheObject get(int id) {
        Node<CacheObject> o = lookupMap.get(id);
        if(o != null) {
            // update reference count
            updateFrequency(o);
            
            return o.data;
        } else {
            return null;
        }
    }
    
    /**
     * API 2: put
     */
    public void put(int id, CacheObject obj) {
        if(lookupMap.size() >= capacity) {
            // The cache is full already and we need to remove LFU
            removeLRU();
        }
        
        Node<CacheObject> o = lookupMap.get(id);
        if(o != null) {
            // The object is already in cache. Let's update it first.
            o.data = obj;
            
            // update reference count
            updateFrequency(o);
        } else {
            // keep the new object in cache
            o = new Node<CacheObject>(obj);
            lookupMap.put(id, o);
            
            addToFrequencyMap(o);
        }
    }
    
    
    // helpers
    
    /**
     * Add this new Node object to cache
     * @param o
     */
    private void addToFrequencyMap(Node<CacheObject> o) {
        if(o == null) {
            return;
        }
        
        // set its reference count to 1 first
        o.data.refCount = 1;
        DLL<CacheObject> ll = frequencyMap.get(o.data.refCount);
        if(ll == null) {
            ll = new DLL<CacheObject>();
            frequencyMap.put(o.data.refCount, ll);
        }
        
        ll.push(o);
    }
    
    private void updateFrequency(Node<CacheObject> obj) {
        // get the Linked List where the object currently resides
        DLL<CacheObject> ll = frequencyMap.get(obj.data.refCount);
        if(ll == null) {
            // this should not happen
            return;
        }
        
        // remove this object from that linked list
        if(ll.size() == 0) {
            frequencyMap.remove(obj.data.refCount);
        } else {
            ll.remove(obj);
        }
        
        // increment count
        obj.data.refCount = 1 + obj.data.refCount;
        
        // get the linked list for the new ref count
        ll = frequencyMap.get(obj.data.refCount);
        if(ll == null) {
            ll = new DLL<CacheObject>();
            frequencyMap.put(obj.data.refCount, ll);
        }
        
        ll.push(obj);
    }
    
    /**
     * Remove the tail object from the DLL at the lowerst reference count
     */
    private void removeLRU() {
        if(frequencyMap.isEmpty()) {
            return;
        }
        
        Iterator<Integer> i = frequencyMap.keySet().iterator();
        if(i.hasNext()) {
            int lowestRefCount = i.next();
            
            DLL<CacheObject> ll = frequencyMap.get(lowestRefCount);
            Node<CacheObject> o = ll.pop();
            
            // remove it from lookup table as well
            lookupMap.remove(o.data.id);
        }
    }
    
    public static void test() {
        LFU p = new LFU(2);
        
        int key = 1;
        CacheObject value = p.get(key);
        print( String.format("Expect not finding value key %d: %s", key, (null==value)) );
        

        for(int i = 1; i < 15; i++) {
            key = 100 + i;
            CacheObject o = new CacheObject(key, "hello " + key);
            p.put(key, o);
            
            if(i == 1) {
                // access this one more time to make it stay in cache
                p.get(key);
            }
        }
        
        for(int i = 1; i < 15; i++) {
            key = 100 + i;
            value = p.get(key);
            print( String.format("Cached value at key %s: %s", key, (value == null? "null" : value.message)) );
        }
        
        print("Cache size: " + p.lookupMap.size());
    }
    
    static void print(String s) {
        System.out.println(s);
    }
}
