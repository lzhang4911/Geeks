package lzhang.question;

import wzhang.model.Node;

/**
 * Provide some basic utilities here.
 * 
 * @author lzhang
 *
 */
public class BaseUtil {
    public static void print(String s) {
        System.out.println(s);
    }
    
//    public static <T> void print(Node<T> n) {
//        if(n == null) return;
//        
//        System.out.println("Node data " + n.data);
//        
//        print(n.next);
//    }
    
    public static <T> void print(Node<T> n) {
        if(n == null) return;
        
        System.out.print("[");
        
        while(n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
        
        System.out.print("]\n");
    }
}
