package lzhang.question;

import lzhang.util.BaseUtil;

/**
 * Given two strings ‘X’ and ‘Y’, find the length of the longest common substring.
 * Assuming both stings are all in lower case.
 * 
 * https://www.geeksforgeeks.org/print-longest-common-substring/
 * https://www.geeksforgeeks.org/longest-common-substring-dp-29/
 *  
 * @author lzhang
 *
 */
public class LongestCommonString extends BaseUtil {
    public static int test() {
        LongestCommonString p = new LongestCommonString();
        
        // LCS = 6, "abcdez"
//        String s1 = "zxabcdezy";
//        String s2 = "yzabcdezx";
        
        // LCS = 4
//        String s1 = "AGTABX";
//        String s2 = "GTABY";
        
        // LCS = 10, "Site:Geeks"
        String s1 = "OldSite:GeeksforGeeks.org";
        String s2 = "NewSite:GeeksQuiz.com";
        
        int ret;
        
//        ret = p.recurse(s1, s2);
//        print("LongestCommonString.recurse(): " + ret);
        
//        ret = p.dp(s1, s2);
        ret = p.dpLessSpace(s1, s2);
        print("LongestCommonString.dp(): " + ret);
        
        return ret;
    }
    
    /**
     * FIXME: Doesn't return correct result
     * 
     * @param s1
     * @param s2
     * @return
     */
    private int recurse(String s1, String s2) {
        return recurseHelp(s1.toCharArray(), s1.length(), s2.toCharArray(), s2.length());
//        return recurseHelp(s1, 0, s1.length(), s2, 0, s2.length());
    }
    
    /**
     * Time complexity of this naive recursive approach is O(2^n) in worst case 
     * and worst case happens when all characters of X and Y mismatch.
     * 
     * I like this version. It starts comparison from end and move backwards. By doing
     * so, we don't need extra index. Instead, changing the length is as efficient as the
     * next implementation but simpler.
     * 
     * @param s1
     * @param len1
     * @param s2
     * @param len2
     * @return
     */
    private int recurseHelp(char[] s1, int len1, char[] s2, int len2) {
        if(s1 == null | s2 == null || len1 <= 0 || len2 <= 0) return 0;
        
        // if last chars are the same
        if(s1[len1 - 1] == s2[len2 -1 ]) {
            return 1 + recurseHelp(s1, len1-1, s2, len2-1);
        } else {
            return Math.max(
                    recurseHelp(s1, len1-1, s2, len2),
                    recurseHelp(s1, len1,   s2, len2-1)
                    );
        }
    }
    
    /**
     * FIXME: Doesn't return correct result
     * 
     * This is as good/bad as the previous one!
     *  
     * find the length of LCS (Least Common Substring)
     * @param s1
     * @param start1
     * @param len1
     * @param s2
     * @param start2
     * @param len2
     * @return
     */
    private int recurseHelp(String s1, int start1, int len1, String s2, int start2, int len2) {
        if(s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty()) return 0;
        if(start1 >= len1 || start2 >= len2) return 0;
        
        if(s1.charAt(start1) == s2.charAt(start2)) {
            return 1 + recurseHelp(s1, start1 + 1, len1, s2, start2 + 1, len2);
        } else {
            return Math.max(recurseHelp(s1, start1 + 1, len1, s2, start2, len2), // move to next char in s1 
                    recurseHelp(s1, start1, len1, s2, start2 + 1, len2) // move to next char in s2
                    );
        }
    }
    

    /**
     * A working DP solution: it finds the length of LCS and can print out the string as well!
     * 
     * LCS[i][j] should be either 
     * max( LCS[i][j-1], LCS[i-1][j] ) or
     * 1 + LCS[i-1][j-1] depending if current one matches.
     * 
     * Time Complexity: O(m*n)
     * Auxiliary Space: O(m*n)
     * 
     * Can we optimize the space complexity?
     * 
     * @param s1
     * @param s2
     * @return
     */
    private int dp(String s1, String s2) {
        if(s1 == null || s2 == null) {
            return 0;
        }
        
        int n = s1.length();
        int m = s2.length();
        int[][] LCS = new int[n + 1][m + 1];
        
        int result = 0;  // To store length of the longest common substring
        int row = 0, col = 0;
        
        /*
         * Note that array LCS is by default filled with 0's. The 1st row and 1st
         * column should always be kept at 0.
         * 
         * At the end, LCS[n][m] will be the max and there is no need to track it.
         */
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                // note that dimension of LCS is 1 greater than s1 and s2
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    LCS[i][j] = 1 + LCS[i - 1][j - 1];
                    
                    // remember the LCS so far
                    if (result < LCS[i][j]) {
                        result = LCS[i][j];
                        row = i;
                        col = j;
                    }
                } else {
                    /*
                     * don't carry over the past value because we at the end
                     * want to print out the LCS based on non-0 cells.
                     */
                    LCS[i][j] = 0;
                }
            }
        }
        
        String resultStr = "";
        
        // traverse up diagonally form the (row, col) cell
        // until LCSuff[row][col] != 0
        while (LCS[row][col] != 0) {
            resultStr = s1.charAt(row - 1) + resultStr; // or s2[col-1]
 
            // move diagonally up to previous cell
            row--;
            col--;
        }
 
        // required longest common substring
        System.out.println(resultStr);
        return result;
    }
    
    /**
     * Can we just use O(n) space? Note the previous method really just need cells
     * from both current and previous rows. If we just allocate 2 rows and round-robin
     * them, 2 rows are enough.
     * 
     * @param s1
     * @param s2
     * @return
     */
    private int dpLessSpace(String s1, String s2) {
        if(s1 == null || s2 == null) {
            return 0;
        }
        
        int n = s1.length();
        int m = s2.length();
        int[][] LCS = new int[2][m + 1];
        
        int result = 0;  // To store length of the longest common substring
        int endIndex = 0;

        // we need to know which row to use currently with a simply binary index (i & 1)
        int curRow = 0;
        
        for(int i = 1; i <= n; i++) {
            curRow = i & 1; // or i % 2 to toggle it between 0 and 1
            
            for(int j = 1; j <= m; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    LCS[curRow][j] = 1 + LCS[1-curRow][j - 1];
                    
                    if (result < LCS[curRow][j]) {
                        result = LCS[curRow][j];
                        endIndex = i;
                    }
                } else {
                    LCS[curRow][j] = 0;
                }
            }
        }
        
        if(endIndex > 0) {
            print("LCS: " + s1.substring(endIndex - result, endIndex + 1));
        }
        
        return result;
    }
}
