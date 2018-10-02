package lzhang.question.array;

import lzhang.util.BaseUtil;

/**
 * Given a sorted integer array with possible repeats and a value,
 * find the start and end index of that value.
 * @author lzhang
 *
 */
public class IndexInSortedArray extends BaseUtil {
    public static void test() {
        IndexInSortedArray p = new IndexInSortedArray();
        
        int[] arr = {1, 2, 2, 2, 2, 3, 4, 7, 8, 8};
        int x = 8;
        int n = arr.length;
        
        int first = p.first(arr, n, x, 0, n-1);
        int last = p.lastIndex(arr, n, x, 0, n-1);
        
        print("first index: " + first + ", last index: " + last);
    }
    
    private int first(int arr[], int n, int x, int low, int high)
    {
        if(high >= low)
        {
            int mid = (low + high)/2;
            
            /*
             * Because of possible repeats, an extreme case would be every element
             * is the same as x. If we just check
             * if(arr[mid] == x)
             * we definitely will return the very first mid index instead of 0.
             * 
             * Since in that case x > arr[mid-1] will be false, it will force to
             * recur the lower half again and again till it finds the very first one!
             */
            if( ( mid == 0 || x > arr[mid-1]) && arr[mid] == x)
                return mid;
             else if(x > arr[mid])
                return first(arr, n, x, (mid + 1), high);
            else
                return first(arr, n, x, low, (mid -1));
        }
        
        return -1;
    }

    private int lastIndex(int[] arr, int n, int x, int l, int h) {
        if (h >= l) {
            int mid = (h + l)/2;
            
            /*
             * Unlike usual case where we just check for if(arr[mid] == x), it is very tricky
             * here to add additional condition - (is mid at the end already or the one after mid is greater already)
             */
            if (( mid == n-1 || x < arr[mid+1]) && arr[mid] == x) {
                 return mid;
            }
            else if (x < arr[mid])
                return lastIndex(arr, n, x, l, (mid -1));
            else
                return lastIndex(arr, n, x, (mid + 1), h);
        }
        
        return -1; 
    }
}
