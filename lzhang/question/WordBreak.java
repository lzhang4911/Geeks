package lzhang.question;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a sentence without spaces and a dictionary, find out how the sentence can
 * be broken into proper words.
 * 
 * Consider the following dictionary 
 * { i, like, sam, sung, samsung, mobile, ice, cream, icecream, man, go, mango}
 * Input: "ilikesamsungmobile"
 * Output: i like sam sung mobile
 *         i like samsung mobile
 * @author lzhang
 *
 */
public class WordBreak extends BaseUtil {
    public static void test() {
        String dictionary[] = {"mobile","samsung","sam","sung",
                "man","mango", "icecream","and",
                "go","i","love","ice","cream"};
        Set<String> dict = new HashSet<>();
        for(String w : dictionary) {
            dict.add(w);
        }
        
        String sentence = "iloveicecreamandmango";
        
        WordBreak p = new WordBreak();
        p.worBreakBacktrack(sentence, dict, "");
    }

    /**
     * Recursive 
     */
    private void worBreakBacktrack(String sentence, Set<String> dict, String result) {
        if(sentence == null || dict == null) return;
        
        int len = sentence.length();
        if(len == 0) return;
        
        // start to check every possible prefix to be a word
        String prefix;
        for(int i = 1; i <= len; i++) { // such index is a convenience for String.substring()
            prefix = sentence.substring(0, i);
            
            // is this a word
            if(dict.contains(prefix)) {
                // there are 2 possibilities: we already reached to the end or no
                if(i == len) {
                    // we are done
                    print("After word break: " + result + prefix);
                    return;
                } else{
                    this.worBreakBacktrack(sentence.substring(i), dict, result + prefix + " ");
                }
            }
        }
    }
    
    /*
     * Better one would be DP:
     * https://www.geeksforgeeks.org/word-break-problem-dp-32/
     */
}
