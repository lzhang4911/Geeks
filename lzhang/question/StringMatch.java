package lzhang.question;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/wildcard-character-matching/
 * 
 * Given two strings where first string may contain wild card characters and second string is a normal string. Write a function that returns true if the two strings match. The following are allowed wild card characters in first string.

* --> Matches with 0 or more instances of any character or set of characters.
? --> Matches with any one character.

For example, “g*ks” matches with “geeks” match. And string “ge?ks*” matches with “geeksforgeeks” (note ‘*’ at the end of first string). But “g*k” doesn’t match with “gee” as character ‘k’ is not present in second string.

 * @author lzhang
 *
 */
public class StringMatch {
    /*
    "g*ks", "geeks" // Yes
    "ge?ks*", "geeksforgeeks" // Yes
    "g*k", "gee"  // No because 'k' is not in second
    */
    public static boolean test() {
        char[] pattern = "g*ks".toCharArray();
        char[] string = "geeks".toCharArray();
        
        return match(pattern, string);
    }

    public static boolean match(char[] pattern, char[] string) {
        // If we reach at the end of both strings, we are done
        if ( (pattern == null || pattern.length == 0) && (string == null || string.length == 0) ) {
            return true;
        }
     
        // If the string is null but the pattern starts with '*' and at least one more character, then no match.
        // This function assumes that the first string will not contain two consecutive '*'
        if (pattern[0] == '*' && pattern.length > 1 && string == null) {
            return false;
        }
     
        /*
         * If the pattern starts '?', or the same character as the first one in sting, they both considered
         * matching, and therefore move to the next character.
         */
        if (pattern[0] == '?' || pattern[0] == string[0]) {
            return match(Arrays.copyOfRange(pattern, 1, pattern.length), Arrays.copyOfRange(string, 1, string.length));
        }
     
        // If pattern starts with *, then there are two possibilities 
        // a) We consider current character of second string (1 or more characters)
        // b) We ignore current character of second string (0 character)
        if (pattern[0] == '*') {
            return match(Arrays.copyOfRange(pattern, 1, pattern.length), string) || match(pattern, Arrays.copyOfRange(string, 1, string.length));
        }
        
        return false;
    }
}
