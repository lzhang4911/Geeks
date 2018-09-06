package lzhang.question;

/**
 * Given an integer, count all of the bits that are set to 1.
 *

Input : n = 6
Output : 2
Binary representation of 6 is 110 and has 2 set bits

Input : n = 13
Output : 3
Binary representation of 11 is 1101 and has 3 set bits

 * 
 * @author lzhang
 *
 */
public class CountSetBits extends BaseUtil {
    public static void test() {
        CountSetBits p = new CountSetBits();
        
        int num = 9;
        printBits(num);
        print("");
        printBits(num - 1);
        print("");
        
        print("9 & 1 = " + (9&1));
        print("8 & 1 = " + (8&1));
        
        p.orderOfn(num);
        p.loopAllBits(num);
        print( "recurseAllBits(): " + p.recurseAllBits(num) );
        
    }
    
    /**
     * This is the an O(n) approach. Not recommended!
     * @param nunm
     */
    private void orderOfn(int num) {
        int count = 0;
        
        for(int i = 0; i < 32; i++) {
            count += (num >> i) & 1;
        }
        
        print( String.format("orderOfn( %d ) has set bits: %d", num, count));
    }
    
    /**
     * Is this O(logn)?
     * 
     * I think it's still O(n) but a little bit more optimized than the first one
     * @param num
     */
    private void loopAllBits(int num) {
        int count = 0;
        
        while(num > 0) {
            count += num & 1;
            
            // shift to right by 1 bit
            num >>= 1;
        }
        
        print( String.format("loopAllBits( %d ) has set bits: %d", num, count));
    }

    /**
     * This is essentially the same as loopAllBits()
     * @param num
     * @return
     */
    private int recurseAllBits(int num) {
        if(num == 0) {
            return 0;
        }
        
        return (num & 1) + recurseAllBits( num >> 1 );
    }
    
    
    public static void printBits(int n) {
        if(n > 1) {
            printBits(n / 2);
        }
        
        System.out.print( n % 2 );
    }
}
