package lzhang.model;

import java.util.HashMap;
import java.util.Map;

public class AnyBinaryTree {
    
    /*
    Input :
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
    */
    public static BinaryNode<Integer> buildExampleTree() {
        BinaryNode<Integer> root = new BinaryNode<>( 1 );
        root.left = new BinaryNode<Integer>(2);
        root.right = new BinaryNode<Integer>(3);
        root.left.left = new BinaryNode<Integer>(4);
        root.right.left = new BinaryNode<Integer>(2);
        root.right.left.left = new BinaryNode<Integer>(4);
        root.right.right = new BinaryNode<Integer>(4);
        
        return root;
    }
    
    public static <T> BinaryNode<T> buildBinaryTreeFromLeveledOrderArray(T[] arr, int nodeId) {
        if(arr == null || nodeId >= arr.length) {
            return null;
        }
        
        BinaryNode<T> node = new BinaryNode<T>(arr[nodeId]);
        node.left = buildBinaryTreeFromLeveledOrderArray(arr, 2 * nodeId + 1);
        node.right = buildBinaryTreeFromLeveledOrderArray(arr, 2 * nodeId + 2);
        
        return node;
    }
    
    /**
     * Serialize every subtree
     * 
     * @param root
     * @return
     */
    public static Map<Integer, String> serializeSubtrees(BinaryNode<Integer> root) {
        Map<Integer, String> nodeIndexString = new HashMap<>();
        
        serializeSubtrees( 0, root, nodeIndexString);
        
        return nodeIndexString;
    }
    
    /*
     * producing:
     * 
    {
        2=1(2(4(, ), ), 3(2(4(, ), ), 4(, ))), 
        3=2(4(, ), ), 
        4=3(2(4(, ), ), 4(, )), 
        5=2(4(, ), ), 
        6=4(, )
        }
    */
    private static String serializeSubtrees(int nodeIndex, BinaryNode<Integer> node, Map<Integer, String> nodeIndexString) {
        String text = "";
        if(node == null) {
            return text;
        }
        
        text = node.value + "(";
        
        nodeIndex++;
        text += serializeSubtrees(nodeIndex, node.left, nodeIndexString);
        text += ", ";
        
        nodeIndex++;
        text += serializeSubtrees(nodeIndex, node.right, nodeIndexString);
        text += ")";
        
        nodeIndexString.put(nodeIndex, text);
        return text;
    }
}
