package lzhang.question;

import java.util.Map;
import java.util.TreeMap;

import lzhang.util.BaseUtil;

/**
 * N items each has value and weight. Given the weight capacity,
 * pick up the items that have the max possible value.
 * @author lzhang
 *
 */
public class Knapsack extends BaseUtil {
    public static int test() {
        int val[] = new int[]{60, 100, 120};
        int wt[] = new int[]{10, 20, 30};
        int  W = 50; // capacity of the Knapsack
        
        int ret = greedy(val, wt, W);
        print("Knapsack.greedy(): " + ret);
        
        ret = yesOrNo(val, wt, 0, W);
        print("Knapsack.yesOrNo(): " + ret);
        
        ret = dp(val, wt, W);
        print("Knapsack.dp(): " + ret);
        
        return ret;
    }

    /**
     * Use this greedy method if you are allowed to pickup more than 1 quantity
     * for the same item.
     * 
     * Correct answer: 300
     * O(n)
     * 
     * If you can only pick at one for each item, then DP or backtrack would have to be used.
     * 
     * @param val
     * @param wt
     * @param W
     * @return
     */
    private static int greedy(int[] val, int[] wt, int W) {
        /*
         * calculate for the unit value for each item and keep it mapped to item (index).
         * Last one is the most expensive.
         */
        Map<Double, Integer> unitValMap = new TreeMap<>();
        for(int i = 0; i < val.length; i++) {
            unitValMap.put( (1.0 * val[i])/wt[i], i );
        }
        
        // get unit price ordering - last one is the most expensive one
        int i = 0;
        int[] unitPriceOrder = new int[val.length];
        for(Double key : unitValMap.keySet()) {
            unitPriceOrder[i++] = unitValMap.get(key);
        }
        
        int maxVal = 0;
        int[] itemsPicked = new int[val.length];
        int itemIndex;
        
        /*
         * Starting from the most expensive one and pick as many as the capacity allows.
         */
        for(i = unitPriceOrder.length - 1; i >= 0; i--) {
            itemIndex = unitPriceOrder[i];
            
            while(W >= wt[itemIndex]) {
                itemsPicked[itemIndex] += 1;
                W -= wt[itemIndex];
                
                maxVal += val[itemIndex];
            }
        }
        
        return maxVal;
    }
    
    /**
     * You can only pick at most one for each item.
     * Pick or not pick in recursion, and find the max
     * Correct answer: 220
     * O(2^n)
     * 
     * @param val
     * @param wt
     * @param W
     * @return
     */
    private static int yesOrNo(int[] val, int[] wt, int index, int W) {
        if(index < 0 || index > val.length - 1 || W <= 0) {
            return 0;
        }
        
        if(wt[index] > W) {
            // we have to skip this item
            return yesOrNo(val, wt, index + 1, W);
        } else {
            // now we have a choice of either including or not including this item and see which way yields more
            int yes = val[index] + yesOrNo(val, wt, index + 1, W - wt[index]);
            int no = yesOrNo(val, wt, index + 1, W);
            
            return Math.max(yes, no);
        }
    }
    
    /**
     * Use DP 
     * @param val
     * @param wt
     * @param W
     * 
     * @return
     */
    private static int dp(int[] val, int[] wt, int W) {
        if(W == 0 || val == null || wt == null) return 0;
        
        int rows = val.length;
        
        /*
         * create a matrix of (rows+1) x (W + 1) to remember the max value for each
         * possible weight  
         */
        int[][] mem = new int[rows+1][W+1];
        
        // populate the matrix
        for(int r = 0; r < rows+1; r++) {
            for(int w = 0; w < W+1; w++) {
                // first column - 0 weight - and first row - no item - are all 0's
                if(r == 0 || w == 0) mem[r][w] = 0;
                else if(w == wt[r - 1]) { // [r - 1] is because matrix has  1 additional row 0
                    // the bag capacity is exact matching the weight of r-th item
                    mem[r][w] = val[r - 1]; // [r - 1] is because matrix has  1 additional row 0
                } else if(w > wt[r - 1]) { // [r - 1] is because matrix has  1 additional row 0
                    mem[r][w] = Math.max(
                            mem[r-1][w], // Not including this item, max value of the same column from previous row
                            val[r-1] + mem[r-1][ w - wt[r-1] ] // including this item
                                    );
                } else {
                    // if capacity is less than this item's weight, takes previous value
                    mem[r][w] = mem[r-1][w];
                    
                }
            }
        }
        
        return mem[rows][W];
    }
}
