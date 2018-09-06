package lzhang.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parentheses {
    public static List<String> test() {
        Parentheses p = new Parentheses();
        List<String> result = new ArrayList<>();
        p.printAllValidParentheses(result, "", 0, 0, 3);
        
        return result;
    }
    
    /**
     * https://blog.csdn.net/liyazhou0215/article/details/78305839
     * 
     * Given the number of pairs of parentheses print out all valid arrangements.
     * For example, if the number is 3, valid arrangements are
     * 
[ 
“((()))”, 
“(()())”, 
“(())()”, 
“()(())”, 
“()()()” 
]
     * 
     * This is a subset of all permutations.
     * 
     * Another approach is to list of the permutations using backtracking.
     * And then screen out those invalid ones. Time complexity is too high.
     * 
     * @param pairs
     */
    private void printAllValidParentheses( List<String> result, String soFar, int openCount, int closeCount, int totalCount) {
        if(soFar.length() == 2 * totalCount) {
            System.out.println(soFar);
            
            /*
             * Have added all of the parentheses but it may not be a valid combination.
             */
            if(valid(soFar)) {
                result.add(soFar);
            }
            return;
        }
        
        /*
         * By doing the following, we will also generate many invalid arrangements.
         */
        if(openCount < totalCount) {
            this.printAllValidParentheses(result, soFar + "(", openCount+1, closeCount, totalCount);
        }
        
        if(closeCount < totalCount) {
            this.printAllValidParentheses(result, soFar + ")", openCount, closeCount+1, totalCount);
        }
    }
    
    /**
     * A valid pattern is that any opening parentheses is eventually closed by ')'.
     * Use a stack for this validation purpose. 
     * @param pattern
     * @return
     */
    private boolean valid(String pattern) {
        if(pattern == null || pattern.length() %2 == 1) {
            return false;
        }
        
        Stack<Character> s = new Stack<>();
        
        for(char c : pattern.toCharArray()) {
            if(c == '(') {
                s.push(c);
            } else {
                // must be ')'
                if( c != ')' || s.isEmpty() || s.pop() != '(' ){
                    // enough to say invalid
                    return false;
                }
            }
        }
        
        return s.isEmpty();
    }
}
