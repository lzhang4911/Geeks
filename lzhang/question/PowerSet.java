package lzhang.question;



/**
 * https://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/
 * 
 * Give a set S = {a, b, c}, its power set would be
 * P(S) = {{}, {a}, {b}, {c}, {a,b}, {a, c}, {b, c}, {a, b, c}}.
 * 
 * Each subset matches the binary pattern of the power set
 * count
 * 
 * Value of Counter            Subset
    000                    -> Empty set
    001                    -> a
    010                    -> b
    011                    -> ab
    100                    -> c
    101                    -> ac
    110                    -> bc
    111                    -> abc
    
 * @author lzhang
 *
 */
public class PowerSet {
    public static void test() {
        String[] set = {"a", "b", "c"};
        
        // print all of its power sets
        iterative(set, set.length);
    }
    
    /**
     * n is the size of the array
     * 
     * @param set
     * @param n
     */
    private static void iterative(String[] set, int n) {
        if(set == null || n <= 0) {
            return;
        }
        
        // number of power sets is 2 ^ n
        long powerSetSize = (long)Math.pow(2, n); // or 1<<n
        
        
        // Run a loop for printing all 2^n subsets
        for(int i = 0; i < powerSetSize; i++) {
            System.out.print("\"");
            
            for(int j = 0; j < n; j++) {
                /* 
                 * Don't understand!
                 * 
                 * Check if jth bit in the  counter i is set.
                 * If set then print jth element from set 
                 */
                // (1<<j) is a number with jth bit 1
                // so when we 'and' them with the
                // subset number we get which numbers
                // are present in the subset and which
                // are not
                if((i & (1 << j)) > 0) {
                    System.out.print(set[j]);
                }
            }
            System.out.print("\"");
             
            System.out.println();
        }
    }
}
