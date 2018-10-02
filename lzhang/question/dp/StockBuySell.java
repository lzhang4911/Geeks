package lzhang.question.dp;

/**
 * Given stock values on each day {100, 180, 260, 310, 40, 535, 695}, find
 * the max profit by buying and selling this stock.
 * 
 * There are 2 variations:
 * 
 * (1) You can only buy and sell once;
 * (2) You can buy and sell multiple times. 
 * 
 * @author lzhang
 *
 */
public class StockBuySell {
    public static int test() {
        int values[] = new int[] {100, 180, 260, 310, 140, 535, 695};
        //return oneRoud2(values);
        return moreRouds(values);
    }
    
    /**
     * Brute force O(n^2) to find the max diff
     * 
     * @param values
     * @return
     */
    private static int oneRoud(int[] values) {
        int max = 0;
        if(values == null || values.length == 0) {
            return max;
        }
        
        for(int buyDay = 0; buyDay < values.length-2; buyDay++) { // no need to buy on last day
            for(int sellDay = buyDay+1; sellDay < values.length; sellDay++) { // don't sell on day when you buy
                if(values[sellDay] - values[buyDay] > max) {
                    max = values[sellDay] - values[buyDay];
                }
            }
        }
        
        return max;
    }
    
    /**
     * O(n) time complexity by tracking both min_value so far and max_diff so far
     * in linear walk.
     * @param values
     * @return
     */
    private static int oneRoud2(int[] values) {
        int max_diff = 0;
        if(values == null || values.length <= 1) {
            return max_diff;
        }
        
        int min_value = values[0];
        
        for(int i = 1; i < values.length; i++) { // check possible sell day
            // find so far the max profit
            max_diff = Math.max(max_diff,  values[i] - min_value);

            // find so far the min value
            min_value = Math.min(min_value, values[i]);
        }
        
        return max_diff;
    }
    
    /**
     * If multiple transactions are allowed.
     * Very similar to the tracking approach in oneRoud2, we can achieve O(n)
     * 
     * @param values
     * @return
     */
    private static int moreRouds(int[] values) {
        int cur_max = 0, max = 0;
        if(values == null || values.length <= 1) {
            return max;
        }
        
        int min_price = values[0];
        
        for(int i = 1; i < values.length; i++) { // no need to sell on first day
            if(values[i] - min_price > cur_max) {
                cur_max = values[i] - min_price;
            }
            
            // Price has dropped! Let's keep current max profit and reset min_price if a lower value is encountered
            if(values[i-1] > values[i]) {
                max += cur_max;
                
                cur_max = 0;
                min_price = values[i];
            }
        }
        
        // note that the last cur_max may have not been included yet
        return max + cur_max;
    }
}
