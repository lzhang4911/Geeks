package lzhang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import lzhang.model.AnyBinaryTree;
import lzhang.model.BinaryNode;
import lzhang.model.BinarySearchTree;
import lzhang.model.MaxBarReact;
import lzhang.model.Node;
import lzhang.question.BuddyBitmap;
import lzhang.question.ClimbStairs;
import lzhang.question.Combination;
import lzhang.question.CountSetBits;
import lzhang.question.DecodeDigits;
import lzhang.question.DecodeDigits2;
import lzhang.question.DecodeString;
import lzhang.question.FriendCircle;
import lzhang.question.GasStationCircle;
import lzhang.question.HeapSort;
import lzhang.question.HistogramRectangle;
import lzhang.question.HouseRobbery;
import lzhang.question.JobAssign;
import lzhang.question.Knapsack;
import lzhang.question.LFU;
import lzhang.question.LRU2;
import lzhang.question.LcsKthPalindrome;
import lzhang.question.LongestCommonString;
import lzhang.question.LookAndReadPattern;
import lzhang.question.MergeIntervals;
import lzhang.question.NumericExpression;
import lzhang.question.Palindrome;
import lzhang.question.PalindromeNum;
import lzhang.question.Parentheses;
import lzhang.question.Permutation;
import lzhang.question.PowerSet;
import lzhang.question.StringMatch;
import lzhang.question.StringPermutation;
import lzhang.question.SumFromCombination;
import lzhang.question.TextDraw;
import lzhang.question.TrackMaxInStack;
import lzhang.question.WaterJar;
import lzhang.question.WeightedJobSchedule;
import lzhang.question.WordBreak;
import lzhang.question.WordLadder;
import lzhang.question.array.CitySkyline;
import lzhang.question.array.CloseSumSubarray;
import lzhang.question.array.EqualSumSubarray;
import lzhang.question.array.GivenArrayAndTarget;
import lzhang.question.array.IndexInSortedArray;
import lzhang.question.array.KthElement;
import lzhang.question.array.LongestAscendingSequence;
import lzhang.question.array.LongestConsecutiveSequence;
import lzhang.question.array.LongestIncreaseSubsequence;
import lzhang.question.array.LongestSubstringWithNoRepeat;
import lzhang.question.array.MaxHeapSort;
import lzhang.question.array.MaxOverlapInterval;
import lzhang.question.array.MaxSumNonAdjacentSequence;
import lzhang.question.array.MaxSumSubarray;
import lzhang.question.array.MaximumProductSubarray;
import lzhang.question.array.MergeKsortedArrays;
import lzhang.question.array.NearestSmallerLeft;
import lzhang.question.array.RollCharacters;
import lzhang.question.array.RotateArray;
import lzhang.question.array.SearchInRotated;
import lzhang.question.array.SlidingWindowMaximum;
import lzhang.question.dp.CoinChangeWays;
import lzhang.question.dp.SnakeLadderGame;
import lzhang.question.dp.StockBuySell;
import lzhang.question.graph.ShortestPath;
import lzhang.question.linkedlist.AddLinkLists;
import lzhang.question.linkedlist.CircularLinkBreak;
import lzhang.question.linkedlist.DeleteNode;
import lzhang.question.linkedlist.FlattenBranchedList;
import lzhang.question.linkedlist.List2Bst;
import lzhang.question.linkedlist.MergeSortedLinkLists;
import lzhang.question.linkedlist.PairSwap;
import lzhang.question.linkedlist.RemoveSmallLeft;
import lzhang.question.linkedlist.Reverse;
import lzhang.question.linkedlist.ReverseInGroup;
import lzhang.question.linkedlist.ReverseListInGroup;
import lzhang.question.matrix.KnightMinSteps;
import lzhang.question.matrix.MaxSubArea;
import lzhang.question.matrix.MaxSumSubMatrix;
import lzhang.question.matrix.PrintMatrixInSpiral;
import lzhang.question.matrix.RotateMatrix;
import lzhang.question.matrix.SurroundedRegions;
import lzhang.question.string.Anagram;
import lzhang.question.string.DictionaryBasedPassword;
import lzhang.question.tree.BinaryTree2List;
import lzhang.question.tree.BinaryTreeSize;
import lzhang.question.tree.BstOperation;
import lzhang.question.tree.CreateTreeFromArray;
import lzhang.question.tree.FindNextBiggerKey;
import lzhang.question.tree.HasSubtree;
import lzhang.question.tree.InverseBt;
import lzhang.question.tree.KthInBinaryTree;
import lzhang.question.tree.KthLargest;
import lzhang.question.tree.LCA;
import lzhang.question.tree.LargestBst;
import lzhang.question.tree.LeveledLinkedList;
import lzhang.question.tree.MaxSubtreeAverage;
import lzhang.question.tree.Merge;
import lzhang.question.tree.Merge2Bst;
import lzhang.question.tree.ModifyBtIntoPreorderLinkedList;
import lzhang.question.tree.PreorderFullBinaryTreeDepth;
import lzhang.question.tree.PrintRootToLeafPath;
import lzhang.question.tree.PrintSideView;
import lzhang.question.tree.SumAllPaths;
import lzhang.question.tree.SumOf2InPathMatchesRoot;
import lzhang.question.tree.SymmetrcTree;
import lzhang.question.tree.TreeDiameter;
import lzhang.question.tree.ValidateBst;
import lzhang.question.tree.WordsPalindrome;
import lzhang.util.BaseUtil;

public class Driver extends BaseUtil {
	public static void main(String[] args) throws Exception {
	    // create empty String array
	    String[] list = new String[0];
	    java.util.Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
	    
	    /*
	     * String literals are stored in a special memory region "String pool". However,
	     * String objects created with its constructor are stored in heap just like any
	     * other objects. Such string object can be manually intern'ed.
	     * 
	     * Since Java 7, string pool is on heap instead of PermGen.
	     */
	    String s1 = "This Is Test";
	    String s2 = "This Is Test";
	    assert s1 == s2;
        String s3 = new String(s1);
        assert s1 != s3;
        
	    // After manual interning
        String s4 = s3.intern();
        assert s1 == s4;
        
	    
	    
		
		// an integer shifted to right by 1 is the same as divided by 2
		print( String.format("12 >> 1 == 12 / 2? %s", (12 >> 1) == (12 / 2)) );
		
		print( String.format("12 & (12 - 1) == %d", (12 & (12 - 1))) );
		print( String.format("11 & (11 - 1) == %d", (11 & (11 - 1))) );
		print( String.format("7 & (7 - 1) == %d", (7 & (7 - 1))) );
		
		int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
		int c = findMaxSumInSubarray(a);
		print("Maximum contiguous sum is " + c);
		
//		int c = findMaxSumInSubarray_1(a);
//		System.out.print("Maximum contiguous sum is " + c);
        
//		printNextGreaterElement(new int[] {13,7,6,12});
		
//		segregate( new int[] {8,9,77,-1,-20,-11,4,5,6,3} );
		
//		print( findExcelColumnNameByIndex(705) );
		
//		int n = 45;
//		print( "fibocci_recusive: " + fibocci_recusive(n) );
//		long t2 = System.currentTimeMillis();
//		print("delta " + (t2-t1));
//		
//		print( "fibocci_loop: " + fibocci_loop(n) );
//		long t3 = System.currentTimeMillis();
//		print("delta " + (t3-t2));
//		
//		print( "fibocci_loop_cache: " + fibocci_loop_cache(n) );
//		long t4 = System.currentTimeMillis();
//		print("delta " + (t4-t3));
		
//		deleteNode();
		
//		printLeftNodes();
		
//		removeAdjacentDuplicatesFromString("aaabcccabbdccdmeffem");
		
		// level 0: 10
		BinaryNode<Integer> node = new BinaryNode<>(10);
		
		// level 1: 8, 12
		node.left = new BinaryNode<Integer>(8);
		node.right = new BinaryNode<Integer>(12);
		
		// level 2: 3, 5, 11
		node.left.left = new BinaryNode<Integer>(3);
		node.left.right = new BinaryNode<Integer>(5);
		
		node.right.left = new BinaryNode<Integer>(11);
		
		// level 24: 22, 20
		node.left.right.left = new BinaryNode<Integer>(24);
		node.left.right.right = new BinaryNode<Integer>(22);
		node.right.left.right = new BinaryNode<Integer>(20);
		
		// level 5: 40
		node.right.left.right.left = new BinaryNode<Integer>(40);
		
//		
//		System.out.print("Fount " + findRootToLeafPathSum(node, 0, 23) );
		
//		System.out.print("BST depth " + bstDepth(node, 0) );
		
//		System.out.print("Max at depth 4: " + maxAtBstDepth(node, 0, 3) );
		
		System.out.print("No. of paths: " + rootToLeafPathsMatchSum(node, 34) );
		
//		List<Integer> r = mergeSortedArrays(new int[]{3,5,7,10,12}, new int[]{2,6});
//		System.out.print("Merge sort: " + r.toArray(new Integer[]{}) );
		
//		BinaryNode n1 = new BinaryNode(11);
//		n1.left = new BinaryNode(22);
//		n1.right = new BinaryNode(23);
//		
//		BinaryNode n3 = new BinaryNode(33);
//		n1.left.left = n3;
//		
//		// cycle
//		n3.right = n1;
//		System.out.print("Found cycles: " + hasCycleInGraph(n1, new ArrayList<BinaryNode>()) );
		
		
//		BinaryNode head = convertIntArrayToBst(new int[] {3,1,4,6,8,23,24,15,29});
//		System.out.print("Longest subsequence " + bstDepth(head, 0));
		
//		int arr[] = {12, 11, 13, 5, 6, 7};
//        int n = arr.length;
// 
//        HeapSort ob = new HeapSort();
//        ob.sort(arr);
// 
//        System.out.print("Sorted array is");
//        ob.printArray(arr);
		
		//System.out.print("Found triplet at given sum " + findTripletAtSum(arr, 24));
//		String string = "this is the original!";
//		System.out.print("The reverse of it: " + reverseString(string));
		
//		System.out.print("The permutations of ABCD: " + stringPermutations("ABCD", ""));
		
//		System.out.print("after swap nibbles, 100 becomes " + swapNibbles((byte)100));
		
		// build 4 level tree
//		BinaryNode node = new BinaryNode(0);
//		node.left = new BinaryNode(5);
//		
//		node.left.left = new BinaryNode(6);
//		node.left.right = new BinaryNode(4);
//		node.left.right.right = new BinaryNode(9);
//		
//		node.right = new BinaryNode(7);
//		node.right.left = new BinaryNode(1);
//		node.right.right = new BinaryNode(3);
//		System.out.print("Sum at 3rd level: " + sumAtNLevel( node, 3) );
		
		// delete node that equals the given value
		Node<Integer> curNode = null, head = null;
		for(int i = 0; i<10; i++) {
			if(curNode == null) {
			    curNode = new Node<Integer>(i);
				
			    head = curNode;
			} else {
			    curNode.append( i );
			    
			    curNode = curNode.next;
			}
		}
		
		print("Linked link head:");
		print(head);
//			        
//		deleteAllOccurances(head, 10);
//		print(head);
		
		trackGreatestNumbers(new Integer[] {4, 12,20,22,4,6,2,55,65,12,23}, 5);
		
		//int hist[] = { 6, 2, 5, 4, 5, 1, 11 };
		int hist[] = { 6, 2, 5, 4, 5, 1, 13 };
        print("Maximum area is " + (new MaxBarReact()).getMaxArea(hist, hist.length));
        
        // bit wise op
        int x = 6, y = 11;
        int and = x & y;
        int xor = x ^ y;
        print( String.format("x = %d, y = %d, and = %d, xor = %d", x, y, and, xor) );
        print( String.format("and = %d, and & x = %d", and, and & x) );
        print( String.format("and = %d, and & y = %d", and, and & y) );
        print( String.format("and = %d, and XOR x = %d", and, and ^ x) );
        print( String.format("xor = %d, xor XOR x = %d (should yeild y!)", xor, xor ^ x) );
        print( String.format("xor = %d, xor XOR z = %d (not x nor y)", xor, xor ^ 7) );
        print( String.format("xor & ~x = %d", xor & ~x) );
        print( String.format("xor & ~z = %d (not x nor y)", xor & ~7) );
        
        print( String.format("xor = %d, xor OR x = %d", xor, xor | x) );
        print( String.format("and & ~and = %d", and & ~and) );
        print( String.format("and | and = %d", and | and) );
        
        print("Jar emptied on day " + WaterJar.daysToEmptyJar(5, 2));
        
        SumFromCombination.printCombinations(new Integer[] {3,9,8,4,5,7,10}, 15);
        
        BinaryNode<Integer> tree = AnyBinaryTree.buildExampleTree();
        Map<Integer, String> map = AnyBinaryTree.serializeSubtrees(tree);
        print(map.toString());
        
        print("abcbdf contains palindrome? " + Palindrome.containsPalindromeSubstring("abcbdf"));
        String string = "ababcdefghgfedskil";
        print("Longest palindrome from " + string + ": " + new Palindrome().findLongestPalindrome(string));
        
        // HouseRobbery
        Integer houseValue[] = {6, 7, 1, 3, 8, 2, 4};
        print(houseValue);
        print("HouseRobbery " + HouseRobbery.robMax(houseValue, houseValue.length));
        
        print("ClimbStairs to 4: " + ClimbStairs.waysToClimb(4));
        
        print("MergeSortedLinkLists: ");
        print( MergeSortedLinkLists.test() );
        
        print("MaxSumSubarray {-2, -3, 4, -1, -2, 1, 5, -3: " + MaxSumSubarray.findMaxSum(new int[] {-2, -3, 4, -1, -2, 1, 5, -3}));
        
        print("PalindromeNum(1221): " + PalindromeNum.isPalindrome(1221));
        
        print("Knapsack: " + Knapsack.test());
        
//        print("EggDrop: " + EggDrop.minTries());
        
        print("StringPermutation " + StringPermutation.test());
        
        print("LongestAscendingSequence: " + LongestAscendingSequence.test());
        
        print("CoinChangeWays: " + CoinChangeWays.test());
        
        print("TextDraw.drawCircle: ");
        TextDraw.test();
        
        print("StockBuySell: " + StockBuySell.test());
        
//        print("BinaryTree2List: " + BinaryTree2List.test(node));
        print("BinaryTree2List: " + BinaryTree2List.test2(node));
        
        BuddyBitmap.test();
        
        print("LongestSubsequence: " + LongestIncreaseSubsequence.test());
        
        print("PowerSet:");
        PowerSet.test();
        
        print("MaxSubtreeAverage: " + MaxSubtreeAverage.test());
        
        print("NumericExpression: " + NumericExpression.test());
        
        print("Combination:");
        Combination.test();
        
        print("Permutation:");
        Permutation.test();
        
        print("StringMatch: " + StringMatch.test());
        
        print("RemoveSmallLeft: " + RemoveSmallLeft.test());
        
        print("FlattenBranchedList: ");
        print(FlattenBranchedList.test());
        
        System.out.print("HeapSort: ");
        print(HeapSort.test());
        
        System.out.print("JobAssign: " + JobAssign.test());
        
        System.out.print("NearestSmallerLeft: ");
        NearestSmallerLeft.test();
        
        print("GivenSumOfNumbers: " + GivenArrayAndTarget.test());
        
        print("CreateTreeFromArray.createBinaryTreeFromInAndPreOrderArrars():");
        BinaryNode.levelOrderPrint( CreateTreeFromArray.test() );
        
        print("LCA: ");
        BinaryNode.levelOrderPrint( LCA.test() );
        
        print("PrintSideView:");
        PrintSideView.test(node);
        
        print("PairSwap: ");
        print(PairSwap.test());
        
        print("PrintMatrixInSpiral:");
        PrintMatrixInSpiral.test();
        
        print("\nDeleteNode: ");
        DeleteNode.test();
        
        print("MergeIntervals:");
        MergeIntervals.test();
        
        print("WordLadder: " + WordLadder.test());
        
        print("Parentheses: " + Parentheses.test());
        
        print("BinaryTreeSize: ");
        BinaryTreeSize.test(node);
        
        print("LRU2:");
        LRU2.test();
        
        print("LFU: ");
        LFU.test();
        
        print("SymmetrcTree: " + SymmetrcTree.test());
        
        print("CreateTreeFromArray.");
        BinaryNode<Integer> temp = new CreateTreeFromArray().buildBstFromLevelOrderArray(new int[] {10, 5, 15, 8, 18, 20});
        BinaryNode.levelOrderPrint(temp);
        
        print("Reverse:");
        print(Reverse.test(head));
        
        print("InverseBt:");
        InverseBt.test();
        
        print("HasSubtree:");
        HasSubtree.test();
        
        print("LongestCommonString: " + LongestCommonString.test());
        
        print("Merge:");
        BinaryNode.levelOrderPrint( Merge.test() );
        
        print("FriendCircle: " + FriendCircle.test());
        
        print("TreeDiameter: " + TreeDiameter.test());
        
        print("HistogramRectangle: " + HistogramRectangle.test());
        
        print("ValidateBst: " + ValidateBst.test());
        
        print("LeveledLinkedList:");
        LeveledLinkedList.test();
        
        print("LongestConsecutiveSequence: " + LongestConsecutiveSequence.test());
        
        print("SurroundedRegions:");
        SurroundedRegions.test();
        
        print("GasStationCircle: " + GasStationCircle.test());
        
        print("MaximumProductSubarray: " + MaximumProductSubarray.test());
        
        print("RotateArray: ");
        print(RotateArray.test());
        
        print("CountSetBits: ");
        CountSetBits.test();
        
        print("ReverseInGroup:");
        ReverseInGroup.test();
        
        print("CircularLinkBreak:");
        CircularLinkBreak.test();
        
        print("AddLinkLists: ");
        AddLinkLists.test();
        
        print("MaxSumSubMatrix: " + MaxSumSubMatrix.test());
        
        print("MaxSubArea: " + MaxSubArea.test());
        print("MaxSubArea: " + new MaxSubArea().calculateMaxHistogram(new int[] {6, 2, 5, 4, 5, 1, 6})); // expect 12
        
        print("KthLargest:");
        KthLargest.test();
        
        print("KthElement:");
        KthElement.test();
        
        print("Merge2Bst:");
        Merge2Bst.test();
        
        print("List2Bst:");
        List2Bst.test();
        
        print("FindNextBiggerKey: " + FindNextBiggerKey.test());
        
        print("SumAllPaths: " + SumAllPaths.test());
        
        print("LcsKthPalindrome:");
        LcsKthPalindrome.test();
        
        print("SlidingWindowMaximum:");
        SlidingWindowMaximum.test();
        
        print("SearchInRotated:");
        SearchInRotated.test();
        
        print("MergeKsortedArrays:");
        MergeKsortedArrays.test();
        
        print("WordsPalindrome:");
        WordsPalindrome.test();
        
        print("MaxHeapSortArray:");
        MaxHeapSort.test();
        
        print("DecodeString: " + DecodeString.test());
        
        print("MaxOverlapInterval:");
        MaxOverlapInterval.test();
        
        print("DecodeDigits:");
        DecodeDigits.test();
        
        print("DecodeDigits2:");
        DecodeDigits2.test();
        
        print("LookAndReadPattern:");
        LookAndReadPattern.test();
        
        print("PrintRootToLeafPath:");
        PrintRootToLeafPath.test();
        
        print("WeightedJobSchedule:");
        WeightedJobSchedule.test();
        
        print("MaxSumNonAdjacentSequence: " + MaxSumNonAdjacentSequence.test());
        
        print("Anagram: " + Anagram.test());
        
        print("DictionaryBasedPassword: ");
        DictionaryBasedPassword.test();
        
        print("CitySkyline:");
        CitySkyline.test();
        
        print("WordBreak:");
        WordBreak.test();
        
        print("IndexInSortedArray:");
        IndexInSortedArray.test();
        
        print("BstOperation:");
        BstOperation.test();
        
        print("SnakeLadderGame:");
        SnakeLadderGame.test();
        
        print("KnightMinSteps:");
        KnightMinSteps.test();
        
        print("RotateMatrix:");
        RotateMatrix.test();
        
        print("SumOf2InPathMatchesRoot:");
        SumOf2InPathMatchesRoot.test();
        
        print("LargestBst: " + LargestBst.test());
        
        print("ShortestPath:");
        ShortestPath.test();
        
        print("KthInBinaryTree:");
        KthInBinaryTree.test();
        
        print("PreorderFullBinaryTreeDepth:");
        PreorderFullBinaryTreeDepth.test();
        
        print("ModifyBtIntoPreorderLinkedList:");
        ModifyBtIntoPreorderLinkedList.test();
        
        print("TrackMaxInStack:");
        TrackMaxInStack.test();
        
        print("CloseSumSubarray:");
        CloseSumSubarray.test();
        
        print("LongestSubstringWithNoRepeat:");
        LongestSubstringWithNoRepeat.test();
        
        print("ReverseListInGroup:");
        ReverseListInGroup.test();
        
        print("EqualSumSubarray: " + EqualSumSubarray.test());
        print("RollCharacters:");
        RollCharacters.test();
	}
	
	public static Node<Integer> deleteAllOccurances(Node<Integer> head, int x)
    {
        if(head == null) {
            System.out.print("null");
            return null;
        }
        
        System.out.print("Examine " + head.data);
        
        if(head.data.equals(x)) {
            System.out.print("Found " + head.data);
            
            if(head.next == null) {
                head.data = 0;
            } else {
                head.data = head.next.data;
                head.next = head.next.next;
            }
        } else {
        	head = head.next;
        }
        
        return deleteAllOccurances(head, x);
    }
	
	public static int sumAtNLevel( BinaryNode<Integer> node, int levelDecreased) {
		if(node == null) return 0;
		if(levelDecreased == 1) return node.value;
		
		levelDecreased--;
		return sumAtNLevel( node.left, levelDecreased) + sumAtNLevel( node.right, levelDecreased);
	}
	
	/*
	 * 100 is 01100100, after swapping 0110 and 0100, it becomes
	 * 01000110, which is 70
	 */
	public static byte swapNibbles(byte x) {
		return (byte) ((x & 0x0F) << 4 | (x & 0xF0) >> 4);
	}
	
	public static int stringPermutations(String string, String prefix) {
		if(string.length()==0){
            System.out.print(prefix);
            return 1;
        } else {
        	int count = 0;
            for(int i=0;i<string.length();i++){
                String rem = string.substring(0,i) + string.substring(i+1);
                count += stringPermutations(rem, prefix+string.charAt(i));
            }
            
            return count;
        }
	}
	
	
	public static String reverseString(String string) {
		char[] chars = string.toCharArray();
		char temp;
		for(int i = 0; i<chars.length/2; i++) {
			temp = chars[i];
			chars[i] = chars[chars.length - 1 - i];
			chars[chars.length - 1 - i] = temp;
		}
		
		return new String(chars);
	}
	
	public static boolean findTripletAtSum(int[] arr, int sum) {
		return false;
	}
	
	public static BinaryNode<Integer> convertIntArrayToBst(int[] array) {
		if(array.length == 0) return null;
		
		boolean isFirst = true;
		BinarySearchTree bt = new BinarySearchTree( new BinaryNode<Integer>(array[0]) );
		
		for(int e : array) {
			if(isFirst) {
				isFirst = false;
				continue;
			}
			
			bt.appendNode(e);
		}
		
		return bt.root;
	}
	
	public static boolean hasCycleInGraph(BinaryNode<Integer> n, List<BinaryNode<Integer>> stack) {
		if(n == null) return false;
		
		if(stack.contains(n)) {
			return true;
		}
		
		stack.add(n);
		return hasCycleInGraph(n.left, stack) || hasCycleInGraph(n.right, stack);
	}
	
	
	public static List<Integer> mergeSortedArrays(int[] a, int[] b) {
		List<Integer> r = new ArrayList<>();
		
		int i=0, j=0;
		while( i < a.length && j < b.length) {
			r.add(a[i] < b[j]? a[i++] : b[j++]);
		}
		
		if(a.length > b.length) {
			while(i < a.length) {
				r.add(a[i++]);
			}
		} else {
			while(j < b.length) {
				r.add(b[j++]);
			}
		}
		
		return r;
	}
	public static int maxAtBstDepth(BinaryNode<Integer> node, int depth, int atDepth) {
		if(node == null) {
			return 0;
		}
		
		if(depth == atDepth) {
			return node.value;
		}
		
		return Math.max( maxAtBstDepth(node.left, depth+1, atDepth), maxAtBstDepth(node.right, depth+1, atDepth) );
	}
	
	/*
	 * find the number of paths from root to leaves whose sum equal the given one 
	 */
	private static int rootToLeafPathsMatchSum(BinaryNode<Integer> node, int sumAfterReduction) {
		if(sumAfterReduction == 0) {
			// just reached
			return 1;
		}
		
		if(node == null) {
			return 0;
		}
		
		return rootToLeafPathsMatchSum(node.left, sumAfterReduction - node.value) +
				rootToLeafPathsMatchSum(node.right, sumAfterReduction - node.value);
	}
	
	public static int bstDepth(BinaryNode<Integer> node, int depth) {
		if(node == null) {
			return depth;
		}
		
		return Math.max( bstDepth(node.left, depth+1), bstDepth(node.right, depth+1) );
	}
	
	public static boolean findRootToLeafPathSum(BinaryNode<Integer> node, int sum, int expected) {
		if(node == null) {
			return false;
		}
		
		sum += node.value;
		
		if(sum == expected) {
			System.out.print("Found!");
			return true;
		}
		
		return (
				findRootToLeafPathSum(node.left, sum, expected) ||
				findRootToLeafPathSum(node.right, sum, expected)
				);
	}
	
	/*
	 * To remove subsequent duplicate characters in a string:
	 * Use 2 string buffers - an empty dest buffer to begin with.
	 * Each time, remove a char from src[0], compare it with the tail char from dest.
	 * If they are equal, delete the tail; otherwise, append to dest.
	 * Until all chars consumed from src. 
	 */
	static void removeAdjacentDuplicatesFromString(String string) {
		StringBuilder dest = new StringBuilder();
		StringBuilder src = new StringBuilder();
		
		src.append(string);
		
		
		// first put the first char from src to dest
		char s = removeFirstChar(src);
		dest.append(s);
		
		char d;
		while(src.length() > 0) {
			// take first from src
			s = removeFirstChar(src);
			
			// take last from dest - there at least one in it
			d = dest.charAt( dest.length() - 1 );
			
			if(s != d) {
				dest.append(s);
			} else {
				// delete from dest first
				dest.deleteCharAt( dest.length() - 1 );
				
				while(src.length() > 0) {
					// take one more from src and see if it should be deleted or append to dest
					s = src.charAt(0);
					
					if(s != d) {
						// do nothing but abort
						if(dest.length() == 0) {
							dest.append(s);
							src.deleteCharAt(0);
						}
						
						break;
					} else {
						src.deleteCharAt(0);
					}
				}
			}
		}
		
		System.out.print("result: " + dest.toString());
	}
	
	/**
	 * Take the first char from src and append to dest
	 * @param dest
	 * @param src
	 */
	private static Character removeFirstChar(StringBuilder src) {
		if(src == null || src.length() == 0) {
			// nothing to move any more
			return null;
		}
		
		char c = src.charAt(0);
		src.deleteCharAt(0);
		return c;
	}
	
	/*
	 * build a binary tree and print all of the left nodes
	 */
	static void printLeftNodes() {
		/* creating a binary tree and entering the nodes */
        BinarySearchTree tree = new BinarySearchTree();
        tree.root = new BinaryNode<Integer>(12);
        tree.root.left = new BinaryNode<Integer>(10);
        tree.root.right = new BinaryNode<Integer>(30);
        tree.root.right.left = new BinaryNode<Integer>(25);
        tree.root.right.right = new BinaryNode<Integer>(40);
 
        tree.leftView();
	}

	static void deleteNode() {
		Node<Integer> head = new Node<Integer>(100);
		Node<Integer> temp2 = new Node<Integer>(200);
		Node<Integer> temp3 = new Node<Integer>(300);
		
		head.append(temp2);
		temp2.append(temp3);
		
		// delete temp2
		if(temp2.next != null) {
			// this is tail
			temp2 = null;
			
			temp3.data = 400;
		}
		
		print(head.toString());
	}
	
	static long fibocci_recusive(long n) {
		return n <= 1? n : fibocci_recusive(n - 1) + fibocci_recusive(n - 2);
	}
	
	static long fibocci_loop(int n) {
		if(n <= 1) return n;
		
		long fn_minus_2 = 0;
		long fn_minus_1 = 1;
		
		long fn = 0;
		for(int i = 2; i <= n; i++) {
			fn = fn_minus_1 + fn_minus_2;
			fn_minus_2 = fn_minus_1;
			fn_minus_1 = fn;
		}
		
		return fn;
	}
	
	static long fibocci_loop_cache(int n) {
		long[] cache = new long[n+1];
		cache[0] = 0;
		cache[1] = 1;
		
		for(int i = 2; i <= n; i++) {
			cache[i] = cache[i - 1] + cache[i - 2];
		}
		
		return cache[n];
	}
	
	// 1-based
	static String findExcelColumnNameByIndex(int index) {
		StringBuffer sb = new StringBuffer();
		
		while(index > 0) {
			int remainder = index % 26;
			
			if(remainder == 0) {
				sb.append('Z');
				
				index = index/26 - 1;
			} else {
				sb.append((char)((remainder-1) + 'A'));
				index = index/26;
			}
		}
		
		return sb.reverse().toString();
	}
	
	
	/* 
	 * Utility function that puts all non-positive
	 * (0 and negative) numbers on left side of
	 * arr[] and return count of such numbers.
	 * 
	 *  To achieve O(n), keep the count of swapped o's
	 *  which also serves as the last index (j-1) for the
	 *  latest 0 on the left hand side. Just need to walk 
	 *  through the list once.  
	 */
	static int segregate (int arr[]) {
        if (arr == null || arr.length == 0)
            return 0;

        int j = 0, i;
        for (i = 0; i < arr.length; i++) {
            if (arr[i] <= 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                // increment count of non-positive
                // integers
                j++;
            }
        }

        return j;
	 }
	
	static void printNextGreaterElement(int[] values) {
		if(values == null || values.length == 0) {
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		int maxFromRight = values[values.length - 1];
		
		int temp;
		for(int i = values.length - 1; i >= 0; i--) {
			temp = values[i];
			if(temp >= maxFromRight) {
				maxFromRight = temp;
			}
			
			sb.insert( 0, String.format("%d -> %d\n", temp, (temp < maxFromRight? maxFromRight : -1) ) );
		}
		
		print( sb.toString() );
	}
	
	private static int findMaxSumInSubarray(int[] nums) {
		if(nums == null || nums.length == 0) {
			return 0;
		}
		
		int sum = nums[0];
		List<Integer> sub = new ArrayList<Integer>();
		List<Integer> newSub = new ArrayList<>();
		
		int newSum = sum;
		for(int i = 1; i < nums.length; i++) {
			newSum += nums[i];
			newSub.add(nums[i]);
			
			if(nums[i] > newSum) {
				// start over
				newSub.clear();
				
				newSum = sum;
			} else if(newSum > sum) {
			    // remember the new max
				sum = newSum;
				
				sub.clear();
				sub.addAll(newSub);
				newSub.clear();
			}
		}
		
		System.out.print("Max sub array: " + Arrays.toString( sub.toArray())  + " from " + Arrays.toString(nums) );
		
		return sum;
	}
	
	static int findMaxSumInSubarray_1(int a[]) {
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;
 
        for (int i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }
	
	/**
	 * Suppose the incoming numberStream is from a stream to
	 * which no random access or re-iteration is possible. Find
	 * n top numbers.
	 * 
	 * A fixed length of PriorityQueue can be a good fit.
	 * The trick here is to sort the numbers in ASCENDING order. Pop a head off,
	 * which is the smallest, whenever the queue size is more than N. 
	 * @param numberStream
	 */
	static void trackGreatestNumbers(Integer[] numberStream, int n) {
	    PriorityQueue<Integer> pq = new PriorityQueue<>( new Comparator<Integer>() {
	        /**
	         * Note we need to sort numbers in ASCENDING order. And hterefore there
	         * is no need to proc=vide this Comparator class.
	         */
	        @Override
	        public int compare(Integer a, Integer b) {
	            if( a.equals(b)) return 0;
	            else if(a > b) return 1;
	            else return -1;
	        }
	    });
	    
	    for(int i : numberStream) {
	        /*
	         * If requirement is to distinct numbers. For better performance,
	         * we may want to use a Set for O(1) lookup instead of calling contains()
	         */
	        if(!pq.contains(i)) {
	            pq.add(i);
	        }
	        
	        // delete the smallest one if total is more than n
	        if(pq.size() > n) {
	            pq.poll();
	        }
	    }
	    
	    print(numberStream);
	    while(pq.size() > 0) {
	        System.out.print(pq.poll());
	    }
	    print("");
	}
	
	static int test(int a, int b) {
		// XOR a nabd 
//		int c = a ^ b;
//		
//		int count = 0;
//		
//		while(c >= 1) {
//			count += (c % 2);
//			c /= 2;
//		}
//		
//	    return count;
		
		int counter = 0;

		while (a != b)
		{
			counter = counter + ((a & 0x01) ^ (b & 0x01));
			a = a >> 1;
			b = b >> 1;
		}
		return counter;
	}
}
