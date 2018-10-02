package lzhang.question.string;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lzhang.util.BaseUtil;

/**
 * Given a dictionary based simple password, create all possible (special character) 
 * passwords based on a provided mapping.
 * Input: face
 * Map: {a -> @, 4, A}
 * Output: f@ce, f4ce, fAce
 * 
 * @author lzhang
 *
 */
public class DictionaryBasedPassword extends BaseUtil {
    public static void test() {
        DictionaryBasedPassword p = new DictionaryBasedPassword();
        String original = "aface";
        Map<Character, List<Character>> dict = new HashMap<Character, List<Character>>();
        List<Character> l = new ArrayList<Character>();
        dict.put('a', l);
        l.add('@');
        l.add('4');
        l.add('A');
        
        // hasn't figured out how to stop the duplicates other than using Set
        Set<String> result = new HashSet<String>();
//        java.util.List<String> result = new java.util.ArrayList<String>();
        p.generatePasswords(original.toCharArray(), dict, result);
        
        print("Original password: " + original);
        for(String s : result) {
            print("New password: " + s);
        }
    }

    private void generatePasswords(char[] original, Map<Character, List<Character>> dict, Collection<String> result) {
        if(original == null) return;
        
        if(!needSpecialCharacter(original, dict)) {
            result.add(new String(original));
            return;
        }
        
        for(int i = 0; i < original.length; i++) {
            char oldChar = original[i];
            List<Character> specialChars = dict.get( oldChar );
            
            if(specialChars != null) {
                for(char s : specialChars) {
                    replace(original, i, s);
                    
                    this.generatePasswords(original, dict, result);
                    
                    // restore
                    replace(original, i, oldChar);
                }
            }
        }
        
        
    }
    
    private boolean needSpecialCharacter(char[] original, Map<Character, List<Character>> dict) {
        for(int i = 0; i < original.length; i++) {
            if(dict.get(original[i]) != null) {
                return true;
            }
        }
        
        return false;
    }
    
    private void replace(char[] original, int index, char c) {
        if(original == null || index < 0 || index > original.length - 1) return;
        original[index] = c;
    }
}
