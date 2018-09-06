package lzhang.question;

/**
 * A permutation, also called an “arrangement number” or “order,” is a rearrangement of the elements 
 * of an ordered list S into a one-to-one correspondence with S itself. 
 * 
 * A string of length n has n! permutation.
 * 
 * A permutation is different from combination. Refer to <code>Combination</code>.
 * 
 * Permutation required backtracking by replacing the character at index with everyone else.
 * 
 * @author lzhang
 *
 */
public class Permutation {
    public static void test() {
        String text = "abc";
        int n = text.length();
        
        recurse(text.toCharArray(), 0, n);
        System.out.println("recurse(arr, index+1, length)");
    }

    /**
     * Unlike combination that uses extra storage for the temporary result, here
     * we temporarily modify the input and then restore for every recursion - a
     * typical "chosen" and "unchosen" technique.
     * 
     * It's not too intuitive to imagine. However, if you apply this method to a much
     * shorter string like "ab", the call stack will look like
     * 1. recurse("ab", o, n) when i = 0;
     *   2. recurse("ab", 1, n);
     *   3. recurse("ab", 2, n) -> got one permutation which is the original one. no swap at all.
     * 1.1 i changed to 1 while index kept at 0, swap to "ba";
     * 1.2 recurse("ba", 1, n) while i started at 1, no swap.
     * 1.3 recurse("ba", 2, n); -> got another result.
     * 
     * 
     * @param arr
     * @param index
     * @param length
     */
    public static void recurse(char[] arr, int index, int length) {
        if(arr == null || index > length) {
            // invalid
            return;
        }
        
        // check if a permutation is available now
        if(index == length) {
            // a permutation is available now in arr
            for (int j=0; j<length; j++) {
                System.out.print(arr[j]+" ");
            }
            System.out.println("");
            
            return;
        }
        
        for(int i = index; i < length; i++) {
            // choose this element at i and swap it with index
            swap(arr, i, index);
            
            // call recursion. Note it's (index+1) not (i + 1)!
            recurse(arr, index+1, length);
            
            // unchosen it by swap them back
            swap(arr, i, index);
        }
    }
    
    private static void swap(char[] arr, int i1, int i2) {
        if(i1 == i2) return;
        
        char temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
}
