package lzhang.question.tree;

/**
 * https://www.geeksforgeeks.org/minimum-swap-required-convert-binary-tree-binary-search-tree/
 * 
 * Given a complete binary tree, find out the minimum number of swaps to change
 * it to a BST.
 * 
 * Input of level order array: arr[] = { 5, 6, 7, 8, 9, 10, 11 }
 * Output : 3
 * Swap 1: Swap node 8 with node 5.
 * Swap 2: Swap node 9 with node 10.
 * Swap 3: Swap node 10 with node 7.
 * 
 * Find the inorder array of the original tree:
 * {8, 6, 9, 5, 10, 7, 11}, sort this array to be
 * {5, 6, 7, 8,  9, 10,11}
 * 
 * Now the question becomes  to find the minimum swaps to make an array sorted:
 * https://www.geeksforgeeks.org/minimum-number-swaps-required-sort-array/
 * 
 * The way is to create a list/array of Pair(value, index) from the inorder array
 * first. And then sort this list by their values. Then
 * 
 * swaps = 0;
 * for each Pair in list:
 *    if( pair[i].value == pre[ pair[i].key ]:
 *        // already in sorted position
 *        continue;
 *    else {
 *        // find cycle_size
 *        cycle_size = 0;
 *        j = i;
 *        while(!visited[j]) {
 *            visited[j] = T;
 *            
 *            // ove to next node
 *            j = air[j].key;
 *            cycle_size++;
 *        }
 *        
 *        swaps += (cycle_size - 1);
 *     }
 * }
 * 
 * @author lzhang
 *
 */
public class MinimumSwap2BST {

}
