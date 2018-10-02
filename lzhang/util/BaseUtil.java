package lzhang.util;

import lzhang.model.Node;

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
    
    public static void print(char[] arr) {
    	System.out.print("[");
        for( char i : arr) {
        	System.out.print(i + ", ");
        }
        System.out.println("]");
    }
    
    public static <T> void print(T[] arr) {
    	System.out.print("[");
        for( T i : arr) {
        	System.out.print(i + ", ");
        }
        System.out.println("]");
    }
    
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
