package lzhang.question.array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
 * 
 * Given a string, find the longest substring that doesn't repeat in characters.
 * Input:  "ABDEFGABEF"
 * Output: 6 (6 non-repeating character substring such as "ABDEFG")
 * 
 * Can I use LinkedHashSet to do it? LinkedHashSet still offers O(1) operation but maintains the
 * insert sequence.
 * 
 * @author lzhang
 *
 */
public class LongestSubstringWithNoRepeat {
    public static void test() {
//        String s = "ABDEFGABEF"; // output is "ABDEFG"
        String s = "ABDEFABDEFG"; // output is "ABDEFG"
        System.out.println("Longest Non-repeat Substring from " + s + "\n");
        
        LongestSubstringWithNoRepeat p = new LongestSubstringWithNoRepeat();
        p.printLongestNonRepeatSubstring(s.toCharArray());
    }
    
    private int printLongestNonRepeatSubstring(char[] arr) {
        if(arr == null) return 0;
        
        int maxLen = Integer.MIN_VALUE;
        Set<Character> set = new LinkedHashSet<>();
        List<Character> result = new ArrayList<>();
        
        for(char c: arr) {
            if(set.contains(c)) {
                // before dropping the prefix, update the max
                if(set.size() > maxLen) {
                    maxLen = set.size();
                    result.clear();
                    result.addAll(set);
                }
                
                // delete the prefix including c from the set
                for(Iterator<Character> i = set.iterator(); i.hasNext();) {
                    if(i.next() == c) {
                        i.remove();
                        break;
                    } else {
                        i.remove();
                    }
                }
            }
            
            set.add(c);
        }
        
        // print out (which is longer, result or current set?)
        Iterator<Character> i = result.iterator();
        if(set.size() > maxLen) {
            maxLen = set.size();
            i = set.iterator();
        }
        
        for(; i.hasNext();) {
            System.out.print(i.next());
        }
        
        return maxLen;
    }
}
