package lzhang.question;

import java.util.ArrayList;
import java.util.List;

import lzhang.model.BinaryNode;
import lzhang.util.BaseUtil;

/**
 * https://www.geeksforgeeks.org/expression-tree/
 * 
 * @author lzhang
 *
 */
public class NumericExpression extends BaseUtil {

    /*
    Test Cases:
        "10 + 2 * 6"            ---> 22
        "100 * 2 + 12"          ---> 212
        "100 * ( 2 + 12 )"      ---> 1400
        "100 * ( 2 + 12 ) / 14" ---> 100    
    */
    public static int test() {
//        String expr = "10 + 2 - 6";
//        String expr = "10 + 2 * 6";
//        String expr = "(23 - 2 * (2 + 3) - 1)";
//        String expr = "3 + ((5+9)*2)";
        String expr = "(1 + 2)*2 + 3*(2 - 1)";
        System.out.println(expr);
        
        int res = evalRecurse(expr);
        print(expr + " = " + res);
        
        
        BinaryNode<Integer> root = new BinaryNode<Integer>((int)'+');
        root.left = new BinaryNode<Integer>(3);
        
        root.right = new BinaryNode<Integer>((int)'*');
        root.right.left = new BinaryNode<Integer>((int)'+');
        root.right.right = new BinaryNode<Integer>(2);
        
        root.right.left.left = new BinaryNode<Integer>(5);
        root.right.left.right = new BinaryNode<Integer>(9);
        
        res = eval(root);
        
        BinaryNode.levelOrderPrint(root);
        print(" = " + res);
        
        return res;
    }
    
    /**
     * Depending on the original expression, expr may contain a list of
     * sub expressions.
     * 
     * This recursion resolves all of the () pairs, one at a time, till there is
     * no more () to process.
     * 
     * "(23 - 2 * (2 + 3) - 1)" will be changed to
     * "(23 - 2 * 5 - 1)", and then
     * "(12)", and then
     * "12"
     * 
     * 
     * @param expr
     * @return
     */
    private static int evalRecurse(String expr) {
        int endIndex = expr.indexOf(')');
        
        if(endIndex == -1) {
            // this expression doesn't have any ( or ). Go ahead to calculate.
            return evalSimple(expr);
        } else {
            // Very important - find the opening parenthesis from the end
            int startIndex = expr.lastIndexOf('(', endIndex);
            
            // find the matched closing parenthesis!
            String subExpr = expr.substring(startIndex + 1, endIndex);
            
            // evaluate this sub expression
            subExpr = String.valueOf( evalSimple(subExpr) );
            
            // re-assemble the expr and call evalRecurse() again
            String prefix = "";
            if(startIndex > 0) {
                // there is a prefix
                prefix = expr.substring(0, startIndex);
            }
            
            String suffix = "";
            if(endIndex < expr.length() - 1) {
                suffix = expr.substring(endIndex + 1);
            }
            
            expr = prefix + subExpr + suffix;
            System.out.println(expr);
            
            return evalRecurse(expr);
        }
    }
    
    /**
     * Use postorder traversal to calculate the value at each sub tree from bottom.
     * 
     * @param n
     * @return
     */
    private static int eval(BinaryNode<Integer> n) {
        if(n == null) {
            return 0;
        }
        
        if(n.left == null && n.right == null) {
            // this is a leaf
            return n.value;
        } else {
            int l = eval(n.left);
            int r = eval(n.right);
            String expr = l + String.valueOf( (char)n.value.intValue() ) + r;
            
            return evalSimple(expr);
        }
    }
    
    /**
     * The expr contains only numbers and operators, no other things such
     * as parentheses.
     * 
     * 1 + 2 * 3 - 4/2 + 7 -4
     * 
     * @param expr
     * @return
     */
    private static int evalSimple(String expr) throws IllegalArgumentException {
        if(expr == null) {
            throw new IllegalArgumentException("Expr must not be null");
        }
        
        char c;
        char[] a = expr.toCharArray();
        List<Integer> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        
        /*
         * Maintain the indexes for all precedent operators (* and /)
         */
        List<Integer> precedentIndex = new ArrayList<>();
        
        for(int i = 0; i < a.length; i++) {
            c = a[i];
            
            if(c == ' ') {
                // skip
                continue;
            } else if(c == '+' || c == '-'  || c == '*' || c == '/') {
                operators.add(c);
                
                if(c == '*' || c == '/') {
                    // remember their positions in operators list
                    precedentIndex.add(operators.size() - 1);
                }
            } else {
                // must be digits
                StringBuilder temp = new StringBuilder();
                temp.append(c);
                
                // get the rest of the entire number
                int j = 0;
                char t;
                while(i+j+1 < a.length) {
                    t = a[i+j+1];
                    if(Character.isDigit(t)) {
                        temp.append(t);
                        j++;
                    } else {
                        break;
                    }
                }
                
                int num = Integer.parseInt(temp.toString());
                numbers.add(num);
                
                // loop pointer must point beyond the current number
                i += j;
            }
        }
        
        /*
         * At the end, numbers.size() should be 1 + operators.size()
         */
        assert numbers.size() == operators.size() + 1;
        
        /*
         * Let's work on x and / first. Note 2 numbers is associated with 1 operator.
         */
        Integer op1, op2;
        char op;
        for(int i = precedentIndex.size() - 1; i >= 0; i--) {
            // the operator index in list operators
            int index = precedentIndex.get(i);
            
            op = operators.remove(index);
            
            op2 = numbers.remove(index + 1);
            op1 = numbers.remove(index);
            
            int tmpResult = applyOp(op, op1, op2);
            
            // add the replacement
            numbers.add(index, tmpResult);
        }
        
        // by now, operators has only '+' and '-'
        int sum = numbers.get(0);
        for(int i = 0; i < operators.size(); i++) {
            op = operators.get(i);
            
            op2 = numbers.get(i + 1);
            
            sum = applyOp(op, sum, op2);
        }
        
        return sum;
    }
    
    private static int applyOp(char op, int a, int b) {
        switch (op)
        {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        case '/':
            if (b == 0)
                throw new
                UnsupportedOperationException("Cannot divide by zero");
            return a / b;
        }
        return 0;
    }
}
