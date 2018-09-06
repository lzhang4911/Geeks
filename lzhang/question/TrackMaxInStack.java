package lzhang.question;

import java.util.Stack;

/**
 * Design a Stack that keeps tracking the current max value.
 * 
 * Simple approach would be using an auxiliary stack to track current max along the
 * normal stack which gives time complexity in O(1) but space in O(n) for getMax(): 
 * https://www.geeksforgeeks.org/tracking-current-maximum-element-in-a-stack/
 * 
 * A better approach is to use O(1) for space complexity as well,
 * https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/
 * 
 * @author lzhang
 *
 */
public class TrackMaxInStack extends BaseUtil {

    static class MaxStack {
        /**
         * 
         */
        private int curMax;
        private Stack<Integer> statck;
        
        public MaxStack() {
            statck = new Stack<Integer>();
        }
        
        /**
         * New mandatory API with time complexity O(1).
         * @return
         */
        public Integer getMax() {
            return curMax;
        }
        
        public Integer push(Integer x) {
            print("\tPush " + x);
            
            if(statck.empty()) {
                statck.push(x);
                curMax = x;
            } else {
                /*
                 * Depending on whether x is less than curMax
                 */
                if(x < curMax) {
                    // just push this value to stack while curMax remain unchanged
                    statck.push(x);
                } else {
                    /*
                     * x is greater than curMax, which means we need to update curMax:
                     * - first push to stack (2x - curMax);
                     * - then update curMax = x
                     * 
                     * Since the value pushed into Stack is not the original value, we
                     * must override both pop() and peek() to return its original value.
                     */
                    statck.push(2 * x - curMax);
                    curMax = x;
                }
            }
            
            return x;
        }
        
        public Integer peek() {
            Integer topValue = statck.peek();
            
            topValue = calculateOriginalValue(topValue, false);
            print( String.format("\tpeek: %d, curMax: %d ", topValue, curMax));
            
            return topValue;
        }
        
        public Integer pop() {
            Integer topValue = statck.pop();
            
            topValue = calculateOriginalValue(topValue, true);
            print( String.format("\tpop: %d, curMax: %d ", topValue, curMax));
            
            return topValue;
        }
        
        public boolean empty() {
            return statck.empty();
        }
        
        private Integer calculateOriginalValue(int topValue, boolean isPop) {
            if(this.curMax < topValue) {
                /*
                 * Remember at the time when we push a greater value, we eventually let
                 * curMax to be that greater x. We could simply return curMax here, but
                 * we must recover the previous max before returning.
                 * 
                 *  Since top = (2*x - prevMax), then 
                 *  prevMax = (2*x - top)
                 *          = (2 * curMax - top) 
                 */
                Integer toReturn = this.curMax;
                
                if(isPop) {
                    // this is called from pop(), update curMax as well.
                    curMax = 2 * curMax - topValue;
                }
                
                topValue = toReturn;
            }
            
            return topValue;
        }
    }
    
    
    public static void test() {
        MaxStack s = new MaxStack();
        
        print("Max Stack:");
        s.push(12);
        print("\tgetMax(): " + s.getMax());
        
        s.push(10);
        print("\tgetMax(): " + s.getMax());
        s.peek();
        
        s.push(20);
        print("\tgetMax(): " + s.getMax());
        s.pop();
        s.peek();
        
        s.push(30);
        print("\tgetMax(): " + s.getMax());
        s.peek();
        
        s.push(40);
        s.push(5);
        
        while(!s.empty()) {
            s.pop();
        }
        print("Max Stack done");
    }
}
