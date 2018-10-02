package lzhang.model;

/**
 * Right child is always grater than parent, but not the left node.
 * 
 * @author lzhang
 *
 */
public class BinarySearchTree {
	public BinaryNode<Integer> root;
	
	public BinarySearchTree() {}
	
	public BinarySearchTree(BinaryNode<Integer> root) {
		this.root = root;
	}
	
	public void appendNode(int val) {
		appendNode(root, new BinaryNode<Integer>(val));
	}
	
	public BinaryNode<Integer> appendNode(BinaryNode<Integer> curNode, BinaryNode<Integer> newNode) {
		if(curNode == null) {
			return newNode;
		}
		
		if(newNode.value >= curNode.value) {
			curNode.right = appendNode(curNode.right, newNode);
		} else {
			curNode.left = appendNode(curNode.left, newNode);
		}
		
		return newNode;
	}
	
	
	private int  max_level = 0;
	 
    // recursive function to print left view
    private void leftViewUtil(BinaryNode<Integer> node, int level) {
        // Base Case
        if (node==null) return;
 
        // If this is the first node of its level
        if (max_level < level)
        {
            System.out.print(" " + node.value);
            max_level = level;
        }
 
        // Recur for left and right subtrees
        leftViewUtil(node.left, level+1);
        leftViewUtil(node.right, level+1);
    }
 
    // A wrapper over leftViewUtil()
    public void leftView() {
        leftViewUtil(root, 1);
    }
}
