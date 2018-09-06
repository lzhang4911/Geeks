package lzhang.question;

import java.util.Stack;

/**
 * An encoded string (s) is given, the task is to decode it. The pattern in which the strings
 * were encoded were as follows
 * 
original string: abbbababbbababbbab 
 encoded string: "3[a3[b]1[ab]]".
 *
 * By looking at the encoded string, [] always go in pair. So, we can use a stack to
 * parse the string.
 * 
 * @author lzhang
 *
 */
public class DecodeString {
    public static String test() {
        DecodeString p = new DecodeString();
        
        String str = "3[a3[b]1[ab]]";
        String result = p.decode(str);
        
        return result;
    }

    /**
     * input: "3[a3[b]1[ab]]"
     * output:"abbbababbbababbbab"
     * @param str
     * @param result
     */
    private String decode(String str) {
        Stack<Character> s = new Stack<>();
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            if(c == ']') {
                // process current segment
                sb.setLength(0);
                
                while(!s.isEmpty()) {
                    int d = 0;
                    if(s.peek() == '[') {
                        // pop out '['
                        s.pop();
                        
                        // retrieve the digit assuming it's from 1 - 9
                        d = s.pop() - '0';
                        
                        // add the decoded segment back to the stack
                        saveDecoded(sb.toString(), d, s);
                        break;
                    } else {
                        // insert into sb
                        sb.append(s.pop());
                    }
                }
            } else {
                // push to the stack
                s.push(c);
            }
        }
        
        // get everything out of the stack
        sb.setLength(0);
        while(!s.isEmpty()) {
            sb.append(s.pop());
        }
        
        return sb.toString();
    }

    /**
     * Push the segment that was enclosed by [] back to the stack "repeats" times.
     * @param string
     * @param repeats
     * @param s
     */
    private void saveDecoded(String string, int repeats, Stack<Character> s) {
        while(repeats > 0) {
            for(char c: string.toCharArray()) {
                s.push(c);
            }
            
            repeats--;
        }
    }
}
