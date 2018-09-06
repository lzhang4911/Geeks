package lzhang.question;


public class Palindrome {

    /**
     * Check if the given string contains a substring that is a palindrome.
     * YES for "abcbdf" -> "bcb"
     * 
     * Finding the 1st one or all is similar.
     * We can walk through char array, starting from index 1 to length-2 for there is no need
     * to check on single char, and then reach out to both side (as far as) to check if chars
     * on both sides are equal.
     * 
     * Note that a palindrome can contain either old or even chars.
     * 
     * To find the longest palindrome, try to reach as far as possible.
     * 
     * O(n)
     * 
     * @param string
     * @return
     */
    public static boolean containsPalindromeSubstring(String string) {
        if(string == null || string.length() == 0) return false;
        
        if(string.length() == 1) return true;
        
        for(int i = 1; i < string.length() - 2; i++) {
            if(string.charAt(i) == string.charAt(i-1) // even pattern
                    || string.charAt(i-1) == string.charAt(i+1) // odd pattern
                ) {
                // return true as long as we found "aa" or "aba"
                    return true;
                }
        }
                
        return false;
    }
    
    /**
     * Linear walk and find the longest palindrome.
     * 
     * With one loop, at each location, expands to both ends to see the longest possible
     * palindrome. Note that we need to consider both even and odd palindromes.
     * 
     * This is O(n^2).
     * 
     * @param string
     * @return
     */
    public String findLongestPalindrome(String string) {
        if(string == null || string.length() == 1) return string;
        int len = string.length();
        
        String longestPalindrome = string.substring(0, 1);
        int maxPalindromeLength = 1;
        int startIndex, endIndex;
        
        for(int i = 1; i < string.length() - 2; i++) {
            // consider both type of palindromes and find out their max lengths respectively
            int evenLength = findLengthByExpandingBothEnds(string, i, i+1, len);
            int oddLength = findLengthByExpandingBothEnds(string, i, i, len);
            
            // choose the longest
            int maxLen = Math.max(evenLength, oddLength);
            
            if(maxLen > maxPalindromeLength) {
                maxPalindromeLength = maxLen;
                // determine indexes at both end
                startIndex = i - (maxPalindromeLength-1)/2; // need -1 for even palindrome!
                endIndex = i + maxPalindromeLength/2;
                longestPalindrome = string.substring(startIndex, endIndex + 1); // +1 b/c end index exclusive!
            }
        }
                
        return longestPalindrome;
    }
    
    // expand both indexes to see the longest possible palindrome
    private int findLengthByExpandingBothEnds(String string, int leftIndex, int rightIndex, int len) {
        if(string == null || string.isEmpty()) {
            return 0;
        }
        
        int palindromeLen = 0;
        while(leftIndex >= 0 && rightIndex < len) {
            if(string.charAt(leftIndex) != string.charAt(rightIndex)) {
                // palindrome pattern breaks
                break;
            } else {
                // calculate current palindrome length
                palindromeLen = 1 + rightIndex - leftIndex;
                
                // expand further
                leftIndex--;
                rightIndex++;
            }
        }
        
        return palindromeLen;
    }
}
