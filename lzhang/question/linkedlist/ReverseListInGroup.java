package lzhang.question.linkedlist;

import lzhang.model.Node;

/**
 * https://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/
 * 
 * @author lzhang
 *
 */
public class ReverseListInGroup {
	public static void test() {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		Node<Integer> node, head  = new Node<Integer>(a[0]);
		node = head;
		
		for(int i = 1; i < a.length; i++) {
			node = node.append(a[i]);
		}
		
		ReverseListInGroup p = new ReverseListInGroup();
		Node<Integer> newList = p.reverse(head, 3);
		newList.print();
	}

	/**
	 * The solution using recursion is to always update the pointer current to point
	 * to the right node - the beginning of the list (head) or the node that starts
	 * the next group.
	 * 
	 * The actual reversal is to reverse these nodes inside the current group - the
	 * first k nodes starting from "current".
	 * 
	 * Recursion is to make head.next = recurse(current, k). Why head.next? Because
	 * "head" has never been updated within the recursion and therefore it still points
	 * to the same first node of the current group before reversing.
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	private Node<Integer> reverse(Node<Integer> head, int k) {
		Node<Integer> current = head;
		Node<Integer> prev = null, next = null;
		
		// first reverse the first k elements
		int count = 0;
		while(count < k && current != null) {
			// first preserve the real "next" for the next iteration
			next = current.next;
			
			// reverse
			current.next = prev;
			
			// update both "prev" and "current" for next iteration
			prev = current;
			current = next;
			
			count++;
		}
		
		// then recurse for the rest
		if(current != null) {
			head.next = reverse(current, k);
		}
		
		return prev;
	}
}
