package lzhang.question;


/**
 * Method 1 and 2 referred to
 * https://www.geeksforgeeks.org/count-ways-reach-nth-stair-using-step-1-2-3/
 * 
 * A child is running up a staircase with n steps and can hop either 1 step, 2 steps, 
 * or 3 steps at a time. Implement a method to count how many possible ways the child
 * can run up the stairs.
 * 
 * If n = 4, result is 7.
 * 
 * DP
 * 
 * @author lzhang
 *
 */
public class ClimbStairs {

    /**
     * To climb up to level n, the child can each try take any of the steps
     * say [1, 2, 3]. How many total ways he could climb to level n?
     * 
     * @param n: n stairs to climb
     * @return
     */
    public static int waysToClimb(int n) {
        int ways = recursive(n);
        ways =  dp(n);
        
        // if n = 29 and k = 5, should give 603
        ways =  genericDP(n, 3);
        return ways;
    }
    
    private static int recursive(int n) {
        if(n <= 0) {
            // there is only 1 way which is no need to climb
            return 1;
        } else if(n == 1) {
            // there is only 1 way which is to climb 1 step
            return 1;
        } else if(n == 2) {
            return 2; // 2 1-step and 1 2-step, totally 2 different ways to climb
        } 
//        else if(n == 3) {
//            return 4; // seems not needed
//        }
        else
        // generic case
        return recursive(n - 1) +
                recursive(n - 2) +
                recursive(n - 3);
    }
    
    private static int dp(int n) {
        // always init an array, sized at n+1, to hold results for every stair
        int[] wayCount = new int[n + 1];
        int index = 0;
        wayCount[index++] = 1; // no stair to climb, of course there is only one way to do it which is doing nothing!
        wayCount[index++] = 1; // one step to climb on
        wayCount[index++] = 2; // one step all the way or just one 2-step
        
        // generic case
        for(int i = index; i <= n; i++) {
            wayCount[i] = wayCount[i - 1] + wayCount[i - 2] + wayCount[i - 3];
        }
        
        return wayCount[n];
    }
    
    /**
     * https://www.geeksforgeeks.org/number-of-ways-to-reach-nth-floor-by-taking-at-most-k-leaps/
     * 
     * Number of COMBINATIONS to reach Nth floor by taking at-most K leaps i each leap.
     * Please note the difference between WAYS and COMBINATIONS: there are 3 WAYS to climb to 3rd 
     * stair (1/1/1, or 1/2, or 2/1), but there are only 2 combinations: one step each time or 1 and
     * 2 steps combination.
     * 
     * In other words, 1-and-2 and 2-and-1 are the same combination.
     * 
     * @param n
     * @param k
     * @return
     */
    private static int genericDP(int n, int k) {
        if(n <= 0) return 1;
        
        // combinations to reach each stair
        int[] combinations = new int[n + 1];
        
        // init the first combination
        combinations[0] = 1;
        
        // iterate over all possible leaps
        for(int i = 1; i <= k; i++) {
            // count all ways of all leaps up to ith to reach stair j
            for(int j = 0; j <= n; j++) {
                if (j >= i) {
                    combinations[j] += combinations[j - i];
                }
            }
        }
        
        return combinations[n];
    }
}
