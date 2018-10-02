package lzhang.question.tree;

import java.util.Arrays;

import lzhang.model.BinaryNode;
import lzhang.util.BaseUtil;

/**
 * 1. Leveled-order array: we can create from it a binary tree if and only if it's
 *    a balanced tree. For a balanced tree, the index relationship between root and 
 *    children can be easily determined:
 *    2i + 1, and 2i + 2.
 *    
 * Given both inorder and preorder sequence in arrays, construct
 * the binary tree.
 * Inorder sequence: D B E A F C 
 * Preorder sequence: A B D E C F
 * 
 * @author lzhang
 *
 */
public class CreateTreeFromArray extends BaseUtil {
    public static BinaryNode<Character> test() {
        CreateTreeFromArray inst = new CreateTreeFromArray();
        
        char[] inorder = {'D', 'B', 'E', 'A', 'F', 'C'};
        char[] preorder = {'A', 'B', 'D', 'E', 'C', 'F'};
        
        BinaryNode<Character> temp = null;
        // working
        temp = inst.createBinaryTreeFromInAndPreOrderArrars(inorder, preorder);
        BinaryNode.levelOrderPrint(temp);
        
        // working
        temp = inst.createBinaryTreeFromInAndPreOrderArrars_2(inorder, preorder, 0, preorder.length-1);
        BinaryNode.levelOrderPrint(temp);
        
        // preorder BST - O(nLogn)
        int[] pre = {10, 5, 1, 7, 40, 50};
        Index cur = new Index();
        BinaryNode<Integer> bst = inst.buildBstFromPreorderArray(pre);
        BinaryNode.levelOrderPrint(bst);
        
        // preorder BST - O(n)
        bst = inst.buildBstFromPreorderOrderN(pre, cur, Integer.MIN_VALUE, Integer.MAX_VALUE, pre.length);
        BinaryNode.levelOrderPrint(bst);
        
        return temp;
    }
    
    /**
     * A generic binary tree (may not be balanced) can be uniquely constructed if both preorder
     * and inorder arrays are provided.
     *  
     * The first element in preorder sequence is always the root, and
     * the root node always divides the inorder sequence into left and right
     * subtrees. This is recursively true for every subtree.
     * 
     * It's better not to Arrays.copy() using additional memory. Instead, we
     * can simply reply on start/end indexes to work with array segments.
     *  
     * @param inorder
     * @param preorder
     * @return
     */
    private BinaryNode<Character> createBinaryTreeFromInAndPreOrderArrars(char[] inorder, char[] preorder) {
        if(inorder == null || preorder == null || inorder.length != preorder.length || inorder.length == 0) {
            // throw IllegalArgumentException?
            return null;
        }
        
        BinaryNode<Character> root = new BinaryNode<>(preorder[0]);
        if(preorder.length == 1) {
            return root;
        }
        
        // find the index of root in inorder sequence
        int rootIndex = findIndex(inorder, root.value);
        if(rootIndex == -1) {
            System.out.println(String.format("root %c not found in inorder"));
            return null;
        }

        // extract the left and right subtree nodes from inorder sequence
        char[] inorderLeft = Arrays.copyOfRange(inorder, 0, rootIndex);
        char[] inorderRight = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
        
        /*
         * Extracting left and right from preorder is simpler, because the lengths for
         * both left and right are the same as those from inorder (differ in sequence).
         * 
         * Note to exclude the first one, the root node.
         */
        char[] preorderLeft = Arrays.copyOfRange(preorder, 1, 1+inorderLeft.length);
        char[] preorderRight = Arrays.copyOfRange(preorder, 1+inorderLeft.length, preorder.length);
        
        root.left = createBinaryTreeFromInAndPreOrderArrars(inorderLeft, preorderLeft);
        root.right = createBinaryTreeFromInAndPreOrderArrars(inorderRight, preorderRight);
        
        return root;
    }
    
    private int findIndex(char[] in, char root) {
        for(int i = 0; i < in.length; i++) {
            if(root == in[i]) {
                return i;
            }
        }
        
        return -1;
    }
    
    /*
     * Approach from geaks
     * Using indexes instead of copying array segments.
     * 
     * Note inStart and inEnd are both start and end indexes in inorder array (to specify
     * boundaries for left/right subtrees).
     * 
     * preIndex is the index in the preorder array.
     */
    private int preIndex = 0;
    private BinaryNode<Character> createBinaryTreeFromInAndPreOrderArrars_2(char in[], char pre[], int inStart, int inEnd) 
    {
        if (inStart > inEnd) { 
            return null;
        }
  
        /* Pick current node from Preorder traversal using preIndex
         * and increment preIndex. This is the root if preIndex == 0.  
         */
        BinaryNode<Character> tNode = new BinaryNode<Character>(pre[preIndex++]);
  
        /* If this node has no children then return */
        if (inStart == inEnd) {
            return tNode;
        }
  
        /* Else find the index of this node in Inorder traversal */
        int inIndex = search(in, inStart, inEnd, tNode.value);
  
        /* Using this index in Inorder array, construct left and
           right subtress */
        tNode.left = createBinaryTreeFromInAndPreOrderArrars_2(in, pre, inStart, inIndex - 1);
        tNode.right = createBinaryTreeFromInAndPreOrderArrars_2(in, pre, inIndex + 1, inEnd);
  
        return tNode;
    }
  
    /* UTILITY FUNCTIONS */
     
    /* Function to find index of value in arr[start...end]
     The function assumes that value is present in in[] */
    int search(char arr[], int strt, int end, char value) 
    {
        int i;
        for (i = strt; i <= end; i++) 
        {
            if (arr[i] == value)
                return i;
        }
        return i;
    }
    
    /**
     * Given a leveled-order array, build a binary tree. It works for balanced tree only!
     * 
     * Note this is a balanced binary tree, but may not be balanced BST!
     * 
     * Starting from root, the first array element i = 0, then its 2 children 2*i + 1
     * and 2*i + 2.
     * 
     * If an element in the array is null, that means the corresponding node shall be 
     * neglected.
     * 
     * Note that the root node can never be null!
     *  
     * @param arr
     * @return
     */
    public <T> BinaryNode<T> buildBalancedTreeFromLeveledOrderArray(T[] arr) {
        if(arr == null || arr.length == 0) {
            return null;
        }
        
        System.out.println("buildBalancedTreeFromLeveledOrderArray: " + Arrays.toString(arr));
        
        int index = 0;
        if(arr[index] == null) {
            throw new IllegalArgumentException("The root node must not be null");
        }
        
        BinaryNode<T> root = new BinaryNode<T>(arr[index]);
        
        buildBalancedTreeFromLeveledOrderArrayHelper(arr, arr.length, index, root);
        
        System.out.println("After buildBalancedTreeFromLeveledOrderArray: ");
        BinaryNode.levelOrderPrint(root);
        
        return root;
    }
    
    /**
     * Current node is at the index, find and create its left and right child at
     * 2*i + 1 and 2*i + 2. Node that any child node might be null and skip it
     * if the element is null.
     *  
     * @param arr
     * @param index
     * @param node
     */
    private <T> void buildBalancedTreeFromLeveledOrderArrayHelper(T[] arr, int length, int index, BinaryNode<T> node) {
        if(arr == null || length == 0) {
            return;
        }
        
        int i = 2 * index + 1;
        if(length > i && arr[i] != null) {
            node.left = new BinaryNode<T>(arr[i]);
            buildBalancedTreeFromLeveledOrderArrayHelper(arr, length, i, node.left);
        }
        
        i = 2 * index + 2;
        if(length > i && arr[i] != null) {
            node.right = new BinaryNode<T>(arr[i]);
            buildBalancedTreeFromLeveledOrderArrayHelper(arr, length, i, node.right);
        }
    }
    
    /**
     * If a BST is given in level order array, even it may not be balanced, we still
     * can precisely construct it without any additional informations such as null
     * values for missing elements.
     * 
     * 
     *     10
     *   /   \
     *   5    15
     *    \     \
     *     8     18
     *             \
     *             20
     * The leveled-order array would be
     * [10, 5, 15, 8, 18, 20]
     * 
     * This is an O(nLogn)
     *              
     * @param arr
     * @return
     */
    public BinaryNode<Integer> buildBstFromLevelOrderArray(int[] arr) {
        if(arr == null || arr.length == 0) {
            return null;
        }
        
        BinaryNode<Integer> root = new BinaryNode<Integer>(arr[0]);
        
        // walk through the rest of the arr
        for(int i = 1; i < arr.length; i++) {
            appendToBst(root, new BinaryNode<Integer>(arr[i]));
        }
        
        System.out.println("After buildBst(): ");
        BinaryNode.levelOrderPrint(root);
        
        return root;
    }
    
    /**
     * In preorder array, the first node is root which is the only certainty.
     * - The next element shall the left node if its smaller than root; if it's
     *   greater, it must be right node.
     *   
     * We can do exactly what was done in previous method.
     * @param arr
     * @return
     */
    public BinaryNode<Integer> buildBstFromPreorderArray(int[] arr) {
        if(arr == null || arr.length == 0) {
            return null;
        }
        
        BinaryNode<Integer> root = new BinaryNode<Integer>(arr[0]);
        
        for(int i = 1; i < arr.length; i++) {
            appendToBst(root, new BinaryNode<Integer>(arr[i]));
        }
        
        System.out.println("After buildBst(): ");
        BinaryNode.levelOrderPrint(root);
        
        return root;
    }
    
    static class Index {
        private int index;
    }
    
    /**
     * This should give O(n) in time.
     * 
     * @param arr
     * @param cur
     * @param min
     * @param max
     * @param len
     * @return
     */
    public BinaryNode<Integer> buildBstFromPreorderOrderN(int[] arr, Index cur, int min, int max, int len) {
        BinaryNode<Integer> root = null;
        if(arr == null || len == 0 || cur.index >= len) {
            return root;
        }
        
        // not sure why we need to check if the value is in range
        if(arr[cur.index] > min && arr[cur.index] < max) {
            root = new BinaryNode<Integer>(arr[cur.index]);
            cur.index += 1;
            
            root.left = this.buildBstFromPreorderOrderN(arr, cur, min, root.value-1, len);
            
            root.right = this.buildBstFromPreorderOrderN(arr, cur, root.value+1, max, len);
        }
        
        return root;
    }
    
    private void appendToBst(BinaryNode<Integer> root, BinaryNode<Integer> node) {
        if(root == null || node == null) {
            return;
        }
        
        if(node.value < root.value) {
            if(root.left == null) {
                root.left = node;
            } else {
                appendToBst(root.left, node);
            }
        } else {
            if(root.right == null) {
                root.right = node;
            } else {
                appendToBst(root.right, node);
            }
        }
    }
    
    /**
     * Note that a sorted array is actually the Inorder array. Therefore, the middle
     * element is the root. In case of even elements, we can choose either of the middle
     * 2 to be the root.
     * 
     * @param arr
     * @return
     */
    public BinaryNode<Integer> buildBalancedBstFromSortedArray(int[] arr, int start, int end) {
        // we assume the array is indeed sorted
        if(arr == null || start > end) {
            return null;
        }
        
        // find the root index and then create the node
        int mid = (start + end)/2;
        BinaryNode<Integer> root = new BinaryNode<Integer>(arr[mid]);
        
        // use recursion to create the 2 children
        root.left = buildBalancedBstFromSortedArray(arr, start, mid - 1);
        root.right = buildBalancedBstFromSortedArray(arr, mid + 1, end);
        
        return root;
    }
}
