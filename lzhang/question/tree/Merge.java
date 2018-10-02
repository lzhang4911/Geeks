package lzhang.question.tree;

import lzhang.model.BinaryNode;

/**
 * Add 2 binary trees to create a new one: the nodes that are overlapping shall
 * be added in value. None overlapping nodes remain as if they were added to 0.
 * 
Example:
Input: 
    Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  

Output: 
Merged tree:
         3
        / \
       4   5
      / \   \ 
     5   4   7
 * 
 * @author lzhang
 *
 */
public class Merge {
    public static BinaryNode<Integer> test() {
        Merge p = new Merge();
        
        // create 2 nodes
        BinaryNode<Integer> n1 = new BinaryNode<Integer>(1);
        n1.left = new BinaryNode<Integer>(3);
        n1.left.left = new BinaryNode<Integer>(5);
        
        n1.right = new BinaryNode<Integer>(2);
        
        
        BinaryNode<Integer> n2 = new BinaryNode<Integer>(2);
        n2.left = new BinaryNode<Integer>(1);
        n2.left.right = new BinaryNode<Integer>(4);
        
        n2.right = new BinaryNode<Integer>(3);
        n2.right.right = new BinaryNode<Integer>(7);
        
        BinaryNode<Integer> result = p.add(n1, n2);
        return result;
    }
    
    
    private BinaryNode<Integer> add(BinaryNode<Integer> n1, BinaryNode<Integer> n2) {
        if(n1 == null && n2 == null) {
            return null;
        }
        
        BinaryNode<Integer> node;
        if(n1 == null) {
            node = n2;
        } else if(n2 == null) {
            node = n1;
        } else {
            // add the 2 nodes
            node = new BinaryNode<Integer>(n1.value + n2.value);
        }
        
        node.left = add( (n1==null? n1 : n1.left), (n2==null? n2 : n2.left) );
        node.right = add( (n1==null? n1 : n1.right), (n2==null? n2 : n2.right) );
        
        return node;
    }
}
