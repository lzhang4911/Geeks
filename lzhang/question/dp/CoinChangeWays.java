package lzhang.question.dp;

import java.util.Arrays;

import lzhang.util.BaseUtil;

/**
 * Given a set of denominators int[] S and the amount of money M to be
 * changed, count how many way the change can be made out of infinite 
 * coin sets.
 * 
 * Another variation is to find the minimum number of coins to make one of
 * such changes.
 *   
 * @author lzhang
 *
 */
public class CoinChangeWays extends BaseUtil {

    public static int test() {
        // ordering in coins has no impact!
        //int[] C = new int[] {9, 6, 5, 1};
        int[] C = new int[] {1, 5, 6, 9};
        int money = 11;
        
        int result = howManyWaysRecurse(money, C, C.length);
        print("howManyWaysRecurse: " + result);
        
        result = howManyWaysDp(money, C);
        print("howManyWaysDp: " + result);
        
        result = minCoinsRecurse(money, C);
        print("minCoinsRecurse: " + result);
        
        result = minCoinsDp(money, C);
        print("minCoinsDp: " + result);
        
        return result;
    }
    
    /**
     * Use recursion
     * 
     * @param money
     * @param C
     * @return
     */
    private static int howManyWaysRecurse(int money, int[] C, int coins) {
        if(money == 0) return 1; // only way is not to do anything
        else if(money < 0) return 0; // there is no way to make a negative change!
        
        if(C == null || coins == 0) return 0;
     
        /*
         * count is sum of solutions 
         * (i) including the coin C[coins-1] and
         * (ii) excluding the coin C[coins-1] 
         */
        return howManyWaysRecurse( money - C[coins - 1], C, coins ) + // use the coin to deduct from the total amount
                howManyWaysRecurse( money, C, coins - 1 // exclude this coin
                        );
    }
    
    /**
     * This is a DP solution to find out how many ways in total to make
     * the change by trying every possible combination.
     * 
     * @param money
     * @param C
     * @return
     */
    private static int howManyWaysDp(int money, int[] C) {
        if(C == null) return 0;
        else if(money < 0) return 0;
        
        if(money == 0) return 1;
        
        // create an array of size (money + 1)
        int[] ways = new int[money + 1];
        
        // init all to 0
        Arrays.fill(ways, 0);
        
        // but always set first one to be 1
        ways[0] = 1; // only 1 way to change for 0 - not to do anything!
        
        // walk through each coin - {1, 5, 6, 9}
        for(int c = 0; c < C.length; c++) {
            // for each possible amount to be changed
            for(int m = 1; m <= money; m++) {
                // update the ways only if the amount is >= coin[c]
                if(m >= C[c]) {
                    ways[m] += ways[ m - C[c] ];
                }
            }
        }
        
        return ways[money];
    }
    
    /**
     * https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
     * Exponential!
     * 
Input: coins[] = {25, 10, 5}, V = 30
Output: Minimum 2 coins required
We can use one coin of 25 cents and one of 5 cents 

Input: coins[] = {9, 6, 5, 1}, V = 11
Output: Minimum 2 coins required
We can use one coin of 6 cents and 1 coin of 5 cents
     * 
     * @param money
     * @param C
     * @return
     */
    private static int minCoinsRecurse(int money, int[] C) {
        if(money == 0 || C == null) return 0;
        
        // Initialize result
        int res = Integer.MAX_VALUE;
       
        // Try every coin that has smaller value than V
        for (int i=0; i<C.length; i++) {
          if (money >= C[i]) {
              // total amount is reduced if current coin is used
              int sub_res = minCoinsRecurse(money-C[i], C);
       
              // Check for INT_MAX to avoid overflow and see if
              // result can minimized
              if (sub_res != Integer.MAX_VALUE) {
                 res = Math.min(res, sub_res + 1);
              }
          }
        }
        
        return res;
    }
    
    /**
     * O(M*C.length)
     * @param money
     * @param C
     * @return
     */
    private static int minCoinsDp(int money, int[] C) {
        if(money <= 0) return 0;
        if(C == null) return 0;
        
        // Initialize result
        int[] mem = new int[money + 1];
        Arrays.fill(mem,  Integer.MAX_VALUE);
        mem[0] = 0;
       
        // Compute minimum coins required for all values from 1 cent to money
        for (int m = 1; m <= money; m++) {
            // Go through all coins smaller than i
            for (int j = 0; j < C.length; j++) { 
                if (m >= C[j]) {
                    int sub_res = mem[m - C[j]];
                    
                    if (sub_res != Integer.MAX_VALUE) {
                        // update the count if we can use less number of coins
                        mem[m] = Math.min(mem[m], sub_res + 1);
                    }
                }
            }
        }
        
        return mem[money];
    }
}
