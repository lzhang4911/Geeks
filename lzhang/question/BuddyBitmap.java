package lzhang.question;

/*
pure storage buddy system bitmap
Given a complete binary tree with nodes of values of either 1 or 0, the following rules always hold:
(1) a node's value is 1 if and only if all its subtree nodes' values are 1
(2) a leaf node can have value either 1 or 0
Implement the following 2 APIs:
set_bit(offset, length), set the bits at range from offset to offset+length-1
clear_bit(offset, length), clear the bits at range from offset to offset+length-1

i.e. The tree is like:
             0
          /     \
         0        1
       /  \      /  \
      1    0    1    1
     /\   / \   / 
    1  1 1   0 1
    Since it's complete binary tree, the nodes can be stored in an array:
    [0,0,1,1,0,1,1,1,1,1,0,1] 
*/            
public class BuddyBitmap {
    public static void test() {
        // the array representation of the BT
        int[] a = {0,0,1,1,0,1,1,1,1,1,0,1};
        
        setBit(a, 3, 4);
    }
    
    private static void setBit(int[] a, int pos, int length) {
        if(a == null || pos < 0 || length == 0) {
            return;
        }
        
        if(pos + length > a.length) {
            // out of bound
            return;
        }
        
        int n = a.length;
        for(int i = pos; i < Math.min(pos+length, 2*pos+1); i++) {
            // the bit
            a[i] = 1;
            
            // set descendants as well
            setbit_down(a, pos, n - 1);
            
            // set ancestors
            while (pos>0)  {
                // make sure its sibling is 1, if its sibling is 0, cannot set ancestors
                if( (pos%2 == 0 && a[pos-1]==1) || (pos%2 == 1 && pos< n && a[pos+1]==1) ) {
                    a[(pos-1)/2] = 1;
                }
                    
                pos = (pos-1)/2;
            }
        }
    }

    /**
     * Since parent node has been set to 1, its 2 children must be set too
     * @param A
     * @param x
     * @param n
     */
    private static void setbit_down(int[] A, int x, int n) {
        if (x>=n) { 
            return;
        }
        
        // set left child
        if (2*x+1<=n && A[2*x+1]==0) {
            A[2*x+1]=1;
            
            // go further down
            setbit_down(A,2*x+1,n);
        }
        
        // set right child
        if (2*x+2<=n && A[2*x+2]==0) {
            A[2*x+2]=1;
            
            setbit_down(A,2*x+2,n);
        }
    }   
}
