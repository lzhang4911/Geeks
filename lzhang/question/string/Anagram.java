package lzhang.question.string;

import lzhang.util.BaseUtil;

/**
 * How to check if 2 given strings are anagrams to each other - "abcd" and "acbd" or "badc"...
 * as long as they have the same characters in any ordering.
 * 
 * The usual practice for this problem is to use 2 count arrays, each 256 long assuming
 * each character is a 8-bit char. The 2 counter arrays will be identical if the 2 strings
 * are anagrams.
 * 
 * Can we use bit OR to use a constant space? I guess so!
 * 
 * @author lzhang
 *
 */
public class Anagram extends BaseUtil {
    public static boolean test() {
        String str1 = "geeksforgeeks";
        String str2 = "forgeeksgeeks";
        
        Anagram p = new Anagram();
        return p.checkAnagram(str1, str2);
    }

    private boolean checkAnagram(String str1, String str2) {
        if(str1 == null || str2 == null) return false;
        if(str1.length() != str2.length()) return false;
        print("Are they anagram? " + str1 + " and " + str2);
        
        int xor = 0;
        for(int i = 0; i < str1.length(); i++) {
            xor ^= str1.charAt(i) ^ str2.charAt(i);
        }
        
        return xor == 0;
    }
}
