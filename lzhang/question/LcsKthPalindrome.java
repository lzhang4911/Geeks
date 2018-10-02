package lzhang.question;

import lzhang.util.BaseUtil;

/**
 * Find if string is K-Palindrome or not
 * Given a string, find out if the string is K-Palindrome or not. A K-palindrome string 
 * transforms into a palindrome on removing at most k characters from it.
 * 
Examples:

Input : String - abcdecba, k = 1
Output : Yes
String can become palindrome by removing
1 character i.e. either d or e

Input  : String - abcdeca, K = 2
Output : Yes
Can become palindrome by removing
2 characters b and e (or b and d).

Input : String - acdcb, K = 1
Output : No
String can not become palindrome by
removing only one character.
 * 
 * 
 * This problem can be solved by converting to LCS problem - find the LCS between the
 * string and its reverse. It's true if the (str.length - LCS.length) <= k; otherwise false.
 * 
 * @author lzhang
 *
 */
public class LcsKthPalindrome extends BaseUtil {
    public static void test() {
        
        LcsKthPalindrome p = new LcsKthPalindrome();
        String s = "abcdeca";
        int k = 2;
        
        boolean ret = p.isKthPalindrome(s, k);
        print( String.format("Is %s %d palindrome? %s", s, k, ret));
        
        s = "acdcb";
        k = 1;
        
        ret = p.isKthPalindrome(s, k);
        print( String.format("Is %s %d palindrome? %s", s, k, ret));
    }
    
    private boolean isKthPalindrome(String s, int k) {
        if(s == null) return false;
        
        char[] a = s.toCharArray();
        char[] b = s.toCharArray();
        this.reverse(b);
        
        //int lcs = this.findLcsInDP(a, b);
        int lcs = this.findLcsInRecursion(a, a.length, b, b.length);
        
        return (lcs + k >= s.length());
    }

    private int findLcsInDP(char[] a, char[] b) {
        if(a == null || b == null) return 0;
        
        int n = a.length;
        int m = b.length;
        
        int[][] LCS = new int[2][m + 1];
        int bi = 0; // binary index for the row in LCS
        
        for(int i = 1; i <= n; i++) {
            bi = i & 1;
            for(int j = 1; j <= m; j++) {
                if(a[i - 1] == b[j - 1]) {
                    LCS[bi][j] = 1 + LCS[1-bi][j];
                } else {
                    LCS[bi][j] = Math.max( LCS[1-bi][j], LCS[bi][j-1] );
                }
            }
        }
        
        return LCS[bi][m];
    }
    
    private int findLcsInRecursion(char[] a1, int len1, char[] a2, int len2) {
        if(a1 == null || a2 == null || len1 <= 0 || len2 <= 0) return 0;
        
        if(a1[len1 - 1] == a2[len2 - 1]) {
            return 1 + findLcsInRecursion(a1, len1 - 1, a2, len2 - 1);
        } else {
            return Math.max( findLcsInRecursion(a1, len1 - 1, a2, len2), 
                    findLcsInRecursion(a1, len1, a2, len2 - 1));
        }
    }
    
    private void reverse(char[] arr) {
        if(arr == null || arr.length <= 1) {
            return;
        }
        
        // indexes from both ends
        int s = 0, e = arr.length - 1;
        char temp;
        while(s < e) {
            temp = arr[s];
            arr[s++] = arr[e];
            arr[e--] = temp;
        }
    }
}
