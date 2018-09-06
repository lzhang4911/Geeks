package lzhang.question;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * A text message is encoded as numbers, A -> 1, ..., Z -> 27. So, a message "ABC"
 * will be converted to "123".
 * 
 * However, "123" is not just "ABC", it can also be "12 3" or "1 23" which are
 * "LC" or "AW".
 * 
 * The question here is to given a message in digits, find out how many possible
 * translations.
 * 
 * @author lzhang
 *
 */
public class DecodeDigits extends BaseUtil {
    public static void test() {
        DecodeDigits p = new DecodeDigits();
        int[] arr = new int[] {1, 2, 3};
//        int[] arr = new int[] {1, 2, 3, 4};
        int lastIndex = arr.length - 1;
        
        Collection<String> list = new HashSet<String>();
        int count = p.translations(arr, lastIndex, "", list);
        print( "After decoding: " + Arrays.toString( list.toArray(new String[0])) );
        
        count = p.countDecoding(arr, arr.length);
        
        print("\nCount: " + count);
    }
    
    /**
     * Copied from https://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/
     * @param digit
     * @param n
     * @return
     */
    private int countDecoding(int[] digit, int n) 
    {
        // base cases
        if (n == 0 || n == 1) {
            return 1;
        }
     
        // Initialize count
        int count = 0; 
     
        // If the last digit is not 0, then 
        // last digit must add to
        // the number of words
        if (digit[n - 1] > 0) {
            count = countDecoding(digit, n - 1);
        }
     
        // If the last two digits form a number
        // smaller than or equal to 26,
        // then consider last two digits and recur
        if (digit[n - 2] == 1 || 
           (digit[n - 2] == 2 && digit[n - 1] < 7)) {
            count += countDecoding(digit, n - 2);
        }
     
        return count;
    }

    /**
     * We must evaluate from the end instead of the beginning. For example,
     * if the last digit is 0, then we know we have to group it with its previous
     * one to form either 10 or 20, because 0 along is not translatable.
     * 
     * Of course, 2 digits that are > 27 is invalid.
     * 
     * To return the possible translations, each translation is available when 
     * lastIndex == -1 (just finished the entire decoded string). Note that there are
     * multiple translations.
     * 
     * The time complexity of above the code is exponential.
     * 
     * @param digit
     * @param lastIndex starting from digits.length() - 1.
     * @return
     */
    private int translations(int[] digit, int lastIndex, String decoded, Collection<String> list) {
        if(lastIndex < 0) {
            // got one decoded string
            list.add(decoded);
            
            return 1;
        }
        
        int result = 0;
        
        /*
         * Exclude 0. Single digit 0 has no corresponding character being mapped. It can
         * only be paired with either 1 or 2 (ie, 10 or 20), which is take care of in the
         * block after this.
         */
        if(digit[lastIndex] != 0) {
            // one possible decoding for this single digit
            result = this.translations(digit, lastIndex - 1, getChar(digit[lastIndex])+decoded, list);
        }
                
        // we can group the last 2 together as long as they <= 26
        if(lastIndex > 0) {
            int digitVal = twoDigits(digit[lastIndex-1], digit[lastIndex]);
            if(digitVal <= 26) {
                result += translations(digit, lastIndex - 2, getChar(digitVal)+decoded, list);
            }
        }
        
        return result;
    }
    
    private int twoDigits(int d1, int d2) {
        return 10 * d1 + d2;
    }
    
    /**
     * Note that there is a shift due to the mapping started from 1 -> A
     * 
     * @param digitVal
     * @return
     */
    private char getChar(int digitVal) {
        return (char)(digitVal - 1 + 'A');
    }
}
