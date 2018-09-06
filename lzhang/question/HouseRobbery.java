package lzhang.question;

/**
 * There are n houses build in a line, each of which contains some value in it. A thief
 * is going to steal the maximal value of these houses, but he can’t steal in two adjacent 
 * houses because owner of the stolen houses will tell his two neighbor left and right side. 
 * What is the maximum stolen value.
 *
 * DP1: recursion with memorization
 * DP2: iteration with memorization
 * 
 * Memorize every possible highest value at every house
 *     
 * @author lzhang
 *
 */
public class HouseRobbery {

    public static int robMax(int[] houseVal, int houses) {
        if(houseVal == null || houses <= 0) {
            return 0;
        }
        
        // DP1
//        int[] mem = new int[houses];
//        return robMaxRecursion(houseVal, 0, houses, mem);
//        return robMaxRecursion(houseVal, 0, houses);
        
        // DP2
        return robMaxIteration(houseVal, houses);
    }
    
//    private static int robMaxRecursion(int[] houseVal, int i, int n, int[] mem) {
    private static int robMaxRecursion(int[] houseVal, int i, int n) {
        // no more house to rob
        if(i >= n || n <= 0 || houseVal == null) {
            return 0;
        }
        
        /*
         * The rest is the generic case - rob house i or not:
         * 1) if I rob this one, the total would be this value + possible robberies from the
         *    rest of houses starting from (i + 2) since I would have to skip the immediate next.
         *    
         * 2) if I don't rob this one, my max would have to come from the rest starting
         *    from the immediate next (i + 1).
         */
//        int robThisOne = houseVal[i] + robMaxRecursion(houseVal, i + 2, n, mem); // skip the immediate next
//        int dontRobThisOne = robMaxRecursion(houseVal, i + 1, n, mem); // skip this one
        
        int robThisOne = houseVal[i] + robMaxRecursion(houseVal, i + 2, n); // skip the immediate next
        int dontRobThisOne = robMaxRecursion(houseVal, i + 1, n); // skip this one
        
//        mem[i] = Math.max(robThisOne, dontRobThisOne);
//        return mem[i];
        
        return Math.max(robThisOne, dontRobThisOne);
    }
    
    /**
     * O(n) time, but O(n) space as well!
     * 
     * @param houseVal
     * @param houses
     * @return
     */
    public static int robMaxIteration(int[] houseVal, int houses) {
        // no house to rob
        if(houseVal == null || houses <= 0) {
            return 0;
        }
        
        // if there are more than 2 houses, use n-element array to remember the max total value at house i
        int[] maxVal = new int[houses];
        
        // No hesitation at the first house but just rob it
        maxVal[0] = houseVal[0];
        
        /*
         * at the second house, he can decide whether to rob the prev house or house
         * this one, which is is more\.
         */
        maxVal[1] = Math.max(houseVal[0], houseVal[1]);
        
        /*
         * At each house afterwards, he will need to think whether to rob it or not
         * to make total value more.  
         */
        for(int i = 2; i < houses; i++) {
            // calculate the total if he robs this house
            int a1 = houseVal[i] + maxVal[i - 2]; // no consecutive houses
            
            // the total value remains the same (as at the the previous house) if he does't rob this house
            int a2 = maxVal[i - 1];
            
            // do whichever ends up with more value
            maxVal[i] = Math.max(a1, a2);
        }
        
        return maxVal[houses-1];
    }
}
